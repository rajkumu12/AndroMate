package com.andromate.Constraints.FServices;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.andromate.Constraints.IdentifyandPerformAction;
import com.andromate.Constraints.TriigersList;
import com.andromate.R;
import com.andromate.Services.ExampleJobService;
import com.andromate.Ui.Activity.HomeActivity;
import com.andromate.db.DBHelper;

import static com.andromate.Constraints.TriigersList.addNotification;
import static com.andromate.Constraints.TriigersList.checkBlutooth_trigger;
import static com.andromate.Constraints.TriigersList.checkbattersavermode;
import static com.andromate.Constraints.TriigersList.retriveNewApp;

public class ForegroundService extends Service {
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Andromate Running")
                .setContentText(input)
                .setSmallIcon(R.drawable.android_icon)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        /*checkUsbstate(this,"jdjdjd");*/
        //do heavy work on a background thread
        //stopSelf();

        final JobScheduler scheduler = (JobScheduler) getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(this, ExampleJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .build();
        if (scheduler != null) {
            // Checking if job is already running
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (scheduler.getPendingJob(1) == jobInfo){

                }

            }
            scheduler.schedule(jobInfo);
        }

        IdentifyandPerformAction.airplanemode_on_of(this);
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Andromate running in background",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }


    public static void checkUsbstate(Context context, String message) {
        BroadcastReceiver mReceiver;
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                /*Log.d(LOG_TAG, "Received Broadcast: "+action);*/
                if (action.equalsIgnoreCase("android.hardware.usb.action.USB_STATE")) { //Check if change in USB state
                    if (intent.getExtras().getBoolean("connected")) {

                            addNotification(context, "Connected");
                        // USB was connected
                    } else {

                            addNotification(context, "Disconnected");

                        // USB was disconnected
                    }
                }

            }
        };
        context.registerReceiver(mReceiver,
                new IntentFilter("android.hardware.usb.action.USB_STATE"));


    }

}
