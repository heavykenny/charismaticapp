package com.example.charismaticapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.charismaticapp.R;

// REFERENCES: https://developer.android.com/develop/ui/views/animations/screen-slide
// REFERENCES: https://youtu.be/gTzcHWfbEXw
public class OnboardingSplashPageAdapter extends PagerAdapter {

    Context appContext;
    LayoutInflater layoutInflater;
    int[] splashTitles = {R.string.welcome1, R.string.welcome2, R.string.welcome3};
    int[] splashTitleTexts = {R.string.onboarding1, R.string.onboarding2, R.string.onboarding3};
    int[] splashImages = {R.drawable.back_to_school, R.drawable.personal_notebook, R.drawable.book_lover};
    int[] splashBackgrounds = {R.drawable.background1, R.drawable.background2, R.drawable.background3};


    public OnboardingSplashPageAdapter(Context appContext) {
        this.appContext = appContext;
    }

    @Override
    public int getCount() {
        return splashTitles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int index) {
        layoutInflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.splash_slide_layout, container, false);

        ImageView slideImg = v.findViewById(R.id.slideImg);
        TextView sliderTitle = v.findViewById(R.id.sliderTitle);
        TextView sliderSubtitle = v.findViewById(R.id.sliderSubtitle);
        LinearLayout sliderLayout = v.findViewById(R.id.sliderLayout);

        slideImg.setImageResource(splashImages[index]);
        sliderTitle.setText(splashTitles[index]);
        sliderSubtitle.setText(splashTitleTexts[index]);
        sliderLayout.setBackgroundResource(splashBackgrounds[index]);

        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
