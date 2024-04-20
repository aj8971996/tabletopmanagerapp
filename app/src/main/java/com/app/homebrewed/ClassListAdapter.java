package com.app.homebrewed;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ClassViewHolder> {

    private String[] classData; // Holds the data for your list
    private int selectedPosition = 0; // Initially, first item is selected
    private OnClassSelectedListener listener;

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

    public String getSelectedClass() {
        if (selectedPosition != -1) {
            return classData[selectedPosition];
        }
        return null; // Or throw an exception if no selection should be possible
    }

    // Required Methods (We'll fill them in)
    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_character_class, parent, false);
        return new ClassViewHolder(listItem); // Correct return statement
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String currentClass = classData[position];
        holder.textView1.setText(currentClass);

        // Selection Logic
        holder.itemView.setSelected(selectedPosition == position); // Highlight if selected
        holder.itemView.setOnClickListener(view -> {
            // Update Selection
            selectedPosition = position;
            notifyDataSetChanged(); // Redraw to show selection changes

            // Notify the listener
            if (listener != null) {
                listener.onClassSelected(classData[position]);
            }
        });
    }

    public interface OnClassSelectedListener {
        void onClassSelected(String selectedClass);
    }

    // Public setter for the listener
    public void setOnClassSelectedListener(OnClassSelectedListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return classData.length;
    }
}
