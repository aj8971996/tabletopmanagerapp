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

public class CreateCharacterActivity extends AppCompatActivity {
    //Dummy Data for Ability Selection
    //Index | Level | Class | Name | Description | Range | Damage | Effect
    private String[][] abilityData = {

                                                            //Dancer Abilities
            {"1", "1", "Dancer", "Fox Trot",
                    "The Fox Trot is, of course, named for it's magical capabilities to summon mystical foxes to attack an opponent",
                    "A target within 25ft",
                    "3 D4 + MGK Lvl + FLX Lvl",
                    "Your target must roll 1 D20 against your MGK level, on fail - target is burned for 3 turns"},
            {"2", "1", "Dancer", "Shimmy",
                    "Performing the Shimmy is an ancient technique that enhances your other Dancer abilities",
                    "Self-Target",
                    "No Damage",
                    "Add 1 D8 Damage to next 2 attacks - this can not stack."},
            {"3", "1", "Dancer", "Jitterbug",
                    "Performing the Jitterbug requires a partner - getting an enemy as a partner requires you to overpower them. Use your partner as a weapon against a group of targets.",
                    "Partner must be within 15ft, attack affects a group of enemies within a 5ft by 5ft area",
                    "2 D6 + Caster FLX lvl + Partner BDY Lvl",
                    "If partner was overpowered - they are dazed for their next action."},
            {"4", "1", "Dancer", "The Black Bottom",
                    "The Black Bottom is a technique that allows the Dancer to transport a small area to a different targeted area. Beware of Fall Damage.",
                    "Target: A 5ft by 5ft area within 15ft from the Caster | Destination : A 5ft by 5ft area within 20ft from the Target",
                    "No Damage - Fall Damage is calculated as 1 D4 rolled per 10ft drop",
                    "Teleport all within the Target Area to the Destination area. The Destination area spawns at the same elevation level as the Target"},
            {"5", "1", "Dancer", "The Texas Tommy",
                    "Performing The Texas Tommy requires a nearby Ally - though more exhausting on the duo performing this dance, the aftermath is unforgettable",
                    "Requires Partner within 20ft of Caster | Target must be within 20ft of Duo",
                    "5 D10 + (Partner FLX and BDY Lvl) + (Caster FLX and BDY Lvl)",
                    "Both Partner and Caster are exhausted for 1 action after performing"},

                                                            //Magician Abilities
            {"6", "1", "Magician", "Is This Your Card?",
                    "Take out a deck of cards - if you guess your targets card, it'll be a magical explosion! Right in their face!",
                    "Target must be anywhere within 40ft of the Caster",
                    "6 D4 + Caster MGK Lvl",
                    "Target rolls 2 D10 - the result is the Magic Check that the Caster must pass to deal damage"},
            {"7", "1", "Magician", "Monster From Hat",
                    "Roll the dice, and hope that you can pull a strong ally from your hat! Though each ally can offer a variety of benefits...Beware of the rabbit.",
                    "Self-Cast",
                    "No Damage",
                    "Roll 1 D20 - the result of the roll changes the companion that is pulled from the hat"},
            {"8", "1", "Magician", "Magic Wand Weapon",
                    "Focus your magic into your wand to transform it into a more powerful weapon.",
                    "Bladed Weapon : Target within 10ft | Firearm Weapon : Target within 40ft | Magical Focus : Target within 10ft",
                    "Bladed Weapon : 2 D10 + BDY Lvl | Firearm Weapon : 6 D4 + MND Lvl | Magical Focus : 2 D6 + BDY lvl (Add 2 D10 to Ability Damage)",
                    "Roll 1 D6 to change your wand to a weapon : (1 - 2) Blade | (3 - 4) Firearm | (5 - 6) Magical Focus"},
            {"9", "1", "Magician", "Magical Box",
                    "You must persuade a target into the magical box where you will have free reign to use your magical saw on their legs!",
                    "Target must be within 20ft from Caster | To Successfully persuade target - caster must make a Charm Check against target",
                    "3 D10 + Caster MGK Lvl",
                    "On Success - Target within the box is unable to move for their next 2 turns"},
            {"10", "1", "Magician", "Easy Escape",
                    "Use magic to escape anything physical that is preventing your movement. This includes things like handcuffs, and cells with simple locks",
                    "Self Cast or Target within 5ft",
                    "No Damage",
                    "Escape Confinement"},

                                                            //Gambler Abilities
            {"11", "1", "Gambler", "One Armed Bandit",
                    "Your able to pick-pocket an unsuspecting target within your immediate area. Just don't go giving back to the casinos again.Though the slots look fun...",
                    "Target within 5ft : Must make successful Deceit Roll against Target",
                    "If Choosing to Attack instead of Steal : Weapon Damage Applied",
                    "On Success - Get one item that was on the target"},
            {"12", "1", "Gambler", "All In",
                    "Put your safety on the line for this next one, really go all in - not like at those tables where you always had something on the side.",
                    "Self Cast",
                    "No Damage",
                    "Double the damage of next attack : Take 1 D20 Damage if Attack Misses"},
            {"13", "1", "Gambler", "Roll The Dice",
                    "Roll 2 Dice - depending on the results will determine the kind of energy you are able to imbue them with. ",
                    "Target within 15ft",
                    "Roll 2 D6 : (1-4) 1 D4 | (5-10) 2 D6 + Caster CHM Lvl | (11-12) 4 D6 + (Caster CHM and MGK Lvl)",
                    "Roll 2 D6 : (1-4) No Effect : (5-10) Target Loses Next Movement | (11-12) Target is Paralyzed for 2 Turns"},
            {"14", "1", "Gambler", "Yahtzee!",
                    "Roll 4 Dice, the more dice that have the same result - the stronger your attack!",
                    "Target within 20ft",
                    "Roll 4 D6 : (No Match) 1 D4 | (2 Match) 2 D6 | (3 match) 4 D6 | (4 Match) 5 D10 + MGK Lvl",
                    "No Effect"},
            {"15", "1", "Gambler", "Poker Face",
                    "By steeling your expression and mind, you are able to remain so still that a mirror image is created using your magical energies.",
                    "Self Cast",
                    "No Damage",
                    "Create a Mirror Image that is destroyed upon taking damage or colliding with an physical object"},

                                                            //Bartender Abilities
            {"16", "1", "Bartender", "Party Tray",
                    "Get together a quick tray for your allies, make sure to put some of their favorite on their.",
                    "Up to 2 Ally's within 20ft of Caster",
                    "No Damage",
                    "Heal up to 2 Ally's for 4 D10 + MGK Lvl"},
            {"17", "1", "Bartender", "Bar Prep",
                    "You sacrifice some time now to get the bar ingredients together - but it will be worth it.",
                    "Self Cast",
                    "No Damage",
                    "Pass your movement and attack action. For your next 3 turns, you can take 1 additional action of any kind."},
            {"18", "1", "Bartender", "Single Shot",
                    "When someone has had a particularly rough day - it's best to get them their favorite hard liquor.",
                    "Target within 10ft",
                    "If Enemy : 4 D4 + DCT lvl",
                    "If Ally : Heal 5 D10 + CHM Lvl"},
            {"19", "1", "Bartender", "Broken Glass",
                    "Broken glass is usually a huge issue - it's time to make it a huge issue for the enemy.",
                    "Target 5ft by 10ft area within 15ft of the Caster",
                    "Anyone Who Passes Area : 3 D8",
                    "Glass remains on area until cleared"},
            {"20", "1", "Bartender", "Magical Molotov",
                    "Imbue magical energies that ignite a bottle on hand. The effects of this depend on the alcohol on hand.",
                    "Area of 10ft by 10ft within 15ft of the caster",
                    "3 D12 + CHM Lvl",
                    "(Vodka) Exhaust Target | (Rum) Paralyze Target | (Whiskey) Burn Target"},

                                                            //Mobster Abilities
            {"21", "1", "Mobster", "Boisterous Charge",
                    "Wildly charge towards your target - intimidating them by your size and speed.",
                    "Target within 15ft from caster",
                    "5 D4 + BDY Lvl + FLX Lvl",
                    "Overpower Target"},
            {"22", "1", "Mobster", "Intimidate Target",
                    "Whether it's a mean snarl, some curt words or something else you deem intimidating without harm.",
                    "Target within 10ft",
                    "No Damage",
                    "Overpower Target | Target can not target you for their next attack action."},
            {"23", "1", "Mobster", "Assert Dominance",
                    "When you're up close nad personal with the target - it's easy to see why you've been picked for this job.",
                    "Target within 5ft",
                    "4 D8 + BDY Lvl",
                    "Grapple Target that is Overpowered"},
            {"24", "1", "Mobster", "Pretty Em' Up",
                    "The boss wants to make sure they are nice and pretty for their meeting with the mark.",
                    "Target within 5ft",
                    "2 D20 + BDY Lvl",
                    "If Target is Overpowered - Add 1 D10 Damage"},
            {"25", "1", "Mobster", "Heavy Assault",
                    "You have been hitting the gym hard lately so that you could carry the big guns...literally, you can carry two big guns now. Both can fire at once!",
                    "Passive : Self",
                    "No Damage",
                    "Caster can use 2 Heavy Assault weapons"},

                                                            //Manager Abilities
            {"26", "1", "Manager", "Rally Staff",
                    "A good leader should always be able to rally the troops right? Well so should a good manager!",
                    "Allies within 10ft by 10ft area around the caster",
                    "No Damage",
                    "All Allies within Target Area gain a 2 D20 Shield"},
            {"27", "1", "Manager", "86 Em'",
                    "Sometimes there are customers who just can't handle themselves, and you need to get a bit rough.",
                    "Target within 15ft",
                    "5 D8 + (Caster BSN and BDY Lvl)",
                    "Overpower Target"},
            {"28", "1", "Manager", "Close Shop",
                    "Closing time, open up the doors and let you all out into the world.",
                    "Target 10ft by 10ft area within 15 ft of caster",
                    "No Damage",
                    "Prevent Targets From Leaving Closed Area"},
            {"29", "1", "Manager", "Entice Employee",
                    "They say that money is the best motivator - but sometimes juicing your staff up with magical energy for their next offensive is just as good.",
                    "Target Ally within 20ft",
                    "No Damage",
                    "Target Ally adds 2 D10 to their next attack."},
            {"30", "1", "Manager", "Bonus Time",
                    "Usually you save Bonus Time for your wonderful staff - but this time, you're going to give yourself a bit of a bonus.",
                    "Allies within 5ft by 5ft Target Area that is up to 10ft away from the caster",
                    "No Damage",
                    "Sacrifice movement | You can take 1 additional attack action this turn and next"},

                                                            //Construction Abilities
            {"31", "1", "Construction", "Build Barrier v1",
                    "Create something to block incoming attacks.",
                    "Target area of 10ftx10ftx10ft must be within 5ft of Caster",
                    "No Damage",
                    "Create a Barrier that is 10ft x 10ft x 10ft - this takes up your movement and additional action."},
            {"32", "1", "Construction", "Repair v1 Construct",
                    "Things break, but you can fix broken things!",
                    "Construct within 10ft",
                    "No Damage",
                    "Repair a damaged or broken version one construct"},
            {"33", "1", "Construction", "Build Pet v1",
                    "Everyone needs a pet - but you can build a much better pet. Make it stronger, faster!",
                    "Pet Range : Target within 20ft",
                    "Pet Attack : 4 D6 + Caster ACA Lvl",
                    "Create a companion that will join you in battle - this takes up your movement and attack action."},
            {"34", "1", "Construction", "Build Weapon v1",
                    "There is nothing quite like assaulting your enemy with a weapon you made yourself!",
                    "Bladed Weapon : Target within 5ft | Light Firearm : Target within 25ft | Heavy Firearm : Target within 15ft",
                    "Bladed Weapon : 3 D12 + BDY Lvl | Light Firearm : 4 D6 + FLX Lvl | Heavy Firearm : 3 D12 + ACA Lvl",
                    "Create a powerful weapon that you can use until it breaks - this takes up your movement and attack action."},
            {"35", "1", "Construction", "Build Turret v1",
                    "You don't have the time to be watching over someone, telling them who to attack - this thing just attacks what's near it!",
                    "Turret Range : 10ft around Turret",
                    "Turret Attack : 5 D8 + Caster ACA Lvl",
                    "Create a turret that will automatically target nearby visible enemies - this takes up your movement and attack action."},

                                                            //Entrepreneur Abilities
            {"36", "1", "Entrepreneur", "Charm the Customer",
                    "",
                    "",
                    "",
                    ""},
            {"37", "1", "Entrepreneur", "Slashing Prices and Enemies",
                    "",
                    "",
                    "",
                    ""},
            {"38", "1", "Entrepreneur", "We Need New Ideas",
                    "",
                    "",
                    "",
                    ""},
            {"39", "1", "Entrepreneur", "Market Place Presence",
                    "",
                    "",
                    "",
                    ""},
            {"40", "1", "Entrepreneur", "Door to Door Knock Em' Out",
                    "",
                    "",
                    "",
                    ""},

                                                            //Fortune Teller Abilities
            {"41", "1", "Fortune Teller", "Predict Next Action",
                    "",
                    "",
                    "",
                    ""},
            {"42", "1", "Fortune Teller", "Predict Weak Spot",
                    "",
                    "",
                    "",
                    ""},
            {"43", "1", "Fortune Teller", "Summon Spectre",
                    "",
                    "",
                    "",
                    ""},
            {"44", "1", "Fortune Teller", "Other Worldly Assault",
                    "",
                    "",
                    "",
                    ""},
            {"45", "1", "Fortune Teller", "Detect Magic",
                    "",
                    "",
                    "",
                    ""}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 1. Fetch String Array
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);
        String[] speciesArray = getResources().getStringArray(R.array.species);
        String[] classArray = getResources().getStringArray(R.array.classes);
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


        // 2. Find RecyclerView
        RecyclerView speciesRecyclerView = findViewById(R.id.speciesRecyclerView);
        Log.d("CreateCharacterActivity", "speciesRecyclerView: " + speciesRecyclerView);
        RecyclerView classRecyclerView = findViewById(R.id.classRecyclerView);
        Log.d("CreateCharacterActivity", "speciesRecyclerView: " + speciesRecyclerView);

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


        // In your CreateCharacterActivity, after setting the LayoutManager:
        SnapHelper speciesSnapHelper = new LinearSnapHelper();
        speciesSnapHelper.attachToRecyclerView(speciesRecyclerView);
        SnapHelper classSnapHelper = new LinearSnapHelper();
        classSnapHelper.attachToRecyclerView(classRecyclerView);

        // Additional for indicator
        final LinearLayout speciesIndicatorLayout = findViewById(R.id.speciesIndicatorLayout);
        setupIndicatorDots(speciesArray.length, speciesIndicatorLayout);
        final LinearLayout classIndicatorLayout = findViewById(R.id.classIndicatorLayout);
        setupIndicatorDots(classArray.length, classIndicatorLayout);

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

