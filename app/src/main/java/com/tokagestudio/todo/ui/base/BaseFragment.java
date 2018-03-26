package com.tokagestudio.todo.ui.base;

import android.support.v4.app.Fragment;

import com.tokagestudio.todo.di.HasComponent;
import com.tokagestudio.todo.data.prefs.PreferencesHelper;

import javax.inject.Inject;

/**
 * Base fragment which can be extended to other fragments.
 * <p>
 * Created by bbarbero on 3/25/2018.
 */

public abstract class BaseFragment extends Fragment {

    @Inject
    public PreferencesHelper preferencesHelper;

    /**
     * Gets a component for dependency injection.
     *
     * @param componentType
     * @param <C>
     * @return component
     */
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent) getActivity()).getComponent());
    }
}
