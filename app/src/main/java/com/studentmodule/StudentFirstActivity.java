package com.studentmodule;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import SHA.*;

public class StudentFirstActivity extends AppCompatActivity
{
    private CustomViewPager mViewPager;
    private studentFirstActivityPagerAdapter viewPagerAdapter;

    private Toolbar toolbar;
    private TextView mTitle;

    private LinearLayout studentDashboardButton;

    private ArrayList<SHA> tutorsList;
    private ArrayList<shAData> tutorData;

    private View firstTutorIndicator;
    private View secondTutorIndicator;
    private View thirdTutorIndicator;

    private int[] tabLayoutImages = {
            R.drawable.mypage,
            R.drawable.convo,
            R.drawable.enrollment,
            R.drawable.nowon
    };

    private String tabTitles[] = new String[] { "마이페이지", "쌤께 말걸기", "수강신청" , "Now On" };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_first);

        toolbar = (Toolbar) findViewById(R.id.firstActivityToolbarInclude);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        firstTutorIndicator  = findViewById(R.id.firstTutorIndicator);
        secondTutorIndicator = findViewById(R.id.secondTutorIndicator);
        thirdTutorIndicator  = findViewById(R.id.thirdTutorIndicator);

        studentDashboardButton = (LinearLayout) findViewById(R.id.studentDashboardButton);
        studentDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentFirstActivity.this , StudentPortalActivity.class);
                startActivity(i);
            }
        });

        tutorData = new ArrayList<>();
        tutorData.add(new shAData("Alan" , "www.google.com/Alan" , 5));
        tutorData.add(new shAData("Doug" , "www.google.com/Doug" , 7));
        tutorData.add(new shAData("Rick" , "www.google.com/Rick" , 9));

        tutorsList = new ArrayList<>();

        for( int i = 0; i < tutorData.size(); i++ )
            tutorsList.add( SHA.newInstance( tutorData.get(i).getTutorName() , tutorData.get(i).getVideoLink() , tutorData.get(i).getRating() ) );

        mViewPager = (CustomViewPager) findViewById(R.id.firstActivityTeachersPager);
        viewPagerAdapter = new studentFirstActivityPagerAdapter(getSupportFragmentManager() , StudentFirstActivity.this , tutorsList);
        mViewPager.setPagingEnabled(true);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                switch(position + 1)
                {
                    case 1:
                    {
                        firstTutorIndicator.setBackgroundResource(R.drawable.circle_pink);
                        secondTutorIndicator.setBackgroundResource(R.drawable.circle_purple);
                        thirdTutorIndicator.setBackgroundResource(R.drawable.circle_purple);
                    }break;

                    case 2:
                    {
                        firstTutorIndicator.setBackgroundResource(R.drawable.circle_purple);
                        secondTutorIndicator.setBackgroundResource(R.drawable.circle_pink);
                        thirdTutorIndicator.setBackgroundResource(R.drawable.circle_purple);
                    }break;

                    case 3:
                    {
                        firstTutorIndicator.setBackgroundResource(R.drawable.circle_purple);
                        secondTutorIndicator.setBackgroundResource(R.drawable.circle_purple);
                        thirdTutorIndicator.setBackgroundResource(R.drawable.circle_pink);
                    }break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public CharSequence getPageTitle(int position)
    {
        // Generate title based on item position
        Drawable image = getResources().getDrawable(tabLayoutImages[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth()/3, image.getIntrinsicHeight()/3);

        // Replace blank spaces with image icon
        SpannableString sb = new SpannableString( " \n" + tabTitles[position] );
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}