package com.khavronsky.exerciseprototype.fragments;

import com.khavronsky.exerciseprototype.R;
import com.khavronsky.exerciseprototype.exercise_models.ExerciseModel;
import com.khavronsky.exerciseprototype.exercise_models.ModelOfExercisePerformance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CardioExPerformFragment extends Fragment implements IDialogFragment {

    public final static String FRAGMENT_TAG = ExerciseModel.ExerciseType.CARDIO.getTag();

    @BindView(R.id.ex_cardio_perform_start_time)
    EditText mExCardioPerformStartTime;

    @BindView(R.id.ex_cardio_perform_duration)
    EditText mExCardioPerformDuration;

    @BindView(R.id.ex_cardio_Intensity_type)
    Spinner mCountCalMethod;

    @BindView(R.id.ex_cardio_perform_note)
    EditText mExCardioPerformNote;

    TextWatcher mTextWatcher;

    Calendar date = Calendar.getInstance();

    private IntNumPickerFragment mIntNumPickerDialog;

    private TimePickerDialogFragment mTimePickerDialog;

    private Unbinder unbinder;

    ModelOfExercisePerformance mModelOfExercisePerformance;

    public static CardioExPerformFragment newInstance(ModelOfExercisePerformance modelOfExercisePerformance) {

        Bundle args = new Bundle();
        args.putParcelable("model", modelOfExercisePerformance);
        CardioExPerformFragment fragment = new CardioExPerformFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cardio_ex_perform_fragment, container, false);
        mModelOfExercisePerformance = getArguments().getParcelable("model");
        unbinder = ButterKnife.bind(this, v);
        init(v);
        return v;
    }

    @OnClick({R.id.ex_cardio_perform_start_time, R.id.ex_cardio_perform_duration})
    void showPickers(EditText eText) {
        int id = eText.getId();
        switch (id){
            case R.id.ex_cardio_perform_start_time:
                if (mTimePickerDialog == null) {
                    mTimePickerDialog = TimePickerDialogFragment.newInstance(date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE));
                    mTimePickerDialog.setCallback(this);
                    mTimePickerDialog.show(getActivity().getSupportFragmentManager(), "time_picker_dialog");
                }
                break;
            case R.id.ex_cardio_perform_duration:
                if (mIntNumPickerDialog == null) {
                    mIntNumPickerDialog = IntNumPickerFragment.newInstance(0, 1440, mModelOfExercisePerformance.getDuration(),
                            1);
                    mIntNumPickerDialog.setCallback(this);
                    mIntNumPickerDialog.show(getActivity().getSupportFragmentManager(), "int_picker_dialog");
                }
                break;
        }
    }

    @Override
    public void doButtonClick1(final Object o) {
        if (mTimePickerDialog != null) {
            date = (Calendar) o;
            mTimePickerDialog.dismiss();
            mTimePickerDialog = null;
            setDate();
        }
        if (mIntNumPickerDialog != null) {
            mModelOfExercisePerformance.setDuration((int) o);
            mIntNumPickerDialog.dismiss();
            mIntNumPickerDialog = null;
            mExCardioPerformDuration.setText(o + " мин");
        }
    }

    @Override
    public void doButtonClick2() {}

    @Override
    public void doByDismissed() {}

    private void setDate() {
        String dateText = DateUtils.formatDateTime(getActivity().getApplicationContext(),
                date.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
        mExCardioPerformStartTime.setText(dateText);
    }

    private void init(final View v) {
        setSpinners();
    }

    private void setSpinners() {
        ArrayAdapter<?> adapter = ArrayAdapter
                .createFromResource(getContext(), R.array.intensity_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountCalMethod.setAdapter(adapter);
        mCountCalMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position,
                    final long id) {
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
