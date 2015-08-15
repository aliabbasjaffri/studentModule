package com.studentmodule;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import SH4.*;
import SHA.*;

public class StudentFirstActivity extends AppCompatActivity
{
    private CustomViewPager mViewPager;
    private studentFirstActivityPagerAdapter viewPagerAdapter;

    private Toolbar toolbar;
    private TextView mTitle;

    private LinearLayout topRatedTutorsButton;
    private LinearLayout studentDashboardButton;

    private ArrayList<SHA> tutorsList;
    private ArrayList<shAData> tutorData;

    private View firstTutorIndicator;
    private View secondTutorIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_first);

        toolbar = (Toolbar) findViewById(R.id.firstActivityToolbarInclude);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Sponsored Tutors of the Month");

        firstTutorIndicator  = findViewById(R.id.firstTutorIndicator);
        secondTutorIndicator = findViewById(R.id.secondTutorIndicator);

        topRatedTutorsButton = (LinearLayout) findViewById(R.id.topRatedTutorsCountrySorted);
        topRatedTutorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(StudentFirstActivity.this , TopRatedTutors.class);
                startActivity(i);
            }
        });

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
                    }break;

                    case 2:
                    {
                        firstTutorIndicator.setBackgroundResource(R.drawable.circle_purple);
                        secondTutorIndicator.setBackgroundResource(R.drawable.circle_pink);
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
        //getMenuInflater().inflate(R.menu.menu_student_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }
}
