package com.example.u.registeration;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class UsagePagerAdapter extends FragmentPagerAdapter {

        public UsagePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 : return UsageFragment1.newInstance(position + 1);
                case 1 : return UsageFragment2.newInstance(position + 1);
                default: return UsageFragment1.newInstance(position + 1);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }