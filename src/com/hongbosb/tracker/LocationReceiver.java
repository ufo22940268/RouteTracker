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

import com.hongbosb.tracker.model.*;
import com.hongbosb.tracker.util.*;

public class LocationReceiver extends BroadcastReceiver {

    static public final String TAG = "LocationReceiver";

    public void onReceive(Context context, Intent intent) {
        Location location = (Location)intent.getExtras().getParcelable(LocationManager.KEY_LOCATION_CHANGED);
        if (location != null) {
            Storage.mLls.add(MapUtils.toLatLng(location));
        } else {
            Log.d(TAG, "location is null");
        }
    }
}

