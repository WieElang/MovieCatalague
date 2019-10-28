package com.example.moviecatalogue5.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.moviecatalogue5.Fragment.MovieFragment;
import com.example.moviecatalogue5.Fragment.TvFragment;

public class PageAdapter extends FragmentPagerAdapter {
    private int tabs;

    public PageAdapter(FragmentManager fragmentManager, int numtabs){
        super(fragmentManager);
        this.tabs = numtabs;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new MovieFragment();
            case 1:
                return new TvFragment();
            default:
                return null;
        }
    }

    public int getCount(){
        return tabs;
    }
}
