package pjatk.tau.service;

import pjatk.tau.model.Diet;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayListDietService implements DietService {

    private boolean enableTimeOnReadDiet = true;
    private boolean enableTimeOnAddDiet = true;
    private boolean enableTimeOnUpdateDiet = true;

    private Clock clock;
    private List<Diet> diets = new ArrayList<Diet>();

    public ArrayListDietService() {
        clock = Clock.systemDefaultZone();
    }

    public ArrayListDietService(Clock clock) {
        this.clock = clock;
    }

    public Diet create(Diet diet) {
        diet.setId(generateId());
        if (enableTimeOnAddDiet) {
            diet.setAddTime(LocalDateTime.now(clock));
        }
        diets.add(diet);

        return diet;
    }

    public List<Diet> readAll() {
        LocalDateTime readTime = LocalDateTime.now(clock);
        diets.stream().forEach(diet -> diet.setReadTime(LocalDateTime.now(clock)));
        return diets;
    }

    public Diet read(final int dietId) {
        Diet readDiet = diets
                .stream()
                .filter(diet -> diet.getId() == dietId)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        if (enableTimeOnReadDiet) {
            readDiet.setReadTime(LocalDateTime.now(clock));
        }

        return readDiet;
    }

    public void update(Diet diet) {
        Diet dbDiet = read(diet.getId());

        dbDiet.setName(diet.getName());
        dbDiet.setCalories(diet.getCalories());
        dbDiet.setPrice(diet.getPrice());
        if(enableTimeOnUpdateDiet) {
            dbDiet.setUpdateTime(LocalDateTime.now(clock));
        }
    }

    public void delete(Diet diet) {
        diets.removeIf(dietInMemory -> dietInMemory.getId() == diet.getId());
    }

    private int generateId() {
        int size = diets.size();
        return size + 1;
    }

    public boolean isEnableTimeOnReadDiet() {
        return enableTimeOnReadDiet;
    }

    public void setEnableTimeOnReadDiet(boolean enableTimeOnReadDiet) {
        this.enableTimeOnReadDiet = enableTimeOnReadDiet;
    }

    public boolean isEnableTimeOnAddDiet() {
        return enableTimeOnAddDiet;
    }

    public void setEnableTimeOnAddDiet(boolean enableTimeOnAddDiet) {
        this.enableTimeOnAddDiet = enableTimeOnAddDiet;
    }

    public boolean isEnableTimeOnUpdateDiet() {
        return enableTimeOnUpdateDiet;
    }

    public void setEnableTimeOnUpdateDiet(boolean enableTimeOnUpdateDiet) {
        this.enableTimeOnUpdateDiet = enableTimeOnUpdateDiet;
    }
}
