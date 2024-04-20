package com.app.homebrewed;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CharacterDatabaseHelper extends SQLiteOpenHelper {
    //Declare Database Variables
    private static final String DATABASE_NAME = "Characters";
    private static final int DATABASE_VERSION = 1;
    public static final String COLUMN_NAME_CHARACTER_NAME = "characterName";
    public static final String COLUMN_NAME_CHARACTER_SPECIES = "characterSpecies";
    public static final String COLUMN_NAME_CHARACTER_CLASS = "characterClass";
    public static final String COLUMN_NAME_CHARACTER_LEVEL = "characterLevel";
    public static final String COLUMN_NAME_CHARACTER_HEALTH = "characterHealth";
    public static final String COLUMN_NAME_CHARACTER_MOD_ONE = "characterModOne";
    public static final String COLUMN_NAME_CHARACTER_MOD_TWO = "characterModTwo";
    public static final String COLUMN_NAME_CHARACTER_MOD_THREE = "characterModThree";
    public static final String COLUMN_NAME_CHARACTER_MOD_FOUR = "characterModFour";
    public static final String COLUMN_NAME_CHARACTER_MOD_FIVE = "characterModFive";
    public static final String COLUMN_NAME_CHARACTER_MOD_SIX = "characterModSix";
    public static final String COLUMN_NAME_CHARACTER_MOD_SEVEN = "characterModSeven";
    public static final String COLUMN_NAME_CHARACTER_MOD_EIGHT = "characterModEight";
    public static final String COLUMN_NAME_CHARACTER_MOD_NINE = "characterModNine";

    public CharacterDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CHARACTERS_TABLE = "CREATE TABLE Characters (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_CHARACTER_NAME + " TEXT, " +
                COLUMN_NAME_CHARACTER_SPECIES + " TEXT, " +
                COLUMN_NAME_CHARACTER_CLASS + " TEXT, " +
                COLUMN_NAME_CHARACTER_LEVEL + " INTEGER, " +
                COLUMN_NAME_CHARACTER_HEALTH + " INTEGER, " +
                COLUMN_NAME_CHARACTER_MOD_ONE + " INTEGER, " +
                COLUMN_NAME_CHARACTER_MOD_TWO + " INTEGER, " +
                COLUMN_NAME_CHARACTER_MOD_THREE + " INTEGER, " +
                COLUMN_NAME_CHARACTER_MOD_FOUR + " INTEGER, " +
                COLUMN_NAME_CHARACTER_MOD_FIVE + " INTEGER, " +
                COLUMN_NAME_CHARACTER_MOD_SIX + " INTEGER, " +
                COLUMN_NAME_CHARACTER_MOD_SEVEN + " INTEGER, " +
                COLUMN_NAME_CHARACTER_MOD_EIGHT + " INTEGER, " +
                COLUMN_NAME_CHARACTER_MOD_NINE + " INTEGER, " +
                ");";
        db.execSQL(CREATE_CHARACTERS_TABLE);
    }

    public void insertCharacter(Character character) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CHARACTER_NAME, character.getName());
        values.put(COLUMN_NAME_CHARACTER_SPECIES, character.getSpecies());
        values.put(COLUMN_NAME_CHARACTER_CLASS, character.getCharacterClass());
        values.put(COLUMN_NAME_CHARACTER_LEVEL, character.getLevel());
        values.put(COLUMN_NAME_CHARACTER_HEALTH, character.getHealth());
        values.put(COLUMN_NAME_CHARACTER_MOD_ONE, character.getBody());
        values.put(COLUMN_NAME_CHARACTER_MOD_TWO, character.getMind());
        values.put(COLUMN_NAME_CHARACTER_MOD_THREE, character.getFlex());
        values.put(COLUMN_NAME_CHARACTER_MOD_FOUR, character.getBusiness());
        values.put(COLUMN_NAME_CHARACTER_MOD_FIVE, character.getCharm());
        values.put(COLUMN_NAME_CHARACTER_MOD_SIX, character.getDeceit());
        values.put(COLUMN_NAME_CHARACTER_MOD_SEVEN, character.getMagic());
        values.put(COLUMN_NAME_CHARACTER_MOD_EIGHT, character.getReligion());
        values.put(COLUMN_NAME_CHARACTER_MOD_NINE, character.getAcademics());

        db.insert("Characters", null, values);
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
