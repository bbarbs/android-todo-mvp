package com.tokagestudio.todo.ui.newtodo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tokagestudio.todo.R;
import com.tokagestudio.todo.data.models.Todo;
import com.tokagestudio.todo.ui.base.BaseFragment;

import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewTodoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewTodoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class NewTodoFragment extends BaseFragment {

    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.note)
    EditText note;
    /**
     * Fragment listener.
     */
    private OnFragmentInteractionListener mListener;
    /**
     * Butterknife unbinder.
     */
    private Unbinder unbinder;

    public NewTodoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewTodoFragment.
     */
    public static NewTodoFragment newInstance() {
        NewTodoFragment fragment = new NewTodoFragment();
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
        View fragmentView = inflater.inflate(R.layout.fragment_new_todo, container, false);
        // Bind view.
        this.unbinder = ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    /**
     * Callback when save button is pressed.
     *
     * @param view
     */
    @OnClick(R.id.save_btn)
    void onClickSaveBtn(View view) {
        title.setError(null);
        View focusView;
        String str_title = title.getText().toString();
        if (TextUtils.isEmpty(str_title)) {
            // Focus title line and show message.
            title.setError(getString(R.string.error_field_required));
            focusView = title;
            focusView.requestFocus();
        } else {
            Todo todo = new Todo();
            todo.setId(UUID.randomUUID().toString());
            todo.setTitle(title.getText().toString());
            todo.setNote(note.getText().toString());
            todo.setTimestamp(new Date());
            // Trigger listener.
            this.mListener.onAddTodoListener(todo);
        }
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
        this.unbinder.unbind();
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
        void onAddTodoListener(Todo todo);
    }
}
