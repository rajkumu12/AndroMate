package com.andromate.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andromate.CustomColors;
import com.andromate.Model.TriggerItemModel;
import com.andromate.R;
import com.andromate.Ui.Adapters.ActionApplicationsAdapters;
import com.andromate.Ui.Adapters.ActionCameraAdapters;
import com.andromate.Ui.Adapters.ActionConnectivityAdapters;
import com.andromate.Ui.Adapters.TriggerItemsAdapters;

import java.util.ArrayList;

public class AddAction extends AppCompatActivity implements View.OnClickListener {
    ArrayList<TriggerItemModel> applicationlist;
    ArrayList<TriggerItemModel> cameraphotolist;
    ArrayList<TriggerItemModel> conditionlist;
    ArrayList<TriggerItemModel> connectivitylist;
    ArrayList<TriggerItemModel> datetimelist;
    ArrayList<TriggerItemModel> deviceactionlist;
    ArrayList<TriggerItemModel> devicesettinglist;
    ArrayList<TriggerItemModel> fileslist;
    ArrayList<TriggerItemModel> locationslist;
    ArrayList<TriggerItemModel> logginglist;
    ArrayList<TriggerItemModel> medialists;
    ArrayList<TriggerItemModel> messaginglist;
    ArrayList<TriggerItemModel> notificationslist;
    ArrayList<TriggerItemModel> phonelist;
    ArrayList<TriggerItemModel> screenlist;
    ArrayList<TriggerItemModel> volumelist;


    RecyclerView recyclerView_application;
    RecyclerView recyclerView_cameraphoto;
    RecyclerView recyclerView_ondition;
    RecyclerView recyclerViewc_connectivity;
    RecyclerView recyclerView_datetime;
    RecyclerView recyclerView_deviceaction;
    RecyclerView recyclerView_devicesetting;
    RecyclerView recyclerView_files;
    RecyclerView recyclerView_locations;
    RecyclerView recyclerView_logging;
    RecyclerView recyclerViewc_media;
    RecyclerView recyclerView_messaging;
    RecyclerView recyclerView_notifications;
    RecyclerView recyclerView_phone;
    RecyclerView recyclerView_screenlist;
    RecyclerView recyclerView_volume;

    TextView tv_application, tv_cameraphoto, tv_ondition, tv_connectivity, tv_datetime,
            tv_deviceaction, tv_devicesetting, tv_files, tv_locations, tv_logging, tv_media, tv_messaging, tv_notifications, tv_phone, tv_screen, tv_volume;
    ImageView img_application, img_cameraphoto, img_ondition, img_connectivity, img_datetime,
            img_deviceaction, img_devicesetting, img_files, img_locations, img_logging, img_media, img_messaging, img_notifications, img_phone, img_screen, img_volume;


    RelativeLayout rly_application, rly_cameraphoto, rly_ondition, rly_connectivity, rly_datetime,
            rly_deviceaction, rly_devicesetting, rly_files, rly_locations, rly_logging, rly_media, rly_messaging, rly_notifications, rly_phone, rly_screen, rly_volume;

    RelativeLayout imageView_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        getSupportActionBar().hide();
        transparentStatusAndNavigation(this);
       /* View decorView =getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);*/
        setContentView(R.layout.activity_add_action);

        findViews();

        loadApplications();
        loadcameraphoto();
        loadondition();
        loadconnectivity();
        loaddatetime();
        loaddeviceaction();
        loaddevicesetting();
        loadfiles();
        loadlocations();
        loadlogging();
        loadmedia();
        loadmessaging();
        loadnotifications();
        loadphone();
        loadscreenlist();
        loadvolume();


    }

    private void loadApplications() {
        applicationlist = new ArrayList<>();
        applicationlist.add(new TriggerItemModel("App Enable/Disable", R.drawable.recent_app_opened));

        applicationlist.add(new TriggerItemModel("Clear App Data", R.drawable.suitcase));

        applicationlist.add(new TriggerItemModel("Kill Application", R.drawable.recent_app_opened));

        applicationlist.add(new TriggerItemModel("Kill Background\nProcess", R.drawable.ic_kill_process));
        applicationlist.add(new TriggerItemModel("Launch Application", R.drawable.recent_app_opened));
        applicationlist.add(new TriggerItemModel("Launch Shortcut", R.drawable.tasker_locator));
        applicationlist.add(new TriggerItemModel("Locale/Tasker Plugin", R.drawable.suitcase));
        applicationlist.add(new TriggerItemModel("Open Website/HTTP\nGET", R.drawable.webhook));
        applicationlist.add(new TriggerItemModel("Shell Script", R.drawable.screen));

        ActionApplicationsAdapters triggerItemsAdapters = new ActionApplicationsAdapters(this, applicationlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(AddAction.this, 2);
        recyclerView_application.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_application.setItemAnimator(new DefaultItemAnimator());
        recyclerView_application.setAdapter(triggerItemsAdapters);
    }

    private void loadcameraphoto() {
        cameraphotolist = new ArrayList<>();
        cameraphotolist.add(new TriggerItemModel("Camera Enable/\n" +
                "Disable", R.drawable.camera));

        cameraphotolist.add(new TriggerItemModel("Open Last Photo", R.drawable.ic_baseline_photo_24));

        cameraphotolist.add(new TriggerItemModel("Share Last Photo", R.drawable.paper_icon));

        cameraphotolist.add(new TriggerItemModel("Take Picture", R.drawable.camera));
        cameraphotolist.add(new TriggerItemModel("Take Screenshot", R.drawable.smartphone));

        ActionCameraAdapters triggerItemsAdapters = new ActionCameraAdapters(this, cameraphotolist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(AddAction.this, 2);
        recyclerView_cameraphoto.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_cameraphoto.setItemAnimator(new DefaultItemAnimator());
        recyclerView_cameraphoto.setAdapter(triggerItemsAdapters);
    }

    private void loadondition() {
        conditionlist = new ArrayList<>();
        conditionlist.add(new TriggerItemModel("Break From Loop", R.drawable.loopicon));

        conditionlist.add(new TriggerItemModel("Continue Loop", R.drawable.continueloop));

        conditionlist.add(new TriggerItemModel("If Confirmed then", R.drawable.if_n));

        conditionlist.add(new TriggerItemModel("If clause", R.drawable.if_claue));
        conditionlist.add(new TriggerItemModel("Repeat actions", R.drawable.repeat_mode));


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, conditionlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(AddAction.this, 2);
        recyclerView_ondition.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_ondition.setItemAnimator(new DefaultItemAnimator());
        recyclerView_ondition.setAdapter(triggerItemsAdapters);
    }

    private void loadconnectivity() {
        connectivitylist = new ArrayList<>();
        connectivitylist.add(new TriggerItemModel("Airplane Mode On/Off",R.drawable.airplane));
        connectivitylist.add(new TriggerItemModel("Android Wear",R.drawable.android_wear));
        connectivitylist.add(new TriggerItemModel("Auto Sync On/Off",R.drawable.async_changed));
        connectivitylist.add(new TriggerItemModel("Bluetooth Configure",R.drawable.bluetooth_event));
        connectivitylist.add(new TriggerItemModel("Bluetooth tethering",R.drawable.bluetooth_event));
        connectivitylist.add(new TriggerItemModel("Connectivity Check",R.drawable.connectivity));
        connectivitylist.add(new TriggerItemModel("HotSpot On/Off",R.drawable.hotspot_enable));
        connectivitylist.add(new TriggerItemModel("Mobile Data On/Off",R.drawable.data_connectivity_change));
        connectivitylist.add(new TriggerItemModel("Send Intent",R.drawable.intent_received));
        connectivitylist.add(new TriggerItemModel("Sync Account",R.drawable.async_changed));
        connectivitylist.add(new TriggerItemModel("USB Tethering",R.drawable.usb_device_connected));
        connectivitylist.add(new TriggerItemModel("Wifi Configuration",R.drawable.wifi_state_change));

        ActionConnectivityAdapters triggerItemsAdapters = new ActionConnectivityAdapters(this, connectivitylist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(AddAction.this, 2);
        recyclerViewc_connectivity.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerViewc_connectivity.setItemAnimator(new DefaultItemAnimator());
        recyclerViewc_connectivity.setAdapter(triggerItemsAdapters);
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
        recyclerView_datetime.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_datetime.setItemAnimator(new DefaultItemAnimator());
        recyclerView_datetime.setAdapter(triggerItemsAdapters);
    }

    private void loaddeviceaction() {
        deviceactionlist = new ArrayList<>();
        deviceactionlist.add(new TriggerItemModel("Android Shortcut", R.drawable.android_icon));

        deviceactionlist.add(new TriggerItemModel("Expand/Collapse\n" +
                "Status Bar", R.drawable.impo_expo));

        deviceactionlist.add(new TriggerItemModel("Fill Clipboard", R.drawable.clipboard_change));

        deviceactionlist.add(new TriggerItemModel("Launch Home Screen", R.drawable.home_buttom_long));
        deviceactionlist.add(new TriggerItemModel("Press Back Button", R.drawable.back_btn));
        deviceactionlist.add(new TriggerItemModel("Reboot/Power Off", R.drawable.power));
        deviceactionlist.add(new TriggerItemModel("Speak Text", R.drawable.text));
        deviceactionlist.add(new TriggerItemModel("Torch On/Off", R.drawable.touch_screen));
        deviceactionlist.add(new TriggerItemModel("UI Interaction", R.drawable.touch_screen));
        deviceactionlist.add(new TriggerItemModel("Vibrate", R.drawable.vibrate));
        deviceactionlist.add(new TriggerItemModel("Voice Search", R.drawable.ic_baseline_mic_24));

        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, deviceactionlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(AddAction.this, 2);
        recyclerView_deviceaction.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_deviceaction.setItemAnimator(new DefaultItemAnimator());
        recyclerView_deviceaction.setAdapter(triggerItemsAdapters);
    }

    private void loaddevicesetting() {
        devicesettinglist = new ArrayList<>();
        devicesettinglist.add(new TriggerItemModel("Ambient Display", R.drawable.screen_orientation));

        devicesettinglist.add(new TriggerItemModel("Auto rotate On/Off", R.drawable.auto_rotate));

        devicesettinglist.add(new TriggerItemModel("Battery Saver", R.drawable.battery_icon));

        devicesettinglist.add(new TriggerItemModel("Car Mode", R.drawable.auto));
        devicesettinglist.add(new TriggerItemModel("Daydream/\nScreenSaver", R.drawable.daydrem));
        devicesettinglist.add(new TriggerItemModel("Demo Mode", R.drawable.device_state));
        devicesettinglist.add(new TriggerItemModel("Speak Text", R.drawable.text));
        devicesettinglist.add(new TriggerItemModel("Font Scale", R.drawable.font_scale));
        devicesettinglist.add(new TriggerItemModel("Immersive Node", R.drawable.screen));
        devicesettinglist.add(new TriggerItemModel("Keyboard-Set\nDefault", R.drawable.keyboard));
        devicesettinglist.add(new TriggerItemModel("Keyboard-Set\nDefault", R.drawable.keyboard));
        devicesettinglist.add(new TriggerItemModel("Secure Setting", R.drawable.ic_baseline_security_24));
        devicesettinglist.add(new TriggerItemModel("Set Quick Setting Tile State", R.drawable.setting_icon));
        devicesettinglist.add(new TriggerItemModel("Set Screen Lock", R.drawable.screen_unlock));
        devicesettinglist.add(new TriggerItemModel("Set Wallpaper", R.drawable.wallpaper));
        devicesettinglist.add(new TriggerItemModel("System Setting", R.drawable.system));

        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, devicesettinglist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(AddAction.this, 2);
        recyclerView_devicesetting.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_devicesetting.setItemAnimator(new DefaultItemAnimator());
        recyclerView_devicesetting.setAdapter(triggerItemsAdapters);
    }

    private void loadfiles() {
        fileslist = new ArrayList<>();
        fileslist.add(new TriggerItemModel("File Operation V2",R.drawable.file_operations));

        fileslist.add(new TriggerItemModel("Open File",R.drawable.folder));

        fileslist.add(new TriggerItemModel("Write to File",R.drawable.writefile));


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, fileslist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(AddAction.this, 2);
        recyclerView_files.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_files.setItemAnimator(new DefaultItemAnimator());
        recyclerView_files.setAdapter(triggerItemsAdapters);
    }

    private void loadlocations() {
        locationslist = new ArrayList<>();
        locationslist.add(new TriggerItemModel("Cell Tower Change",R.drawable.cell_tower_change));
        locationslist.add(new TriggerItemModel("Geofence Trigger",R.drawable.cell_tower_change));
        locationslist.add(new TriggerItemModel("Location Trigger",R.drawable.locations));
        locationslist.add(new TriggerItemModel("Sunset/Sunrise",R.drawable.sunrise));
        locationslist.add(new TriggerItemModel("Weather",R.drawable.weather));


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, locationslist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView_locations.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_locations.setItemAnimator(new DefaultItemAnimator());
        recyclerView_locations.setAdapter(triggerItemsAdapters);
    }

    private void loadlogging() {
        logginglist = new ArrayList<>();
        logginglist.add(new TriggerItemModel("Calender-Add Event", R.drawable.datetime));
        logginglist.add(new TriggerItemModel("Clear Log", R.drawable.call_ended));
        logginglist.add(new TriggerItemModel("Log Event", R.drawable.call_incoming));
        logginglist.add(new TriggerItemModel("Open Macrodroid Log", R.drawable.call_active));

        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, logginglist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView_logging.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_logging.setItemAnimator(new DefaultItemAnimator());
        recyclerView_logging.setAdapter(triggerItemsAdapters);
    }

    private void loadmedia() {
        medialists = new ArrayList<>();
        medialists.add(new TriggerItemModel("Control Media", R.drawable.mdia_pressed_v_t));
        medialists.add(new TriggerItemModel("Play/Stop Sound", R.drawable.playbtn));
        medialists.add(new TriggerItemModel("Record Microphone", R.drawable.ic_baseline_mic_24));


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, medialists);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerViewc_media.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerViewc_media.setItemAnimator(new DefaultItemAnimator());
        recyclerViewc_media.setAdapter(triggerItemsAdapters);
    }

    private void loadmessaging() {
        messaginglist = new ArrayList<>();
        messaginglist.add(new TriggerItemModel("Send Email", R.drawable.ic_baseline_email_24));
        messaginglist.add(new TriggerItemModel("Send SMS", R.drawable.ic_baseline_sms_24));
        messaginglist.add(new TriggerItemModel("Tweet", R.drawable.tweet));
        messaginglist.add(new TriggerItemModel("UDP Command", R.drawable.udp));


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, messaginglist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView_messaging.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_messaging.setItemAnimator(new DefaultItemAnimator());
        recyclerView_messaging.setAdapter(triggerItemsAdapters);
    }

    private void loadnotifications() {
        notificationslist = new ArrayList<>();
        notificationslist.add(new TriggerItemModel("Clear Notifications",R.drawable.ic_baseline_visibility_off_24));
        notificationslist.add(new TriggerItemModel("Configure App\nNotifications",R.drawable.call_ended));
        notificationslist.add(new TriggerItemModel("Display Dialog",R.drawable.keyboard));
        notificationslist.add(new TriggerItemModel("Display Notification",R.drawable.call_incoming));
        notificationslist.add(new TriggerItemModel("Heads-up Enable/\nDisable",R.drawable.messaging));
        notificationslist.add(new TriggerItemModel("Notification\nInteraction",R.drawable.finger_print_gesture));
        notificationslist.add(new TriggerItemModel("Notification LED\nEnable/Disable",R.drawable.bell_icon));
        notificationslist.add(new TriggerItemModel("Popup Message",R.drawable.ic_baseline_sms_24));
        notificationslist.add(new TriggerItemModel("Set Notification Sound",R.drawable.volume));

        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, notificationslist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView_notifications.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_notifications.setItemAnimator(new DefaultItemAnimator());
        recyclerView_notifications.setAdapter(triggerItemsAdapters);
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

    private void loadscreenlist() {
        screenlist = new ArrayList<>();
        screenlist.add(new TriggerItemModel("Brightness",R.drawable.sun));
        screenlist.add(new TriggerItemModel("Dim Screen",R.drawable.dim));
        screenlist.add(new TriggerItemModel("Force Screen\nRotation",R.drawable.auto_rotate));
        screenlist.add(new TriggerItemModel("Keep Device Awake",R.drawable.device_state));
        screenlist.add(new TriggerItemModel("Screen On/Off",R.drawable.screenon));
        screenlist.add(new TriggerItemModel("Set Screen Timeout",R.drawable.timer));


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, screenlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView_screenlist.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_screenlist.setItemAnimator(new DefaultItemAnimator());
        recyclerView_screenlist.setAdapter(triggerItemsAdapters);
    }

    private void loadvolume() {
        volumelist = new ArrayList<>();
        volumelist.add(new TriggerItemModel("Priority Mode/Do\nNot Disturb",R.drawable.donot_disturb));
        volumelist.add(new TriggerItemModel("Silent-Vibrate Off",R.drawable.vibrate));
        volumelist.add(new TriggerItemModel("Speakerphone On/Off",R.drawable.silent_mode));
        volumelist.add(new TriggerItemModel("Vibrate Enable/Disable",R.drawable.vibrate));
        volumelist.add(new TriggerItemModel("Volume Change",R.drawable.volume));
        volumelist.add(new TriggerItemModel("Volume Up/Down",R.drawable.volume_down));


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(this, volumelist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView_volume.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_volume.setItemAnimator(new DefaultItemAnimator());
        recyclerView_volume.setAdapter(triggerItemsAdapters);
    }

    private void findViews() {

        imageView_back = findViewById(R.id.back_icon_add_action);
        recyclerView_application = findViewById(R.id.recy_application);
        recyclerView_cameraphoto = findViewById(R.id.recy_camera_photo);
        recyclerView_ondition = findViewById(R.id.recy_loop);
        recyclerViewc_connectivity = findViewById(R.id.recy_connectivity);
        recyclerView_datetime = findViewById(R.id.recy_datetime);
        recyclerView_deviceaction = findViewById(R.id.recy_device_action);
        recyclerView_devicesetting = findViewById(R.id.recy_device_setting);
        recyclerView_files = findViewById(R.id.recy_files);
        recyclerView_locations = findViewById(R.id.recy_location);
        recyclerView_logging = findViewById(R.id.recy_logging);
        recyclerViewc_media = findViewById(R.id.recy_media);
        recyclerView_messaging = findViewById(R.id.recy_messaging);
        recyclerView_notifications = findViewById(R.id.recy_notifications);
        recyclerView_phone = findViewById(R.id.recy_phone);
        recyclerView_screenlist = findViewById(R.id.recy_screen);
        recyclerView_volume = findViewById(R.id.recy_volume);

        tv_application = findViewById(R.id.tv_applic);
        tv_cameraphoto = findViewById(R.id.tv_camera_photo);
        tv_ondition = findViewById(R.id.tv_loop_icons);
        tv_connectivity = findViewById(R.id.tv_connectivity);
        tv_datetime = findViewById(R.id.tv_date_time);
        tv_deviceaction = findViewById(R.id.tv_device_action);
        tv_devicesetting = findViewById(R.id.tv_device_setting);
        tv_files = findViewById(R.id.tv_files);
        tv_locations = findViewById(R.id.tv_location);
        tv_logging = findViewById(R.id.tv_logging);
        tv_media = findViewById(R.id.tv_media);
        tv_messaging = findViewById(R.id.tv_messaging);
        tv_notifications = findViewById(R.id.tv_notifications);
        tv_phone = findViewById(R.id.tv_phone);
        tv_screen = findViewById(R.id.tv_screen);
        tv_volume = findViewById(R.id.tv_volume);

        img_application = findViewById(R.id.icon_applications);
        img_cameraphoto = findViewById(R.id.Camera_Photo);
        img_ondition = findViewById(R.id.icon_loop);
        img_connectivity = findViewById(R.id.icon_connectivity);
        img_datetime = findViewById(R.id.icon_date_time);
        img_deviceaction = findViewById(R.id.icon_device_action);
        img_devicesetting = findViewById(R.id.icon_device_setting);
        img_files = findViewById(R.id.icon_files);
        img_locations = findViewById(R.id.icon_location);
        img_logging = findViewById(R.id.icon_logging);
        img_media = findViewById(R.id.icon_media);
        img_messaging = findViewById(R.id.icon_messaging);
        img_notifications = findViewById(R.id.icon_bell_not);
        img_phone = findViewById(R.id.icon_phone);
        img_screen = findViewById(R.id.icon_screen2);
        img_volume = findViewById(R.id.icon_volume);



        rly_application = findViewById(R.id.rly_applicationd);
        rly_cameraphoto = findViewById(R.id.rly_Camera_Photo);
        rly_ondition = findViewById(R.id.rly_loop);
        rly_connectivity = findViewById(R.id.rly_connectivity);
        rly_datetime = findViewById(R.id.rly_date_time);
        rly_deviceaction = findViewById(R.id.rly_device_action);
        rly_devicesetting = findViewById(R.id.rly_device_setting);
        rly_files = findViewById(R.id.rly_files);
        rly_locations = findViewById(R.id.rly_location);
        rly_logging = findViewById(R.id.rly_logging);
        rly_media = findViewById(R.id.rly_media);
        rly_messaging = findViewById(R.id.rly_messaging);
        rly_notifications = findViewById(R.id.rly_bell_not);
        rly_phone = findViewById(R.id.rly_phone);
        rly_screen = findViewById(R.id.rly_screen2);
        rly_volume = findViewById(R.id.rly_volume);


        ;


        imageView_back.setOnClickListener(this);

        tv_application.setOnClickListener(this);
        tv_cameraphoto.setOnClickListener(this);
        tv_ondition.setOnClickListener(this);
        tv_connectivity.setOnClickListener(this);
        tv_datetime.setOnClickListener(this);
        tv_deviceaction.setOnClickListener(this);
        tv_devicesetting.setOnClickListener(this);
        tv_files.setOnClickListener(this);
        tv_logging.setOnClickListener(this);
        tv_locations.setOnClickListener(this);
        tv_media.setOnClickListener(this);
        tv_messaging.setOnClickListener(this);
        tv_notifications.setOnClickListener(this);
        tv_phone.setOnClickListener(this);
        tv_screen.setOnClickListener(this);
        tv_volume.setOnClickListener(this);


      /*  img_application.setOnClickListener(this);
        img_cameraphoto.setOnClickListener(this);
        img_ondition.setOnClickListener(this);
        img_connectivity.setOnClickListener(this);
        img_datetime.setOnClickListener(this);
        img_deviceaction.setOnClickListener(this);
        img_devicesetting.setOnClickListener(this);
        img_files.setOnClickListener(this);
        img_locations.setOnClickListener(this);
        img_logging.setOnClickListener(this);
        img_media.setOnClickListener(this);
        img_messaging.setOnClickListener(this);
        img_notifications.setOnClickListener(this);
        img_phone.setOnClickListener(this);
        img_screen.setOnClickListener(this);
        img_volume.setOnClickListener(this);*/



        rly_application.setOnClickListener(this);
        rly_cameraphoto.setOnClickListener(this);
        rly_ondition.setOnClickListener(this);
        rly_connectivity.setOnClickListener(this);
        rly_datetime.setOnClickListener(this);
        rly_deviceaction.setOnClickListener(this);
        rly_devicesetting.setOnClickListener(this);
        rly_files.setOnClickListener(this);
        rly_locations.setOnClickListener(this);
        rly_logging.setOnClickListener(this);
        rly_media.setOnClickListener(this);
        rly_messaging.setOnClickListener(this);
        rly_notifications.setOnClickListener(this);
        rly_phone.setOnClickListener(this);
        rly_screen.setOnClickListener(this);
        rly_volume.setOnClickListener(this);


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
            case R.id.back_icon_add_action:
                // code block
                finish();
            case R.id.tv_applic:
                showexpnded(tv_application, recyclerView_application, img_application);
                break;
            case R.id.tv_camera_photo:
                showexpnded(tv_cameraphoto, recyclerView_cameraphoto, img_cameraphoto);
                break;
            case R.id.tv_loop_icons:
                showexpnded(tv_ondition, recyclerView_ondition, img_ondition);
                break;
            case R.id.tv_connectivity:
                showexpnded(tv_connectivity, recyclerViewc_connectivity, img_connectivity);
                break;
            case R.id.tv_date_time:
                showexpnded(tv_datetime, recyclerView_datetime, img_datetime);
                break;
            case R.id.tv_device_action:
                showexpnded(tv_deviceaction, recyclerView_deviceaction, img_deviceaction);
                break;
            case R.id.tv_device_setting:
                showexpnded(tv_devicesetting, recyclerView_devicesetting, img_devicesetting);
                break;
            case R.id.tv_files:
                showexpnded(tv_files, recyclerView_files, img_files);
                break;
            case R.id.tv_location:
                showexpnded(tv_locations, recyclerView_locations, img_locations);
                break;
            case R.id.tv_logging:
                showexpnded(tv_logging, recyclerView_logging, img_logging);
                break;
            case R.id.tv_media:
                showexpnded(tv_media, recyclerViewc_media, img_media);
                break;
            case R.id.tv_messaging:
                showexpnded(tv_messaging, recyclerView_messaging, img_messaging);
                break;
            case R.id.tv_notifications:
                showexpnded(tv_notifications, recyclerView_notifications, img_notifications);
                break;
            case R.id.tv_phone:
                showexpnded(tv_phone, recyclerView_phone, img_phone);
                break;
            case R.id.tv_screen:
                showexpnded(tv_screen, recyclerView_screenlist, img_screen);
                break;
            case R.id.tv_volume:
                showexpnded(tv_volume, recyclerView_volume, img_volume);
                break;
            case R.id.rly_applicationd:
                if (tv_application.getVisibility()==View.VISIBLE){
                    showexpnded(tv_application, recyclerView_application, img_application);
                }else {
                    hideexpnded(tv_application, recyclerView_application, img_application);
                }
                break;
            case R.id.rly_Camera_Photo:
                if (tv_cameraphoto.getVisibility()==View.VISIBLE){
                    showexpnded(tv_cameraphoto, recyclerView_cameraphoto, img_cameraphoto);
                }else {
                    hideexpnded(tv_cameraphoto, recyclerView_cameraphoto, img_cameraphoto);
                }
                break;
            case R.id.rly_loop:
                if (tv_ondition.getVisibility()==View.VISIBLE){
                    showexpnded(tv_ondition, recyclerView_ondition, img_ondition);
                }else {
                    hideexpnded(tv_ondition, recyclerView_ondition, img_ondition);
                }
                break;
            case R.id.rly_connectivity:
                if (tv_connectivity.getVisibility()==View.VISIBLE){
                    showexpnded(tv_connectivity, recyclerViewc_connectivity, img_connectivity);
                }else {
                    hideexpnded(tv_connectivity, recyclerViewc_connectivity, img_connectivity);
                }
                break;
            case R.id.rly_date_time:
                if (tv_datetime.getVisibility()==View.VISIBLE){
                    showexpnded(tv_datetime, recyclerView_datetime, img_datetime);
                }else {
                    hideexpnded(tv_datetime, recyclerView_datetime, img_datetime);
                }

                break;
            case R.id.rly_device_action:
                if (tv_deviceaction.getVisibility()==View.VISIBLE){
                    showexpnded(tv_deviceaction, recyclerView_deviceaction, img_deviceaction);
                }else {
                    hideexpnded(tv_deviceaction, recyclerView_deviceaction, img_deviceaction);
                }

                break;
            case R.id.rly_device_setting:
                if (tv_devicesetting.getVisibility()==View.VISIBLE){
                    showexpnded(tv_devicesetting, recyclerView_devicesetting, img_devicesetting);
                }else {
                    hideexpnded(tv_devicesetting, recyclerView_devicesetting, img_devicesetting);
                }

                break;
            case R.id.rly_files:
                if (tv_files.getVisibility()==View.VISIBLE){
                    showexpnded(tv_files, recyclerView_files, img_files);
                }else {
                    hideexpnded(tv_files, recyclerView_files, img_files);
                }

                break;
            case R.id.rly_location:
                if (tv_locations.getVisibility()==View.VISIBLE){
                    showexpnded(tv_locations, recyclerView_locations, img_locations);
                }else {
                    hideexpnded(tv_locations, recyclerView_locations, img_locations);
                }

                break;
            case R.id.rly_logging:
                if (tv_logging.getVisibility()==View.VISIBLE){
                    showexpnded(tv_logging, recyclerView_logging, img_logging);
                }else {
                    hideexpnded(tv_logging, recyclerView_logging, img_logging);
                }
                break;
            case R.id.rly_media:
                if (tv_media.getVisibility()==View.VISIBLE){
                    showexpnded(tv_media, recyclerViewc_media, img_media);
                }else {
                    hideexpnded(tv_media, recyclerViewc_media, img_media);
                }

                break;
            case R.id.rly_messaging:
                if (tv_messaging.getVisibility()==View.VISIBLE){
                    showexpnded(tv_messaging, recyclerView_messaging, img_messaging);
                }else {
                    hideexpnded(tv_messaging, recyclerView_messaging, img_messaging);
                }

                break;
            case R.id.rly_bell_not:
                if (tv_notifications.getVisibility()==View.VISIBLE){
                    showexpnded(tv_notifications, recyclerView_notifications, img_notifications);
                }else {
                    hideexpnded(tv_notifications, recyclerView_notifications, img_notifications);
                }

                break;
            case R.id.rly_phone:
                if (tv_phone.getVisibility()==View.VISIBLE){
                    showexpnded(tv_phone, recyclerView_phone, img_phone);
                }else {
                    hideexpnded(tv_phone, recyclerView_phone, img_phone);
                }
                break;
            case R.id.rly_screen2:
                if (tv_screen.getVisibility()==View.VISIBLE){
                    showexpnded(tv_screen, recyclerView_screenlist, img_screen);
                }else {
                    hideexpnded(tv_screen, recyclerView_screenlist, img_screen);
                }

                break;
            case R.id.rly_volume:
                if (tv_volume.getVisibility()==View.VISIBLE){
                    showexpnded(tv_volume, recyclerView_volume, img_volume);
                }else {
                    hideexpnded(tv_volume, recyclerView_volume, img_volume);
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