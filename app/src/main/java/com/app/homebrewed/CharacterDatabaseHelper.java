package com.app.homebrewed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
                COLUMN_NAME_CHARACTER_MOD_NINE + " INTEGER" + // No comma here
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

    // Inside your CharacterDatabaseHelper class
    public List<Character> getAllCharacters() {
        List<Character> characters = new ArrayList<>();

        // Get a readable database reference
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the query
        String query = "SELECT * FROM Characters"; // Assuming your table is named 'Characters'

        // Execute the query
        Cursor cursor = db.rawQuery(query, null);

        // Iterate through the results and create Character objects
        if (cursor.moveToFirst()) {
            do {
                // Access column values using cursor.get...(column_index)
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_NAME));
                String species = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_SPECIES));
                String characterClass = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_CLASS));
                int level = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_LEVEL));
                int health = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_HEALTH));
                int modOne = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_MOD_ONE));
                int modTwo = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_MOD_TWO));
                int modThree = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_MOD_THREE));
                int modFour = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_MOD_FOUR));
                int modFive = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_MOD_FIVE));
                int modSix = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_MOD_SIX));
                int modSeven = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_MOD_SEVEN));
                int modEight = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_MOD_EIGHT));
                int modNine = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CHARACTER_MOD_NINE));

                // Create a Character object
                Character character = new Character(name, species, level, characterClass);
                character.setHealth(health);
                character.setModBody(modOne);
                character.setModMind(modTwo);
                character.setModFlex(modThree);
                character.setModBusiness(modFour);
                character.setModCharm(modFive);
                character.setModDeceit(modSix);
                character.setModMagic(modSeven);
                character.setModReligion(modEight);
                character.setModAcademics(modNine);

                // Add the character to the list
                characters.add(character);
            } while (cursor.moveToNext());
        }


        cursor.close();  // Close the cursor
        db.close();     // Close the database connection

        return characters;
    }

}
