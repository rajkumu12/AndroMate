package com.andromate;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;


//import com.google.firebase.crashlytics.FirebaseCrashlytics;

public class App extends Application {
    private static final String TAG = "App";
    //    private AppComponent mAppComponent;
    private static Context context;

  //  FirebaseCrashlytics crashlytics;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        try {
//            crashlytics = FirebaseCrashlytics.getInstance();
//            crashlytics.log("Start logging!");
//            crashlytics.setCustomKey("DeviceName", "" + Utility.getDevice());
//            crashlytics.setCustomKey("Email", "dnkumar.chauhan@developer.com");
//        }catch (Exception e) {
//            Log.e(TAG , "getMessage3 "+e.getMessage());
//        }

//        mAppComponent = DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .netModule(new NetModule("http://coincap.io"))
//                .build();

    }

    public static Context getAppContext() {
        return context;
    }


    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


//    public AppComponent getAppComponent(){
//        return mAppComponent;
//    }


}
