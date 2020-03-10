package com.example.fyp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentMonday(), "Monday");
        adapter.AddFragment(new FragmentTuesday(), "Tuesday");
        adapter.AddFragment(new FragmentWednesday(), "Wednesday");
        adapter.AddFragment(new FragmentThursday(), "Thursday");
        adapter.AddFragment(new FragmentFriday(), "Friday");
        adapter.AddFragment(new FragmentSaturday(), "Saturday");
        adapter.AddFragment(new FragmentSunday(), "Sunday");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        ActionBar actionBar = getSupportActionBar();
        //actionBar.setElevation(0);

    }
}
