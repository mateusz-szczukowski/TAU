package pjatk.tau.service;

import org.junit.Before;
import org.junit.Test;
import pjatk.tau.model.Diet;
import pjatk.tau.service.ArrayListDietService;
import pjatk.tau.service.DietService;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ArrayListDietServiceTests {

    private DietService service;

    @Before
    public void setup() {
        service = new ArrayListDietService();
    }

    @Test
    public void testAddingNewDietAndCheckingIfTheIdIsUpdated() {
        Diet diet = new Diet("Dieta pełna energi", 5000, 500.00);
        Diet addedDiet = service.create(diet);

        assertEquals(1, addedDiet.getId());
    }

    @Test
    public void testGettingAllDiets() {
        service.create(new Diet("Super dieta dla Kobiet", 1000, 123.00));
        service.create(new Diet("Super dieta na młodość", 1500, 999.00));
        service.create(new Diet("Dieta dla odważnych", 9999, 9999));

        assertEquals(3, service.readAll().size());
    }

    @Test
    public void testGettingOneDietFromMemory() {
        Diet diet = service.create(new Diet("Super dieta dla Kobiet", 1000, 123.00));
        Diet addedDiet = service.read(diet.getId());

        assertNotNull(addedDiet);
        assertEquals(diet.getName(), addedDiet.getName());
        assertEquals(diet.getCalories(), addedDiet.getCalories());
    }

    @Test(expected = NoSuchElementException.class)
    public void testThrowExceptionWhenNoDietsWereAdded() {
        Diet diet = service.read(1);
    }

    @Test
    public void testDietDeletion() {
        Diet diet = service.create(new Diet("Super dieta dla Kobiet", 1000, 123.00));
        service.delete(diet);

        assertEquals(0, service.readAll().size());
    }

    @Test
    public void testUpdateDiet() {
        Diet diet = service.create(new Diet("Super dieta dla Kobiet", 1000, 123.00));

        Diet addedDiet = service.read(diet.getId());
        addedDiet.setName("Super dieta dla młodych kobiet");
        service.update(addedDiet);

        Diet updateDiet = service.read(diet.getId());

        assertEquals(diet.getId(), updateDiet.getId());
        assertEquals(addedDiet.getName(), updateDiet.getName());
    }

    @Test
    public void testNotNullDietList() {
        List<Diet> diets = service.readAll();
        assertNotNull(diets);
    }
}
