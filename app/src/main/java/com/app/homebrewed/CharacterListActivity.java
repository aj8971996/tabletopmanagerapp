package com.app.homebrewed;

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

        RecyclerView characterList = (RecyclerView) findViewById(R.id.character_list);
        characterList.setLayoutManager(new LinearLayoutManager(this));
        characterList.setAdapter(new CharacterListAdapter(dummyData));

    }
}