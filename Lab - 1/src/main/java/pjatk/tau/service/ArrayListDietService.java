package pjatk.tau.service;

import pjatk.tau.model.Diet;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayListDietService implements DietService{

    private List<Diet> diets = new ArrayList<Diet>();

    public Diet create(Diet diet) {
        diet.setId(generateId());
        diets.add(diet);

        return diet;
    }

    public List<Diet> readAll() {
        return diets;
    }

    public Diet read(final int dietId) {
        return diets
                .stream()
                .filter(diet -> diet.getId() == dietId)
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
            ;
    }

    public void update(Diet diet) {
        Diet dbDiet = read(diet.getId());

        dbDiet.setName(diet.getName());
        dbDiet.setCalories(diet.getCalories());
        dbDiet.setPrice(diet.getPrice());
    }

    public void delete(Diet diet) {
        diets.removeIf(dietInMemory -> dietInMemory.getId() == diet.getId());
    }

    private int generateId() {
        int size = diets.size();
        return size + 1;
    }
}
