package com.generict.shoppingwithfriends;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.*;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class FriendsListActivity extends ListActivity implements ActionMode.Callback {


    protected Object mActionMode;
    public int selectedItem = -1;
    protected UserSingleton model = UserSingleton.getInstance();
    protected List<User> listOfUsers = model.getUsers();
    protected UserArrayAdapter adapter;
    private Button mAddFriends;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        //EmptyArrayList
        List<User> listOfFriends = new ArrayList<>();

        //Create new adaptor and set it
        adapter = new UserArrayAdapter(this, android.R.layout.simple_list_item_1, listOfFriends);

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
                FriendsListActivity.this.startActionMode(FriendsListActivity.this);
                view.setSelected(true);
                return true;
            }
        });


        //Activity to feed into the button
        final Activity activity = this;

        //Button that will lead you to adding friends
        mAddFriends = (Button) findViewById(R.id.add_friend_button);
        mAddFriends.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, FriendsListActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Handles dynamic insertion
     * HAVE TO FIGURE OUT THE LOGISTICS HERE!
     *
     * @param v view
     */
    public void addItems(View v, String name, String email, int rating, int numPostings) {
        model.getUsers().add(new User(name, email, rating, numPostings));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Get the item that was clicked
        User o = (User) this.getListAdapter().getItem(position);
        String keyword = o.getName();
        Toast.makeText(this, "You selected: " + keyword, Toast.LENGTH_SHORT)
                .show();
    }

    /**
     * ?
     */
    private void show() {
        Toast.makeText(FriendsListActivity.this, String.valueOf(selectedItem), Toast.LENGTH_LONG).show();
    }

    // Called when the action mode is created; startActionMode() was called
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // Inflate a menu resource providing context menu items
        MenuInflater inflater = mode.getMenuInflater();
        // Assumes that you have "contexual.xml" menu resources
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
            case R.id.menuitem1_show:
                show();
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

