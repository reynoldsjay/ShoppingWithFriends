package com.generict.shoppingwithfriends;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Tests that the login functionality works.
 *
 * @author Jay Reynolds
 */
public class LoginTest extends ActivityInstrumentationTestCase2<LoginPageActivity> {

    private LoginPageActivity myActivity;
    private Button mBackButton;
    private Button mGoButton;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private String validUser;
    private String validPass;

    public LoginTest() {
        super(LoginPageActivity.class);
    }


    @Override
    protected void setUp() throws Exception {

        super.setUp();

        setActivityInitialTouchMode(true);

        myActivity = getActivity();

        mUsernameEditText = (EditText) myActivity.findViewById(R.id.username_field);
        mPasswordEditText = (EditText) myActivity.findViewById(R.id.password_field);
        mBackButton = (Button) myActivity.findViewById(R.id.back_button);
        mGoButton = (Button) myActivity.findViewById(R.id.go_button);

        validUser = "jayrey";
        validPass = "Testing1";
    }


    // Makes sure ui objects are in place
    public void testFieldsOnScreen() {
        final View decorView = myActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView, mUsernameEditText);
        ViewAsserts.assertOnScreen(decorView, mPasswordEditText);
        ViewAsserts.assertOnScreen(decorView, mBackButton);
        ViewAsserts.assertOnScreen(decorView, mGoButton);
    }


    // makes sure invalid inputs don't work
    public void testBlankInputs() {

        // Set up an ActivityMonitor for app home
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(ApplicationHomeActivity.class.getName(),
                        null, false);


        // Validate that app home hasn't started yet
        TouchUtils.clickView(this, mGoButton);
        ApplicationHomeActivity receiverActivity = (ApplicationHomeActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNull("Login page launched.", receiverActivity);

        // enter just a username
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                mUsernameEditText.requestFocus();
                mUsernameEditText.setText(validUser);
            }
        });

        // Validate that app home hasn't started yet
        TouchUtils.clickView(this, mGoButton);
        receiverActivity = (ApplicationHomeActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNull("Login page launched.", receiverActivity);


        // enter just a password
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                mUsernameEditText.requestFocus();
                mUsernameEditText.setText("");
                mPasswordEditText.requestFocus();
                mPasswordEditText.setText(validPass);
            }
        });


        // Validate that app home hasn't started yet
        TouchUtils.clickView(this, mGoButton);
        receiverActivity = (ApplicationHomeActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNull("Login page launched.", receiverActivity);


        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

    }


    // make sure entering a valid username and password launches the home activity
    public void testValidUser() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                mUsernameEditText.requestFocus();
                mUsernameEditText.setText(validUser);
                mPasswordEditText.requestFocus();
                mPasswordEditText.setText(validPass);
            }
        });

        // Set up an ActivityMonitor for app home
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(ApplicationHomeActivity.class.getName(),
                        null, false);


        // Validate that app home has launched
        TouchUtils.clickView(this, mGoButton);
        ApplicationHomeActivity receiverActivity = (ApplicationHomeActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(10000);
        assertNotNull("Login page didn't launch.", receiverActivity);


        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);
    }


}
