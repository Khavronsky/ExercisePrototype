package com.khavronsky.exerciseprototype.exercise_models;


import android.os.Parcel;
import android.support.annotation.IntDef;

public class CardioExerciseModel extends ExerciseModel {

    private final static ExerciseType type = ExerciseType.CARDIO;

    @IntDef({
            MET,
            KCAL_PER_HOUR,
    })

    @interface IntensityType {}
    private static final int MET = 0;
    private static final int KCAL_PER_HOUR = 1;

    @IntensityType
    private int intensityType;

    private int high;

    private int middle;

    private int low;

    private int defValue;

    public CardioExerciseModel() {
        super.setType(type);
    }

    @IntensityType
    public int getIntensityType() {
        return intensityType;
    }

    public CardioExerciseModel setIntensityType(@IntensityType final int intensityType) {
        this.intensityType = intensityType;
        return this;
    }

    public int getHigh() {
        return high;
    }

    public CardioExerciseModel setHigh(final int high) {
        this.high = high;
        return this;
    }

    public int getMiddle() {
        return middle;
    }

    public CardioExerciseModel setMiddle(final int middle) {
        this.middle = middle;
        return this;
    }

    public int getLow() {
        return low;
    }

    public CardioExerciseModel setLow(final int low) {
        this.low = low;
        return this;
    }

    public int getDefValue() {
        return defValue;
    }

    public CardioExerciseModel setDefValue(final int defValue) {
        this.defValue = defValue;
        return this;
    }

    /**
     * P A R C E L A B L E   I M P L E M E N T A T I O N
     * */

    public static final Creator<CardioExerciseModel> CREATOR = new Creator<CardioExerciseModel>() {
        @Override
        public CardioExerciseModel createFromParcel(final Parcel source) {
            return new CardioExerciseModel();
        }

        @Override
        public CardioExerciseModel[] newArray(final int size) {
            return new CardioExerciseModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {

    }
}
