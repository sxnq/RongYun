package baway.com.rongyun;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by : Xunqiang
 * 2017/7/28 19:49
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RongIM.init(this);
    }
}
