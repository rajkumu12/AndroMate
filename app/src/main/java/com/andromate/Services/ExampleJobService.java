package com.andromate.Services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.andromate.R;
import com.andromate.Receivers.MyStartServiceReceiver;
import com.andromate.Receivers.PackageremovalReceiver;
import com.andromate.SplashActivity;
import com.andromate.Ui.Activity.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ExampleJobService extends JobService {
    private LongRunningTask mRunningTask;
    PackageremovalReceiver packageremovalReceiver;
    @Override
    public boolean onStartJob(JobParameters params) {
        if (mRunningTask == null)
            mRunningTask = new LongRunningTask();
        mRunningTask.startTask(this);

        return true;
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        mRunningTask.finishTask();
        return true;
    }
}

class LongRunningTask {
    private final String TAG = "LongRunningTask";
    private Timer mTimer;
    private int count = 0;
    LongRunningTask() {
    }
    void startTask(ExampleJobService exampleJobService) {
        if (mTimer == null)
            mTimer = new Timer();
        Toast.makeText(exampleJobService, "fh", Toast.LENGTH_SHORT).show();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int a=count++;

                /*addNotification(exampleJobService,a);*/
                /*Log.e(TAG, "Executing long running task " + count++);*/
                  /*  showToast();*/

                // Create a network change broadcast receiver.

            }
        }, 0, 1000);
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
   /* private void addNotification(ExampleJobService exampleJobService, int count) {
        String channelId =exampleJobService.getString(R.string.default_notification_channel_id);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(exampleJobService, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("ok"+count)
                        .setContentText("kl")
                        .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager)exampleJobService. getSystemService(Context.NOTIFICATION_SERVICE);
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
        notificationManager.notify(0 *//* ID of notification *//*, notificationBuilder.build());
    }*/
}
