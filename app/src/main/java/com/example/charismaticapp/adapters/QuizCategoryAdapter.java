package com.example.charismaticapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.data.QuizCategory;

import java.util.List;

public class QuizCategoryAdapter extends BaseAdapter implements View.OnClickListener {
    private final List<QuizCategory> quizCategoryList;
    private final OnQuizCategoryClickListener onQuizCategoryClickListener;

    public QuizCategoryAdapter(List<QuizCategory> quizCategoryList, OnQuizCategoryClickListener onQuizCategoryClickListener) {
        this.quizCategoryList = quizCategoryList;
        this.onQuizCategoryClickListener = onQuizCategoryClickListener;
    }

    @Override
    public int getCount() {
        return quizCategoryList.size();
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
        catName.setText(quizCategoryList.get(position).getName());
        testCount.setText(quizCategoryList.get(position).getTestCounts() + " tests");
        v.setTag(position);
        v.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View view) {
        // Get the position of the clicked item
        int position = (Integer) view.getTag();
        // Call the onQuizCategoryClickListener with the clicked item
        onQuizCategoryClickListener.onQuizCategoryClick(quizCategoryList.get(position));
    }

    // Interface for handling quiz category clicks
    public interface OnQuizCategoryClickListener {
        void onQuizCategoryClick(QuizCategory quizCategory);
    }
}
