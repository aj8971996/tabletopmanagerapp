package com.app.homebrewed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpeciesListAdapter extends RecyclerView.Adapter<SpeciesListAdapter.SpeciesViewHolder> {

    private String[] speciesData; // Holds the data for your list

    // Constructor
    public SpeciesListAdapter(String[] speciesData) {
        this.speciesData = speciesData;
    }

    // Inner class to define how a single list item looks
    public static class SpeciesViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;

        public SpeciesViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
        }
    }

    public String getSpecies() {
        if (selectedPosition != -1) {
            return speciesData[selectedPosition];
        } else {
            return null; // Or maybe a default value or throw an exception
        }
    }

    // Required Methods (We'll fill them in)
    @NonNull
    @Override
    public SpeciesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_character_species, parent, false);
        return new SpeciesViewHolder(listItem); // Correct return statement
    }
    private int selectedPosition = -1;
    @Override
    public void onBindViewHolder(@NonNull SpeciesViewHolder holder, int position) {
        String currentSpecies = speciesData[position];
        holder.textView1.setText(currentSpecies);

        // Highlight if selected
        holder.itemView.setSelected(selectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return speciesData.length;
    }
}
