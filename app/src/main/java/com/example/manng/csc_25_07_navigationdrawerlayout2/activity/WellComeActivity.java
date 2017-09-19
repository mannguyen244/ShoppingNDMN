package com.example.manng.csc_25_07_navigationdrawerlayout2.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.manng.csc_25_07_navigationdrawerlayout2.R;
import com.example.manng.csc_25_07_navigationdrawerlayout2.adapter.PreferenceManager;

import java.util.ArrayList;


public class WellComeActivity extends AppCompatActivity {

    ViewPager viewPager;
    PreferenceManager preferenceManager;
    PagerAdapter pagerAdapter;
    Button btnNext, btnSkip;
    LinearLayout dotsLayout;
    TextView[] dots;
    ArrayList<Integer> arrayList;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        preferenceManager = new PreferenceManager(getApplicationContext());
        if (!preferenceManager.isFirstTimeLauch()) {
            launchHomeScreen();
            finish();
        }
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        
        setContentView(R.layout.activity_well_come);
        
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        btnNext = (Button)findViewById(R.id.btnNext);
        btnSkip = (Button)findViewById(R.id.btnSkip);
        dotsLayout = (LinearLayout)findViewById(R.id.layoutDots);
        view = (View)findViewById(R.id.viewLine);
        
        arrayList = new ArrayList<>();
        arrayList.add(R.layout.layout_wellcome_slide_1);
        arrayList.add(R.layout.layout_wellcome_slide_2);
        arrayList.add(R.layout.layout_wellcome_slide_3);
        arrayList.add(R.layout.layout_wellcome_slide_4);
        arrayList.add(R.layout.layout_wellcome_slide_5);

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        pagerAdapter = new com.example.manng.csc_25_07_navigationdrawerlayout2.adapter.PagerAdapter(WellComeActivity.this, arrayList);

        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(onPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchHomeScreen();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < arrayList.size()) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });
        
        
        
        
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }


    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[arrayList.size()];
        int[] colorsActive = getResources().getIntArray(R.array.arr_dots_active);
        int[] colorsInactive = getResources().getIntArray(R.array.arr_dots_inactive);
        int[] colorsView = getResources().getIntArray(R.array.arr_dots_active);

        dotsLayout.removeAllViews();

        for (int i = 0; i<dots.length; i++) {
            dots[i] = new TextView(WellComeActivity.this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
            if (i % 2 == 0) {
                btnSkip.setTextColor(colorsActive[currentPage]);
                btnNext.setTextColor(colorsActive[currentPage]);
                view.setBackgroundColor(colorsActive[currentPage]);
            } else {
                btnSkip.setTextColor(colorsInactive[currentPage]);
                btnNext.setTextColor(colorsInactive[currentPage]);
                view.setBackgroundColor(colorsInactive[currentPage]);
            }
        }

        if (dots.length > 0){
            dots[currentPage].setTextColor(colorsActive[currentPage]);
            dots[currentPage].setText(Html.fromHtml("&#9679;"));
            dots[currentPage].setTextSize(30);

        }
    }

    private void launchHomeScreen() {
        preferenceManager.setFirstTimeLauch(false);
        startActivity(new Intent(WellComeActivity.this,MainActivity.class));
        finish();
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            addBottomDots(position);
//            int[] colorsActive = getResources().getIntArray(R.array.array_dots_active);
//            int[] colorsInactive = getResources().getIntArray(R.array.array_dots_inactive);
            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == arrayList.size() - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
