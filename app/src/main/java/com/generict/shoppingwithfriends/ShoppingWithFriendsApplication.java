package com.generict.shoppingwithfriends;

import android.app.Application;
import com.parse.Parse;


/**
 * Created by vignesh on 2/19/15.
 */
public class ShoppingWithFriendsApplication extends Application {
    public void onCreate() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "Vzefv3Hqe6B34kWwEBUGnqCBbqvfRomeIQi9TL3U", "AXBCHigmBdZLL0NHJ4tTFAiyDFis7CO6BtWfEcnV");
    }
}
