package com.tokagestudio.todo.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tokagestudio.todo.R;
import com.tokagestudio.todo.data.models.Todo;
import com.tokagestudio.todo.data.models.TodoFields;
import com.tokagestudio.todo.ui.decorator.DividerItemDecoration;
import com.tokagestudio.todo.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.Sort;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TodoListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TodoListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class TodoListFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    /**
     * Fragment listener.
     */
    private OnFragmentInteractionListener mListener;
    /**
     * Butterknife unbinder.
     */
    private Unbinder unbinder;
    /**
     * Realm.
     */
    private Realm realm;

    public TodoListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TodoListFragment.
     */
    public static TodoListFragment newInstance() {
        TodoListFragment fragment = new TodoListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_todo_list, container, false);
        // Bind fragment.
        this.unbinder = ButterKnife.bind(this, fragmentView);
        // Instantiate realm.
        this.realm = Realm.getDefaultInstance();
        // Set recycler view.
        setupRecyclerView();
        return fragmentView;
    }

    /**
     * Setup recycler view.
     */
    private void setupRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        this.recyclerView.setAdapter(
                new TodoListAdapter(
                        this,
                        realm.where(Todo.class).findAll().sort(TodoFields.TIMESTAMP, Sort.DESCENDING),
                        true)
        );
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), null, false, true));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
        realm.close();
        unbinder.unbind();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

    }
}
