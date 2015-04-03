package com.generict.shoppingwithfriends;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import java.util.ArrayList;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.location.Geocoder;
import android.location.Address;
import android.util.Log;

import java.util.List;


public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MapsActivity:", "Maps activity called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                Log.d("MapsActivity:", "mMap is not Null");
                setUpMap();
            }
        }else{
            Log.d("MapsActivity:", "mMap is Null");
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera.
     * Marker was added to a typical sale location, Atlantic Station.
     * This does not pull locations from items. This is hard coded.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        final ArrayList<ParseObject> salesReports = new ArrayList<>(10);

        Log.d("MapsActivity:", "setUpMap in action");
        //query is empty
        ParseQuery<ParseObject> query = ParseQuery.getQuery("SalesReport");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    for (ParseObject elem : parseObjects) {
                        salesReports.add(elem);
                    }
                }
            }
        });

        ArrayList<LatLng> list = new ArrayList<>(10);

        //can't get the sales reports
        for (ParseObject saleReport: salesReports) {
            Log.d("MapsActivity: ", "sale: " + saleReport.getString("name"));
            list.add(getLocationFromAddress(saleReport.getString("location")));

        }
        Log.d("MapsActivity:", "list size: " + list.size());

        //list is a list of lat/lng
        for (int i = 0; i < list.size(); i++) {
            mMap.addMarker(new MarkerOptions().position(list.get(i)).title(list.get(i).toString()));
        }
    }

    public LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            p1 = new LatLng((int) (location.getLatitude() * 1E6),
                    (int) (location.getLongitude() * 1E6));

            return p1;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return p1;
    }
}

//
//    public void ParseQueryMap() {
//        ParseQuery query = new ParseQuery("MyObject");
//        query.findInBackground(new FindCallback() {
//            public void done(List<ParseObject> myObject, ParseException e) {
//                if (e == null) {
//
//                    for ( int i = 0; i < myObject.size(); i++) {
//
//                        commGet =  myObject.get(i).getString("Comment");
//
//                        geo1Dub = myObject.get(i).getParseGeoPoint("location").getLatitude();
//                        geo2Dub = myObject.get(i).getParseGeoPoint("location").getLongitude();
//
//                        Location aLocation = new Location("first");
//                        aLocation.setLatitude(geo1Dub);
//                        aLocation.setLongitude(geo2Dub);
//                        Location bLocation = new Location("second");
//                        bLocation.setLatitude(location.getLatitude());
//                        bLocation.setLongitude(location.getLongitude());
//                        int distance = (int)aLocation.distanceTo(bLocation);
//                        if (distance<rad) {  // where "rad" radius display points
//                            myMap.addMarker(new MarkerOptions().position(new LatLng(geo1Dub,geo2Dub)).title(commGet)                                   .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//
//                        } else {
//                        }
//
//                    }
//
//                } else {
//                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
