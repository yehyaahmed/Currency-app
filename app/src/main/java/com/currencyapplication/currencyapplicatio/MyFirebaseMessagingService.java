package com.currencyapplication.currencyapplicatio;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFireBaseIdService";


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"New Token: "+refreshToken);
        Common.currenToken = refreshToken;
    }
}
