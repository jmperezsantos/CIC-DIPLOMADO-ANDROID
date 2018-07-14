package cic.ipn.mx.fragmentsexample.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cic.ipn.mx.fragmentsexample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    private EditText etInput;

    public static FirstFragment newInstance() {

        Bundle args = new Bundle();

        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(
                R.layout.fragment_first,
                container,
                false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.etInput = view.findViewById(R.id.etInput);

        Button btnNavega = view.findViewById(R.id.btnNavega);

        btnNavega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirstFragment.this.navigateToSecondFragment();

            }
        });

    }

    private void navigateToSecondFragment() {

        String mensaje = this.etInput.getText().toString();

        Fragment second = SecondFragment.newInstance(mensaje);

        FragmentManager fragmentManager =
                this.getFragmentManager();

        FragmentTransaction transaction =
                fragmentManager.beginTransaction();

        transaction.replace(R.id.container, second);

        transaction.addToBackStack(null);

        transaction.commit();

    }

}
