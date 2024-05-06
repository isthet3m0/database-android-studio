package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class AdminOpenHelper extends SQLiteOpenHelper {
    public AdminOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario(id INTERGER PRIMARY KEY, user TEXT, pass TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS usuario");
        db.execSQL("CREATE TABLE usuario(id INTERGER PRIMARY KEY, user TEXT, pass TEXT)");
    }

}
