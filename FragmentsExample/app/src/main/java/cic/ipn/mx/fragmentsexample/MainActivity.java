package cic.ipn.mx.fragmentsexample;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cic.ipn.mx.fragmentsexample.fragments.FirstFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            FirstFragment fragment =
                    FirstFragment.newInstance();

            FragmentManager fm =
                    this.getSupportFragmentManager();

            FragmentTransaction transaction =
                    fm.beginTransaction();

            transaction.add(R.id.container, fragment);

            transaction.commit();

        }

    }
}
