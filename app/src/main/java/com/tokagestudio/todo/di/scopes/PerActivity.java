package com.tokagestudio.todo.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * A scope annotation to permit objects whose lifetime should conform
 * to the life of the activity.
 * <p>
 * Created by bbarbero on 3/24/2018.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
