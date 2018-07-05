package com.example.home.bt_worldcup.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.home.bt_worldcup.fragments.FactsFragment;
import com.example.home.bt_worldcup.fragments.LiveEventsFragment;
import com.example.home.bt_worldcup.fragments.StatisticsFragment;
import com.example.home.bt_worldcup.models.Match;
import com.example.home.bt_worldcup.models.TeamsInMatch;

/**
 * Created by Home on 6/29/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new StatisticsFragment();

                break;
            case 1:
                frag = new LiveEventsFragment();
                break;
            case 2:
                frag = new FactsFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "STATISTICS";
                break;
            case 1:
                title = "LIVE EVENTS";
                break;
            case 2:
                title = "FACTS";
                break;
        }
        return title;
    }
}
