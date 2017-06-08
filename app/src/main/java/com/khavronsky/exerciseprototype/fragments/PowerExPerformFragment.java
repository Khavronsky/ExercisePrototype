package com.khavronsky.exerciseprototype.fragments;

import com.khavronsky.exerciseprototype.R;
import com.khavronsky.exerciseprototype.exercise_models.ModelOfExercisePerformance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;




















public class PowerExPerformFragment extends Fragment {

    @BindView(R.id.ex_power_perform_start_time)
    EditText mStartTime;

    @BindView(R.id.ex_power_perform_duration)
    EditText mDuration;

    @BindView(R.id.ex_power_perform_sets)
    EditText mSets;

    @BindView(R.id.ex_power_perform_repeats)
    EditText mRepeats;

    @BindView(R.id.ex_power_perform_weight)
    EditText mWeight;

    @BindView(R.id.ex_power_perform_note)
    EditText mNote;

    TextWatcher mTextWatcher;

    private Unbinder unbinder;

    ModelOfExercisePerformance mModelOfExercisePerformance;

    public static PowerExPerformFragment newInstance(ModelOfExercisePerformance modelOfExercisePerformance) {

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        Log.d("KhSY_NewWaterMainFrg", "onCreateView: ");
        View v = inflater.inflate(R.layout.power_ex_perform_fragment, container, false);
        mModelOfExercisePerformance = getArguments().getParcelable("model");
        unbinder = ButterKnife.bind(this, v);
        init(v);
        return v;
    }

    private void init(final View v) {
        initTextWatcher();
        mStartTime.addTextChangedListener(mTextWatcher);
        mDuration.addTextChangedListener(mTextWatcher);
        mSets.addTextChangedListener(mTextWatcher);
        mRepeats.addTextChangedListener(mTextWatcher);
        mWeight.addTextChangedListener(mTextWatcher);
        mNote.addTextChangedListener(mTextWatcher);
    }

    private void initTextWatcher() {
        mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count,
                    final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                try {
                    if(Integer.parseInt(String.valueOf(s)) == 0){
                        s.clear();
                    }
                } catch (NumberFormatException e) {}
            }
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
