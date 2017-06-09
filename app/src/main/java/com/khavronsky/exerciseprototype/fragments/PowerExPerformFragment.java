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

import static com.khavronsky.exerciseprototype.fragments.FloatNumPickerFragment.EXTRA_DECIMAL_STEP_IS_01;

public class PowerExPerformFragment extends Fragment {

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
    @OnClick({R.id.ex_power_perform_sets, R.id.ex_power_perform_repeats, R.id.ex_power_perform_weight})
    void showFloatPicker(View v){
        showPicker((EditText) v);
    }

    private void init(final View v) {
        initTextWatcher();
//        mStartTime.addTextChangedListener(mTextWatcher);
//        mDuration.addTextChangedListener(mTextWatcher);
//        mSets.addTextChangedListener(mTextWatcher);
//        mRepeats.addTextChangedListener(mTextWatcher);
//        mWeight.addTextChangedListener(mTextWatcher);
        mNote.addTextChangedListener(mTextWatcher);
    }
    void showPicker(EditText editText) {
        FloatNumPickerFragment dialog= (FloatNumPickerFragment) getFragmentManager()
                .findFragmentByTag("picker");
        if(dialog!=null){
            return;
        }
        dialog = new FloatNumPickerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("min_value", 0);
        bundle.putInt("max_value", 2);
        float curVal = 1;
        if(editText.getText().length() != 0) curVal = Float.parseFloat(String.valueOf(editText.getText()));
        bundle.putFloat("current_value", curVal);
        bundle.putBoolean(EXTRA_DECIMAL_STEP_IS_01, true);
//        bundle.putInt("one_point_value", 1);
        dialog.setArguments(bundle);
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
