package mx.com.intercam.databaseexample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mx.com.intercam.databaseexample.entity.DocumentEntity;

@Dao
public interface DocumentDao {

    @Query("SELECT * FROM document")
    List<DocumentEntity> getAll();

    @Query("SELECT * FROM document WHERE name LIKE :first LIMIT 1")
    DocumentEntity findByName(String first);

    @Query("SELECT * FROM document WHERE user_id in (:userId)")
    List<DocumentEntity> getAllByUserID(int userId);

    @Insert
    void insertAll(DocumentEntity... documents);

    @Insert
    void insert(DocumentEntity document);

    @Delete
    void delete(DocumentEntity document);
}
