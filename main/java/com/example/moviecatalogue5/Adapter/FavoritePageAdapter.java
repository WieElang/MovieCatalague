package com.example.moviecatalogue5.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.moviecatalogue5.Fragment.FavoriteMovieFragment;
import com.example.moviecatalogue5.Fragment.FavoriteTvFragment;

public class FavoritePageAdapter extends FragmentPagerAdapter {
    private int tabs;

    public FavoritePageAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new FavoriteMovieFragment();
            case 1:
                return new FavoriteTvFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabs;
    }
}
