package com.webakruti.designpractice;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.constraint.solver.widgets.Helper;
import android.support.multidex.MultiDex;

/**
 * Created by DELL on 5/25/2019.
 */

public class MyApplication extends Application {

    /*@Override
    public void onCreate() {
        super.onCreate();


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }*/

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    /*@Override
    protected void attachBaseContext(Context paramContext) {
        super.attachBaseContext(paramContext);
        Helper.install(MyApplication.this);
    }*/
}
