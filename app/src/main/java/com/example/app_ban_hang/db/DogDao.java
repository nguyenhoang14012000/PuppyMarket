package com.example.dogstore.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dogstore.model.DogClass;

import java.util.List;

@Dao
public interface DogDao {
    @Query("SELECT * FROM DOGCLASS")
    public List<DogClass> getAllDogs();

    @Insert
    public void insertDog(DogClass... dogBreed);

    @Query("DELETE FROM DogClass")
    public void deleteAll();

    @Query("SELECT * FROM DogClass WHERE Name LIKE '%' || :name || '%'")
    public  List<DogClass> searchByName(String name);
}
