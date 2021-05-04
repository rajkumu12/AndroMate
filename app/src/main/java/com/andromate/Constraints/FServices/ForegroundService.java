package com.andromate.Constraints.FServices;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.andromate.Constraints.IdentifyandPerformAction;
import com.andromate.Constraints.TriigersList;
import com.andromate.R;
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
        DBHelper dbHelper=new DBHelper(this);
        SharedPreferences sharedpreferences = this.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        String selApp = sharedpreferences.getString("key", "");

        retriveNewApp(this, selApp,dbHelper);
        String bs = sharedpreferences.getString("bs", "");
        checkbattersavermode(this,bs);
        String gps = sharedpreferences.getString("gps", "");
        Log.d("jfhdlkjfhdlkf", "gggg" + gps);
        TriigersList.ChecGps(this, gps,dbHelper);


        String appevent = sharedpreferences.getString("appevent", "");
        TriigersList.check_appinstallUninstall(this, appevent,dbHelper);


        String boot = sharedpreferences.getString("db", "");
        TriigersList.device_boot(this,boot);


        String bt = sharedpreferences.getString("bt", "");

        checkBlutooth_trigger(this,bt);
        String dataon = sharedpreferences.getString("data_on", "");

        TriigersList.checkdataenble(this,dataon);
        String headphone = sharedpreferences.getString("head", "");

        TriigersList.headphonesInserted(this,headphone);
        TriigersList.check_hot_spot(this,headphone);

        String mobile_service = sharedpreferences.getString("mob_ser", "");

        TriigersList.newtVail(this,mobile_service);

        String usb = sharedpreferences.getString("usb", "");

        TriigersList.checkUsbstate(this,usb);


        String roaming = sharedpreferences.getString("roaming", "");
        TriigersList.roamingcheck(this,roaming);


        String flip_sensor = sharedpreferences.getString("flip", "");
        TriigersList.flipdevice(this,flip_sensor);


        String lightsensor = sharedpreferences.getString("light", "");
        String point = sharedpreferences.getString("point", "");
        TriigersList.showLightSensor(this,lightsensor,point);


                /*String proximity = sharedpreferences.getString("porximity", "");
                TriigersList.proximity_sensor(exampleJobService,proximity);*/

        String orientation = sharedpreferences.getString("orientation", "");
        TriigersList.show_orientation(this,orientation);

        String shaking = sharedpreferences.getString("shaking", "");
        TriigersList.shaking(this,shaking);

        //music
        String musicstate = sharedpreferences.getString("music", "");
        TriigersList.musicplayingcheck(this,musicstate);

        String callstate = sharedpreferences.getString("call", "");
        String num = sharedpreferences.getString("call", "");
        TriigersList.checkCallState(this,callstate);






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
