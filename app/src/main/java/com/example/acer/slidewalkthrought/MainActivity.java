package com.example.acer.slidewalkthrought;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPage;
    private LinearLayout mDotsLayout;

    private Button mNextBtn;
    private Button mPrevBtn;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSlideViewPage=findViewById(R.id.slideViewPager);
        mDotsLayout=findViewById(R.id.dotsLayout);

        mNextBtn=findViewById(R.id.nextBtn);
        mPrevBtn=findViewById(R.id.prevBtn);

        sliderAdapter=new SliderAdapter(this);
        mSlideViewPage.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSlideViewPage.addOnPageChangeListener(viewListener);

        //OnClickListener

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPage.setCurrentItem(mCurrentPage+1);
            }
        });
        mPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPage.setCurrentItem(mCurrentPage-1);
            }
        });

    }
    public void addDotsIndicator(int position){
        mDots=new TextView[3];
        mDotsLayout.removeAllViews();
        for (int i=0;i<mDots.length;i++){
            mDots[i]= new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotsLayout.addView(mDots[i]);
        }
        if (mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }
    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            mCurrentPage=position;

            if (position==0){
                mNextBtn.setEnabled(true);
                mPrevBtn.setEnabled(false);
                mPrevBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("NEXT");
                mPrevBtn.setText("");
            }
            else if (position==mDots.length -1){
                mNextBtn.setEnabled(true);
                mPrevBtn.setEnabled(true);
                mPrevBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("FINISH");
                mPrevBtn.setText("BACK");
            }
            else {
                mNextBtn.setEnabled(true);
                mPrevBtn.setEnabled(true);
                mPrevBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("NEXT");
                mPrevBtn.setText("BACK");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}

