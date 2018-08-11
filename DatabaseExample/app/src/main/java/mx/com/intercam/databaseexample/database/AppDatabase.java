package mx.com.intercam.databaseexample.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import mx.com.intercam.databaseexample.MainActivity;
import mx.com.intercam.databaseexample.dao.DocumentDao;
import mx.com.intercam.databaseexample.dao.UserDao;
import mx.com.intercam.databaseexample.entity.DocumentEntity;
import mx.com.intercam.databaseexample.entity.UserEntity;

@Database(entities = {UserEntity.class, DocumentEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract DocumentDao documentDao();

    private static AppDatabase instance;

    public static AppDatabase sharedInstance() {

        if (instance == null) {

            instance = Room.databaseBuilder(
                    MainActivity.instance.getApplicationContext(),
                    AppDatabase.class,
                    "database-name").allowMainThreadQueries().build();
        }

        return instance;

    }
}
