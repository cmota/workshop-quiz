package com.cmota.quiz.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.cmota.quiz.QuizApplication;
import com.cmota.quiz.model.Question;
import com.cmota.quiz.utils.Values;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class LoadDataService extends IntentService {

    private final String TAG = "LoadDataService";
    private final String ENCODING = "UTF-8";
    private final String DATABASE_FILE = "questions.json";

    public LoadDataService() {
        super("LoadDataService");
    }

    public LoadDataService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        readDatabase();
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Values.ACTION_READ_DATA));
    }

    private void readDatabase() {
        Gson gson = new Gson();
        Type TYPE = new TypeToken<List<Question>>() {}.getType();

        try {
            InputStream stream = getAssets().open(DATABASE_FILE);
            JsonReader reader = new JsonReader(new InputStreamReader(stream, ENCODING));
            List<Question> questions = gson.fromJson(reader, TYPE);
            QuizApplication.setQuestions(questions);
        } catch (IOException e) {
            Log.w(TAG, "Unable to read " + DATABASE_FILE + ". Error: " + e.getMessage());
        }
    }
}
