package com.app.homebrewed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder> {

    private List<Character> data;
    // Inside your CharacterListAdapter class
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public CharacterListAdapter(List<Character> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_saved_characters, parent, false); // Use your list item layout
        return new CharacterViewHolder(listItem);
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView typeTextView;
        TextView levelTextView;

        CharacterViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.character_name);
            typeTextView = itemView.findViewById(R.id.character_class);
            levelTextView = itemView.findViewById(R.id.character_level);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = data.get(position);

        String characterName = character.getName();
        String characterType = character.getCharacterClass();
        int characterLevel = character.getLevel();

        holder.nameTextView.setText(characterName);
        holder.typeTextView.setText(characterType);
        holder.levelTextView.setText(String.valueOf(characterLevel));

        // Click handling using the listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();  // Return the size of the List
    }


    // Also inside CharacterListAdapter
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }




    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}