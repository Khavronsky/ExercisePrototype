package com.khavronsky.exerciseprototype;

import com.khavronsky.exerciseprototype.custom_views.collapsing_card.CustomCollapsingView;
import com.khavronsky.exerciseprototype.exercise_models.ModelOfExercisePerformance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.khavronsky.exerciseprototype.exercise_models.ExerciseModel.ExerciseType.CARDIO;
import static com.khavronsky.exerciseprototype.exercise_models.ExerciseModel.ExerciseType.POWER;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        PresenterOfExercisePerformance.IView {

    @BindView(R.id.my_view)
    CustomCollapsingView mCustomCollapsingView;

    ModelOfExercisePerformance mModelOfExercisePerformance;

    private PresenterOfExercisePerformance mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (mPresenter == null) {
            mPresenter = new PresenterOfExercisePerformance();
        }
        mPresenter.loadData();
        mPresenter.attachView(this);
        createMyView();
        setToolbar();
    }

    @Override
    public void show(final ModelOfExercisePerformance modelOfExercisePerformance) {
        this.mModelOfExercisePerformance = modelOfExercisePerformance;
        startFragment();
    }

    private void startFragment() {
        String tag = mModelOfExercisePerformance
                .getExercise()
                .getType()
                .getTag();
        Fragment fragment;
        if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
            fragment = getSupportFragmentManager().findFragmentByTag(tag);
        } else if (tag == CARDIO.getTag()){
//            fragment =;
        } else if (tag == POWER.getTag()){
//            fragment =;
        }
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
        mCustomCollapsingView.setTextTitle("Пляски святого Вита. Гимнастика Хантингтона")
                .setTextSubtitle("Невро-Кардио")
                .setTextValue("120")
                .setTextUnit("ккал")
                .setTextExtraDescription("за 23 минуты")
                .setImageID(R.drawable.ic_cardio_ex)
                .applyChanges();
    }

    private void showAlertView() {
        mCustomCollapsingView.showAlertView(true).applyChanges();
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
            showAlertView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
