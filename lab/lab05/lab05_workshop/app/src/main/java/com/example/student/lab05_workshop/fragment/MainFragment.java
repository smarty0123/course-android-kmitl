package com.example.student.lab05_workshop.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.student.lab05_workshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    String message = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = getArguments().getString("message");
    }
    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String s) {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        args.putString("message", s);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView fragmentTextView = (TextView) rootView.findViewById(R.id.fragmentTextView);
        fragmentTextView.setText(message);
        if(!message.isEmpty()){
            fragmentTextView.setText(message);
        }

        return rootView;

    }

}
