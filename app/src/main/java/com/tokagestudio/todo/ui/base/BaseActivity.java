package com.tokagestudio.todo.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.tokagestudio.todo.TodoApplication;
import com.tokagestudio.todo.di.components.ActivityComponent;
import com.tokagestudio.todo.di.components.ApplicationComponent;
import com.tokagestudio.todo.di.components.DaggerActivityComponent;
import com.tokagestudio.todo.data.prefs.PreferencesHelper;

import javax.inject.Inject;

/**
 * Base class of activity.
 * <p>
 * Created by bbarbero on 3/25/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private final static String TAG = "BaseActivity";

    @Inject
    public PreferencesHelper preferencesHelper;

    /**
     * Activity component dependency for activities.
     */
    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inject the application component dependency.
        this.getApplicationComponent().inject(this);
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((TodoApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Activity component.
     *
     * @return {@link ActivityComponent}
     */
    protected ActivityComponent getActivityComponent() {
        this.activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();
        return this.activityComponent;
    }

    /**
     * Add fragments.
     *
     * @param containerViewId
     * @param fragment
     * @param tag
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, tag);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * Get the fragment tag.
     *
     * @param tag
     * @return
     */
    protected Fragment getCurrentFragmentByTag(String tag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        return fragment;
    }
}
