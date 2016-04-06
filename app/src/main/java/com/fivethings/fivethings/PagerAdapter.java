package com.fivethings.fivethings;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by eliete on 3/1/16.
 */
public class PagerAdapter extends FragmentPagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ConnectionFragment();
            case 1:
                return new FacebookFragment();
            case 2:
                return new YoutubeFragment();
            case 3:
                return new GoogleFrament();
            case 4:
                return new AskFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Conexão";
            case 1:
                return "Facebook";
            case 2:
                return "Youtube";
            case 3:
                return "Google";
            case 4:
                return "Dúvidas";

        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
