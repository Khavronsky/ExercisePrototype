package com.khavronsky.exerciseprototype;


import com.khavronsky.exerciseprototype.exercise_models.ModelOfExercisePerformance;
import com.khavronsky.exerciseprototype.exercise_models.PowerExerciseModel;

public class PresenterOfExercisePerformance extends AbstractPresenter<PresenterOfExercisePerformance.IView> {

    private ModelOfExercisePerformance modelOfExercisePerformance;

    void loadData() {
        if (getView() != null) {
            ModelOfExercisePerformance model;
            if (modelOfExercisePerformance != null) {
                model = modelOfExercisePerformance;
            } else {
                model = createFakeData();
            }
            getView().show(model);
        }
    }

    private ModelOfExercisePerformance createFakeData() {
        return new ModelOfExercisePerformance(
                new PowerExerciseModel()
                        .setRepeats(15)
                        .setSets(3)
                        .setWeight(50000)
                        .setTitle("Мадагаскарский жим")
                        .setCustomExercise(true))
                .setStartTime(12000L)
                .setDuration(5000L)
                .setNote("Заметка о самочуствии во время осуществления мадагаскарского жима");
    }

    public void setData(ModelOfExercisePerformance modelOfExercisePerformance) {
        this.modelOfExercisePerformance = modelOfExercisePerformance;
        //куда-то сохраняем

    }

    interface IView {

        void show(ModelOfExercisePerformance modelOfExercisePerformance);
    }
}
