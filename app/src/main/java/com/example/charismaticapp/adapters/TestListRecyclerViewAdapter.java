package com.example.charismaticapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.models.TestListModel;
import com.example.charismaticapp.models.UserModel;
import com.example.charismaticapp.ui.StartQuestionActivity;

import java.util.List;

public class TestListRecyclerViewAdapter extends RecyclerView.Adapter<TestListRecyclerViewAdapter.ViewHolder> {
    List<TestListModel> testListModelList;

    UserModel userModel;
    Context appContext;

    public TestListRecyclerViewAdapter(List<TestListModel> testListModelList, Context appContext, UserModel userModel) {
        this.testListModelList = testListModelList;
        this.userModel = userModel;
        this.appContext = appContext;
    }

    @NonNull
    @Override
    public TestListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        return new TestListRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TestListRecyclerViewAdapter.ViewHolder holder, int index) {
        holder.txtTitle.setText(testListModelList.get(index).name);
        holder.txtCount.setText(testListModelList.get(index).count + " tests");
    }

    @Override
    public int getItemCount() {
        return testListModelList.size();
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
            int index = getBindingAdapterPosition();
            String testId = testListModelList.get(index).getTestId();

            Intent i = new Intent(appContext, StartQuestionActivity.class);
            i.putExtra("TestId", testId);
            i.setExtrasClassLoader(UserModel.class.getClassLoader());
            i.putExtra("UserModel", userModel);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            appContext.startActivity(i);
        }
    }
}
