package minhna.photostory_pinterest.module;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.pinterest.android.pdk.PDKClient;

import minhna.photostory_pinterest.R;

/**
 * Created by Administrator on 25-Jan-17.
 */

public class ApplicationModule extends Application {

    private Tracker mTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        PDKClient.configureInstance(this, AC.APP_ID);
        PDKClient.getInstance().onConnect(this);
    }

    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }


}
