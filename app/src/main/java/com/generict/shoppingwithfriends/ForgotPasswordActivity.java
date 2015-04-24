package com.generict.shoppingwithfriends;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;


public class ForgotPasswordActivity extends ActionBarActivity {

    private EditText emailEditText;
    private Button mDoneButton;
    private Button mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        final Activity activity = this;

        emailEditText = (EditText) findViewById(R.id.email);
        emailEditText.addTextChangedListener(new TextValidator(emailEditText) {
            //Override the validator of the abstract class
            @Override
            public void validate(TextView textView, String text) {
                if (text.equals("")) {
                    textView.setError("Please enter a valid email ID");
                }
            }
        });

        mDoneButton = (Button) findViewById(R.id.done_button);
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Done button displays a message and takes the user back to the login page.
                String email = emailEditText.getText().toString();
                ParseUser.requestPasswordResetInBackground(email,
                        new RequestPasswordResetCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                                    builder.setMessage("Your password details have been sent to you through email");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent intent = new Intent(activity, LoginPageActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                } else {
                                    emailEditText.setError("Enter a valid email");
                                }
                            }
                        });
            }
        });

        mBackButton = (Button)findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            //Back button takes the user back to the login fragment
            public void onClick(View v) {
                Intent intent = new Intent(activity, LoginPageActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forgot_password, menu);
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
