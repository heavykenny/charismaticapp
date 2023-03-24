package com.example.charismaticapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.models.QuizCategoryModel;

import java.util.List;

public class QuizCategoryFragmentAdapter extends BaseAdapter implements View.OnClickListener {
    private final List<QuizCategoryModel> quizCategoryModelList;
    private final OnQuizCategoryClickListener onQuizCategoryClickListener;

    public QuizCategoryFragmentAdapter(List<QuizCategoryModel> quizCategoryModelList, OnQuizCategoryClickListener onQuizCategoryClickListener) {
        this.quizCategoryModelList = quizCategoryModelList;
        this.onQuizCategoryClickListener = onQuizCategoryClickListener;
    }

    @Override
    public int getCount() {
        return quizCategoryModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_layout, parent, false);
        }

        TextView catName = v.findViewById(R.id.txtViewCatName);
        TextView testCount = v.findViewById(R.id.txtViewTestCount);
        catName.setText(quizCategoryModelList.get(position).getQuizCategoryTitle());
        testCount.setText(quizCategoryModelList.get(position).getTestCounts() + " tests");
        v.setTag(position);
        v.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View view) {
        // Get the position of the clicked item
        int position = (Integer) view.getTag();
        // Call the onQuizCategoryClickListener with the clicked item
        onQuizCategoryClickListener.onQuizCategoryClick(quizCategoryModelList.get(position));
    }

    // Interface for handling quiz category clicks
    public interface OnQuizCategoryClickListener {
        void onQuizCategoryClick(QuizCategoryModel quizCategoryModel);
    }
}
