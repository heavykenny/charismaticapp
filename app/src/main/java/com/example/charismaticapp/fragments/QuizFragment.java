package com.example.charismaticapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.charismaticapp.R;
import com.example.charismaticapp.models.QuizCategoryModel;
import com.example.charismaticapp.adapters.QuizCategoryFragmentAdapter;
import com.example.charismaticapp.models.UserModel;
import com.example.charismaticapp.logics.UtilClass;
import com.example.charismaticapp.ui.TestListActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment implements QuizCategoryFragmentAdapter.OnQuizCategoryClickListener {
    UtilClass utilClass = new UtilClass();
    private List<QuizCategoryModel> quizCategoryModelList = new ArrayList<>();
    private GridView quizView;
    private final UserModel userModel;
    private Context appContext;

    public QuizFragment(UserModel userModel) {
        this.userModel = userModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.quiz_fragment, container, false);
        quizView = v.findViewById(R.id.quiz_gridView);

        appContext = getContext();
        getAllCategories();
        quizView.setAdapter(new QuizCategoryFragmentAdapter(quizCategoryModelList, this));
        return v;
    }

    private void getAllCategories() {
        Gson gson = new Gson();
        String jsonString = utilClass.getJsonFromStorage("questions.json", appContext);
        quizCategoryModelList = gson.fromJson(jsonString, new TypeToken<List<QuizCategoryModel>>() {
        }.getType());
    }

    @Override
    public void onQuizCategoryClick(QuizCategoryModel quizCategoryModel) {
        Intent i = new Intent(getContext(), TestListActivity.class);
        i.putExtra("QuizCategoryModel", quizCategoryModel);
        i.setExtrasClassLoader(UserModel.class.getClassLoader());
        i.putExtra("UserModel", userModel);
        // REFERENCE - https://stackoverflow.com/a/39078856/9332871
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }

}