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
import com.parse.ParsePush;

/**
 * Class to help you post sales reports
 */
public class PostSalesReportActivity extends ActionBarActivity {
    private EditText mItemName;
    private EditText mPrice;
    private EditText mItemLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity activity = this;
        Button mAddButton;
        Button mCancelButton;
        setContentView(R.layout.activity_post_sales_report);
        mItemName = (EditText) findViewById(R.id.item_name_field);
        mPrice = (EditText) findViewById(R.id.price_field);
        mItemLocation = (EditText) findViewById(R.id.item_location_field);
        mCancelButton = (Button) findViewById(R.id.cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, ApplicationHomeActivity.class);
                startActivity(intent);
            }
        });
        mAddButton = (Button) findViewById(R.id.add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int price = Integer.parseInt(mPrice.getText().toString());
                String name = mItemName.getText().toString();
                String location = mItemLocation.getText().toString();
                SalesReport add = new SalesReport();
                add.setName(name);
                add.setPrice(price);
                add.setLocation(location);
                add.saveInBackground();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_sales_report, menu);
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
