package com.khavronsky.exerciseprototype.fragments;

import com.khavronsky.exerciseprototype.R;
import com.khavronsky.exerciseprototype.exercise_models.ModelOfExercisePerformance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PowerExPerformFragment extends Fragment {

    ModelOfExercisePerformance mModelOfExercisePerformance;

    public static PowerExPerformFragment newInstance(ModelOfExercisePerformance modelOfExercisePerformance){

        Bundle args = new Bundle();
        args.putParcelable("model", modelOfExercisePerformance);
        PowerExPerformFragment fragment = new PowerExPerformFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("KhSY_NewWaterMainFrg", "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("KhSY_NewWaterMainFrg", "onCreateView: ");
        View v = inflater.inflate(R.layout.power_ex_perform_fragment, container, false);
        mModelOfExercisePerformance = getArguments().getParcelable("model");
        init(v);
        return v;
    }

    private void init(final View v) {

    }

}
