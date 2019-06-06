package com.cmota.quiz.model;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("question")
    private String mQuestion;

    @SerializedName("A")
    private String mOptionA;

    @SerializedName("B")
    private String mOptionB;

    @SerializedName("C")
    private String mOptionC;

    @SerializedName("D")
    private String mOptionD;

    @SerializedName("answer")
    private String mAnswer;

    public Question(String question, String optionA, String optionB, String optionC, String optionD, String answer) {
        mQuestion = question;
        mOptionA = optionA;
        mOptionB = optionB;
        mOptionC = optionC;
        mOptionD = optionD;
        mAnswer = answer;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getOptionA() {
        return mOptionA;
    }

    public String getOptionB() {
        return mOptionB;
    }

    public String getOptionC() {
        return mOptionC;
    }

    public String getOptionD() {
        return mOptionD;
    }

    public String getAnswer() {
        return mAnswer;
    }
}
