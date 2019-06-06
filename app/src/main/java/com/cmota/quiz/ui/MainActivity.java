package com.cmota.quiz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cmota.quiz.QuizApplication;
import com.cmota.quiz.R;
import com.cmota.quiz.utils.Values;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(QuizApplication.getCurrentTheme());
        setContentView(R.layout.activity_main);

        setToolbar();
        setUiComponents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_themes:
                navigateToThemes();
                return true;
            case R.id.action_share:
                shareApplication();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    private void setUiComponents() {
        if (getIntent().hasExtra(Values.EXTRA_NUMBER_OF_WINS)) {

            int nWins = getIntent().getExtras().getInt(Values.EXTRA_NUMBER_OF_WINS, 0);
            TextView welcome = findViewById(R.id.tv_n_wins);
            welcome.setVisibility(View.VISIBLE);
            welcome.setText(getString(R.string.main_n_wins, nWins));

        } else {
            findViewById(R.id.tv_n_wins).setVisibility(View.INVISIBLE);
        }

        findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToGame();
            }
        });
    }

    private void navigateToGame() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToThemes() {
        Intent intent = new Intent(MainActivity.this, ThemesActivity.class);
        startActivity(intent);
        finish();
    }

    private void shareApplication() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.main_share_message));
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.main_select)));
    }
}
