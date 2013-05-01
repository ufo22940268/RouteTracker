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

public class RecorderService extends Service {

    static public final String TAG = "RecorderService";
    private int time;
    private List<LatLng> mLls = new ArrayList<LatLng>();

    private LocalBinder mIBinder = new LocalBinder();

    public void onCreate() {
        LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //manager.requestLocationUpdates(CustomConfig.MOCK_LOCATION_NAME, CustomConfig.MIN_UPDATE_TIME, 0, mLocationListener);

        Intent intent = new Intent("hongbosb.receiver");
        PendingIntent launchIntent = PendingIntent.getBroadcast(this, 5000, intent, 0);
        manager.requestLocationUpdates(CustomConfig.MOCK_LOCATION_NAME, 0, 0, launchIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public class LocalBinder extends Binder {
        RecorderService getService() {
            return RecorderService.this;
        }
    };
    
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    public List<LatLng> getLls() {
        return mLls;
    }

    // Define a listener that responds to location updates
    private LocationListener mLocationListener =  new LocationListener() {

        public void onLocationChanged(Location location) {
            System.out.println("++++++++++++++++++++RecorderService:" + location + "++++++++++++++++++++");
            mLls.add(MapUtils.toLatLng(location));
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}
    };
}
