package com.example.charismaticapp.adapters;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.data.NoteData;

import java.util.List;

public class NoteListRecyclerViewAdapter extends RecyclerView.Adapter<NoteListRecyclerViewAdapter.ViewHolder> {

    List<NoteData> list;
    Context context;

    public NoteListRecyclerViewAdapter(List<NoteData> list, Application application) {
        this.list = list;
        this.context = application;
    }

    @NonNull
    @Override
    public NoteListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent, false);
        return new NoteListRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.noteName.setText(list.get(position).getName());
        holder.noteDate.setText(list.get(position).getDate());

        holder.quizMenu.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(context, holder.quizMenu);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(menuItem -> {

                int id = menuItem.getItemId();
                if (R.id.view == id) {
                    return true;
                } else if (R.id.edit_name == id) {
                    return true;
                } else return R.id.delete == id;
            });
            popupMenu.show();
        });

        holder.relLayList.setOnClickListener(v -> {
            Toast.makeText(context, "SAMPLE " + position, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView noteName;
        public TextView noteDate;
        public ImageView quizMenu;

        public RelativeLayout relLayList;
        public RelativeLayout relLayMenu;

        public ViewHolder(View itemView) {
            super(itemView);

            noteName = itemView.findViewById(R.id.txtViewCatName);
            noteDate = itemView.findViewById(R.id.txtViewTestCount);
            quizMenu = itemView.findViewById(R.id.quizMenu);
            relLayList = itemView.findViewById(R.id.relLayList);
            relLayMenu = itemView.findViewById(R.id.relLayMenu);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getBindingAdapterPosition();
            String itemName = list.get(position).getName();

        }
    }
}