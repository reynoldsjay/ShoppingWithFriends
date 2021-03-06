package com.generict.shoppingwithfriends;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jenna Kwon
 * TO MAKE THE LIST COMPATIBLE ACROSS ALL DEVICES, LOOK AT MASTER DETAIL DEMO!
 */


@SuppressWarnings("ALL")
public class UsersListActivity extends ListActivity implements ActionMode.Callback {

    private Object mActionMode;
    private int selectedItem = -1;
    private List<ParseUser> listOfUsers;
    private UserArrayAdapter adapter;

    //Helpful guide:
    //https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button mBackButton;
        listOfUsers = new ArrayList<>();
        //set layout
        setContentView(R.layout.activity_users_list);
        //Create new adapter and set it
        final Activity activity = this;
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    listOfUsers = objects;
                    List<ParseUser> friendsList = (List<ParseUser>) ParseUser.getCurrentUser().get("Friends");
                    for (ParseUser item : friendsList) {
                        listOfUsers.remove(item);
                    }
                    listOfUsers.remove(ParseUser.getCurrentUser());
                    adapter = new UserArrayAdapter(activity, android.R.layout.simple_list_item_1, listOfUsers);
                    setListAdapter(adapter);
                }
            }
        });
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
                UsersListActivity.this.startActionMode(UsersListActivity.this);
                view.setSelected(true);
                return true;
            }
        });

        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, FriendsListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Get the item that was clicked
        ParseUser o = (ParseUser) this.getListAdapter().getItem(position);
        FriendsListActivity.addFriend(o);
        Intent intent = new Intent(this, FriendsListActivity.class);
        startActivity(intent);
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
                Toast.makeText(UsersListActivity.this, String.valueOf(selectedItem), Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.menu_main_list, menu);
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
