package com.tokagestudio.todo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tokagestudio.todo.R;
import com.tokagestudio.todo.di.HasComponent;
import com.tokagestudio.todo.di.components.ActivityComponent;
import com.tokagestudio.todo.ui.newtodo.NewTodoActivity;
import com.tokagestudio.todo.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements HasComponent<ActivityComponent>,
        TodoListFragment.OnFragmentInteractionListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        setFragmentDisplay();
    }

    /**
     * Set toolbar.
     */
    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    /**
     * Fragment to display.
     */
    private void setFragmentDisplay() {
        this.addFragment(R.id.fragment_container, new TodoListFragment(), "TodoListFragment", false);
    }

    /**
     * Callback when floating button is pressed.
     */
    @OnClick(R.id.fab)
    void onClickFabButton() {
        startActivity(new Intent(this, NewTodoActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //return true;
        //}
        return super.onOptionsItemSelected(item);
    }

    @Override
    public ActivityComponent getComponent() {
        return this.getActivityComponent();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
