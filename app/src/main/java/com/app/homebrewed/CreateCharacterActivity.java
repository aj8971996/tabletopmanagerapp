package com.app.homebrewed;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CreateCharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 1. Fetch String Array
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);
        String[] speciesArray = getResources().getStringArray(R.array.species);
        if (speciesArray == null || speciesArray.length == 0) {
            Log.e("CreateCharacterActivity", "Species array is empty or null!");
            // Handle the error - maybe show a message to the user
            return;
        }

        // 2. Find RecyclerView
        RecyclerView speciesRecyclerView = findViewById(R.id.speciesRecyclerView);
        Log.d("CreateCharacterActivity", "speciesRecyclerView: " + speciesRecyclerView);

        // 3. Create and set Adapter
        SpeciesListAdapter adapter = new SpeciesListAdapter(speciesArray);
        speciesRecyclerView.setAdapter(adapter);

        // 4. Set Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        speciesRecyclerView.setLayoutManager(layoutManager);
    }
}
