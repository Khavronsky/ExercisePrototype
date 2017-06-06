package com.khavronsky.exerciseprototype;


import com.khavronsky.exerciseprototype.exercise_models.ModelOfExercisePerformance;
import com.khavronsky.exerciseprototype.exercise_models.PowerExerciseModel;

public class PresenterOfExercisePerformance extends AbstractPresenter<PresenterOfExercisePerformance.IView> {

    void loadData() {
        if (getView() != null) {
            getView().show(createFakeData());
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
                .setDate(12000L)
                .setDuration(5000L)
                .setNote("Заметка о самочуствии во время осуществления мадагаскарского жима");
    }

    interface IView {
        void show(ModelOfExercisePerformance modelOfExercisePerformance);
    }
}
