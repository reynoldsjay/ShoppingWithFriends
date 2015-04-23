package com.generict.shoppingwithfriends;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.PushService;
import com.parse.SaveCallback;
import com.parse.ParseInstallation;


/**
 * Application startup. Init Parse.
 */
public class ShoppingWithFriendsApplication extends Application {
    public void onCreate() {
        // Enable Local data store.
        Parse.enableLocalDatastore(this);
//        ParseObject.registerSubclass(Item.class);
//        ParseObject.registerSubclass(SalesReport.class);
        Parse.initialize(this, "nm6pIvVQ1qGqOoDWDRgcsxvasrt6LPZF7WKmQ8HM", "qfjcEM85Wpmw6ozG2Fzrmf0vE5Dyc9XOx1x6MnDd");
        ParsePush.subscribeInBackground("testing", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

//        // PUSH SENT WHEN NEW ITEM IS ADDED
//        ParsePush push = new ParsePush();
//        push.setChannel("testing");
//        push.setMessage("New Item has been added by your friend!");
//        push.sendInBackground();
    }
}
