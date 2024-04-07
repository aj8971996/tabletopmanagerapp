package com.app.homebrewed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CharacterSheetActivity extends AppCompatActivity {

    private String[][] dummyData = {
            {"Bugsy Siegal", "Magical Being", "8", "Mobster", "100", "30", "10", "20", "20", "30", "40", "50", "10", "10"},
            {"Elvis Presley", "Human Being", "6", "Entertainer", "100", "20", "10", "30", "30", "50", "20", "20", "10", "10"},
            {"Ebbers Caulklin", "Magical Being", "3", "Manager", "100", "20", "10", "30", "30", "30", "40", "50", "10", "10"},
            {"Brandon Pinkowski", "Human Being", "5", "Security", "100", "40", "10", "30", "30", "50", "20", "20", "10", "10"},
            {"Oscar Meyer", "Magical Being", "4", "Entrepreneur", "100", "10", "30", "30", "50", "20", "20", "10", "10", "50"},
            {"Trucker Washington", "Human Being", "4", "Security", "100", "10", "30", "30", "50", "20", "20", "10", "10", "10"},
            {"Sibillye Durn", "Human Being", "6", "Gambler", "100", "40", "10", "30", "30", "50", "20", "20", "10", "10"}
    };

    // Grid Modifier Positions (BDY = index 5)
    int[] modifierIndices = {5, 6, 7, 8, 9, 10, 11, 12, 13};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charactersheet);

        // Obtain the selected character index
        Intent intent = getIntent();
        int selectedCharacterIndex = intent.getIntExtra("selectedCharacterIndex", 0);

        // Get references to the TextViews
        TextView levelValue = findViewById(R.id.level_value);
        TextView classValue = findViewById(R.id.class_value);
        TextView nameValue = findViewById(R.id.name_value);
        TextView speciesValue = findViewById(R.id.species_value);
        TextView healthValue = findViewById(R.id.health_value);

        // Load character data
        List<Character> characters = loadCharacterData();
        Character selectedCharacter = characters.get(selectedCharacterIndex);

        // Populate fields (Level, Class, Name, etc.)
        levelValue.setText(String.valueOf(selectedCharacter.getLevel()));
        classValue.setText(selectedCharacter.getCharacterClass());
        nameValue.setText(selectedCharacter.getName());
        speciesValue.setText(selectedCharacter.getSpecies());
        healthValue.setText(String.valueOf(selectedCharacter.getHealth()));

        // Populate Modifiers
        GridLayout modifierMatrix = findViewById(R.id.modifier_matrix);

        int[] modifierValues = {
                Integer.parseInt(dummyData[selectedCharacterIndex][5]), // BDY
                Integer.parseInt(dummyData[selectedCharacterIndex][6]), // MND
                Integer.parseInt(dummyData[selectedCharacterIndex][7]), // FLX
                Integer.parseInt(dummyData[selectedCharacterIndex][8]), // BSN
                Integer.parseInt(dummyData[selectedCharacterIndex][9]), // CHM
                Integer.parseInt(dummyData[selectedCharacterIndex][10]), // DCT
                Integer.parseInt(dummyData[selectedCharacterIndex][11]), // MGK
                Integer.parseInt(dummyData[selectedCharacterIndex][12]), // RGN
                Integer.parseInt(dummyData[selectedCharacterIndex][13])  // ACA
                // ... add other modifiers using your modifierIndices array
        };

        for (int i = 0; i < modifierMatrix.getChildCount(); i++) {
            View modifierItem = modifierMatrix.getChildAt(i);
            TextView modifierLevelValue = modifierItem.findViewById(R.id.modifier_level_value);

            if (i < modifierValues.length) {
                modifierLevelValue.setText(String.valueOf(modifierValues[i]));
            } else {
                // Handle cases where you have more grid items than modifiers
            }
        }
    }

    private List<Character> loadCharacterData() {
        List<Character> characters = new ArrayList<>();

        // Start from index 1 to skip the header row
        for (int i = 1; i < dummyData.length; i++) {
            Character character = new Character();
            character.setName(dummyData[i][0]);
            character.setSpecies(dummyData[i][1]);
            character.setLevel(Integer.parseInt(dummyData[i][2]));
            character.setCharacterClass(dummyData[i][3]);
            character.setHealth(Integer.parseInt(dummyData[i][4]));
            character.setModBody(Integer.parseInt(dummyData[i][5]));
            character.setModMind(Integer.parseInt(dummyData[i][6]));
            character.setModFlex(Integer.parseInt(dummyData[i][7]));
            character.setModBusiness(Integer.parseInt(dummyData[i][8]));
            character.setModCharm(Integer.parseInt(dummyData[i][9]));
            character.setModDeceit(Integer.parseInt(dummyData[i][10]));
            character.setModMagic(Integer.parseInt(dummyData[i][11]));
            character.setModReligion(Integer.parseInt(dummyData[i][12]));
            character.setModAcademics(Integer.parseInt(dummyData[i][13]));

            characters.add(character);
        }

        return characters;
    }
}