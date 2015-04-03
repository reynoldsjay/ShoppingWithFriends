package com.generict.shoppingwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;

/**
 * Shows the registration view.
 * @author Jay Reynolds
 * @version 1.0
 */
public class RegistrationPageActivity extends ActionBarActivity {

    private EditText mName;
    private EditText mEmail;
    private EditText mUsername;
    private EditText mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity activity = this;
        Button mCancelButton;
        Button mRegisterButton;
        setContentView(R.layout.activity_registration_page);
        mName = (EditText) findViewById(R.id.name);
        mEmail = (EditText) findViewById(R.id.email);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mCancelButton = (Button) findViewById(R.id.cancel_button);
        mRegisterButton = (Button) findViewById(R.id.done_button);

        // cancel goes back to view with login and register buttons
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, LoginRegistrationActivity.class);
                startActivity(intent);
            }
        });

        // done creating user goes to login page
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // makes sure username isn't taken
                if (mName.getText().toString().equals("") || mEmail.getText().toString().equals("") || mUsername.getText().toString().equals("") || mPassword.getText().toString().equals("")){
                    Toast.makeText(RegistrationPageActivity.this, R.string.fillAll, Toast.LENGTH_SHORT).show();
                } else {
                    // adds user to static collection
                    ParseUser user = new ParseUser();
                    user.setUsername(mUsername.getText().toString());
                    user.setPassword(mPassword.getText().toString());
                    user.setEmail(mEmail.getText().toString());
                    user.put("Name", mName.getText().toString());
                    user.put("Rating", 0);
                    user.put("Friends", new ArrayList<ParseUser>());
                    user.put("NumPostings", 0);
                    user.put("WishList", new ArrayList<Item>());
                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                Intent intent = new Intent(activity, LoginPageActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegistrationPageActivity.this, "Invalid registration", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration_page, menu);
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
