package com.generict.shoppingwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * App home view
 * @author Vignesh
 * @version 1.0
 */
public class ApplicationHomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity activity = this;
        Button mLogout;
        Button mFriends;
        Button mWishListButton;
        Button mSalesReport;
        Button mViewNotifications;
        Button mMaps;
        setContentView(R.layout.activity_application_home);
        mWishListButton = (Button) findViewById(R.id.wish_list_button);
        mWishListButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, WishListActivity.class);
                startActivity(intent);
            }
        });
        mLogout = (Button) findViewById(R.id.logout_button);
        mLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, LoginRegistrationActivity.class);
                startActivity(intent);
            }
        });

        mFriends = (Button) findViewById(R.id.friends_button);
        mFriends.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, FriendsListActivity.class);
                startActivity(intent);
            }
        });

        mSalesReport = (Button) findViewById(R.id.sales_report_button);
        mSalesReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, PostSalesReportActivity.class);
                startActivity(intent);
            }
        });

        mViewNotifications = (Button) findViewById(R.id.view_notifications_button);
        mViewNotifications.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, ViewNotificationsActivity.class);
                startActivity(intent);
            }
        });

        mMaps = (Button) findViewById(R.id.maps_button);
        mMaps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, MapsActivity.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_application_home, menu);
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
}
