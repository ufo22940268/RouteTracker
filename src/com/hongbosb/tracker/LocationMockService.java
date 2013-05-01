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

public class LocationMockService extends Service {

    static public final String TAG = "LocationMockService";
    private LocationManager mLocationManager;
    private boolean mRunning;

    @Override
    public void onCreate() {
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (mLocationManager.getAllProviders().contains(CustomConfig.MOCK_LOCATION_NAME)) {
            mLocationManager.removeTestProvider(CustomConfig.MOCK_LOCATION_NAME);
        }
        mLocationManager.addTestProvider(CustomConfig.MOCK_LOCATION_NAME, true, false, false, false, false, true, false, 1, 1);
        mLocationManager.setTestProviderEnabled(CustomConfig.MOCK_LOCATION_NAME, true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mRunning) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mRunning = true;
                    ArrayList<LatLng> lls = MockUtils.makeMockLatLngList();
                    for (LatLng ll : lls) {
                        mockLocation(ll);

                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    mRunning = false;
                }
            }).start();
        }
        return START_STICKY;
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void mockLocation(LatLng pos) {
        Location loc = new Location(CustomConfig.MOCK_LOCATION_NAME);
        loc.setLatitude(pos.latitude);
        loc.setLongitude(pos.longitude);
        mLocationManager.setTestProviderLocation(CustomConfig.MOCK_LOCATION_NAME, loc);
    }
}
