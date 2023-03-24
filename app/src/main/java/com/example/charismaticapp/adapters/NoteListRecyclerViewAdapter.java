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
import com.example.charismaticapp.models.NoteModel;
import com.example.charismaticapp.models.UserModel;
import com.example.charismaticapp.ui.NoteActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteListRecyclerViewAdapter extends RecyclerView.Adapter<NoteListRecyclerViewAdapter.ViewHolder> {

    List<NoteModel> noteModelList;
    Context appContext;
    Intent i;
    NoteActivity noteActivity = new NoteActivity();
    UserModel userModel;

    public NoteListRecyclerViewAdapter(List<NoteModel> noteModelList, Context appContext, Intent i) {
        this.noteModelList = noteModelList;
        this.appContext = appContext;
        this.i = i;
        this.userModel = i.getParcelableExtra("UserModel");
    }

    @NonNull
    @Override
    public NoteListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent, false);
        return new NoteListRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListRecyclerViewAdapter.ViewHolder viewHolder, int index) {
        viewHolder.noteTitle.setText(noteModelList.get(index).getNoteTitle());

        Date currentDate = new Date(Long.parseLong(noteModelList.get(index).getCreatedDate()));
        SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        viewHolder.noteCreatedDate.setText(currentDateFormat.format(currentDate));

        viewHolder.quizMenu.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(appContext, viewHolder.quizMenu);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int id = menuItem.getItemId();
                if (R.id.editNote == id) {
                    noteActivity.viewNoteDetails(appContext, noteModelList.get(index).getNoteTitle(), userModel);
                    return true;
                } else if (R.id.deleteNote == id) {
                    noteActivity.deleteNoteDetails(appContext, noteModelList.get(index).getNoteTitle());
                    noteModelList.remove(index);
                    notifyItemRemoved(index);
                    notifyItemRangeChanged(index, noteModelList.size());
                    return true;
                }

                return false;
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return noteModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView noteTitle;
        public TextView noteCreatedDate;
        public ImageView quizMenu;

        public RelativeLayout relLayList;
        public RelativeLayout relLayMenu;

        public ViewHolder(View itemView) {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.txtViewCatName);
            noteCreatedDate = itemView.findViewById(R.id.txtViewTestCount);
            quizMenu = itemView.findViewById(R.id.quizMenu);
            relLayList = itemView.findViewById(R.id.relLayList);
            relLayMenu = itemView.findViewById(R.id.relLayMenu);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int index = getBindingAdapterPosition();
            noteActivity.viewNoteDetails(appContext, noteModelList.get(index).getNoteTitle(), userModel);
        }
    }
}