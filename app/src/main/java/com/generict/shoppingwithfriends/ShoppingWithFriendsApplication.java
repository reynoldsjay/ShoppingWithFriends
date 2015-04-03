package com.generict.shoppingwithfriends;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;


/**
 * Application startup. Init Parse.
 */
public class ShoppingWithFriendsApplication extends Application {
    public void onCreate() {
        // Enable Local data store.
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Item.class);
        ParseObject.registerSubclass(SalesReport.class);
        Parse.initialize(this, "nm6pIvVQ1qGqOoDWDRgcsxvasrt6LPZF7WKmQ8HM", "qfjcEM85Wpmw6ozG2Fzrmf0vE5Dyc9XOx1x6MnDd");
    }
}
