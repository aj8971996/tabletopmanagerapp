package com.app.homebrewed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class CharacterListActivity extends AppCompatActivity {

    private String[][] dummyData = {
            {"Bugsy Siegal", "Magical Being", "8"},
            {"Elvis Presley", "Human Being", "6"},
            {"Ebbers Caulklin", "Magical Being", "3"},
            {"Brandon Pinkowski", "Human Being", "5"},
            {"Oscar Meyer", "Magical Being", "4"},
            {"Trucker Washington", "Human Being", "4"},
            {"Sibillye Durn", "Human Being", "6"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characterlist);

        // Initialize the RecyclerView (pre-existing)
        RecyclerView characterList = (RecyclerView) findViewById(R.id.character_list);
        characterList.setLayoutManager(new LinearLayoutManager(this));

        // Create the OnItemClickListener
        CharacterListAdapter.OnItemClickListener listener = new CharacterListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Start the CharacterSheetActivity and send the selected character's position
                Intent intent = new Intent(CharacterListActivity.this, CharacterSheetActivity.class);
                intent.putExtra("selectedCharacterIndex", position);
                startActivity(intent);
            }
        };

        // Create the adapter
        CharacterListAdapter adapter = new CharacterListAdapter(dummyData);

        // Set the adapter to the RecyclerView
        characterList.setAdapter(adapter);

        // Set the listener on the adapter
        adapter.setListener(listener);
    }
}