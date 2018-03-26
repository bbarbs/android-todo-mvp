package com.tokagestudio.todo.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.tokagestudio.todo.TodoApplication;
import com.tokagestudio.todo.data.repository.TodoRepository;
import com.tokagestudio.todo.data.repository.impl.TodoRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Data module that provides object related to data storage.
 * <p>
 * Created by bbarbero on 3/24/2018.
 */

@Module
public class DataModule {

    private static final String PREF_NAME = "PREFS";
    private final TodoApplication todoApplication;

    public DataModule(TodoApplication todoApplication) {
        this.todoApplication = todoApplication;
    }

    @Provides
    @Singleton
    SharedPreferences providePreferences() {
        return this.todoApplication.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    TodoRepository provideTodoRepository() {
        return new TodoRepositoryImpl();
    }
}
