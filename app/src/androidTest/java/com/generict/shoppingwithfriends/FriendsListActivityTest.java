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
     * @throws Exception
     */
    public void testSimple() throws Exception {
        final int expected = 5;
        final int reality = 5;
        Assert.assertEquals("Do not equal", expected, reality);
    }
}
//
//
//    // Testing this activity's interaction with other activities
//    @Test
//    public void testOpenNextActivity() {
//        // register next activity that need to be monitored.
//        // Upon clicking
//        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(UsersListActivity.class.getName(), null, false);
//
//        // open current activity.
//        FriendsListActivity myActivity = getActivity();
//        final Button button = (Button) myActivity.findViewById(com.generict.shoppingwithfriends.R.id.add_friend_button);
//        myActivity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                // click button and open next activity.
//                button.performClick();
//            }
//        });
//
//        // 5000 miliseconds = 5 seconds
//        UsersListActivity user_activity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
//
//        // next activity is opened and captured.
//        assertNotNull(user_activity);
//        user_activity.finish();
//    }
//}