package com.tokagestudio.todo.di.components;

import com.tokagestudio.todo.di.scopes.PerActivity;
import com.tokagestudio.todo.ui.main.MainActivity;
import com.tokagestudio.todo.ui.newtodo.NewTodoActivity;

import dagger.Component;

/**
 * A base component for activity component.
 * <p>
 * Decorated with annotation {@link PerActivity}.
 * <p>
 * Created by bbarbero on 3/24/2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(NewTodoActivity newTodoActivity);
}
