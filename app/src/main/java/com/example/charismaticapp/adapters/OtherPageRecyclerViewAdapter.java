package com.example.charismaticapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.models.OtherPagesModel;
import com.example.charismaticapp.ui.OtherActivity;

import java.util.List;


// REFERENCE: https://developer.android.com/develop/ui/views/layout/recyclerview
public class OtherPageRecyclerViewAdapter extends RecyclerView.Adapter<OtherPageRecyclerViewAdapter.ViewHolder> {

    List<OtherPagesModel> otherPagesModelList;
    Context appContext;
    OtherActivity otherActivity = new OtherActivity();

    public OtherPageRecyclerViewAdapter(List<OtherPagesModel> otherPagesModelList, Context appContext) {
        this.otherPagesModelList = otherPagesModelList;
        this.appContext = appContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.itemImage.setImageResource(otherPagesModelList.get(position).imageId);
        holder.txtTitle.setText(otherPagesModelList.get(position).pageName);

        // Set the onClickListener for itemImage and txtTitle
        holder.itemImage.setOnClickListener(v -> {
            // Retrieve the data for the clicked itemImage
            String itemName = otherPagesModelList.get(holder.getLayoutPosition()).pageName;
            otherActivity.openOtherActivity(itemName, appContext);
        });

        holder.txtTitle.setOnClickListener(v -> {
            // Retrieve the data for the clicked txtTitle
            String itemName = otherPagesModelList.get(holder.getLayoutPosition()).pageName;
            otherActivity.openOtherActivity(itemName, appContext);
        });
    }

    @Override
    public int getItemCount() {
        return otherPagesModelList.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView txtTitle;
        public ImageView itemImage;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the appContext from any ViewHolder instance.
            super(itemView);

            itemImage = itemView.findViewById(R.id.itemImage);
            txtTitle = itemView.findViewById(R.id.txtTitle);

            // Set onClickListener for the whole item view
            itemView.setOnClickListener(this);
        }

        // Handle onClick events for the whole item view
        @Override
        public void onClick(View v) {
            // Retrieve the data for the clicked item
            int position = getBindingAdapterPosition();
            String itemName = otherPagesModelList.get(position).pageName;
            otherActivity.openOtherActivity(itemName, appContext);
        }
    }
}


