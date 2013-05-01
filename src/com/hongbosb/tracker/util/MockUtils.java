package com.hongbosb.tracker.util;

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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import com.hongbosb.tracker.model.*;
import com.hongbosb.tracker.util.*;

public class MockUtils {
    private static final LatLng MY_POS = new LatLng(30.2897668, 120.0587243);
    static public final LatLng[] MOVE_PATTERNS = {
        new LatLng(0.00, 0.005),
        new LatLng(-0.003, 0.00),
    };

    public static ArrayList<LatLng> makeMockLatLngList() {
        ArrayList<LatLng> lls = new ArrayList<LatLng>();
        lls.add(MY_POS);
        for (int i = 0; i <= 500; i ++) {
            LatLng d = MOVE_PATTERNS[i%MOVE_PATTERNS.length];
            LatLng l = lls.get(lls.size() - 1);
            LatLng nll = new LatLng(l.latitude + d.latitude, l.longitude + d.longitude);
            lls.add(nll);
        }
        return lls;
    }
}
