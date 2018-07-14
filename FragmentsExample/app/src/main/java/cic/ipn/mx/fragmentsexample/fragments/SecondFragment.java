package cic.ipn.mx.fragmentsexample.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cic.ipn.mx.fragmentsexample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private String message;

    public static SecondFragment newInstance(String message) {

        Bundle args = new Bundle();
        args.putString("MESSAGE", message);

        SecondFragment fragment = new SecondFragment();

        fragment.setArguments(args);

        return fragment;

    }

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments= this.getArguments();
        if(arguments != null){

            this.message = arguments.getString("MESSAGE");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(this.message);

    }
}
