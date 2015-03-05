package com.generict.shoppingwithfriends;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;


/**
 * Application startup. Init Parse.
 */
public class ShoppingWithFriendsApplication extends Application {
    public void onCreate() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Item.class);
        Parse.initialize(this, "Vzefv3Hqe6B34kWwEBUGnqCBbqvfRomeIQi9TL3U", "AXBCHigmBdZLL0NHJ4tTFAiyDFis7CO6BtWfEcnV");
    }
}
