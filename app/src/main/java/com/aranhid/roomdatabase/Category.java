package com.aranhid.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Locale;

@Entity(tableName = "categories")
public class Category {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int _id;

    String name;

    public Category(int _id, String name) {
        this._id = _id;
        this.name = name;
    }

    @Ignore
    public  Category (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "id: %d, name: %s", _id, name);
    }
}
