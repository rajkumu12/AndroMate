package com.andromate.Constraints;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.location.LocationManager;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andromate.Model.ApplicationsInfo;
import com.andromate.Model.ContactModel;
import com.andromate.Model.Triggerlistmodel;
import com.andromate.R;
import com.andromate.Services.ExampleJobService;
import com.andromate.Ui.Activity.AddMacroActivity;
import com.andromate.Ui.Adapters.ApplicationlistAdapters;
import com.andromate.Ui.Adapters.Call_SmsAdapter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class TriigersList {
    public static String TAG = "TriigersList";

    //Gps trigger
    public static void ChecGps(ExampleJobService exampleJobService, String gps) {
        LocationManager locationManager = (LocationManager) exampleJobService.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (gps != null && gps.equals("GPS Enabled")) {
                addNotification(exampleJobService, "Gps Enable");
            }
        } else {
            if (gps != null && gps.equals("GPS Disabled")) {
                addNotification(exampleJobService, "Gps Disabled");
            }
        }
    }


    //AppClose Open
    public static String retriveNewApp(Context ctx, String selApp) {
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

            if (currentApp.equals(selApp)) {
                addNotification(ctx, nn);
            }
            /*  Log.e(TAG, "Current App in foreground is: " + currentApp);*/

            return currentApp;

        } else {

            ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
            String mm = (manager.getRunningTasks(1).get(0)).topActivity.getPackageName();
            String name = (manager.getRunningTasks(1).get(0)).topActivity.getClassName();
            if (mm.equals(selApp)) {
                addNotification(ctx, name);
                /* Toast.makeText(context, ""+name+" is Running", Toast.LENGTH_SHORT).show();*/
            }
            Log.e(TAG, "Current App in foreground is: " + name);
            return mm;
        }
    }

    //Power Saving Mode
    public static void checkbattersavermode(ExampleJobService exampleJobService, String bs) {


        PowerManager powerManager = (PowerManager) exampleJobService.getSystemService(Context.POWER_SERVICE);
        boolean powerSaveMode = powerManager.isPowerSaveMode();

        if (powerSaveMode && bs.equals("Enable")) {
            addNotification(exampleJobService, "Battery saver mode is ON");
        } else {
            /*   addNotification(exampleJobService,"Battery saver mode is OFF");*/
        }


    }

    //Bluetooth trigger

    public static void checkBlutooth_trigger(ExampleJobService exampleJobService, String message) {


        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth

        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                if (!message.isEmpty() && message.equals("Bluetooth Disabled")) {
                    addNotification(exampleJobService, message);
                }
            } else {
                if (!message.isEmpty() && message.equals("Bluetooth Enabled")) {
                    addNotification(exampleJobService, message);
                }
            }
        }
    }


//data conocetvity

    public static void checkdataenble(Context context, String sate) {
        if (isInternetIsConnected(context)) {
            if (!sate.isEmpty() && sate.equals("Data Available")) {
                addNotification(context, sate);
            }
        } else {
            if (!sate.isEmpty() && sate.equals("No Data Connection")) {
                addNotification(context, sate);
            }
        }

    }

    public static boolean isInternetIsConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;

            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        } else {
            // not connected to the internet
            return false;
        }
        return false;
    }

    //headphones
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void headphonesInserted(Context context, String message) {
        BroadcastReceiver mReceiver;
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                    int state = intent.getIntExtra("state", -1);
                    switch (state) {
                        case 0:

                            Log.d(TAG, "Removed");
                            if (!message.isEmpty() && message.equals("Headphones Removed")) {
                                addNotification(context, message);
                            }
                            break;
                        case 1:
                            Log.d(TAG, "Headset plugged");
                            if (!message.isEmpty() && message.equals("Headphones Inserted")) {
                                addNotification(context, message);
                            }
                            addNotification(context, "inserted");
                            break;
                    }
                }
            }
        };
        context.registerReceiver(mReceiver,
                new IntentFilter(Intent.ACTION_HEADSET_PLUG));

    }


    //HotSpot trigger

    public static void check_hot_spot(Context context, String message) {
        BroadcastReceiver mReceiver;
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.d("jflkdfjklfjklfjklf", "dddfdd" + action);
                if (intent.getAction() == "android.net.wifi.WIFI_AP_STATE_CHANGED") {
                    int apState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
                    if (apState == 13) {
                        // Hotspot AP is enabled
                        addNotification(context, "enable");
                    } else {
                        // Hotspot AP is disabled/not ready
                    }
                }

            }
        };
        context.registerReceiver(mReceiver,
                new IntentFilter("android.net.wifi.WIFI_AP_STATE_CHANGED"));


    }


    public static void newtVail(Context context, String message) {
        if (getNetworkState(context)) {
            if (!message.isEmpty() && message.equals("Service Available")) {
                addNotification(context, message);
            }

        } else {
            if (!message.isEmpty() && message.equals("Service Unavailable")) {
                addNotification(context, message);
            }
        }
    }

    public static boolean getNetworkState(Context context) {
        boolean network = false;
        boolean data = false;
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = connectivity.getAllNetworkInfo();
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].isAvailable()) {
                network = true;
            }
            if (infos[i].isConnected()) {
                data = true;
            }
        }

        if (network)
            if (!data)
                return true;

        return false;
    }


    //Usb State
    public static void checkUsbstate(Context context, String message) {


        BroadcastReceiver mReceiver;
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                /*Log.d(LOG_TAG, "Received Broadcast: "+action);*/
                if (action.equalsIgnoreCase("android.hardware.usb.action.USB_STATE")) { //Check if change in USB state
                    if (intent.getExtras().getBoolean("connected")) {
                        if (!message.isEmpty() && message.equals("USB device connected")) {
                            addNotification(context, message);
                        }
                        // USB was connected
                    } else {
                        if (!message.isEmpty() && message.equals("USB device disconnected")) {
                            addNotification(context, message);
                        }
                        // USB was disconnected
                    }
                }

            }
        };
        context.registerReceiver(mReceiver,
                new IntentFilter("android.hardware.usb.action.USB_STATE"));


    }


    //Roaming

    public static void roamingcheck(Context context, String message) {
        if (isDataRoamingEnabled(context)) {
            addNotification(context, "Roaming");
        } else {
            addNotification(context, "Roaming no");

        }


    }


    public static final Boolean isDataRoamingEnabled(final Context application_context) {
        try {
            if (Build.VERSION.SDK_INT < 17) {
                return (Settings.System.getInt(application_context.getContentResolver(), Settings.Secure.DATA_ROAMING, 0) == 1);
            }

            return (Settings.Global.getInt(application_context.getContentResolver(), Settings.Global.DATA_ROAMING, 0) == 1);
        } catch (Exception exception) {
            return false;
        }
    }


    //Notifications

    public static void addNotification(Context context, String string) {
        String channelId = context.getString(R.string.default_notification_channel_id);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("" + string)
                        .setContentText("kl")
                        .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
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


//end region



