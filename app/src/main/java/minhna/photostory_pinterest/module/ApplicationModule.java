package minhna.photostory_pinterest.module;

import android.app.Application;

import com.pinterest.android.pdk.PDKClient;

/**
 * Created by Administrator on 25-Jan-17.
 */

public class ApplicationModule extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PDKClient.configureInstance(this, AC.APP_ID);
        PDKClient.getInstance().onConnect(this);
    }

}
