package com.tokagestudio.todo.ui.base;

import android.support.annotation.NonNull;

import com.tokagestudio.todo.ui.base.BaseView;

/**
 * Interface representing a BasePresenter in a model view presenter (MVP) pattern.
 * <p>
 * Created by bbarbero on 3/24/2018.
 */

public interface BasePresenter<T extends BaseView> {
    void onResume();

    void onDestroy();

    void onPause();

    void attachView(@NonNull T view);
}
