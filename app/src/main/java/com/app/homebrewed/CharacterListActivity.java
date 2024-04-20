package com.app.homebrewed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CharacterListActivity extends AppCompatActivity {

    private CharacterDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characterlist);

        // Initialize the database helper
        dbHelper = new CharacterDatabaseHelper(this);

        // Initialize views
        AppCompatImageButton btnCreateChar = findViewById(R.id.btncreatecharacter);
        RecyclerView characterList = findViewById(R.id.character_list);

        // Set layout manager for RecyclerView
        characterList.setLayoutManager(new LinearLayoutManager(this));

        // Set click listener for create character button
        btnCreateChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCharacter(); // Call createCharacter method
            }
        });

        // Load character data from the database
        List<Character> characterData = loadCharacterData();

        // Create the adapter
        CharacterListAdapter adapter = new CharacterListAdapter(characterData);

        // Set the adapter to the RecyclerView
        characterList.setAdapter(adapter);

        // Set click listener for RecyclerView items
        adapter.setOnItemClickListener(new CharacterListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Start the CharacterSheetActivity and send the selected character's position
                Intent intent = new Intent(CharacterListActivity.this, CharacterSheetActivity.class);
                intent.putExtra("selectedCharacterIndex", position);
                startActivity(intent);
            }
        });
    }

    // Method to load characters from the database
    private List<Character> loadCharacterData() {
        return dbHelper.getAllCharacters();
    }

    // Method to start CreateCharacterActivity
    public void createCharacter() {
        Intent intent = new Intent(CharacterListActivity.this, CreateCharacterActivity.class);
        startActivity(intent);
    }
}