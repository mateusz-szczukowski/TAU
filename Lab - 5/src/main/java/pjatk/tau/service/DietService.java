package pjatk.tau.service;

import pjatk.tau.model.Diet;

import java.util.List;

public interface DietService {

    void setEnableTimeOnAddDiet(boolean enableTimeOnAddDiet);
    void setEnableTimeOnReadDiet(boolean enableTimeOnReadDiet);
    void setEnableTimeOnUpdateDiet(boolean enableTimeOnUpdateDiet);

    Diet create(Diet diet);
    List<Diet> readAll();
    Diet read(int dietId);
    void update(Diet diet);
    void delete(Diet diet);

}
