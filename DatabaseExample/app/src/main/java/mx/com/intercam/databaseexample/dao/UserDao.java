package mx.com.intercam.databaseexample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import mx.com.intercam.databaseexample.entity.UserEntity;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<UserEntity> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<UserEntity> loadAllByIds(int[] userIds);

    @Query("select * from user where uid in (:id)")
    UserEntity findById(int id);

    @Insert
    void insertAll(UserEntity... users);

    @Insert
    void insert(UserEntity user);

    @Update
    void update(UserEntity user);

    @Delete
    void delete(UserEntity user);
}