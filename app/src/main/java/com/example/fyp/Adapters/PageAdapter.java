package com.example.fyp.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fyp.Fragments.tab1;
import com.example.fyp.Fragments.tab2;
import com.example.fyp.Fragments.tab3;
import com.example.fyp.Fragments.tab4;
import com.example.fyp.Fragments.tab5;
import com.example.fyp.Fragments.tab6;
import com.example.fyp.Fragments.tab7;

public class PageAdapter extends FragmentPagerAdapter {

    int numTabs;

    public PageAdapter(FragmentManager fm, int numTabs) {
        super(fm);

        this.numTabs = numTabs;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new tab1();
            case 1:
                return new tab2();
            case 2:
                return new tab3();
            case 3:
                return new tab4();
            case 4:
                return new tab5();
            case 5:
                return new tab6();
            case 6:
                return new tab7();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return numTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
