package com.app.homebrewed;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AbilityDatabaseHelper extends SQLiteOpenHelper {
    //Declare Database Variables
    private static final String DATABASE_NAME = "Abilities";
    private static final int DATABASE_VERSION = 1;

    public AbilityDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
