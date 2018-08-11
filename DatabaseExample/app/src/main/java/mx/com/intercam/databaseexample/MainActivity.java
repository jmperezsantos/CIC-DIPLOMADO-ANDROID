package mx.com.intercam.databaseexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import mx.com.intercam.databaseexample.database.AppDatabase;
import mx.com.intercam.databaseexample.entity.UserEntity;

public class MainActivity extends AppCompatActivity {

    public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;

        setContentView(R.layout.activity_main);

        AppDatabase db = AppDatabase.sharedInstance();

        UserEntity user = new UserEntity("OTRO", "OTRO APP");

        db.userDao().insert(user);

        List<UserEntity> consulted = db.userDao().getAll();

        for (UserEntity u : consulted) {

            Log.d("TAG", u.toString());

        }

    }
}
