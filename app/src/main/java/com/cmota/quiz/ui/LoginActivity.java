package com.cmota.quiz.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cmota.quiz.R;
import com.cmota.quiz.service.LoadDataService;
import com.cmota.quiz.utils.Values;

public class LoginActivity extends AppCompatActivity {

    private boolean SHOULD_REDIRECT = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SHOULD_REDIRECT) {
            readDatabase();
        } else {
            setUiComponents();
        }

        setUiComponents();
    }

    private void readDatabase() {
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                navigateToMain();

                LocalBroadcastManager.getInstance(LoginActivity.this).unregisterReceiver(this);
            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(Values.ACTION_READ_DATA));

        Intent msgIntent = new Intent(this, LoadDataService.class);
        startService(msgIntent);
    }

    private void setUiComponents() {
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.tv_username);
                EditText password = findViewById(R.id.tv_password);

                if (username.getText().toString().equals(password.getText().toString())) {
                    ((TextView) findViewById(R.id.tv_info)).setText("Success!");
                } else {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Login")
                            .setMessage("Invalid credentials")
                            .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }
        });
    }

    private void navigateToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
