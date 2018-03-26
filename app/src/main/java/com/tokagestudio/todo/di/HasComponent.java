package com.tokagestudio.todo.di;

/**
 * Interface representing a contract for clients that contains a component for dependency injection.
 * It is commonly used to inject component dependency in fragment that is set in activity.
 * <p>
 * Created by bbarbero on 3/24/2018.
 */

public interface HasComponent<C> {
    C getComponent();
}
