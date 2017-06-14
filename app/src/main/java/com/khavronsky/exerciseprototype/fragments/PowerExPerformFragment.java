package com.khavronsky.exerciseprototype.fragments;

import com.khavronsky.exerciseprototype.R;
import com.khavronsky.exerciseprototype.exercise_models.ExerciseModel;
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
import butterknife.OnClick;
import butterknife.Unbinder;

public class PowerExPerformFragment extends Fragment implements IDialogFragment {

    public final static String FRAGMENT_TAG = ExerciseModel.ExerciseType.POWER.getTag();

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

    @Override
    public void doButtonClick1(final Object o) {

    }

    @Override
    public void doButtonClick2() {

    }

    @Override
    public void doByDismissed() {

    }

    @OnClick({R.id.ex_power_perform_start_time, R.id.ex_power_perform_duration, R.id.ex_power_perform_sets, R.id
            .ex_power_perform_repeats, R.id.ex_power_perform_weight})
    void showPicker(EditText v) {
        int id = v.getId();
        switch (id) {
            case R.id.ex_power_perform_start_time:
                break;
            case R.id.ex_power_perform_duration:
                break;
            case R.id.ex_power_perform_sets:
                showIntPicker(v, 1, 1);
                break;
            case R.id.ex_power_perform_repeats:
                showIntPicker(v, 10, 1);
                break;
            case R.id.ex_power_perform_weight:
                showIntPicker(v, 0, 1);
                break;
        }
    }

    private void init(final View v) {
        initTextWatcher();
        mNote.addTextChangedListener(mTextWatcher);
    }

    void showIntPicker(EditText editText, int currentVal, int onePointVal) {
        IntNumPickerFragment dialog = (IntNumPickerFragment) getFragmentManager()
                .findFragmentByTag("picker");
        if (dialog != null) {
            return;
        }
        dialog = IntNumPickerFragment.newInstance(0, 1000, currentVal, onePointVal);
        dialog.setCallback(new IDialogFragment() {
            @Override
            public void doButtonClick1(final Object o) {
                editText.setText(String.valueOf(o));
            }

            @Override
            public void doButtonClick2() {
            }

            @Override
            public void doByDismissed() {

            }
        });

        dialog.show(getFragmentManager(), "picker");
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
                    if (Integer.parseInt(String.valueOf(s)) == 0) {
                        s.clear();
                    }
                } catch (NumberFormatException e) {
                }
            }
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
