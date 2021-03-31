package com.andromate.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andromate.CustomColors;
import com.andromate.Model.TriggerItemModel;
import com.andromate.R;
import com.andromate.Ui.Adapters.TriggerItemsAdapters;

import java.util.ArrayList;

public class AddConstraintsActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<TriggerItemModel> battery_powerlist;
    ArrayList<TriggerItemModel> notificationlist;
    ArrayList<TriggerItemModel> connectivitylist;
    ArrayList<TriggerItemModel> datetimelist;
    ArrayList<TriggerItemModel> devicestatelist;
    ArrayList<TriggerItemModel> phonelist;
    ArrayList<TriggerItemModel> sensorlist;
    ArrayList<TriggerItemModel> screen_speakerlist;


    RecyclerView recyclerView_battery_power;
    RecyclerView recyclerView_notification;
    RecyclerView recyclerView_connectivity;
    RecyclerView recyclerViewc_datetime;
    RecyclerView recyclerView_devicestate;
    RecyclerView recyclerView_phone;
    RecyclerView recyclerView_sensor;
    RecyclerView recyclerView_screen_speaker;


    TextView tv_battery_power, tv_notification, tv_connectivity, tv_datetime, tv_devicestate,
            tv__phone, tv_sensor, tv_screen_speaker;
    ImageView img_battery_power, img_notification, img_connectivity, img_datetime, img_devicestate,
            img_phone, img_sensor, img_screen_speaker;

    RelativeLayout rly_battery_power, rly_notification, rly_connectivity, rly_datetime, rly_devicestate,
            rly_phone, rly_sensor, rly_screen_speaker;
    RelativeLayout image_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        transparentStatusAndNavigation(this);
        setContentView(R.layout.activity_add_constraints);


        findViews();

        loadpowerbattery();
        loadnotifications();
        loadconnectivity();
        loaddatetime();
        loaddevicestate();
        loadphone();
        loadsensor();
        loadspeaker();
    }

    private void loadpowerbattery() {
        battery_powerlist=new ArrayList<>();
        battery_powerlist.add(new TriggerItemModel("Battery Level",R.drawable.battery_hori_icon));
        battery_powerlist.add(new TriggerItemModel("Battery Saver State",R.drawable.battery_icon));
        battery_powerlist.add(new TriggerItemModel("Battery Temperature",R.drawable.thermometer));
        battery_powerlist.add(new TriggerItemModel("Power Button Toggle",R.drawable.power));
        battery_powerlist.add(new TriggerItemModel("Power Connected/\n" +
                "Disconnected",R.drawable.powe_connected));



        TriggerItemsAdapters triggerItemsAdapters=new TriggerItemsAdapters(this,battery_powerlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this,2);
        recyclerView_battery_power.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_battery_power.setItemAnimator(new DefaultItemAnimator());
        recyclerView_battery_power.setAdapter(triggerItemsAdapters);
    }

    private void loadnotifications() {
        notificationlist = new ArrayList<>();
        notificationlist.add(new TriggerItemModel("Clear Notifications",R.drawable.ic_baseline_visibility_off_24));
        notificationlist.add(new TriggerItemModel("Configure App\nNotifications",R.drawable.call_ended));
        notificationlist.add(new TriggerItemModel("Display Dialog",R.drawable.keyboard));
        notificationlist.add(new TriggerItemModel("Display Notification",R.drawable.call_incoming));
        notificationlist.add(new TriggerItemModel("Heads-up Enable/\nDisable",R.drawable.messaging));
        notificationlist.add(new TriggerItemModel("Notification\nInteraction",R.drawable.finger_print_gesture));
        notificationlist.add(new TriggerItemModel("Notification LED\nEnable/Disable",R.drawable.bell_icon));
        notificationlist.add(new TriggerItemModel("Popup Message",R.drawable.ic_baseline_sms_24));
        notificationlist.add(new TriggerItemModel("Set Notification Sound",R.drawable.volume));


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, notificationlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView_notification.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_notification.setItemAnimator(new DefaultItemAnimator());
        recyclerView_notification.setAdapter(triggerItemsAdapters);
    }

    private void loadconnectivity() {
        connectivitylist = new ArrayList<>();
        connectivitylist.add(new TriggerItemModel("Bluetooth Event",R.drawable.bluetooth_event));
        connectivitylist.add(new TriggerItemModel("Data Connectivity\n" +
                "Change",R.drawable.data_connectivity_change));
        connectivitylist.add(new TriggerItemModel("Headphones Insert/\n" +
                "Remove",R.drawable.headphones_inserts_remove));
        connectivitylist.add(new TriggerItemModel("Hotspot Enabled/\n" +
                "Disabled",R.drawable.hotspot_enable));
        connectivitylist.add(new TriggerItemModel("IP Address Change",R.drawable.iip_address));
        connectivitylist.add(new TriggerItemModel("Mobile Service Status",R.drawable.mobile_service_enable));
        connectivitylist.add(new TriggerItemModel("Roaming Started/\n" +
                "Stopped",R.drawable.roaming_enamble));
        connectivitylist.add(new TriggerItemModel("USB Device Connect/\n" +
                "Disconnect",R.drawable.usb_device_connected));
        connectivitylist.add(new TriggerItemModel("VPN State Change",R.drawable.vpn_state_change));
        connectivitylist.add(new TriggerItemModel("Webhook (Url)",R.drawable.webhook));
        connectivitylist.add(new TriggerItemModel("Wifi SSID Transition",R.drawable.wifi_sssid));
        connectivitylist.add(new TriggerItemModel("Wifi State Change",R.drawable.wifi_state_change));


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, connectivitylist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView_connectivity.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_connectivity.setItemAnimator(new DefaultItemAnimator());
        recyclerView_connectivity.setAdapter(triggerItemsAdapters);
    }

    private void loaddatetime() {
        datetimelist = new ArrayList<>();
        datetimelist.add(new TriggerItemModel("Calendar Event",R.drawable.calendar_event));
        datetimelist.add(new TriggerItemModel("Day of Week/Month",R.drawable.day_month));
        datetimelist.add(new TriggerItemModel("Day/Time Trigger",R.drawable.day_timer));
        datetimelist.add(new TriggerItemModel("Regular Interval",R.drawable.regular_interval));
        datetimelist.add(new TriggerItemModel("Stopwatch",R.drawable.stopwatches_icon));


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, datetimelist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerViewc_datetime.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerViewc_datetime.setItemAnimator(new DefaultItemAnimator());
        recyclerViewc_datetime.setAdapter(triggerItemsAdapters);
    }

    private void loaddevicestate() {
        devicestatelist = new ArrayList<>();
        devicestatelist.add(new TriggerItemModel("Airplane Mode", R.drawable.airplane));
        devicestatelist.add(new TriggerItemModel("Application Running", R.drawable.running_app));
        devicestatelist.add(new TriggerItemModel("Auto Rotate", R.drawable.auto_rotate));
        devicestatelist.add(new TriggerItemModel("Auto Sync", R.drawable.async_changed));
        devicestatelist.add(new TriggerItemModel("Clipnoard Contents", R.drawable.clipboard_change));
        devicestatelist.add(new TriggerItemModel("Device Locked/Unlocked", R.drawable.device_docked));
        devicestatelist.add(new TriggerItemModel("NFC State", R.drawable.android_icon));
        devicestatelist.add(new TriggerItemModel("Roaming Enabled", R.drawable.roaming_enamble));
        devicestatelist.add(new TriggerItemModel("Rooted Device", R.drawable.ic_baseline_security_24));
        devicestatelist.add(new TriggerItemModel("Time Since Boot", R.drawable.device_boot));
        devicestatelist.add(new TriggerItemModel("Torch On/Off", R.drawable.torch));
        devicestatelist.add(new TriggerItemModel("VPN State", R.drawable.vpn_state_change));


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, devicestatelist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView_devicestate.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_devicestate.setItemAnimator(new DefaultItemAnimator());
        recyclerView_devicestate.setAdapter(triggerItemsAdapters);
    }

    private void loadphone() {
        phonelist = new ArrayList<>();
        phonelist.add(new TriggerItemModel("Answer Call", R.drawable.call_active));
        phonelist.add(new TriggerItemModel("Call Reject", R.drawable.call_ended));
        phonelist.add(new TriggerItemModel("Clear Call Log", R.drawable.call_incoming));
        phonelist.add(new TriggerItemModel("Contact via App", R.drawable.call_incoming));
        phonelist.add(new TriggerItemModel("Make Call", R.drawable.call_incoming));
        phonelist.add(new TriggerItemModel("Open Call Log", R.drawable.call_incoming));
        phonelist.add(new TriggerItemModel("Ringtone Configuration", R.drawable.call_incoming));


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, phonelist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView_phone.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_phone.setItemAnimator(new DefaultItemAnimator());
        recyclerView_phone.setAdapter(triggerItemsAdapters);
    }

    private void loadsensor() {
        sensorlist=new ArrayList<>();
        sensorlist.add(new TriggerItemModel("Activity Recognition",R.drawable.activity_recognition));
        sensorlist.add(new TriggerItemModel("Flip Device",R.drawable.flip_devices));
        sensorlist.add(new TriggerItemModel("Light Sensor",R.drawable.light_sensor));
        sensorlist.add(new TriggerItemModel("Proximity Sensor",R.drawable.proximity));
        sensorlist.add(new TriggerItemModel("Screen Orientation",R.drawable.screen_orientation));
        sensorlist.add(new TriggerItemModel("Shake Device",R.drawable.shake_device));


        TriggerItemsAdapters triggerItemsAdapters=new TriggerItemsAdapters(this,sensorlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this,2);
        recyclerView_sensor.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_sensor.setItemAnimator(new DefaultItemAnimator());
        recyclerView_sensor.setAdapter(triggerItemsAdapters);
    }

    private void loadspeaker() {
        screen_speakerlist = new ArrayList<>();
        screen_speakerlist.add(new TriggerItemModel("Brightness", R.drawable.sun));
        screen_speakerlist.add(new TriggerItemModel("Ringer Volume", R.drawable.volume));
        screen_speakerlist.add(new TriggerItemModel("Screen On/Off", R.drawable.screen_on_0ff));
        screen_speakerlist.add(new TriggerItemModel("Speakerphone On/Off", R.drawable.silent_mode));
        screen_speakerlist.add(new TriggerItemModel("Volume level", R.drawable.volume_down));



        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, screen_speakerlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView_screen_speaker.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_screen_speaker.setItemAnimator(new DefaultItemAnimator());
        recyclerView_screen_speaker.setAdapter(triggerItemsAdapters);
    }

    private void findViews() {
        image_back = findViewById(R.id.back_icon_constraints);
        recyclerView_battery_power = findViewById(R.id.recy_battery_power);
        recyclerView_notification = findViewById(R.id.recy_notifications);
        ;
        recyclerView_connectivity = findViewById(R.id.recy_connectivity);
        ;
        recyclerViewc_datetime = findViewById(R.id.recy_date_time);
        ;
        recyclerView_devicestate = findViewById(R.id.recy_device_state);
        ;
        recyclerView_phone = findViewById(R.id.recy_phone);
        ;
        recyclerView_sensor = findViewById(R.id.recy_sensors);
        ;
        recyclerView_screen_speaker = findViewById(R.id.recy_speaker);
        ;

        tv_battery_power = findViewById(R.id.tv_battery_power);
        tv_notification = findViewById(R.id.tv_notifications);
        tv_connectivity = findViewById(R.id.tv_connectivity);
        tv_datetime = findViewById(R.id.tv_date_time);
        tv_devicestate = findViewById(R.id.tv_device_state);
        tv__phone = findViewById(R.id.tv_phone);
        tv_sensor = findViewById(R.id.tv_sensors);
        tv_screen_speaker = findViewById(R.id.tv_speaker);
        ;


        img_battery_power = findViewById(R.id.icon_battery);
        img_notification = findViewById(R.id.icon_bell);
        img_connectivity = findViewById(R.id.icon_coonectivity);
        img_datetime = findViewById(R.id.icon_date_time);
        img_devicestate = findViewById(R.id.device_state);
        img_phone = findViewById(R.id.icon_phone);
        img_sensor = findViewById(R.id.icon_sensors);
        img_screen_speaker = findViewById(R.id.icon_speake);


        rly_battery_power  = findViewById(R.id.rly_battery);
        rly_notification = findViewById(R.id.rly_bell);
        rly_connectivity = findViewById(R.id.rly_coonectivity);
        rly_datetime = findViewById(R.id.rly_date_time);
        rly_devicestate = findViewById(R.id.rly_device_state);
        rly_phone = findViewById(R.id.rly_phone);
        rly_sensor = findViewById(R.id.rly_sensors);
        rly_screen_speaker = findViewById(R.id.rly_speake);
        ;

        image_back.setOnClickListener(this);

        tv_battery_power.setOnClickListener(this);
        tv_notification.setOnClickListener(this);
        tv_connectivity.setOnClickListener(this);
        tv_datetime.setOnClickListener(this);
        tv_devicestate.setOnClickListener(this);
        tv__phone.setOnClickListener(this);
        tv_sensor.setOnClickListener(this);
        tv_screen_speaker.setOnClickListener(this);


       /* img_battery_power.setOnClickListener(this);
        img_notification.setOnClickListener(this);
        img_connectivity.setOnClickListener(this);
        img_datetime.setOnClickListener(this);
        img_devicestate.setOnClickListener(this);
        img_phone.setOnClickListener(this);
        img_sensor.setOnClickListener(this);
        img_screen_speaker.setOnClickListener(this);*/

        rly_battery_power.setOnClickListener(this);
        rly_notification.setOnClickListener(this);
        rly_connectivity.setOnClickListener(this);
        rly_datetime.setOnClickListener(this);
        rly_devicestate.setOnClickListener(this);
        rly_phone.setOnClickListener(this);
        rly_sensor.setOnClickListener(this);
        rly_screen_speaker.setOnClickListener(this);
    }

    public static void transparentStatusAndNavigation(Activity activity) {

        Window window = activity.getWindow();

        // make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(window, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            int visibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            visibility = visibility | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            window.getDecorView().setSystemUiVisibility(visibility);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            int windowManager = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            windowManager = windowManager | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            setWindowFlag(window, windowManager, false);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

    }

    private static void setWindowFlag(Window window, final int bits, boolean on) {
        Window win = window;
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.back_icon_constraints:
               finish();
                break;
            case R.id.tv_battery_power:
                showexpnded(tv_battery_power, recyclerView_battery_power, img_battery_power);
                break;
            case R.id.tv_notifications:
                showexpnded(tv_notification, recyclerView_notification, img_notification);
                break;
            case R.id.tv_connectivity:
                showexpnded(tv_connectivity, recyclerView_connectivity, img_connectivity);
                break;
            case R.id.tv_date_time:
                showexpnded(tv_datetime, recyclerViewc_datetime, img_datetime);
                break;
            case R.id.tv_device_state:
                showexpnded(tv_devicestate, recyclerView_devicestate, img_devicestate);
                break;
            case R.id.tv_phone:
                showexpnded(tv__phone, recyclerView_phone, img_phone);
                break;
            case R.id.tv_sensors:
                showexpnded(tv_sensor,recyclerView_sensor,img_sensor);
                break;
            case R.id.tv_speaker:
                showexpnded(tv_screen_speaker, recyclerView_screen_speaker, img_screen_speaker);
                break;
            case R.id.rly_battery:
                if (tv_battery_power.getVisibility()==View.VISIBLE){
                    showexpnded(tv_battery_power, recyclerView_battery_power, img_battery_power);
                }else {
                    hideexpnded(tv_battery_power, recyclerView_battery_power, img_battery_power);
                }

                break;
            case R.id.rly_bell:
                if (tv_notification.getVisibility()==View.VISIBLE){
                    showexpnded(tv_notification, recyclerView_notification, img_notification);
                }else {
                    hideexpnded(tv_notification, recyclerView_notification, img_notification);
                }

                break;
            case R.id.rly_coonectivity:
                if (tv_connectivity.getVisibility()==View.VISIBLE){
                    showexpnded(tv_connectivity, recyclerView_connectivity, img_connectivity);
                }else {
                    hideexpnded(tv_connectivity, recyclerView_connectivity, img_connectivity);
                }

                break;
            case R.id.rly_date_time:
                if (tv_datetime.getVisibility()==View.VISIBLE){
                    showexpnded(tv_datetime, recyclerViewc_datetime, img_datetime);
                }else {
                    hideexpnded(tv_datetime, recyclerViewc_datetime, img_datetime);
                }

                break;
            case R.id.rly_device_state:
                if (tv_devicestate.getVisibility()==View.VISIBLE){
                    showexpnded(tv_devicestate, recyclerView_devicestate, img_devicestate);
                }else {
                    hideexpnded(tv_devicestate, recyclerView_devicestate, img_devicestate);
                }

                break;
            case R.id.rly_phone:
                if (tv__phone.getVisibility()==View.VISIBLE){
                    showexpnded(tv__phone, recyclerView_phone, img_phone);
                }else {
                    hideexpnded(tv__phone, recyclerView_phone, img_phone);
                }

                break;
            case R.id.rly_sensors:
                if (tv_sensor.getVisibility()==View.VISIBLE){
                    showexpnded(tv_sensor,recyclerView_sensor,img_sensor);
                }else {
                    hideexpnded(tv_sensor,recyclerView_sensor,img_sensor);
                }
                break;
            case R.id.rly_speake:
                if (tv_screen_speaker.getVisibility()==View.VISIBLE){
                    showexpnded(tv_screen_speaker, recyclerView_screen_speaker, img_screen_speaker);
                }else {
                    hideexpnded(tv_screen_speaker, recyclerView_screen_speaker, img_screen_speaker);
                }

                break;

            default:
        }

    }

    void showexpnded(TextView tv_hide, RecyclerView recyclerView_show,
                     ImageView imageView_colored) {


        tv_hide.setVisibility(View.GONE);
        recyclerView_show.setVisibility(View.VISIBLE);
        imageView_colored.setColorFilter(CustomColors.golden);

    }

    void hideexpnded(TextView tv_show, RecyclerView recyclerView_hide,
                     ImageView imageView_revert) {

        tv_show.setVisibility(View.VISIBLE);
        recyclerView_hide.setVisibility(View.GONE);
        imageView_revert.setColorFilter(CustomColors.black);
    }
}