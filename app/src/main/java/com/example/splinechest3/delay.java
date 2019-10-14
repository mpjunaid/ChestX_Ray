package com.example.splinechest3;

import android.app.Application;
import android.os.SystemClock;

public class delay extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(500);
    }
}
