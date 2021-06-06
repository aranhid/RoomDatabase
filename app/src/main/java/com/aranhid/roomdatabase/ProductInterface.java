package com.aranhid.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductInterface {
    @Query("SELECT * FROM products ORDER BY _id")
    List<Product> selectAll();

    @Query("SELECT * FROM products WHERE _id=:id")
    Product findById(int id);

    @Query("SELECT * FROM products WHERE categoty_id=:category_id")
    List<Product> findByCategory(int category_id);

    @Insert
    void insert(Product... items);
}
