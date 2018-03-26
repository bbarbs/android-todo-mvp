package com.tokagestudio.todo.ui.main;


import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tokagestudio.todo.R;
import com.tokagestudio.todo.data.models.Todo;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class TodoListAdapter extends RealmRecyclerViewAdapter<Todo, TodoListAdapter.TodoListViewHolder> {

    private final TodoListFragment fragment;

    public TodoListAdapter(TodoListFragment fragment, @Nullable OrderedRealmCollection<Todo> data, boolean autoUpdate) {
        super(data, autoUpdate);
        this.fragment = fragment;
    }

    @Override
    public TodoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item, parent, false);
        return new TodoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoListViewHolder holder, int position) {
        Todo todo = getData().get(position);
        holder.title.setText(todo.getTitle());
        holder.note.setText(todo.getNote());
    }

    class TodoListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.note)
        TextView note;

        public TodoListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
