package com.tokagestudio.todo.ui.newtodo;

import com.tokagestudio.todo.data.models.Todo;
import com.tokagestudio.todo.ui.base.BaseView;

public interface NewTodoView extends BaseView {
    void onSuccessAddTodo(Todo todo);

    void onErrorAddTodo(Throwable e);
}
