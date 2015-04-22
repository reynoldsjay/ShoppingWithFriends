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
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import java.io.IOException;
import android.widget.Toast;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.places.ui.PlacePicker.IntentBuilder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.Context;
import android.app.Activity;


/**
 * Created by JennaKwon on 4/22/15.
 */
public class MyLocationListener implements LocationListener {

    Context c;

    public MyLocationListener(Context context) {
        this.c = context;
    }

    @Override
    public void onLocationChanged(Location loc) {
        loc.getLatitude();
        loc.getLongitude();
        String Text = "My current location is: " + "Latitude = " + loc.getLatitude() +
                "Longitude = " + loc.getLongitude();
//        Toast.makeText(this, Text, Toast.LENGTH_LONG).show();
        Toast.makeText(c, String.valueOf(Text), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(c, "GPS Disabled", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(c, "GPS enabled", Toast.LENGTH_SHORT).show();
    }

    @Override

    public void onStatusChanged(String provider, int status, Bundle extras){
    }
}