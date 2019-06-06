package com.cmota.quiz.model;

public class Theme {

    private String mName;
    private int mColor;
    private int mTheme;

    public Theme(String name, int color, int theme) {
        mName = name;
        mColor = color;
        mTheme = theme;
    }

    public String getName() {
        return mName;
    }

    public int getColor() {
        return mColor;
    }

    public int getTheme() {
        return mTheme;
    }
}
