package com.app.homebrewed;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.ArrayList;
import java.util.List;

public class CreateCharacterActivity extends AppCompatActivity {

    private String selectedClass;
    private List<String> excludedAbilities = new ArrayList<>();
    private AbilityListAdapter abilityListAdapterOne;
    private AbilityListAdapter abilityListAdapterTwo;
    private AbilityListAdapter abilityListAdapterThree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);
        Log.d("TEST", "onCreate started");

        // Fetch String Array
        String[] speciesArray = getResources().getStringArray(R.array.species);
        String[] classArray = getResources().getStringArray(R.array.classes);

        // Error handling for empty or null arrays
        if (speciesArray == null || speciesArray.length == 0) {
            Log.e("CreateCharacterActivity", "Species array is empty or null!");
            // Handle the error - maybe show a message to the user
            return;
        }
        if (classArray == null || classArray.length == 0) {
            Log.e("CreateCharacterActivity", "Class array is empty or null!");
            // Handle the error - maybe show a message to the user
            return;
        }

        // Set the listener to receive class selections
        ClassListAdapter classAdapter = new ClassListAdapter(classArray);
        classAdapter.setOnClassSelectedListener(new ClassListAdapter.OnClassSelectedListener() {
            @Override
            public void onClassSelected(String selectedClass) {
                // Call the method to handle class selection
                handleClassSelection(selectedClass);
            }
        });



        // Find RecyclerViews
        RecyclerView speciesRecyclerView = findViewById(R.id.speciesRecyclerView);
        RecyclerView classRecyclerView = findViewById(R.id.classRecyclerView);
        RecyclerView abilitySelectOneRecyclerView = findViewById(R.id.abilitySelectOne);
        RecyclerView abilitySelectTwoRecyclerView = findViewById(R.id.abilitySelectTwo);
        RecyclerView abilitySelectThreeRecyclerView = findViewById(R.id.abilitySelectThree);

        // Create and set Adapters
        SpeciesListAdapter speciesAdapter = new SpeciesListAdapter(speciesArray);
        speciesRecyclerView.setAdapter(speciesAdapter);
        classRecyclerView.setAdapter(classAdapter);
        abilityListAdapterOne = new AbilityListAdapter();
        abilitySelectOneRecyclerView.setAdapter(abilityListAdapterOne);
        abilityListAdapterTwo = new AbilityListAdapter();
        abilitySelectTwoRecyclerView.setAdapter(abilityListAdapterTwo);
        abilityListAdapterThree = new AbilityListAdapter();
        abilitySelectThreeRecyclerView.setAdapter(abilityListAdapterThree);

        EditText edtCharName = findViewById(R.id.edtCharName);
        EditText edtCharHealth = findViewById(R.id.edtCharHealth);
        EditText edtModOne = findViewById(R.id.edtModOne);
        EditText edtModTwo = findViewById(R.id.edtModTwo);
        EditText edtModThree = findViewById(R.id.edtModThree);
        EditText edtModFour = findViewById(R.id.edtModFour);
        EditText edtModFive = findViewById(R.id.edtModFive);
        EditText edtModSix = findViewById(R.id.edtModSix);
        EditText edtModSeven = findViewById(R.id.edtModSeven);
        EditText edtModEight = findViewById(R.id.edtModEight);
        EditText edtModNine = findViewById(R.id.edtModNine);

        String charName = edtCharName.getText().toString();
        String selectedSpecies = speciesAdapter.getSpecies();
        String selectedClass = classAdapter.getSelectedClass();
        int charHealth = Integer.parseInt(edtCharHealth.getText().toString());
        int modOne = Integer.parseInt(edtModOne.getText().toString());
        int modTwo = Integer.parseInt(edtModTwo.getText().toString());
        int modThree = Integer.parseInt(edtModThree.getText().toString());
        int modFour = Integer.parseInt(edtModFour.getText().toString());
        int modFive = Integer.parseInt(edtModFive.getText().toString());
        int modSix = Integer.parseInt(edtModSix.getText().toString());
        int modSeven = Integer.parseInt(edtModSeven.getText().toString());
        int modEight = Integer.parseInt(edtModEight.getText().toString());
        int modNine = Integer.parseInt(edtModNine.getText().toString());

        // Set LayoutManagers
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

        // Attach SnapHelpers
        SnapHelper speciesSnapHelper = new LinearSnapHelper();
        speciesSnapHelper.attachToRecyclerView(speciesRecyclerView);
        SnapHelper classSnapHelper = new LinearSnapHelper();
        classSnapHelper.attachToRecyclerView(classRecyclerView);
        SnapHelper abilitySnapHelperOne = new LinearSnapHelper();
        abilitySnapHelperOne.attachToRecyclerView(abilitySelectOneRecyclerView);
        SnapHelper abilitySnapHelperTwo = new LinearSnapHelper();
        abilitySnapHelperTwo.attachToRecyclerView(abilitySelectTwoRecyclerView);
        SnapHelper abilitySnapHelperThree = new LinearSnapHelper();
        abilitySnapHelperThree.attachToRecyclerView(abilitySelectThreeRecyclerView);

        // Indicator Dot Setup
        final LinearLayout speciesIndicatorLayout = findViewById(R.id.speciesIndicatorLayout);
        setupIndicatorDots(speciesArray.length, speciesIndicatorLayout);
        final LinearLayout classIndicatorLayout = findViewById(R.id.classIndicatorLayout);
        setupIndicatorDots(classArray.length, classIndicatorLayout);

        // Add scroll listeners for RecyclerViews
        speciesRecyclerView.addOnScrollListener(createScrollListener(speciesIndicatorLayout));
        classRecyclerView.addOnScrollListener(createScrollListener(classIndicatorLayout));
    }

    // Event handler for class selection
    // Event handler for class selection
    private void handleClassSelection(String selectedClass) {
        RecyclerView abilitySelectOneRecyclerView = findViewById(R.id.abilitySelectOne);
        this.selectedClass = selectedClass;
        updateAbilitiesForRecyclerView(abilitySelectOneRecyclerView, selectedClass, excludedAbilities);
        Log.d("CLASS_SELECTION", "Selected Class: " + selectedClass);
    }


    // Event handler for ability selection in abilitySelectOneRecyclerView
    // Event handler for ability selection in abilitySelectOneRecyclerView
    public void onAbilitySelectedInListOne(String selectedAbility) {
        RecyclerView abilitySelectOneRecyclerView = findViewById(R.id.abilitySelectOne);
        excludedAbilities.add(selectedAbility);
        updateAbilitiesForRecyclerView(abilitySelectOneRecyclerView, selectedClass, excludedAbilities);
    }

    public void onAbilitySelectedInListTwo(String selectedAbility) {
        RecyclerView abilitySelectTwoRecyclerView = findViewById(R.id.abilitySelectTwo);
        excludedAbilities.add(selectedAbility);
        updateAbilitiesForRecyclerView(abilitySelectTwoRecyclerView, selectedClass, excludedAbilities);
    }

    public void onAbilitySelectedInListThree(String selectedAbility) {
        RecyclerView abilitySelectTwoRecyclerView = findViewById(R.id.abilitySelectThree);
        excludedAbilities.add(selectedAbility);
        updateAbilitiesForRecyclerView(abilitySelectTwoRecyclerView, selectedClass, excludedAbilities);
    }


    private void updateAbilitiesForRecyclerView(RecyclerView recyclerView, String selectedClass, List<String> excludedAbilities) {
        AbilityListAdapter adapter = (AbilityListAdapter) recyclerView.getAdapter();
        if (adapter == null) { // Add a null check!
            Log.e("CreateCharacterActivity", "Adapter for RecyclerView is null");
            return;
        }

        List<Ability> filteredAbilities = adapter.filterClassAbilities(selectedClass, excludedAbilities);
        adapter.updateAbilities(filteredAbilities); // Update RecyclerView with filtered abilities
    }


    // Scroll listener creation
    private RecyclerView.OnScrollListener createScrollListener(final LinearLayout indicatorLayout) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null) {
                    updateIndicatorDots(layoutManager.findFirstVisibleItemPosition(), indicatorLayout);
                }
            }
        };
    }

    // Indicator dot setup
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

    // Update indicator dots
    private void updateIndicatorDots(int position, LinearLayout indicatorLayout) {
        int childCount = indicatorLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView dot = (ImageView) indicatorLayout.getChildAt(i);
            dot.setImageDrawable(getResources().getDrawable(i == position ? R.drawable.selected_dot : R.drawable.unselected_dot));
        }
    }
}