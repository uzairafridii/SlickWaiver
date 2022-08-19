package com.uzair.slickwaiver;

import android.app.Application;

import com.onesignal.OneSignal;

public class MyApplication extends Application
{
    // onesignal demo app id #242c5fac-4afc-4091-9e9e-698beebe7d40
    private static final String ONESIGNAL_APP_ID = "242c5fac-4afc-4091-9e9e-698beebe7d40";

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}
