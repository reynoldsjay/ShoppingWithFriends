/*
 * @Author: Jenna Kwon
 * junit testing for FriendsListActivity
 *
 */

package com.generict.shoppingwithfriends;


import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.ActionMode;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;

import junit.framework.Assert;


public class FriendsListActivityTest extends ActivityInstrumentationTestCase2<FriendsListActivity> {
    private FriendsListActivity activityUnderTest;
    private Button addFriendButton;
    private Button backButton;
    private View decorView;


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
        super.setUp();
        setActivityInitialTouchMode(true);
        activityUnderTest = getActivity();
        addFriendButton = (Button) activityUnderTest.findViewById(R.id.add_friend_button);
        backButton = (Button) activityUnderTest.findViewById(R.id.back_button);
        decorView = activityUnderTest.getWindow().getDecorView();
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
        // Set up an activity monitor
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ApplicationHomeActivity.class.getName(), null, false);

        // Click on addFriendButton
        activityUnderTest.runOnUiThread(new Runnable() {
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


    /**
     * Checks that buttons show up on the screen
     */
    public void testListViewShow() {
        ViewAsserts.assertOnScreen(decorView, addFriendButton);
        ViewAsserts.assertOnScreen(decorView, backButton);
        assertTrue(View.VISIBLE == addFriendButton.getVisibility());
        assertTrue(View.VISIBLE == backButton.getVisibility());
    }

    /**
     * Tests onActionItemClicked method
     * Creates an arbitrary menu item that doesn't exist have its view as the current ActionMode object
     * Must return false
     * <p/>
     * public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
     * switch (item.getItemId()) {
     * case R.id.menu_item1_show:
     * Toast.makeText(FriendsListActivity.this, String.valueOf(selectedItem), Toast.LENGTH_LONG).show();
     * // Action picked, so close the CAB
     * mode.finish();
     * return true;
     * default:
     * return false;
     * }
     * }
     */
    @SuppressWarnings("ConstantConditions")
    public void testOnActionItemClickedFalse() {
        //Tests that onActionItemClicked will return false
        ActionMode badActionMode = null;
        MenuItem badItem = new MenuItem() {
            @Override
            public int getItemId() {
                return 0;
            }

            @Override
            public int getGroupId() {
                return 0;
            }

            @Override
            public int getOrder() {
                return 0;
            }

            @Override
            public MenuItem setTitle(CharSequence title) {
                return null;
            }

            @Override
            public MenuItem setTitle(int title) {
                return null;
            }

            @Override
            public CharSequence getTitle() {
                return null;
            }

            @Override
            public MenuItem setTitleCondensed(CharSequence title) {
                return null;
            }

            @Override
            public CharSequence getTitleCondensed() {
                return null;
            }

            @Override
            public MenuItem setIcon(Drawable icon) {
                return null;
            }

            @Override
            public MenuItem setIcon(int iconRes) {
                return null;
            }

            @Override
            public Drawable getIcon() {
                return null;
            }

            @Override
            public MenuItem setIntent(Intent intent) {
                return null;
            }

            @Override
            public Intent getIntent() {
                return null;
            }

            @Override
            public MenuItem setShortcut(char numericChar, char alphaChar) {
                return null;
            }

            @Override
            public MenuItem setNumericShortcut(char numericChar) {
                return null;
            }

            @Override
            public char getNumericShortcut() {
                return 0;
            }

            @Override
            public MenuItem setAlphabeticShortcut(char alphaChar) {
                return null;
            }

            @Override
            public char getAlphabeticShortcut() {
                return 0;
            }

            @Override
            public MenuItem setCheckable(boolean checkable) {
                return null;
            }

            @Override
            public boolean isCheckable() {
                return false;
            }

            @Override
            public MenuItem setChecked(boolean checked) {
                return null;
            }

            @Override
            public boolean isChecked() {
                return false;
            }

            @Override
            public MenuItem setVisible(boolean visible) {
                return null;
            }

            @Override
            public boolean isVisible() {
                return false;
            }

            @Override
            public MenuItem setEnabled(boolean enabled) {
                return null;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public boolean hasSubMenu() {
                return false;
            }

            @Override
            public SubMenu getSubMenu() {
                return null;
            }

            @Override
            public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener) {
                return null;
            }

            @Override
            public ContextMenu.ContextMenuInfo getMenuInfo() {
                return null;
            }

            @Override
            public void setShowAsAction(int actionEnum) {

            }

            @Override
            public MenuItem setShowAsActionFlags(int actionEnum) {
                return null;
            }

            @Override
            public MenuItem setActionView(View view) {
                return null;
            }

            @Override
            public MenuItem setActionView(int resId) {
                return null;
            }

            @Override
            public View getActionView() {
                return null;
            }

            @Override
            public MenuItem setActionProvider(ActionProvider actionProvider) {
                return null;
            }

            @Override
            public ActionProvider getActionProvider() {
                return null;
            }

            @Override
            public boolean expandActionView() {
                return false;
            }

            @Override
            public boolean collapseActionView() {
                return false;
            }

            @Override
            public boolean isActionViewExpanded() {
                return false;
            }

            @Override
            public MenuItem setOnActionExpandListener(OnActionExpandListener listener) {
                return null;
            }
        };

        assertEquals(false, activityUnderTest.onActionItemClicked(badActionMode, badItem));


    }

    /**
     * Tests onActionItemClicked method
     * Creates an a valid menu item have its view as the current ActionMode object
     * Must return true
     * <p/>
     * public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
     * switch (item.getItemId()) {
     * case R.id.menu_item1_show:
     * Toast.makeText(FriendsListActivity.this, String.valueOf(selectedItem), Toast.LENGTH_LONG).show();
     * // Action picked, so close the CAB
     * mode.finish();
     * return true;
     * default:
     * return false;
     * }
     * }
     */
    public void testOnActionItemClickedTrue() {

        final ActionMode goodActionMode = new ActionMode() {
            CharSequence title;
            int intTitle;
            CharSequence subTitle;
            int intSubTitle;
            View view = activityUnderTest.findViewById(R.id.menu_item1_show);

            @Override
            public void setTitle(CharSequence title) {
                this.title = title;
            }

            @Override
            public void setTitle(int resId) {
                this.intTitle = resId;
            }

            @Override
            public void setSubtitle(CharSequence subtitle) {
                this.subTitle = subtitle;
            }

            @Override
            public void setSubtitle(int resId) {
                this.intSubTitle = resId;
            }

            @Override
            public void setCustomView(View view) {
                this.view = activityUnderTest.findViewById(R.id.menu_item1_show);
            }

            @Override
            public void invalidate() {
            }

            @Override
            public void finish() {
            }

            @Override
            public Menu getMenu() {
                return null;
            }

            @Override
            public CharSequence getTitle() {
                return null;
            }

            @Override
            public CharSequence getSubtitle() {
                return null;
            }

            @Override
            public View getCustomView() {
                return activityUnderTest.findViewById(R.id.menu_item1_show);
            }

            @Override
            public MenuInflater getMenuInflater() {
                return activityUnderTest.getMenuInflater();
            }
        };

        MenuItem goodItem = new MenuItem() {

            final Intent intent = new Intent(activityUnderTest, FriendsListActivity.class);

            @Override
            public int getItemId() {
                return R.id.menu_item1_show;
            }

            @Override
            public int getGroupId() {
                return 30;
            }

            @Override
            public int getOrder() {
                return 0;
            }

            @Override
            public MenuItem setTitle(CharSequence title) {
                return null;
            }

            @Override
            public MenuItem setTitle(int title) {
                return null;
            }

            @Override
            public CharSequence getTitle() {
                return null;
            }

            @Override
            public MenuItem setTitleCondensed(CharSequence title) {
                return null;
            }

            @Override
            public CharSequence getTitleCondensed() {
                return null;
            }

            @Override
            public MenuItem setIcon(Drawable icon) {
                return null;
            }

            @Override
            public MenuItem setIcon(int iconRes) {
                return null;
            }

            @Override
            public Drawable getIcon() {
                return null;
            }

            @Override
            public MenuItem setIntent(Intent intent) {
                return null;
            }

            @Override
            public Intent getIntent() {
                return intent;
            }

            @Override
            public MenuItem setShortcut(char numericChar, char alphaChar) {
                return null;
            }

            @Override
            public MenuItem setNumericShortcut(char numericChar) {
                return null;
            }

            @Override
            public char getNumericShortcut() {
                return 0;
            }

            @Override
            public MenuItem setAlphabeticShortcut(char alphaChar) {
                return null;
            }

            @Override
            public char getAlphabeticShortcut() {
                return 0;
            }

            @Override
            public MenuItem setCheckable(boolean checkable) {
                return null;
            }

            @Override
            public boolean isCheckable() {
                return false;
            }

            @Override
            public MenuItem setChecked(boolean checked) {
                return null;
            }

            @Override
            public boolean isChecked() {
                return false;
            }

            @Override
            public MenuItem setVisible(boolean visible) {
                return null;
            }

            @Override
            public boolean isVisible() {
                return false;
            }

            @Override
            public MenuItem setEnabled(boolean enabled) {
                return null;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public boolean hasSubMenu() {
                return false;
            }

            @Override
            public SubMenu getSubMenu() {
                return null;
            }

            @Override
            public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener) {
                return null;
            }

            @Override
            public ContextMenu.ContextMenuInfo getMenuInfo() {
                return null;
            }

            @Override
            public void setShowAsAction(int actionEnum) {

            }

            @Override
            public MenuItem setShowAsActionFlags(int actionEnum) {
                return null;
            }

            @Override
            public MenuItem setActionView(View view) {
                return null;
            }

            @Override
            public MenuItem setActionView(int resId) {
                return null;
            }

            @Override
            public View getActionView() {
                return null;
            }

            @Override
            public MenuItem setActionProvider(ActionProvider actionProvider) {
                return null;
            }

            @Override
            public ActionProvider getActionProvider() {
                return null;
            }

            @Override
            public boolean expandActionView() {
                return false;
            }

            @Override
            public boolean collapseActionView() {
                return false;
            }

            @Override
            public boolean isActionViewExpanded() {
                return false;
            }

            @Override
            public MenuItem setOnActionExpandListener(OnActionExpandListener listener) {
                return null;
            }
        };

        assertEquals(true, activityUnderTest.onActionItemClicked(goodActionMode, goodItem));
    }
}
