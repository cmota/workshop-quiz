package com.cmota.quiz.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cmota.quiz.QuizApplication;
import com.cmota.quiz.R;
import com.cmota.quiz.model.Question;
import com.cmota.quiz.utils.Values;

import java.util.LinkedList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private final int N_QUESTIONS = 6;
    private List<Question> mQuestions = new LinkedList<>();
    private int mCurrentQuestion = 0;
    private int mWins = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(QuizApplication.getCurrentTheme());
        setContentView(R.layout.activity_game);

        setUiComponents();
    }

    private void setUiComponents() {
        findViewById(R.id.btn_next_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentQuestion < N_QUESTIONS) {
                    showNextQuestion();
                } else {
                    navigateToHome();
                }

                if (mCurrentQuestion == N_QUESTIONS-1) {
                    TextView nextQuestion = findViewById(R.id.btn_next_question);
                    nextQuestion.setText(R.string.game_last_question);
                } else if (mCurrentQuestion == N_QUESTIONS) {
                    TextView nextQuestion = findViewById(R.id.btn_next_question);
                    nextQuestion.setText(R.string.game_back_home);
                }
            }
        });

        mQuestions = QuizApplication.getRandomQuestions(N_QUESTIONS);

        showNextQuestion();
    }

    private void showNextQuestion() {
        final String answer = mQuestions.get(mCurrentQuestion).getAnswer();

        TextView wins = findViewById(R.id.tv_wins);
        wins.setText(getString(R.string.game_wins, mWins));

        TextView question = findViewById(R.id.tv_question);
        question.setText(mQuestions.get(mCurrentQuestion).getQuestion());

        TextView optionA = findViewById(R.id.tv_option_a);
        optionA.setBackgroundResource(R.drawable.btn_selector_round);
        optionA.setText(mQuestions.get(mCurrentQuestion).getOptionA());
        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAnswer("A", answer, view);
            }
        });

        TextView optionB = findViewById(R.id.tv_option_b);
        optionB.setBackgroundResource(R.drawable.btn_selector_round);
        optionB.setText(mQuestions.get(mCurrentQuestion).getOptionB());
        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAnswer("B", answer, view);
            }
        });


        TextView optionC = findViewById(R.id.tv_option_c);
        optionC.setBackgroundResource(R.drawable.btn_selector_round);
        optionC.setText(mQuestions.get(mCurrentQuestion).getOptionC());
        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAnswer("C", answer, view);
            }
        });

        TextView optionD = findViewById(R.id.tv_option_d);
        optionD.setBackgroundResource(R.drawable.btn_selector_round);
        optionD.setText(mQuestions.get(mCurrentQuestion).getOptionD());
        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAnswer("D", answer, view);
            }
        });

        TextView status = findViewById(R.id.tv_status);
        status.setText("");

        mCurrentQuestion++;
    }

    private void validateAnswer(String selected, String answer, View view) {
        if (selected.equalsIgnoreCase(answer)) {
            animateView(view, answer,true);
        } else {
            animateView(view, answer,false);
        }
    }

    private void animateView(final View view, final String answer, final boolean correct) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0.25f, 1, 1);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //Do nothing
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                TextView status = findViewById(R.id.tv_status);

                if (correct) {
                    updateScore();

                    status.setText(R.string.game_answer_correct);
                    view.setBackgroundResource(R.drawable.btn_selector_round_win);
                } else {
                    status.setText(getString(R.string.game_answer_incorrect, answer));
                    view.setBackgroundResource(R.drawable.btn_selector_round_lost);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //Do nothing
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //Do nothing
            }
        });
        animator.setDuration(500);
        animator.setRepeatCount(3);
        animator.start();
    }

    private void updateScore() {
        mWins++;

        TextView wins = findViewById(R.id.tv_wins);
        wins.setText(getString(R.string.game_wins, mWins));
    }

    private void navigateToHome() {
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        intent.putExtra(Values.EXTRA_NUMBER_OF_WINS, mWins);
        startActivity(intent);
        finish();
    }
}