package cic.ipn.mx.cameraexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cic.ipn.mx.cameraexample.fragments.CameraFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            Fragment fragment = CameraFragment.newInstance();

            FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();

            transaction.add(R.id.container, fragment);

            transaction.commit();

        }
    }
}
