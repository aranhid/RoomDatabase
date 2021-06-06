package com.aranhid.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Locale;

@Entity(tableName = "products")
public class Product {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int _id;

    String name;
    int categoty_id;
    String pictureUrl;

    public Product(int _id, String name, int categoty_id, String pictureUrl) {
        this._id = _id;
        this.name = name;
        this.categoty_id = categoty_id;
        this.pictureUrl = pictureUrl;
    }

    @Ignore
    public  Product (String name, int categoty_id, String pictureUrl) {
        this.name = name;
        this.categoty_id = categoty_id;
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "id: %d, name: %s, category_id: %d", _id, name, categoty_id);
    }
}
