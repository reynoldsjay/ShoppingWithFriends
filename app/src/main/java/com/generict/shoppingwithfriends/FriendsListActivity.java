package com.generict.shoppingwithfriends;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Shows the current users friends.
*/
public class FriendsListActivity extends ListActivity implements ActionMode.Callback {


    public Object mActionMode;
    public int selectedItem = -1;
    protected static UserArrayAdapter adapter;
    private Button mAddFriends;
    public static final String TAG = "FriendListActivity";
    public Button mBackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        //Create new adaptor and set it
        ArrayList<ParseUser> listOfFriends = (ArrayList<ParseUser>) ParseUser.getCurrentUser().get("Friends");
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
                Intent intent = new Intent(activity, UsersListActivity.class);
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

    /**
     * Handles dynamic insertion
     *
     * @param user clicked user
     */
    public static void addFriend(ParseUser user) {
        List<ParseUser> listOfFriends = (List<ParseUser>) ParseUser.getCurrentUser().get("Friends");
        listOfFriends.add(user);
        ParseUser.getCurrentUser().put("Friends", listOfFriends);
        ParseUser.getCurrentUser().saveInBackground();
        adapter.notifyDataSetChanged();
    }

    /**
     * Handles dynamic deletion
     *
     * @param user clicked user
     */
    public static void deleteFriend(ParseUser user) {
        List<ParseUser> listOfFriends = (List<ParseUser>) ParseUser.getCurrentUser().get("Friends");
        listOfFriends.remove(user);
        ParseUser.getCurrentUser().put("Friends", listOfFriends);
        ParseUser.getCurrentUser().saveInBackground();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Get the item that was clicked
        final ParseUser o = (ParseUser) this.getListAdapter().getItem(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(o.getEmail() + "\nRating: " + o.get("Rating").toString() + "\n" + "Number of Postings: " + o.get("NumPostings"))
                .setTitle(o.get("Name").toString());
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                deleteFriend(o);
            }
        });
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
            case R.id.menuitem1_show:
                Toast.makeText(FriendsListActivity.this, String.valueOf(selectedItem), Toast.LENGTH_LONG).show();
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

