package com.iamaravind.fcmapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ARAVIND on 28-11-2017.
 */

public class FcmIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCM Tocken", "Refreshed token: " + refreshedToken);
    }
}
