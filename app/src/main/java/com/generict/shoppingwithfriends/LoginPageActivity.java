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

/**
 * Login page view
 * @author Vignesh
 * @version 1.0
 */
public class LoginPageActivity extends ActionBarActivity {

    private Button mBackButton;
    private Button mGoButton;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity activity = this;
        setContentView(R.layout.activity_login_page);
        mUsernameEditText = (EditText) findViewById(R.id.username_field);
        mPasswordEditText = (EditText) findViewById(R.id.password_field);
        mBackButton = (Button) findViewById(R.id.back_button);
        // go back to login and registration button
        mBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, LoginRegistrationActivity.class);
                startActivity(intent);
            }
        });
        mGoButton = (Button) findViewById(R.id.go_button);
        mGoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // check if user is valid and login if it is
                User user = new User(mUsernameEditText.getText().toString(), mPasswordEditText.getText().toString());
                if (!user.isValid()) {
                    Toast.makeText(LoginPageActivity.this, R.string.incorrectLoginToast, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(activity, ApplicationHomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_registration, menu);
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