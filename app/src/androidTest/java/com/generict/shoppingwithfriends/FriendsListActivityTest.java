/*
 * @Author: Jenna Kwon
 * junit testing for the application
 * MAKE SURE TO RUN WITH THE ANDROID TEST (GREEN ROBOT)
 *
 */

//http://stackoverflow.com/questions/13042015/testing-onactivityresult

package com.generict.shoppingwithfriends;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.test.*;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import junit.framework.Assert;


public class FriendsListActivityTest extends ActivityInstrumentationTestCase2<FriendsListActivity> {
    FriendsListActivity activityUnderTest;

    /**
     * No param constructor that passes the activity
     */
    public FriendsListActivityTest() {
        super(FriendsListActivity.class);
    }

    /**
     * Launches the activity in set up
     *
     * @throws java.lang.Exception
     */
    @Override
    public void setUp() throws Exception {
        activityUnderTest = getActivity();
    }

    /**
     * Simple test that is meant to succeed
     *
     * @throws Exception
     */
    public void testSimple() throws Exception {
        final int expected = 5;
        final int reality = 5;
        Assert.assertEquals("Do not equal", expected, reality);
    }

    /**
     * Test for ensuring that upon clicking "SEARCH & ADD FRIENDS" button,
     * List of registered users show up by redirecting to UsersListActivity
     */
    @MediumTest
    public void testClickAddFriend() {
        // Button that will lead to UsersListActivity
        final Button addFriendButton = (Button) activityUnderTest.findViewById(R.id.add_friend_button);

        // Set up an activity monitor
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(UsersListActivity.class.getName(), null, false);

        // Open current activity
        FriendsListActivity myActivity = getActivity();

        // Click on addFriendButton
        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                addFriendButton.performClick();
            }
        });

        // Test for 5 seconds
        UsersListActivity user_activity = (UsersListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);

        // Check that next activity is opened and captured
        assertNotNull(user_activity);
        user_activity.finish();
    }

    /**
     * Test for ensuring that upon clicking "Back" button,
     * User is redirected to the home page
     */
    @MediumTest
    public void testClickBack() {
        // Button that will lead to UsersListActivity
        final Button backButton = (Button) activityUnderTest.findViewById(R.id.back_button);

        // Set up an activity monitor
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ApplicationHomeActivity.class.getName(), null, false);

        // Open current activity
        FriendsListActivity myActivity = getActivity();

        // Click on addFriendButton
        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                backButton.performClick();
            }
        });

        // Test for 5 seconds
        ApplicationHomeActivity home_activity = (ApplicationHomeActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);

        // Check that next activity is opened and captured
        assertNotNull(home_activity);
        home_activity.finish();
    }


}