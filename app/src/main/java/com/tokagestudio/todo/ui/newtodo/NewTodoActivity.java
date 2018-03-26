package com.tokagestudio.todo.ui.newtodo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.tokagestudio.todo.R;
import com.tokagestudio.todo.di.HasComponent;
import com.tokagestudio.todo.di.components.ActivityComponent;
import com.tokagestudio.todo.data.models.Todo;
import com.tokagestudio.todo.ui.base.BaseActivity;
import com.tokagestudio.todo.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class NewTodoActivity extends BaseActivity implements NewTodoView, HasComponent<ActivityComponent>,
        NewTodoFragment.OnFragmentInteractionListener {

    @Inject
    NewTodoPresenter newTodoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);
        ButterKnife.bind(this);
        setToolbar();
        setFragmentDisplay();
        injectComponent();
        initPresenter();
    }

    /**
     * Set toolbar.
     */
    private void setToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Fragment display.
     */
    private void setFragmentDisplay() {
        this.addFragment(R.id.fragment_container, new NewTodoFragment(), "NewTodoFragment", false);
    }

    /**
     * Inject component in the activity.
     */
    private void injectComponent() {
        this.getActivityComponent().inject(this);
    }

    /**
     * Initialize presenter.
     */
    private void initPresenter() {
        this.newTodoPresenter.attachView(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Back button.
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public ActivityComponent getComponent() {
        return this.getActivityComponent();
    }

    @Override
    public void onAddTodoListener(Todo todo) {
        this.newTodoPresenter.addTodo(todo);
    }

    @Override
    public void onSuccessAddTodo(Todo todo) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onErrorAddTodo(Throwable e) {
        Toast.makeText(this, getString(R.string.toast_error_add_todo), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.newTodoPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.newTodoPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.newTodoPresenter.onDestroy();
    }
}
