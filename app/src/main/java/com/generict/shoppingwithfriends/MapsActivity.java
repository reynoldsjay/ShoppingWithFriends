package com.generict.shoppingwithfriends;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.location.Criteria;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener mlocListener = new MyLocationListener(this);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        centerMapOnMyLocation();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
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
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera.
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     *
     * Gets everything from parse query and sets up map
     * PARSE QUERY IS EMPTY RIGHT NOW
     */
    private void setUpMap() {

        ArrayList<LatLng> hcList = new ArrayList<>(10);
        hcList.add(new LatLng(33.7744, -84.3965));
        hcList.add(new LatLng(33.7744, -84.3865));
        hcList.add(new LatLng(33.7744, -84.3765));
        hcList.add(new LatLng(33.7744, -84.3665));
        mMap.addMarker(new MarkerOptions().position(hcList.get(1)).title("Clough"));
        mMap.addMarker(new MarkerOptions().position(hcList.get(2)).title("Clough2"));
        mMap.addMarker(new MarkerOptions().position(hcList.get(3)).title("Clough3"));
        mMap.addMarker(new MarkerOptions().position(hcList.get(4)).title("Clough4"));



        ParseObject.registerSubclass(SalesReport.class);
        final ArrayList<SalesReport> salesReports = new ArrayList<>(20);
        Log.d("MapsActivity:", "setUpMap in action");
        // BUG = PARSE QUERY IS EMPTY
        // CANNOT FIGURE OUT WHY
        ParseQuery<SalesReport> query = ParseQuery.getQuery(SalesReport.class).setLimit(100);
        query.findInBackground(new FindCallback<SalesReport>() {
            @Override
            public void done(List<SalesReport> reports, ParseException e) {
                if (e == null) {
                    for (SalesReport report : reports) {
                        salesReports.add(report);
                    }
                } else {
                    Log.d("Query exc raised....", "Exception!");
                }
            }
        });
        ArrayList<LatLng> list = new ArrayList<>(20);
        //can't get the sales reports
        for (SalesReport saleReport : salesReports) {
            Log.d("MapsActivity: ", "sale: " + saleReport.getString("name"));
            list.add(getLocationFromAddress(saleReport.getString("location")));
        }
        Log.d("MapsActivity:", "list size: " + list.size());
        //list is a list of lat/lng
        for (int i = 0; i < list.size(); i++) {
            mMap.addMarker(new MarkerOptions().position(list.get(i)).title(list.get(i).toString()));
        }
    }


    /**
     * Private helper method for getting a point based on string address
     * @param strAddress address in strings
     * @return LatLng object
     */
    public LatLng getLocationFromAddress(String strAddress) {
        Geocoder coder = new Geocoder(this);
        List<Address> address;
        LatLng p1;
        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            p1 = new LatLng((int) (location.getLatitude() * 1E6),
                    (int) (location.getLongitude() * 1E6));

            return p1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Private helper method for centering the map on the current location
     */
    private void centerMapOnMyLocation() {
        mMap.setMyLocationEnabled(true);
        Location location = getMyLocation();
        LatLng myLocation = null;
        if (location != null) {
            myLocation = new LatLng(location.getLatitude(),
                    location.getLongitude());
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 16));
    }


    /**
     * Private helper method for getting user's current loc
     * @return dynamic current location
     */
    private Location getMyLocation() {
        // Get location from GPS if it's available
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location myLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        // Location wasn't found, check the next most accurate place for the current location
        if (myLocation == null) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            // Finds a provider that matches the criteria
            String provider = lm.getBestProvider(criteria, true);
            // Use the provider to get the last known location
            myLocation = lm.getLastKnownLocation(provider);
        }
        return myLocation;
    }
}