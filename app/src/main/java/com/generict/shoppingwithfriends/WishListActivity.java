package com.generict.shoppingwithfriends;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Shows the current user's wish list
 */
public class WishListActivity extends ListActivity implements ActionMode.Callback {

    protected Object mActionMode;
    public int selectedItem = -1;
    protected static ItemArrayAdapter adapter;
    public static final String TAG = "WishListActivity";
    public Button mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        Button mAddItemsButton;
        ArrayList<Item> items = (ArrayList<Item>) ParseUser.getCurrentUser().get("WishList");
        Log.d(TAG, String.valueOf(items));
        adapter = new ItemArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        Log.d(TAG, String.valueOf(adapter));
        setListAdapter(adapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //Set list view click stuff
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (mActionMode != null) {
                    return false;
                }
                selectedItem = position;

                // Start the CAB using the ActionMode.Callback defined above
                WishListActivity.this.startActionMode(WishListActivity.this);
                view.setSelected(true);
                return true;
            }
        });
        //Activity to feed into the button
        final Activity activity = this;

        mAddItemsButton = (Button) findViewById(R.id.add_items_button);
        mAddItemsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, AddToWishListActivity.class);
                startActivity(intent);
            }
        });

        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, ApplicationHomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Get the item that was clicked
        final Item o = (Item) this.getListAdapter().getItem(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            builder.setMessage("Price: " + o.getPrice())
                    .setTitle(o.getName());
        } catch (ParseException e) {
            Log.d(TAG, e.getMessage());
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Called when the action mode is created; startActionMode() was called
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // Inflate a menu resource providing context menu items
        MenuInflater inflater = mode.getMenuInflater();
        // Assumes that you have "contextual.xml" menu resources
        inflater.inflate(R.menu.rowselection, menu);
        return true;
    }

    // Called each time the action mode is shown. Always called after
    // onCreateActionMode, but
    // may be called multiple times if the mode is invalidated.
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false; // Return false if nothing is done
    }

    // Called when the user selects a contextual menu item
    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item1_show:
                Toast.makeText(WishListActivity.this, String.valueOf(selectedItem), Toast.LENGTH_LONG).show();
                // Action picked, so close the CAB
                mode.finish();
                return true;
            default:
                return false;
        }
    }

    // Called when the user exits the action mode
    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mActionMode = null;
        selectedItem = -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friends_list, menu);
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

        Toast.makeText(this,
                String.valueOf(getListView().getCheckedItemCount()),
                Toast.LENGTH_LONG).show();
        return true;
    }
}
