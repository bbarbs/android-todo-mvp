package com.tokagestudio.todo.data.repository;


import com.tokagestudio.todo.data.models.Todo;

import io.reactivex.Maybe;

public interface TodoRepository {

    /**
     * Add new item.
     *
     * @param entity
     * @return
     */
    Maybe<Todo> addTodo(Todo entity);
}
