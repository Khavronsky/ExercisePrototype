package com.khavronsky.exerciseprototype;

import com.khavronsky.exerciseprototype.custom_views.collapsing_card.CustomCollapsingView;
import com.khavronsky.exerciseprototype.exercise_models.CardioExerciseModel;
import com.khavronsky.exerciseprototype.exercise_models.ModelOfExercisePerformance;
import com.khavronsky.exerciseprototype.fragments.CardioExPerformFragment;
import com.khavronsky.exerciseprototype.fragments.PowerExPerformFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.khavronsky.exerciseprototype.exercise_models.ExerciseModel.ExerciseType.CARDIO;
import static com.khavronsky.exerciseprototype.exercise_models.ExerciseModel.ExerciseType.POWER;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        PresenterOfExercisePerformance.IView, CardioExPerformFragment.IExerciseListener {

    @BindView(R.id.my_view)
    CustomCollapsingView mCustomCollapsingView;

    @BindView(R.id.ex_performance_add_btn)
    View addExButton;

    ModelOfExercisePerformance mModelOfExercisePerformance;

    private PresenterOfExercisePerformance mPresenter;

    @Override
    public void updateModel(final ModelOfExercisePerformance modelOfExercisePerformance) {
        createMyView();
    }

    @Override
    public void show(final ModelOfExercisePerformance modelOfExercisePerformance) {
        this.mModelOfExercisePerformance = modelOfExercisePerformance;
        createMyView();
        startFragment();
        Log.d("qwert", "show: ");
    }

    @Override
    public void onClick(final View v) {
        onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete) {
            recreate();
            return true;
        }
        if (id == R.id.edit) {
//            showAlertView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (mPresenter == null) {
            mPresenter = new PresenterOfExercisePerformance();
        }
        mPresenter.attachView(this);
        mPresenter.loadData();

        setToolbar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    private void startFragment() {
        String tag = mModelOfExercisePerformance
                .getExercise()
                .getType()
                .getTag();
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        Log.d("KhS", "startFragment: ");
        if (fragmentManager.findFragmentByTag(tag) != null) {
            fragment = fragmentManager.findFragmentByTag(tag);
        } else if (Objects.equals(tag, CARDIO.getTag())) {
            Log.d("KhS", "startFragment: CARDIO");
            fragment = CardioExPerformFragment.newInstance(mModelOfExercisePerformance);
            ((CardioExPerformFragment)fragment).setListener(this);
        } else if (Objects.equals(tag, POWER.getTag())) {
            Log.d("KhS", "startFragment: POWER");
            fragment = PowerExPerformFragment.newInstance(mModelOfExercisePerformance);
        }
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment,
                        tag)
                .commit();
    }

    private void setToolbar() {
        Toolbar toolbar = mCustomCollapsingView.getToolbar();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(this);
    }

    private void createMyView() {
        String subTitle = "";
        String value = "";
        String unit = "Ккал";
        String description = "";
        int kcalBurned = 0;
        int resID = R.drawable.ic_cardio_ex;
        if (mModelOfExercisePerformance.getExercise().getType() == POWER){
            mCustomCollapsingView.showAlertView(true);
            subTitle = "Силовые";
        }else
            if(mModelOfExercisePerformance.getExercise().getType() == CARDIO){
                mCustomCollapsingView.showAlertView(false);
                subTitle = "Кардио";

                kcalBurned = (int) (mModelOfExercisePerformance.getCurrentKcalPerHour() * getFakeWeight() *
                                        mModelOfExercisePerformance.getDuration()/60);
            }

        mCustomCollapsingView.setTextTitle(mModelOfExercisePerformance.getExercise().getTitle())
                .setTextSubtitle(subTitle)
                .setTextValue(String.valueOf(kcalBurned))
                .setTextUnit(unit)
                .setTextExtraDescription(description)
                .setImageID(resID)
                .applyChanges();
    }

    private void showAlertView() {
        mCustomCollapsingView
                .showAlertView(true)
                .applyChanges();
    }

    @OnClick(R.id.ex_performance_add_btn)
    void addExercise() {
        mPresenter.setData(mModelOfExercisePerformance);
    }

    int getFakeWeight(){
        return 80;
    }

    int burnedKcalories(float burnPerHourValue, int duration,
            @CardioExerciseModel.CountingCaloriesMethod int countingMethod){
        if (countingMethod == CardioExerciseModel.METHOD_CAL_PER_HOUR){
//            return (int) (burnPerHourValue * duration * getFakeWeight() / 60);
        } else {
        }
        return (int) (burnPerHourValue * duration * getFakeWeight() / 60);

    }
}
