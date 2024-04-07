package com.app.homebrewed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder> {

    private String[][] data;
    private OnItemClickListener listener;

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
        return data.length;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
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
        }
    }
}