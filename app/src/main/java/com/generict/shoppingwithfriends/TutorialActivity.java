package com.generict.shoppingwithfriends;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.viewpagerindicator.CirclePageIndicator;


/**
 * Created by Vignesh Prasad on 4/5/15.
 */
public class TutorialActivity extends Activity {
    int position;
    PagerAdapter mPagerAdapter;
    ViewPager mViewPager;

    public static final String TAG = "TUTORIAL_ACTIVITY";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        position = 0;
        mPagerAdapter = new PagerAdapter(this);
        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        CirclePageIndicator cpi = (CirclePageIndicator) findViewById(R.id.circle_page_indicator);
        cpi.setRadius(20);
        cpi.setFillColor(Color.parseColor("#ffff9d8e"));
        cpi.setViewPager(mViewPager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // respond to action-up button:
        }
        return super.onOptionsItemSelected(item);
    }

    private class PagerAdapter extends android.support.v13.app.FragmentPagerAdapter {

        Activity mParent;
        public PagerAdapter(Activity parent) {
            super(parent.getFragmentManager());
            mParent = parent;
        }

        @Override
        public Fragment getItem(int position) {
            return Tutorial.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;  // total
        }

        @Override
        public CharSequence getPageTitle(int pos) {
            return String.valueOf(pos);
        }
    }

}
