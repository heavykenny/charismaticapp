package com.example.charismaticapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.charismaticapp.R;

import java.util.List;

public class QuizCategoryAdapter extends BaseAdapter {
    public QuizCategoryAdapter(List<QuizCategory> quizCategoryList) {
        this.quizCategoryList = quizCategoryList;
    }

    private List<QuizCategory> quizCategoryList;

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
        testCount.setText(quizCategoryList.get(position).getNumbers() + " tests");
        return v;
    }
}
