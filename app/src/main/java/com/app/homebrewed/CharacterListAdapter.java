package com.app.homebrewed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder> {

    private String[][] data;

    public CharacterListAdapter(String[][] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new CharacterViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        String characterName = data[position][0];
        String characterType = data[position][1];
        String characterLevel = data[position][2];

        holder.nameTextView.setText(characterName);
        holder.typeTextView.setText(characterType);
        holder.levelTextView.setText(characterLevel);
        // ...
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView typeTextView;
        TextView levelTextView;

        CharacterViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.character_name);
            typeTextView = itemView.findViewById(R.id.character_type);
            levelTextView = itemView.findViewById(R.id.character_level);
            // ... (find other TextViews if you have more)
        }
    }
}
