package com.app.homebrewed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ClassViewHolder> {

    private String[] classData; // Holds the data for your list

    // Constructor
    public ClassListAdapter(String[] speciesData) {
        this.classData = speciesData;
    }

    // Inner class to define how a single list item looks
    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;

        public ClassViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
        }
    }

    // Required Methods (We'll fill them in)
    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_character_class, parent, false);
        return new ClassViewHolder(listItem); // Correct return statement
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        String currentSpecies = classData[position];
        holder.textView1.setText(currentSpecies);
    }

    @Override
    public int getItemCount() {
        return classData.length;
    }
}
