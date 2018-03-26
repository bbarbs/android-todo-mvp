package com.tokagestudio.todo.data.repository.impl;


import com.tokagestudio.todo.data.models.Todo;
import com.tokagestudio.todo.data.repository.TodoRepository;

import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.realm.Realm;

@Singleton
public class TodoRepositoryImpl implements TodoRepository {

    @Override
    public Maybe<Todo> addTodo(Todo entity) {
        return Maybe.fromCallable(() -> {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(r -> r.copyToRealmOrUpdate(entity));
            realm.close();
            return entity;
        });
    }
}
