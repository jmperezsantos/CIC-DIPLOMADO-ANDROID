package cic.ipn.mx.agendarestexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import cic.ipn.mx.agendarestexample.fragments.ContactListFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){

            Fragment listFragment= ContactListFragment.newInstance();

            FragmentManager manager = this.getSupportFragmentManager();

            FragmentTransaction transaction = manager.beginTransaction();

            transaction.add(R.id.container, listFragment);

            transaction.commit();

        }

    }
}
