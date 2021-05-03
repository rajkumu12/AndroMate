package com.andromate.Services;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.andromate.Constraints.TriigersList;
import com.andromate.R;
import com.andromate.Receivers.MyStartServiceReceiver;
import com.andromate.Receivers.PackageremovalReceiver;
import com.andromate.SplashActivity;
import com.andromate.Ui.Activity.HomeActivity;
import com.andromate.db.DBHelper;

import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import static android.content.Context.ACTIVITY_SERVICE;
import static com.andromate.Constraints.TriigersList.checkBlutooth_trigger;
import static com.andromate.Constraints.TriigersList.checkbattersavermode;
import static com.andromate.Constraints.TriigersList.retriveNewApp;

public class ExampleJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    private LongRunningTask mRunningTask;

    PackageremovalReceiver packageremovalReceiver;

    @Override
    public boolean onStartJob(JobParameters params) {
        if (mRunningTask == null)
            mRunningTask = new LongRunningTask();
        DBHelper dbHelper=new DBHelper(this);
        SharedPreferences sharedpreferences = this.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        mRunningTask.startTask(this, sharedpreferences,dbHelper);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        mRunningTask.finishTask();
        return true;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.d(TAG, "TASK REMOVED");

        PendingIntent service = PendingIntent.getService(
                getApplicationContext(),
                1001,
                new Intent(getApplicationContext(), ExampleJobService.class),
                PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, service);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}

class LongRunningTask {
    private final String TAG = "LongRunningTask";
    private Timer mTimer;
    private int count = 0;

    LongRunningTask() {
    }

    void startTask(ExampleJobService exampleJobService, SharedPreferences sharedpreferences, DBHelper dbHelper) {

        if (mTimer == null)
            mTimer = new Timer();
        Toast.makeText(exampleJobService, "fh", Toast.LENGTH_SHORT).show();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void run() {
                int a = count++;
                String selApp = sharedpreferences.getString("key", "");

                retriveNewApp(exampleJobService, selApp);
                String bs = sharedpreferences.getString("bs", "");
                checkbattersavermode(exampleJobService,bs);
                String gps = sharedpreferences.getString("gps", "");
                Log.d("jfhdlkjfhdlkf", "gggg" + gps);
                TriigersList.ChecGps(exampleJobService, gps,dbHelper);


                String appevent = sharedpreferences.getString("appevent", "");
                TriigersList.check_appinstallUninstall(exampleJobService, appevent,dbHelper);


                String boot = sharedpreferences.getString("db", "");
                TriigersList.device_boot(exampleJobService,boot);


                String bt = sharedpreferences.getString("bt", "");

                checkBlutooth_trigger(exampleJobService,bt);
                String dataon = sharedpreferences.getString("data_on", "");

                TriigersList.checkdataenble(exampleJobService,dataon);
                String headphone = sharedpreferences.getString("head", "");

                TriigersList.headphonesInserted(exampleJobService,headphone);
                TriigersList.check_hot_spot(exampleJobService,headphone);

                String mobile_service = sharedpreferences.getString("mob_ser", "");

                TriigersList.newtVail(exampleJobService,mobile_service);

                String usb = sharedpreferences.getString("usb", "");

                TriigersList.checkUsbstate(exampleJobService,usb);


                String roaming = sharedpreferences.getString("roaming", "");
                TriigersList.roamingcheck(exampleJobService,roaming);


                String flip_sensor = sharedpreferences.getString("flip", "");
                TriigersList.flipdevice(exampleJobService,flip_sensor);


                String lightsensor = sharedpreferences.getString("light", "");
                String point = sharedpreferences.getString("point", "");
                TriigersList.showLightSensor(exampleJobService,lightsensor,point);


                /*String proximity = sharedpreferences.getString("porximity", "");
                TriigersList.proximity_sensor(exampleJobService,proximity);*/

                String orientation = sharedpreferences.getString("orientation", "");
                TriigersList.show_orientation(exampleJobService,orientation);

                String shaking = sharedpreferences.getString("shaking", "");
                TriigersList.shaking(exampleJobService,shaking);

                //music
                String musicstate = sharedpreferences.getString("music", "");
                TriigersList.musicplayingcheck(exampleJobService,musicstate);

                String callstate = sharedpreferences.getString("call", "");
                String num = sharedpreferences.getString("call", "");
                TriigersList.checkCallState(exampleJobService,callstate);
            }
        }, 0, 15000);
    }



/*

    private void showToast() {
        Toast.makeText(, "", Toast.LENGTH_SHORT).show();
    }
*/

    void finishTask() {
        if (mTimer == null) return;
        mTimer.cancel();
    }






}
