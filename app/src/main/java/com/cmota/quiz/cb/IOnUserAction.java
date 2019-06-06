package com.cmota.quiz.cb;

import android.view.View;

public interface IOnUserAction {

    void onUserClickAction(Object object, View view);

    boolean onUserLongClickAction(Object object, View view);
}
