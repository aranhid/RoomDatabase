package com.aranhid.roomdatabase;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {Category.class, Product.class}, version = 2)
public abstract class Database extends RoomDatabase{
    public abstract CategoryInterface categoryInterface();
    public abstract ProductInterface productInterface();

    private static final String DB_NAME = "database.db";
    private static volatile Database INSTANCE = null;

    synchronized static Database get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = create(context, false);
        }
        return INSTANCE;
    }

    public static Database create(Context context, boolean memoryOnly) {
        RoomDatabase.Builder<Database> builder;
        if (memoryOnly) {
            builder = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), Database.class);
        }
        else {
            builder = Room.databaseBuilder(context.getApplicationContext(), Database.class, DB_NAME);
        }
        return builder.addMigrations(MIGRATION_1_2).build();
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE categories ADD COLUMN pictureUrl TEXT");
            database.execSQL("ALTER TABLE products ADD COLUMN pictureUrl TEXT");
        }
    };
}

