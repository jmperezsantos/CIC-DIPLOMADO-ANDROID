package mx.com.intercam.databaseexample.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "document",
        foreignKeys = @ForeignKey(entity = UserEntity.class,
                parentColumns = "uid",
                childColumns = "user_id"))
public class DocumentEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "did")
    private int id;

    @ColumnInfo(name = "image_path")
    private String imagePath;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "user_id")
    private int user_id;

    public DocumentEntity() {
    }

    public DocumentEntity(String imagePath, String name, int user_id) {
        this.imagePath = imagePath;
        this.name = name;
        this.user_id = user_id;
    }

    public DocumentEntity(int id, String imagePath, String name, int user_id) {
        this.id = id;
        this.imagePath = imagePath;
        this.name = name;
        this.user_id = user_id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "DocumentEntity{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", name='" + name + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
