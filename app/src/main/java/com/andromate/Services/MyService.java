package com.andromate.Services;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.andromate.R;
import com.andromate.Ui.Activity.HomeActivity;

import java.util.Random;

public class MyService extends  Service {


    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    private final static String TAG = "MyForegroundService";

    Random r;

    public MyService() {
    }

    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            //promote to foreground and create persistent notification.
            //in Oreo we only have a few seconds to do this or the service is killed.
          /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                startMyOwnForeground();
            }else {
                Notification notification = getNotification("MyService is running");
                startForeground(msg.arg1, notification);  //not sure what the ID needs to be.
            }
*/


            Log.d(TAG, "should be foreground now.");
            // Normally we would do some work here, like download a file.
            // For our example, we just sleep for 5 seconds then display toasts.
            //setup how many messages
            int times = 1, i;

            Bundle extras = msg.getData();
            if (extras != null) {
                times = extras.getInt("times", 1);  //default is one
            }
            //loop that many times, sleeping for 5 seconds.
            for (i = 0; i < times; i++) {
                synchronized (this) {
                    try {
                        wait(5000);
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.toString());
                    }
                }
                String info = i + " random " + r.nextInt(100);
                Log.d("intentServer", info);
                //make a toast
                //unable to ensure the toasts will always show, so use a handler and post it for later.
                // Toast.makeText(MyForeGroundService.this, info, Toast.LENGTH_SHORT).show();
                toast(info);
            }
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(msg.arg1);  //notification will go away as well.
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        private void startMyOwnForeground() {
            String NOTIFICATION_CHANNEL_ID = "com.example.simpleapp";
            String channelName = "My Background Service";
            NotificationChannel chan = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                chan.setLightColor(Color.BLUE);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            }
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            assert manager != null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                manager.createNotificationChannel(chan);
            }

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID);
            Notification notification = notificationBuilder.setOngoing(true)
                    .setSmallIcon(R.drawable.invite_icon)
                    .setContentTitle("App is running in background")
                    .setPriority(NotificationManager.IMPORTANCE_MIN)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .build();
            startForeground(2, notification);
        }
    }

    final Handler mHandler = new Handler();

    // Helper for showing tests
    void toast(final CharSequence text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyService.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreate() {
        r = new Random();
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;//needed for stop.

        if (intent != null) {
            msg.setData(intent.getExtras());
            mServiceHandler.sendMessage(msg);
        } else {
            Toast.makeText(MyService.this, "The Intent to start is null?!", Toast.LENGTH_SHORT).show();
        }

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }

    // build a persistent notification and return it.
    /*public Notification getNotification(String message) {

        return new NotificationCompat.Builder(getApplicationContext(), HomeActivity.id1)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(true)  //persistent notification!
                .setChannelId(HomeActivity.id1)
                .setContentTitle("Service")   //Title message top row.
                .setContentText(message)  //message when looking at the notification, second row
                .build();  //finally build and return a Notification.
    }*/
}