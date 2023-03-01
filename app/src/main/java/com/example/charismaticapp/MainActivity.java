package com.example.charismaticapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.example.charismaticapp.adapters.OnboardingAdapter;
import com.example.charismaticapp.helpers.SaveState;
import com.example.charismaticapp.ui.HomeScreenActivity;
import com.example.charismaticapp.ui.LoginActivity;
import com.example.charismaticapp.ui.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    CardView nextCard;
    LinearLayout dotsLayout;
    ViewPager viewPager;

    TextView[] dots;
    SaveState saveState;

    int currentPosition;
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            dotsFunction(position);
            currentPosition = position;
            if (currentPosition <= 1) {
                nextCard.setOnClickListener(view -> viewPager.setCurrentItem(currentPosition + 1));
            } else {
                nextCard.setOnClickListener(view -> {
                    saveState.setState(1);
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                });
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextCard = findViewById(R.id.next_navigation);
        dotsLayout = findViewById(R.id.dotsLayout);
        viewPager = findViewById(R.id.slider);

        dotsFunction(0);
        saveState = new SaveState(MainActivity.this, "OB");
        if (saveState.getState() == 1) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        OnboardingAdapter adapter = new OnboardingAdapter(this);
        viewPager.setAdapter(adapter);
        dotsFunction(0);

        nextCard.setOnClickListener(v -> viewPager.setCurrentItem(currentPosition + 1, true));
        viewPager.setOnPageChangeListener(onPageChangeListener);

    }

    private void dotsFunction(int pos) {
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("•"));
            dots[i].setTextColor(getColor(R.color.white));   //this is the non selection color
            dots[i].setTextSize(30);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[pos].setTextColor(getColor(R.color.teal_700));  //this is the selection color
            dots[pos].setTextSize(40);  //this is the selection size
        }
    }
}