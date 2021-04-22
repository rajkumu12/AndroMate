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

import com.andromate.R;
import com.andromate.Receivers.MyStartServiceReceiver;
import com.andromate.Receivers.PackageremovalReceiver;
import com.andromate.SplashActivity;
import com.andromate.Ui.Activity.HomeActivity;

import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import static android.content.Context.ACTIVITY_SERVICE;

public class ExampleJobService extends JobService {
    private static final String TAG ="ExampleJobService" ;
    private LongRunningTask mRunningTask;

    PackageremovalReceiver packageremovalReceiver;
    @Override
    public boolean onStartJob(JobParameters params) {
        if (mRunningTask == null)
            mRunningTask = new LongRunningTask();
        SharedPreferences sharedpreferences =this.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        mRunningTask.startTask(this,sharedpreferences);

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
    void startTask(ExampleJobService exampleJobService, SharedPreferences sharedpreferences) {

        if (mTimer == null)
            mTimer = new Timer();
        Toast.makeText(exampleJobService, "fh", Toast.LENGTH_SHORT).show();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void run() {
                int a=count++;
               /* Log.d("dfndafdf","ooooooo"+sharedpreferences.getString("key",""));*/
               /*if (Helper.isAppRunning(exampleJobService, sharedpreferences.getString("key",""))) {
                    // App is running
                    Log.d("jdsakljdlkfjlkfalf","eee");
                    Toast.makeText(exampleJobService, "App Launched", Toast.LENGTH_SHORT).show();
                } else {
                    // App is not running
                    Log.d("jdsakljdlkfjlkfalf","fffff");
                }
*/
                String selApp=sharedpreferences.getString("key","");
                retriveNewApp(exampleJobService,selApp);
                String bs=sharedpreferences.getString("bs","");
               /* checkbattersavermode(exampleJobService,bs);*/
                String gps=sharedpreferences.getString("gps","");
                Log.d("jfhdlkjfhdlkf","gggg"+gps);
                ChecGps(exampleJobService,gps);

              /* final ActivityManager activityManager = (ActivityManager) exampleJobService.getSystemService(Context.ACTIVITY_SERVICE);
                final List<ActivityManager.RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
                Log.d("dfndafdf","ooooooo"+sharedpreferences.getString("key","")+"kkkkk"+recentTasks.size());
                for (int i = 0; i < recentTasks.size(); i++)
                {
                    Log.d("Runninask", "Running task: " + recentTasks.get(i).baseActivity.toShortString() + "\t\t ID: " + recentTasks.get(i).id);
                }
*/


               /* addNotification(exampleJobService,a);*/
                /*Log.e(TAG, "Executing long running task " + count++);*/
                  /*  showToast();*/

                // Create a network change broadcast receiver.

            }
        }, 0, 1000);
    }

    private void ChecGps(ExampleJobService exampleJobService, String gps) {
        LocationManager locationManager = (LocationManager) exampleJobService.getSystemService(Context.LOCATION_SERVICE);
        if (gps !=null && gps.equals("Enabled")){
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                addNotification(exampleJobService, "Gps Enable");
            }
            }else if (gps !=null && gps.equals("Disabled")){

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                addNotification(exampleJobService, "Gps Disabled");
            }
            }
        }



    private void checkbattersavermode(ExampleJobService exampleJobService, String bs) {





        PowerManager powerManager = (PowerManager)exampleJobService.getSystemService(Context.POWER_SERVICE);
        boolean powerSaveMode = powerManager.isPowerSaveMode();

        if (powerSaveMode && bs.equals("Enable")){
            addNotification(exampleJobService,"Battery saver mode is ON");
        }else {
         /*   addNotification(exampleJobService,"Battery saver mode is OFF");*/
        }


        if (isLocationEnabled(exampleJobService)){
            addNotification(exampleJobService,"Gps Enable");
        }else {
            addNotification(exampleJobService,"Gps Disable");
        }

       /* PowerManager powerManager = (PowerManager)
                exampleJobService.getSystemService(Context.POWER_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                && powerManager.isPowerSaveMode()) {
            // Animations are disabled in power save mode, so just show a toast instead.
            Log.d(TAG,"gfgfgf     On");
        }else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                && !powerManager.isPowerSaveMode()){
            Log.d(TAG,"gfgfgf     Off");
        }*/

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

    private String retriveNewApp(Context ctx, String selApp) {
        if (Build.VERSION.SDK_INT >= 21) {
            String currentApp = null;
            String nn = null;
            UsageStatsManager usm = (UsageStatsManager) ctx.getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            List<UsageStats> applist = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 1000, time);
            if (applist != null && applist.size() > 0) {
                SortedMap<Long, UsageStats> mySortedMap = new TreeMap<>();
                for (UsageStats usageStats : applist) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (mySortedMap != null && !mySortedMap.isEmpty()) {
                    currentApp = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                    nn = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                }
            }

            if (currentApp.equals(selApp)){
                addNotification(ctx,nn);
            }
            Log.e(TAG, "Current App in foreground is: " + currentApp);

            return currentApp;

        }
        else {

            ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
            String mm=(manager.getRunningTasks(1).get(0)).topActivity.getPackageName();
            String name=(manager.getRunningTasks(1).get(0)).topActivity.getClassName();
            if (mm.equals(selApp)){
                addNotification(ctx,name);
               /* Toast.makeText(context, ""+name+" is Running", Toast.LENGTH_SHORT).show();*/
            }
            Log.e(TAG, "Current App in foreground is: " + name);
            return mm;
        }
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }


    private void addNotification(Context context, String string) {
        String channelId =context.getString(R.string.default_notification_channel_id);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(""+string)
                        .setContentText("kl")
                        .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager)context. getSystemService(Context.NOTIFICATION_SERVICE);
        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background);
            notificationBuilder.setColor(0x169AB9);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
        assert notificationManager != null;
        notificationManager.notify(0  /*ID of notification*/, notificationBuilder.build());
    }
}
