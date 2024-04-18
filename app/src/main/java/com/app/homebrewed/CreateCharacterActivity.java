package com.app.homebrewed;

import android.os.Bundle;
import android.util.Log;
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

        // Find RecyclerViews
        RecyclerView speciesRecyclerView = findViewById(R.id.speciesRecyclerView);
        RecyclerView classRecyclerView = findViewById(R.id.classRecyclerView);
        RecyclerView abilitySelectOneRecyclerView = findViewById(R.id.abilitySelectOne);
        RecyclerView abilitySelectTwoRecyclerView = findViewById(R.id.abilitySelectTwo);
        RecyclerView abilitySelectThreeRecyclerView = findViewById(R.id.abilitySelectThree);

        // Create and set Adapters
        SpeciesListAdapter speciesAdapter = new SpeciesListAdapter(speciesArray);
        speciesRecyclerView.setAdapter(speciesAdapter);
        ClassListAdapter classAdapter = new ClassListAdapter(classArray);
        classRecyclerView.setAdapter(classAdapter);
        abilityListAdapterOne = new AbilityListAdapter();
        abilitySelectOneRecyclerView.setAdapter(abilityListAdapterOne);
        abilityListAdapterTwo = new AbilityListAdapter();
        abilitySelectTwoRecyclerView.setAdapter(abilityListAdapterTwo);
        abilityListAdapterThree = new AbilityListAdapter();
        abilitySelectThreeRecyclerView.setAdapter(abilityListAdapterThree);

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
    public void onClassSelected(String selectedClass) {
        this.selectedClass = selectedClass;
        updateAbilitiesForRecyclerViewOne();
    }

    // Event handler for ability selection in abilitySelectOneRecyclerView
    public void onAbilitySelectedInListOne(String selectedAbility) {
        excludedAbilities.add(selectedAbility);
        updateAbilitiesForRecyclerViewTwo();
    }

    // Similarly for ability selection in abilitySelectTwoRecyclerView
    public void onAbilitySelectedInListTwo(String selectedAbility) {
        excludedAbilities.add(selectedAbility);
        updateAbilitiesForRecyclerViewThree();
    }

    private void updateAbilitiesForRecyclerViewOne() {
        List<Ability> classOneAbilities = abilityListAdapterOne.filterClassAbilities(selectedClass, excludedAbilities);
        abilityListAdapterOne.allAbilities = classOneAbilities; // Update the adapter's data
        abilityListAdapterOne.notifyDataSetChanged();
    }

    private void updateAbilitiesForRecyclerViewTwo() {
        List<Ability> classTwoAbilities = abilityListAdapterTwo.filterClassAbilities(selectedClass, excludedAbilities); // Filter based on class two adapter
        abilityListAdapterTwo.allAbilities = classTwoAbilities;
        abilityListAdapterTwo.notifyDataSetChanged();
    }

    private void updateAbilitiesForRecyclerViewThree() {
        List<Ability> classThreeAbilities = abilityListAdapterThree.filterClassAbilities(selectedClass, excludedAbilities); // Filter based on class three adapter
        abilityListAdapterThree.allAbilities = classThreeAbilities;
        abilityListAdapterThree.notifyDataSetChanged();
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