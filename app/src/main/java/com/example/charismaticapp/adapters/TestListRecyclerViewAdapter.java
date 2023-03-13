package com.example.charismaticapp.adapters;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;

import java.util.List;

public class TestListRecyclerViewAdapter extends RecyclerView.Adapter<TestListRecyclerViewAdapter.ViewHolder> {
    List<TestListData> list;
    Context context;

    public TestListRecyclerViewAdapter(List<TestListData> data, Application application) {
        this.list = data;
        this.context = application;
    }
    @NonNull
    @Override
    public TestListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        return new TestListRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TestListRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(list.get(position).name);
        holder.txtCount.setText(String.valueOf(list.get(position).count));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtTitle;
        public TextView txtCount;
        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtCount = itemView.findViewById(R.id.txtCount);

            itemView.setOnClickListener(this);
        }

        // Handle onClick events for the whole item view
        @Override
        public void onClick(View v) {
            int position = getBindingAdapterPosition();

        }
    }
}
