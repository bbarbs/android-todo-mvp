package com.tokagestudio.todo.di.components;

import android.content.Context;
import android.content.SharedPreferences;

import com.tokagestudio.todo.di.modules.ApplicationModule;
import com.tokagestudio.todo.di.modules.DataModule;
import com.tokagestudio.todo.data.repository.TodoRepository;
import com.tokagestudio.todo.ui.base.BaseActivity;
import com.tokagestudio.todo.data.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A dagger component whose lifetime is the duration of application.
 * <p>
 * Created by bbarbero on 3/24/2018.
 */

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                DataModule.class
        }
)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context context();

    SharedPreferences sharedPreferences();

    PreferencesHelper prefUtil();

    TodoRepository todoRepository();
}
