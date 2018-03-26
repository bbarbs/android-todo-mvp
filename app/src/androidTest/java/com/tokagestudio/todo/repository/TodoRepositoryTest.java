package com.tokagestudio.todo.repository;


import android.support.test.runner.AndroidJUnit4;

import com.tokagestudio.todo.data.models.Todo;
import com.tokagestudio.todo.data.repository.TodoRepository;
import com.tokagestudio.todo.data.repository.impl.TodoRepositoryImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

import io.reactivex.Maybe;
import io.reactivex.observers.TestObserver;

@RunWith(AndroidJUnit4.class)
public class TodoRepositoryTest {

    TodoRepository todoRepository;

    @Before
    public void setup() {
        this.todoRepository = new TodoRepositoryImpl();
    }

    @Test
    public void testShouldAddTodo() {
        Todo todo = new Todo();
        todo.setId(UUID.randomUUID().toString());
        todo.setTitle("Test");
        TestObserver<Todo> observer = new TestObserver<>();
        Maybe<Todo> res = this.todoRepository.addTodo(todo);
        res.subscribe(observer);
        observer.assertNoErrors();
        observer.assertValue(todo);
        observer.assertValueCount(1);
    }
}
