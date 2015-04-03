package com.generict.shoppingwithfriends;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * Activity where user can view his or her notifications
 * that is if a sales report matches an item in his or her
 * wishlist.
 */
@SuppressWarnings("ALL")
public class ViewNotificationsActivity extends ListActivity {
    public static final String TAG = "ViewNotifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button mBackButton;
        Log.d(TAG, "Running On Create");
        setContentView(R.layout.activity_view_notifications);
        final Activity activity = this;
        new NotificationsInitializationTask(activity).execute();
        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, ApplicationHomeActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_notifications, menu);
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Get the item that was clicked
        final SalesReport o = (SalesReport) this.getListAdapter().getItem(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Price: " + o.getPrice() + "\nLocation " + o.getLocation())
                .setTitle(o.getName());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private class NotificationsInitializationTask extends AsyncTask<Void, Void, Void> {
        ArrayList<SalesReport> match;
        Activity activity;

        NotificationsInitializationTask(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {
        }


        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<Item> items = (ArrayList<Item>) ParseUser.getCurrentUser().get("WishList");
            match = new ArrayList<>();
            for(final Item item : items) {
                ParseQuery<SalesReport> query = ParseQuery.getQuery("SalesReport");
                //I have a try catch here to handle early objects that don't have all attributes
                try {
                    query.whereEqualTo("name", item.getName());
                    List<SalesReport> itemList = query.find();
                    for (SalesReport matchingItem : itemList) {
                        if ((matchingItem.getPrice() <= item.getPrice())) {
                            Log.d(TAG, matchingItem.getName() + matchingItem.getLocation());
                            match.add(matchingItem);
                        }
                    }
                } catch (ParseException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void _) {
            super.onPostExecute(_);
            SalesReportArrayAdapter adapter;
            adapter = new SalesReportArrayAdapter(activity, android.R.layout.simple_list_item_1, match);
            setListAdapter(adapter);
        }
    }
}