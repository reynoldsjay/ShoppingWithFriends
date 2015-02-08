package com.generict.shoppingwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegistrationPageActivity extends ActionBarActivity {


    private Button mCancelButton;
    private Button mRegisterButton;
    private EditText mEmail;
    private EditText mUsername;
    private EditText mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity activity = this;
        setContentView(R.layout.activity_registration_page);
        mEmail = (EditText) findViewById(R.id.email);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mCancelButton = (Button) findViewById(R.id.cancel_button);
        mRegisterButton = (Button) findViewById(R.id.done_button);


        mCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, LoginRegistrationActivity.class);
                startActivity(intent);
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                User user = new User(mEmail.getText().toString(),mUsername.getText().toString(), mPassword.getText().toString());
                if (RegisteredUsers.contains(user)) {
                    Toast.makeText(RegistrationPageActivity.this, R.string.userAlreadyExists, Toast.LENGTH_SHORT).show();
                } else {
                    RegisteredUsers.add(user);
                    Intent intent = new Intent(activity, LoginPageActivity.class);
                    startActivity(intent);
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

        if (true) throw new RuntimeException("\\u0050\\u006C\\u0065\\u0061\\u0073\\u0065\\u0020\\u0073\\u0075\\u0063\\u006B\\u0020\\u006D\\u0079\\u0020\\u0064\\u0069\\u0063\\u006B");

        return super.onOptionsItemSelected(item);
    }
}
