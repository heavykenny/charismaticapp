package com.example.charismaticapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.charismaticapp.R;
import com.example.charismaticapp.data.QuizCategory;
import com.example.charismaticapp.adapters.QuizCategoryAdapter;
import com.example.charismaticapp.logics.UtilClass;
import com.example.charismaticapp.ui.TestListActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment implements QuizCategoryAdapter.OnQuizCategoryClickListener {
    UtilClass utilClass = new UtilClass();
    private List<QuizCategory> quizCategoryList = new ArrayList<>();
    private GridView quizView;
    private Context appContext;

    public QuizFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.quiz_fragment, container, false);
        quizView = v.findViewById(R.id.quiz_gridView);

        appContext = getContext();
        getAllCategories();
        quizView.setAdapter(new QuizCategoryAdapter(quizCategoryList, this));
        return v;
    }

    private void getAllCategories() {
        Gson gson = new Gson();
        String jsonString = utilClass.loadJsonFileFromAssets("questions.json", appContext);
        Type quizCategoryListType = new TypeToken<List<QuizCategory>>() {
        }.getType();
        quizCategoryList = gson.fromJson(jsonString, quizCategoryListType);
    }

    @Override
    public void onQuizCategoryClick(QuizCategory quizCategory) {
        Intent intent = new Intent(getContext(), TestListActivity.class);
        intent.putExtra("QuizCategory", quizCategory);
        // REFERENCE - https://stackoverflow.com/a/39078856/9332871
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}