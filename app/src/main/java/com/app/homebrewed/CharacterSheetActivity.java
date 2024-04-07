package com.app.homebrewed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_charactersheet);

            // Grid Modifier Positions (BDY = index 5)
            int[] modifierIndices = {5, 6, 7, 8, 9, 10, 11, 12, 13};

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
    }

}

