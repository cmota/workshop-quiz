package com.cmota.quiz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.cmota.quiz.QuizApplication;
import com.cmota.quiz.R;
import com.cmota.quiz.cb.IOnUserAction;
import com.cmota.quiz.model.Theme;

import java.util.LinkedList;
import java.util.List;

public class ThemesActivity extends AppCompatActivity implements IOnUserAction {

    private static final String TAG = "ThemesActivity";
    private List<Theme> mThemes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(QuizApplication.getCurrentTheme());
        setContentView(R.layout.activity_themes);

        populateList();

        setToolbar();
        setUiComponents();
    }

    private void populateList() {
        mThemes = new LinkedList<>();
        mThemes.add(new Theme("Red", R.color.colorPrimary, R.style.AppTheme));
        mThemes.add(new Theme("Pink", R.color.colorPink, R.style.AppThemePink));
        mThemes.add(new Theme("Purple", R.color.colorPurple, R.style.AppThemePurple));
        mThemes.add(new Theme("Deep Purple", R.color.colorDeepPurple, R.style.AppThemeDeepPurple));
        mThemes.add(new Theme("Indigo", R.color.colorIndigo, R.style.AppThemeIndigo));
        mThemes.add(new Theme("Blue", R.color.colorBlue, R.style.AppThemeBlue));
        mThemes.add(new Theme("Light Blue", R.color.colorLightBlue, R.style.AppThemeLightBlue));
        mThemes.add(new Theme("Cyan", R.color.colorCyan, R.style.AppThemeCyan));
        mThemes.add(new Theme("Teal", R.color.colorTeal, R.style.AppThemeTeal));
        mThemes.add(new Theme("Green", R.color.colorGreen, R.style.AppThemeGreen));
        mThemes.add(new Theme("Light Green", R.color.colorLightGreen, R.style.AppThemeLightGreen));
        mThemes.add(new Theme("Lime", R.color.colorLime, R.style.AppThemeLime));
        mThemes.add(new Theme("Yellow", R.color.colorYellow, R.style.AppThemeYellow));
        mThemes.add(new Theme("Amber", R.color.colorAmber, R.style.AppThemeAmber));
        mThemes.add(new Theme("Orange", R.color.colorOrange, R.style.AppThemeOrange));
        mThemes.add(new Theme("Brown", R.color.colorBrown, R.style.AppThemeBrown));
        mThemes.add(new Theme("Grey", R.color.colorGrey, R.style.AppThemeGrey));
        mThemes.add(new Theme("Blue Grey", R.color.colorBlueGrey, R.style.AppThemeBlueGrey));
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            Log.e(TAG, "No action bar defined");
            return;
        }

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);

        toolbar.setTitle(R.string.toolbar_themes);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMain();
            }
        });
    }

    private void setUiComponents() {
        RecyclerView recyclerView = findViewById(R.id.rv_content);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ThemesAdapter adapter = new ThemesAdapter(mThemes, this);
        recyclerView.setAdapter(adapter);

        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(getApplicationContext()) {
            @Override protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };

        smoothScroller.setTargetPosition(getSelectedThemePosition());
        recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);
    }

    private int getSelectedThemePosition() {
        for (int index=0; index<mThemes.size(); index++) {
            if (mThemes.get(index).getTheme() == QuizApplication.getCurrentTheme()) {
                return index;
            }
        }

        return 0;
    }

    private void navigateToMain() {
        Intent intent = new Intent(ThemesActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //Methods from IOnUserAction

    @Override
    public void onUserClickAction(Object object, View view) {
        QuizApplication.setNewTheme(((Theme) object).getTheme());
        navigateToMain();
    }

    @Override
    public boolean onUserLongClickAction(Object object, View view) {
        return false;
    }
}
