package com.generict.shoppingwithfriends;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Testing the user's inputs for registering a new user.
 *
 * @author Vignesh
 *
 */
public class RegistrationTest extends ActivityInstrumentationTestCase2<RegistrationPageActivity> {

    private RegistrationPageActivity myActivity;
    private Button mCancelButton;
    private Button mRegisterButton;
    private EditText mName;
    private EditText mEmail;
    private EditText mUsername;
    private EditText mPassword;



    public RegistrationTest() {
        super(RegistrationPageActivity.class);
    }

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        setActivityInitialTouchMode(true);

        myActivity = getActivity();

        mName = (EditText) myActivity.findViewById(R.id.name);
        mEmail = (EditText) myActivity.findViewById(R.id.email);
        mUsername = (EditText) myActivity.findViewById(R.id.username);
        mPassword = (EditText) myActivity.findViewById(R.id.password);
        mCancelButton = (Button) myActivity.findViewById(R.id.cancel_button);
        mRegisterButton = (Button) myActivity.findViewById(R.id.done_button);
    }


    // Makes sure ui objects are in place
    public void testFieldsOnScreen() {
        final View decorView = myActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView, mCancelButton);
        ViewAsserts.assertOnScreen(decorView, mRegisterButton);
        ViewAsserts.assertOnScreen(decorView, mName);
        ViewAsserts.assertOnScreen(decorView, mUsername);
        ViewAsserts.assertOnScreen(decorView, mEmail);
        ViewAsserts.assertOnScreen(decorView, mPassword);
    }


    // makes sure invalid inputs don't work
    public void testBlankInputs() {

        // Set up an ActivityMonitor for LoginPage
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(LoginPageActivity.class.getName(),
                        null, false);


        // Validate that LoginPage hasn't started yet
        TouchUtils.clickView(this, mRegisterButton);
        LoginPageActivity receiverActivity = (LoginPageActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNull("Login page launched.", receiverActivity);


        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                mName.requestFocus();
                mName.setText("George P");
            }
        });


        // Validate that LoginPage hasn't started yet
        TouchUtils.clickView(this, mRegisterButton);
        receiverActivity = (LoginPageActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNull("Login page launched.", receiverActivity);


        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                mEmail.requestFocus();
                mEmail.setText("georgep@gmail.com");
            }
        });

        // Validate that LoginPage hasn't started yet
        TouchUtils.clickView(this, mRegisterButton);
        receiverActivity = (LoginPageActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNull("Login page launched.", receiverActivity);


        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                mUsername.requestFocus();
                mUsername.setText("george");
            }
        });


        // Validate that LoginPage hasn't started yet
        TouchUtils.clickView(this, mRegisterButton);
        receiverActivity = (LoginPageActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNull("Login page launched.", receiverActivity);



        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

    }

}
