package com.tokagestudio.todo.ui.newtodo;

import android.support.annotation.NonNull;

import com.tokagestudio.todo.di.scopes.PerActivity;
import com.tokagestudio.todo.data.models.Todo;
import com.tokagestudio.todo.data.repository.TodoRepository;
import com.tokagestudio.todo.ui.base.BasePresenter;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;


@PerActivity
public class NewTodoPresenter implements BasePresenter<NewTodoView> {

    private TodoRepository todoRepository;
    private NewTodoView newTodoView;
    private CompositeDisposable disposable;

    @Inject
    public NewTodoPresenter(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        this.disposable = new CompositeDisposable();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        if (!disposable.isDisposed()) {
            this.disposable.dispose();
        }
        this.newTodoView = null;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(@NonNull NewTodoView view) {
        this.newTodoView = view;
    }

    public void addTodo(Todo todo) {
        Preconditions.checkNotNull(todo);
        Disposable d = this.todoRepository.addTodo(todo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new AddTodoObserver());
        this.disposable.add(d);
    }

    private class AddTodoObserver extends DisposableMaybeObserver<Todo> {

        @Override
        public void onSuccess(Todo todo) {
            newTodoView.onSuccessAddTodo(todo);
        }

        @Override
        public void onError(Throwable e) {
            newTodoView.onErrorAddTodo(e);
        }

        @Override
        public void onComplete() {

        }
    }
}
