package pjatk.tau.service;

import org.junit.Before;
import org.junit.Test;
import pjatk.tau.model.Diet;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TimestampArrayListDietServiceTests {
    LocalDateTime timestamp;
    DietService service;

    @Before
    public void setup() {
        timestamp = LocalDateTime.of(2018, 11, 3, 21, 38);
        service = new ArrayListDietService(Clock.fixed(timestamp.toInstant(ZoneOffset.UTC), ZoneId.of("UTC")));
    }

    @Test
    public void testAddingNewDietWithTime() {
        Diet diet = new Diet("Dieta pełna energi", 5000, 500.00);
        Diet addedDiet = service.create(diet);

        assertEquals(timestamp, addedDiet.getAddTime());
    }

    @Test
    public void testGettingAllDiets() {
        service.create(new Diet("Super dieta dla Kobiet", 1000, 123.00));
        service.create(new Diet("Super dieta na młodość", 1500, 999.00));
        service.create(new Diet("Dieta dla odważnych", 9999, 9999));

        for(Diet diet : service.readAll()) {
            assertEquals(timestamp, diet.getReadTime());
        }
    }

    @Test
    public void testGettingOneDietFromMemory() {
        Diet diet = service.create(new Diet("Super dieta dla Kobiet", 1000, 123.00));
        Diet addedDiet = service.read(diet.getId());

        assertEquals(timestamp, addedDiet.getReadTime());
    }

    @Test
    public void testNotSetReadTimeWhenGettingDiet() {
        service.setEnableTimeOnReadDiet(false);

        Diet diet = service.create(new Diet("Super dieta dla Kobiet", 1000, 123.00));
        Diet addedDiet = service.read(diet.getId());

        assertNotNull(addedDiet);
        assertNull(addedDiet.getReadTime());
    }

    @Test
    public void testNotSetAddTimeWhenGettingDiet() {
        service.setEnableTimeOnAddDiet(false);

        Diet diet = service.create(new Diet("Super dieta dla Kobiet", 1000, 123.00));
        Diet addedDiet = service.read(diet.getId());

        assertNotNull(addedDiet);
        assertNull(addedDiet.getAddTime());
    }

    @Test
    public void testNotSetUpadateTimeWhenGettingDiet() {
        service.setEnableTimeOnUpdateDiet(false);

        Diet diet = service.create(new Diet("Super dieta dla Kobiet", 1000, 123.00));
        Diet addedDiet = service.read(diet.getId());

        assertNotNull(addedDiet);
        assertNull(addedDiet.getUpdateTime());
    }

    @Test
    public void testSetReadTimeWhenGettingDiet() {
        service.setEnableTimeOnReadDiet(true);

        Diet diet = service.create(new Diet("Super dieta dla Kobiet", 1000, 123.00));
        Diet addedDiet = service.read(diet.getId());

        assertNotNull(addedDiet);
        assertNull(addedDiet.getReadTime());
    }

    @Test
    public void testSetAddTimeWhenGettingDiet() {
        service.setEnableTimeOnAddDiet(true);

        Diet diet = service.create(new Diet("Super dieta dla Kobiet", 1000, 123.00));
        Diet addedDiet = service.read(diet.getId());

        assertNotNull(addedDiet);
        assertNull(addedDiet.getAddTime());
    }

    @Test
    public void testSetUpadateTimeWhenGettingDiet() {
        service.setEnableTimeOnUpdateDiet(true);

        Diet diet = service.create(new Diet("Super dieta dla Kobiet", 1000, 123.00));
        Diet addedDiet = service.read(diet.getId());

        assertNotNull(addedDiet);
        assertNull(addedDiet.getUpdateTime());
    }


}
