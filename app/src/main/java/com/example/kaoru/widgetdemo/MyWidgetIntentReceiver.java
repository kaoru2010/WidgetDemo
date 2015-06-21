package com.example.kaoru.widgetdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by kaoru on 15/06/21.
 */
public class MyWidgetIntentReceiver extends BroadcastReceiver {
    private static final String TAG = "MyWidgetIntentReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, intent.toString());
    }
}
