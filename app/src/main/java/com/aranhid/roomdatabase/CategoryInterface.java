package com.aranhid.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryInterface {
    @Query("SELECT * FROM categories ORDER BY _id")
    List<Category> selectAll();

    @Query("SELECT * FROM categories WHERE _id=:id")
    Category findById(int id);

    @Insert
    void insert(Category... items);
}
