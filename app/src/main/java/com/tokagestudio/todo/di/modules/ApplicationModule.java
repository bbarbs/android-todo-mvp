package com.tokagestudio.todo.di.modules;

import android.content.Context;

import com.tokagestudio.todo.TodoApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module that provide objects which will leave during application duration.
 * <p>
 * Created by bbarbero on 3/24/2018.
 */

@Module
public class ApplicationModule {

    private final TodoApplication todoApplication;

    public ApplicationModule(TodoApplication todoApplication) {
        this.todoApplication = todoApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return this.todoApplication;
    }
}
