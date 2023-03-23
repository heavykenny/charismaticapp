package com.example.charismaticapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.data.NoteData;
import com.example.charismaticapp.data.UserData;
import com.example.charismaticapp.ui.NoteActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteListRecyclerViewAdapter extends RecyclerView.Adapter<NoteListRecyclerViewAdapter.ViewHolder> {

    List<NoteData> list;
    Context appContext;
    Intent intent;
    NoteActivity noteActivity = new NoteActivity();
    UserData userData;

    public NoteListRecyclerViewAdapter(List<NoteData> list, Context appContext, Intent intent) {
        this.list = list;
        this.appContext = appContext;
        this.intent = intent;
        this.userData = intent.getParcelableExtra("UserData");
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

        Date date = new Date(Long.parseLong(list.get(position).getDate()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.noteDate.setText(dateFormat.format(date));

        holder.quizMenu.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(appContext, holder.quizMenu);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int id = menuItem.getItemId();
                if (R.id.editNote == id) {
                    noteActivity.viewNoteDetails(appContext, list.get(position).getName(), userData);
                    return true;
                } else if (R.id.deleteNote == id) {
                    noteActivity.deleteNoteDetails(appContext, list.get(position).getName(), userData);
                    list.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, list.size());
                    return true;
                }

                return false;
            });
            popupMenu.show();
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
            noteActivity.viewNoteDetails(appContext, itemName, userData);
        }
    }
}