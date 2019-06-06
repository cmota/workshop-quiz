package com.cmota.quiz;

import android.app.Application;

import com.cmota.quiz.model.Question;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QuizApplication extends Application {

    private static int mCurrentTheme = R.style.AppTheme;
    private static List<Question> mQuestions = new LinkedList<>();

    public static void setNewTheme(int theme) {
        mCurrentTheme = theme;
    }

    public static int getCurrentTheme() {
        return mCurrentTheme;
    }

    public static void setQuestions(List<Question> questions) {
        mQuestions = questions;
    }

    public static List<Question> getQuestions() {
        return mQuestions;
    }

    public static List<Question> getRandomQuestions(int total) {
        Random rand = new Random();
        int startIndex = rand.nextInt(mQuestions.size() - total);
        return mQuestions.subList(startIndex, startIndex + total);
    }
}
