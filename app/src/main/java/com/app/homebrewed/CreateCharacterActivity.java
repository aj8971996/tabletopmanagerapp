package com.app.homebrewed;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class CreateCharacterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 1. Fetch String Array
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);
        String[] speciesArray = getResources().getStringArray(R.array.species);
        String[] classArray = getResources().getStringArray(R.array.classes);



        // 2. Find RecyclerView
        RecyclerView speciesRecyclerView = findViewById(R.id.speciesRecyclerView);
        Log.d("CreateCharacterActivity", "speciesRecyclerView: " + speciesRecyclerView);
        RecyclerView classRecyclerView = findViewById(R.id.classRecyclerView);
        Log.d("CreateCharacterActivity", "classRecyclerView: " + classRecyclerView);
        RecyclerView abilitySelectOneRecyclerView = findViewById(R.id.abilitySelectOne);
        Log.d("CreateCharacterActivity", "abilitySelectOneRecyclerView: " + abilitySelectOneRecyclerView);
        RecyclerView abilitySelectTwoRecyclerView = findViewById(R.id.abilitySelectTwo);
        Log.d("CreateCharacterActivity", "abilitySelectTwoRecyclerView: " + abilitySelectTwoRecyclerView);
        RecyclerView abilitySelectThreeRecyclerView = findViewById(R.id.abilitySelectThree);
        Log.d("CreateCharacterActivity", "abilitySelectThreeRecyclerView: " + abilitySelectThreeRecyclerView);


        // 3. Create and set Adapter
        SpeciesListAdapter species_adapter = new SpeciesListAdapter(speciesArray);
        speciesRecyclerView.setAdapter(species_adapter);
        ClassListAdapter class_adapter = new ClassListAdapter(classArray);
        classRecyclerView.setAdapter(class_adapter);

        // Separate LayoutManagers for each RecyclerView
        LinearLayoutManager speciesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        speciesRecyclerView.setLayoutManager(speciesLayoutManager);
        LinearLayoutManager classLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        classRecyclerView.setLayoutManager(classLayoutManager);
        LinearLayoutManager abilityLayoutManagerOne = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        abilitySelectOneRecyclerView.setLayoutManager(abilityLayoutManagerOne);
        LinearLayoutManager abilityLayoutManagerTwo = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        abilitySelectTwoRecyclerView.setLayoutManager(abilityLayoutManagerTwo);
        LinearLayoutManager abilityLayoutManagerThree = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        abilitySelectThreeRecyclerView.setLayoutManager(abilityLayoutManagerThree);

        SnapHelper speciesSnapHelper = new LinearSnapHelper();
        speciesSnapHelper.attachToRecyclerView(speciesRecyclerView);
        SnapHelper classSnapHelper = new LinearSnapHelper();
        classSnapHelper.attachToRecyclerView(classRecyclerView);

        // References to your AbilityDataHelper (assuming you have one)
        AbilityListAdapter abilityDataHelper = new AbilityListAdapter();

        // Inside the listener where selectedClass changes:
        String selectedClass = class_adapter.getSelectedClass(); // Get selected class
        List<Ability> allAbilities = abilityDataHelper.filterClassAbilities(selectedClass, new ArrayList<>());

        // Filter for Ability One
        List<Ability> filteredAbilitiesOne = new ArrayList<>(allAbilities); // Copy the list

        // Filter for Ability Two
        List<Ability> filteredAbilitiesTwo = new ArrayList<>(allAbilities);
        String selectedAbilityOne = ""; // Get this from abilitySelectOneRecyclerView
        if (!selectedAbilityOne.isEmpty()) {
            filteredAbilitiesTwo = abilityDataHelper.filterAbilities(selectedClass, selectedAbilityOne);
        }

        // Filter for Ability Three
        List<Ability> filteredAbilitiesThree = new ArrayList<>(allAbilities);
        String selectedAbilityTwo = ""; // Get this from abilitySelectTwoRecyclerView

        if (!selectedAbilityTwo.isEmpty()) {
            filteredAbilitiesThree = abilityDataHelper.filterAbilities(selectedClass, selectedAbilityTwo);
        }

        if (speciesArray == null || speciesArray.length == 0) {
            Log.e("CreateCharacterActivity", "Species array is empty or null!");
            // Handle the error - maybe show a message to the user
            return;
        }
        if (classArray == null || classArray.length == 0) {
            Log.e("CreateCharacterActivity", "Species array is empty or null!");
            // Handle the error - maybe show a message to the user
            return;
        }

        // Indicator Dot Setup
        final LinearLayout speciesIndicatorLayout = findViewById(R.id.speciesIndicatorLayout);
        setupIndicatorDots(speciesArray.length, speciesIndicatorLayout);
        final LinearLayout classIndicatorLayout = findViewById(R.id.classIndicatorLayout);
        setupIndicatorDots(classArray.length, classIndicatorLayout);
        final LinearLayout abilityOneIndicatorLayout = findViewById(R.id.abilityOneIndicatorLayout);
        if (filteredAbilitiesOne.size() > 0) { // Check if the list is not empty
            setupIndicatorDots(filteredAbilitiesOne.size(), abilityOneIndicatorLayout);
        }
        final LinearLayout abilityTwoIndicatorLayout = findViewById(R.id.abilityTwoIndicatorLayout);
        if (filteredAbilitiesTwo.size() > 0) { // Check if the list is not empty
            setupIndicatorDots(filteredAbilitiesTwo.size(), abilityTwoIndicatorLayout);
        }
        final LinearLayout abilityThreeIndicatorLayout = findViewById(R.id.abilityThreeIndicatorLayout);
        if (filteredAbilitiesThree.size() > 0) { // Check if the list is not empty
            setupIndicatorDots(filteredAbilitiesThree.size(), abilityThreeIndicatorLayout);
        }

        speciesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                updateIndicatorDots(layoutManager.findFirstVisibleItemPosition(), speciesIndicatorLayout);
            }
        });

        classRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                updateIndicatorDots(layoutManager.findFirstVisibleItemPosition(), classIndicatorLayout);
            }
        });
    }

    private void setupIndicatorDots(int count, LinearLayout indicatorLayout) {
        ImageView[] dots = new ImageView[count];
        for (int i = 0; i < count; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.unselected_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);

            indicatorLayout.addView(dots[i], params);
        }

        // Initially highlight the first dot
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selected_dot));
    }

    private void updateIndicatorDots(int position, LinearLayout indicatorLayout) {
        int childCount = indicatorLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView dot = (ImageView) indicatorLayout.getChildAt(i);
            dot.setImageDrawable(getResources().getDrawable(i == position ? R.drawable.selected_dot : R.drawable.unselected_dot));
        }
    }
}

