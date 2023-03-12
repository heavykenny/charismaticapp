package com.example.charismaticapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.charismaticapp.R;
import com.example.charismaticapp.adapters.QuizCategory;
import com.example.charismaticapp.adapters.QuizCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment {
    private final List<QuizCategory> quizCategoryList = new ArrayList<>();

    private GridView quizView;
    public QuizFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.quiz_fragment, container, false);
        quizView = v.findViewById(R.id.quiz_gridView);

        getAllCategories();
        QuizCategoryAdapter quizCategoryAdapter = new QuizCategoryAdapter(quizCategoryList);
        quizView.setAdapter(quizCategoryAdapter);
        return v;
    }

    private void getAllCategories() {
        quizCategoryList.add(new QuizCategory("1", "Maths", 10));
        quizCategoryList.add(new QuizCategory("2", "Biology", 20));
        quizCategoryList.add(new QuizCategory("3", "ICT", 24));
        quizCategoryList.add(new QuizCategory("4", "English", 13));
        quizCategoryList.add(new QuizCategory("5", "History", 8));
    }
}