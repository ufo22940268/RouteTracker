package com.hongbosb.tracker;

import android.util.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.app.*;
import android.os.*;
import android.database.*;
import android.net.*;
import android.opengl.*;
import android.graphics.*;
import android.view.animation.*;
import android.location.*;

import java.util.*;

import org.json.*;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import com.hongbosb.tracker.model.*;
import com.hongbosb.tracker.util.*;
import com.hongbosb.tracker.util.MockUtils;


public class MainActivity extends FragmentActivity
{
    //Location[mProvider=network,mTime=1367291738389,mLatitude=30.2897668,mLongitude=120.0587243,mHasAltitude=false,mAltitude=0.0,mHasSpeed=false,mSpeed=0.0,mHasBearing=false,mBearing=0.0,mHasAccuracy=true,mAccuracy=40.5,mExtras=Bundle[mParcelledData.dataSize=212]]
    private static final LatLng MY_POS = new LatLng(30.2897668, 120.0587243);
    private static final LatLng MY_POS_OFFSET_1 = new LatLng(30.2997668, 120.0587243);
    private static final LatLng MY_POS_OFFSET_2 = new LatLng(30.2697668, 120.0987243);

    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private MyLocationListener mLocationListener = new MyLocationListener();;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUpMapIfNeeded();
        testListenToLocationUpdater();
        //testDrawLine();

        startService(new Intent(Intents.ACTION_MOCK_LOCATION));
        startService(new Intent(Intents.ACTION_RECORDER));
        //bindService(new Intent(Intents.ACTION_RECORDER), mRecorderConnection, Context.BIND_AUTO_CREATE);
    }


    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mLocationListener.reset();
        restoreRecords();
        System.out.println("++++++++++++++++++++size:" + Storage.mLls.size() + "++++++++++++++++++++");
    }

    private void restoreRecords() {
        LatLng[] lla = Storage.mLls.toArray(new LatLng[0]);
        mMap.addPolyline(new PolylineOptions()
                .add(Storage.mLls.toArray(lla))
                .color(Color.BLUE));
    }

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

    private void setUpMap() {
        mMap.setMyLocationEnabled(true);

        CameraPosition cp = new CameraPosition.Builder()
            .target(MY_POS)
            .zoom(13f)
            .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
    }

    private void testDrawLine() {
        ArrayList<LatLng> lls = MockUtils.makeMockLatLngList();
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(lls.toArray(new LatLng[0]))
                .color(Color.GREEN));
    }

    private void testListenToLocationUpdater() {
        // Acquire a reference to the system Location Manager
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Register the listener with the Location Manager to receive location updates
        mLocationManager.requestLocationUpdates(CustomConfig.MOCK_LOCATION_NAME, 0, 0, mLocationListener);
    }

    // Define a listener that responds to location updates
    private class MyLocationListener implements LocationListener {

        private Location mPrev;

        public void onLocationChanged(Location location) {
            // Called when a new location is found by the network location provider.
            //System.out.println("++++++++++++++++++++MainActivity:" + location + "++++++++++++++++++++");
            if (mPrev != null) {
                drawLine(mPrev, location);
            }
            //mark(location);
            mPrev = location;
        }

        public void reset() {
            mPrev = null;
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}
    };

    private void drawLine(Location from, Location to) {
        mMap.addPolyline(new PolylineOptions()
                .add(MapUtils.toLatLng(from), MapUtils.toLatLng(to))
                .color(Color.GREEN));
    }

    private void drawLine(LatLng from, LatLng to) {
        mMap.addPolyline(new PolylineOptions()
                .add(from, to)
                .color(Color.GREEN));
    }

    private void mark(Location loc) {
        LatLng ll = MapUtils.toLatLng(loc);
        mark(ll);
    }

    private void mark(LatLng ll) {
        mMap.addMarker(new MarkerOptions()
                .position(ll)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }
}
