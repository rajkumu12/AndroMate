package com.andromate.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.andromate.Ui.Adapters.Application_trigger_item;
import com.andromate.Ui.Adapters.NotificationsAdapters;
import com.andromate.Ui.Adapters.TriggerItemsAdapters;

import java.util.ArrayList;

public class Add_triggersActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<TriggerItemModel>applicationlist;
    ArrayList<TriggerItemModel>battery_powerslist;
    ArrayList<TriggerItemModel>call_smslist;
    ArrayList<TriggerItemModel>connectivitylist;
    ArrayList<TriggerItemModel>date_and_timelist;
    ArrayList<TriggerItemModel>deviceevent_list;
    ArrayList<TriggerItemModel>locationlist;
    ArrayList<TriggerItemModel>sensorslist;
    ArrayList<TriggerItemModel>userinpot_list;



    RecyclerView recyclerView_application;
    RecyclerView recyclerView_battery;
    RecyclerView recyclerView_call_list;
    RecyclerView recyclerView_connectivitylist;
    RecyclerView recyclerView_date_and_timelist;
    RecyclerView recyclerView_deviceevent_list;
    RecyclerView recyclerView_locationlist;
    RecyclerView recyclerView_sensorslist;
    RecyclerView recyclerView_userinpot_list;


    TextView  textView_application, textView_battery,textView_call,textView_connectivity,textView_date_and_time,
            textView_deviceevent,textView_location,textView_sensors,textView_userinpot;
    ImageView img_application, img_battery,img_call,img_connectivity,img_date_and_time,
            img_deviceevent,img_location,img_sensors,img_userinpot;

    RelativeLayout rly_application, rly_battery,rly_call,rly_connectivity,rly_date_and_time,
            rly_deviceevent,rly_location,rly_sensors,rly_userinpot;



    RelativeLayout back_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        getSupportActionBar().hide();
        transparentStatusAndNavigation(this);
        /*View decorView =getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
*/
        setContentView(R.layout.activity_add_triggers);

        fidViews();

        load_Application();
        load_battery();
        load_call();
        load_connectivity();
        load_dateandtime();
        load_device_event();
        load_location_list();
        load_sensor_list();
        load_userlist();

    }

    private void load_Application() {
       applicationlist=new ArrayList<>();
        applicationlist.add(new TriggerItemModel("App Install/Remove/\nupdate",R.drawable.suitcase));

        applicationlist.add(new TriggerItemModel("Application/Launched/\n" +
                "Closed",R.drawable.recent_app_opened));

       /* applicationlist.add(new TriggerItemModel("Recent Apps Opened",R.drawable.recent_app_opened));*/

        applicationlist.add(new TriggerItemModel("Tasker/Locate Plugin",R.drawable.tasker_locator));

        Application_trigger_item triggerItemsAdapters=new Application_trigger_item(this,applicationlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(Add_triggersActivity.this,2);
        recyclerView_application.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_application.setItemAnimator(new DefaultItemAnimator());
        recyclerView_application.setAdapter(triggerItemsAdapters);



    }

    private void load_battery() {

        battery_powerslist=new ArrayList<>();
        battery_powerslist.add(new TriggerItemModel("Battery Level",R.drawable.battery_hori_icon));
        battery_powerslist.add(new TriggerItemModel("Battery Saver State",R.drawable.battery_icon));
        battery_powerslist.add(new TriggerItemModel("Battery Temperature",R.drawable.thermometer));
        battery_powerslist.add(new TriggerItemModel("Power Button Toggle",R.drawable.power));
        battery_powerslist.add(new TriggerItemModel("Power Connected/\n" +
                "Disconnected",R.drawable.powe_connected));



        TriggerItemsAdapters triggerItemsAdapters=new TriggerItemsAdapters(this,battery_powerslist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(Add_triggersActivity.this,2);
        recyclerView_battery.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_battery.setItemAnimator(new DefaultItemAnimator());
        recyclerView_battery.setAdapter(triggerItemsAdapters);


    }

    private void load_call() {
        call_smslist=new ArrayList<>();
        call_smslist.add(new TriggerItemModel("Call Active",R.drawable.call_active));
        call_smslist.add(new TriggerItemModel("Call Ended",R.drawable.call_ended));
        call_smslist.add(new TriggerItemModel("Call Incoming",R.drawable.call_incoming));
        call_smslist.add(new TriggerItemModel("Call Missed",R.drawable.call_missed));
        call_smslist.add(new TriggerItemModel("Call Outgoing",R.drawable.call_outgoing));
        call_smslist.add(new TriggerItemModel("Dial Phone Number",R.drawable.dial_number));
        call_smslist.add(new TriggerItemModel("SMS Received",R.drawable.sms_received));



        TriggerItemsAdapters triggerItemsAdapters=new TriggerItemsAdapters(this,call_smslist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(Add_triggersActivity.this,2);
        recyclerView_call_list.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_call_list.setItemAnimator(new DefaultItemAnimator());
        recyclerView_call_list.setAdapter(triggerItemsAdapters);
    }

    private void load_connectivity() {
        connectivitylist=new ArrayList<>();
        connectivitylist.add(new TriggerItemModel("Android Wear",R.drawable.android_wear));
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



        TriggerItemsAdapters triggerItemsAdapters=new TriggerItemsAdapters(this,connectivitylist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(Add_triggersActivity.this,2);
        recyclerView_connectivitylist.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_connectivitylist.setItemAnimator(new DefaultItemAnimator());
        recyclerView_connectivitylist.setAdapter(triggerItemsAdapters);
    }

    private void load_dateandtime() {
        date_and_timelist=new ArrayList<>();
        date_and_timelist.add(new TriggerItemModel("Calendar Event",R.drawable.calendar_event));
        date_and_timelist.add(new TriggerItemModel("Day of Week/Month",R.drawable.day_month));
        date_and_timelist.add(new TriggerItemModel("Day/Time Trigger",R.drawable.day_timer));
        date_and_timelist.add(new TriggerItemModel("Regular Interval",R.drawable.regular_interval));
        date_and_timelist.add(new TriggerItemModel("Stopwatch",R.drawable.stopwatches_icon));




        TriggerItemsAdapters triggerItemsAdapters=new TriggerItemsAdapters(this,date_and_timelist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(Add_triggersActivity.this,2);
        recyclerView_date_and_timelist.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_date_and_timelist.setItemAnimator(new DefaultItemAnimator());
        recyclerView_date_and_timelist.setAdapter(triggerItemsAdapters);
    }

    private void load_device_event() {
        deviceevent_list=new ArrayList<>();
        deviceevent_list.add(new TriggerItemModel("Airplane Mode\n" +
                "Changed",R.drawable.airplane));
        deviceevent_list.add(new TriggerItemModel("Auto Rotate Changed",R.drawable.auto_rotate));
        deviceevent_list.add(new TriggerItemModel("Autosync Changed",R.drawable.async_changed));
        deviceevent_list.add(new TriggerItemModel("Clipboard Changed",R.drawable.clipboard_change));
        deviceevent_list.add(new TriggerItemModel("Daydream On/Off",R.drawable.daydrem));
        deviceevent_list.add(new TriggerItemModel("Device Boot",R.drawable.device_boot));
        deviceevent_list.add(new TriggerItemModel("Device Docked/\n" +
                "Undocked",R.drawable.device_docked));
        deviceevent_list.add(new TriggerItemModel("Failed Login Attempt",R.drawable.failed_to_login));
        deviceevent_list.add(new TriggerItemModel("GPS Enabled/\n" +
                "Disabled",R.drawable.location_gps));
        deviceevent_list.add(new TriggerItemModel("Intent Received",R.drawable.intent_received));
        deviceevent_list.add(new TriggerItemModel("Logcat Message",R.drawable.logcat_manager));
        deviceevent_list.add(new TriggerItemModel("Music/Sound Playing",R.drawable.music));
        deviceevent_list.add(new TriggerItemModel("Notification",R.drawable.bell_icon));
        deviceevent_list.add(new TriggerItemModel("Priority Mode/Do Not\n" +
                "Disturb",R.drawable.donot_disturb));
        deviceevent_list.add(new TriggerItemModel("SIM Card Change",R.drawable.simcard_change));
        deviceevent_list.add(new TriggerItemModel("Screen On/Off",R.drawable.screen_on_0ff));
        deviceevent_list.add(new TriggerItemModel("Screen Unlocked",R.drawable.screen_unlock));
        deviceevent_list.add(new TriggerItemModel("Silent Mode Enabled/\n" +
                "DIsabled",R.drawable.silent_mode));






        TriggerItemsAdapters triggerItemsAdapters=new TriggerItemsAdapters(this,deviceevent_list);
        GridLayoutManager layoutManager2 = new GridLayoutManager(Add_triggersActivity.this,2);
        recyclerView_deviceevent_list.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_deviceevent_list.setItemAnimator(new DefaultItemAnimator());
        recyclerView_deviceevent_list.setAdapter(triggerItemsAdapters);
    }

    private void load_location_list() {
        locationlist=new ArrayList<>();
        locationlist.add(new TriggerItemModel("Cell Tower Change",R.drawable.cell_tower_change));
        locationlist.add(new TriggerItemModel("Geofence Trigger",R.drawable.cell_tower_change));
        locationlist.add(new TriggerItemModel("Location Trigger",R.drawable.locations));
        locationlist.add(new TriggerItemModel("Sunset/Sunrise",R.drawable.sunrise));
        locationlist.add(new TriggerItemModel("Weather",R.drawable.weather));

        TriggerItemsAdapters triggerItemsAdapters=new TriggerItemsAdapters(this,locationlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(Add_triggersActivity.this,2);
        recyclerView_locationlist.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_locationlist.setItemAnimator(new DefaultItemAnimator());
        recyclerView_locationlist.setAdapter(triggerItemsAdapters);

    }

    private void load_sensor_list() {
        sensorslist=new ArrayList<>();
        sensorslist.add(new TriggerItemModel("Activity Recognition",R.drawable.activity_recognition));
        sensorslist.add(new TriggerItemModel("Flip Device",R.drawable.flip_devices));
        sensorslist.add(new TriggerItemModel("Light Sensor",R.drawable.light_sensor));
        sensorslist.add(new TriggerItemModel("Proximity Sensor",R.drawable.proximity));
        sensorslist.add(new TriggerItemModel("Screen Orientation",R.drawable.screen_orientation));
        sensorslist.add(new TriggerItemModel("Shake Device",R.drawable.shake_device));

        TriggerItemsAdapters triggerItemsAdapters=new TriggerItemsAdapters(this,sensorslist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(Add_triggersActivity.this,2);
        recyclerView_sensorslist.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_sensorslist.setItemAnimator(new DefaultItemAnimator());
        recyclerView_sensorslist.setAdapter(triggerItemsAdapters);
    }

    private void load_userlist() {
        userinpot_list=new ArrayList<>();
        userinpot_list.add(new TriggerItemModel("Fingerprint Gesture",R.drawable.finger_print_gesture));
        userinpot_list.add(new TriggerItemModel("Floating Button",R.drawable.floating_buttons));
        userinpot_list.add(new TriggerItemModel("Home Button Long\n" +
                "Press",R.drawable.home_buttom_long));
        userinpot_list.add(new TriggerItemModel("Media Button\n" +
                "Pressed",R.drawable.media_button_pressed));
        userinpot_list.add(new TriggerItemModel("Media Button V2",R.drawable.mdia_pressed_v_t));
        userinpot_list.add(new TriggerItemModel("Shortcut Launched",R.drawable.shortcut_launched));
        userinpot_list.add(new TriggerItemModel("Swipe Screen",R.drawable.swipe_screen));
        userinpot_list.add(new TriggerItemModel("Volume Button Long \n" +
                "Press",R.drawable.volume_white));
        userinpot_list.add(new TriggerItemModel("Volume Button\n" +
                "Pressed",R.drawable.volume_white));
        userinpot_list.add(new TriggerItemModel("Widget Button",R.drawable.widgets));


        TriggerItemsAdapters triggerItemsAdapters=new TriggerItemsAdapters(this,userinpot_list);
        GridLayoutManager layoutManager2 = new GridLayoutManager(Add_triggersActivity.this,2);
        recyclerView_userinpot_list.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_userinpot_list.setItemAnimator(new DefaultItemAnimator());
        recyclerView_userinpot_list.setAdapter(triggerItemsAdapters);

    }

    private void fidViews() {
        recyclerView_application=findViewById(R.id.recy_application);
        recyclerView_battery=findViewById(R.id.recy_battery_power);
        recyclerView_call_list=findViewById(R.id.recy_callsms);
        recyclerView_connectivitylist=findViewById(R.id.recy_connectivity);
        recyclerView_date_and_timelist=findViewById(R.id.recy_date_time);
        recyclerView_deviceevent_list=findViewById(R.id.recy_device_event);
        recyclerView_locationlist=findViewById(R.id.recy_location);
        recyclerView_sensorslist=findViewById(R.id.recy_sensors);
        recyclerView_userinpot_list=findViewById(R.id.recy_user_input);


        textView_application=findViewById(R.id.tv_applications);
        textView_battery=findViewById(R.id.tv_battery_power);
        textView_call=findViewById(R.id.tv_callsms);
        textView_connectivity=findViewById(R.id.tv_connectivity);
        textView_date_and_time=findViewById(R.id.tv_date_time);
        textView_deviceevent=findViewById(R.id.tv_device_event);
        textView_location=findViewById(R.id.tv_location);
        textView_sensors=findViewById(R.id.tv_sensors);
        textView_userinpot=findViewById(R.id.tv_userInput);


        img_application=findViewById(R.id.icon_applications);
        img_battery=findViewById(R.id.icon_battery);
        img_call=findViewById(R.id.icon_call);
        img_connectivity=findViewById(R.id.icon_coonectivity);
        img_date_and_time=findViewById(R.id.icon_date_time);
        img_deviceevent=findViewById(R.id.device_invent);
        img_location=findViewById(R.id.icon_location);
        img_sensors=findViewById(R.id.icon_sensors);
        img_userinpot=findViewById(R.id.icon_userinput);



        rly_application=findViewById(R.id.rly_applicationd);
        rly_battery=findViewById(R.id.rly_battery);
        rly_call=findViewById(R.id.rly_call);
        rly_connectivity=findViewById(R.id.rly_coonectivity);
        rly_date_and_time=findViewById(R.id.rly_date_time);
        rly_deviceevent=findViewById(R.id.rly_device_invent);
        rly_location=findViewById(R.id.rly_location);
        rly_sensors=findViewById(R.id.rly_sensors);
        rly_userinpot=findViewById(R.id.rly_userinput);

        back_image=findViewById(R.id.back_icon_trigger);


        textView_application.setOnClickListener(this);
        textView_battery.setOnClickListener(this);
        textView_call.setOnClickListener(this);
        textView_connectivity.setOnClickListener(this);
        textView_date_and_time.setOnClickListener(this);
        textView_deviceevent.setOnClickListener(this);
        textView_location.setOnClickListener(this);
        textView_sensors.setOnClickListener(this);
        textView_userinpot.setOnClickListener(this);

      /*  img_application.setOnClickListener(this);
        img_battery.setOnClickListener(this);
        img_call.setOnClickListener(this);
        img_connectivity.setOnClickListener(this);
        img_date_and_time.setOnClickListener(this);
        img_deviceevent.setOnClickListener(this);
        img_location.setOnClickListener(this);
        img_sensors.setOnClickListener(this);
        img_userinpot.setOnClickListener(this);*/


        rly_application.setOnClickListener(this);
        rly_battery.setOnClickListener(this);
        rly_call.setOnClickListener(this);
        rly_connectivity.setOnClickListener(this);
        rly_date_and_time.setOnClickListener(this);
        rly_deviceevent.setOnClickListener(this);
        rly_location.setOnClickListener(this);
        rly_sensors.setOnClickListener(this);
        rly_userinpot.setOnClickListener(this);


        back_image.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
      if (id==R.id.tv_applications){
          textView_application.setVisibility(View.GONE);
          recyclerView_application.setVisibility(View.VISIBLE);
          img_application.setColorFilter(CustomColors.golden);
      }else if (id==R.id.rly_applicationd){
          if (textView_application.getVisibility()==View.VISIBLE){
              textView_application.setVisibility(View.GONE);
              recyclerView_application.setVisibility(View.VISIBLE);
              img_application.setColorFilter(CustomColors.golden);
          }else {
              textView_application.setVisibility(View.VISIBLE);
              recyclerView_application.setVisibility(View.GONE);
              img_application.setColorFilter(CustomColors.black);
          }

      }else if (id==R.id.tv_battery_power){
          textView_battery.setVisibility(View.GONE);
          recyclerView_battery.setVisibility(View.VISIBLE);
          img_battery.setColorFilter(CustomColors.golden);
      }else if (id==R.id.rly_battery){
          if (textView_battery.getVisibility()==View.VISIBLE){
              textView_battery.setVisibility(View.GONE);
              recyclerView_battery.setVisibility(View.VISIBLE);
              img_battery.setColorFilter(CustomColors.golden);
          }else {
              textView_battery.setVisibility(View.VISIBLE);
              recyclerView_battery.setVisibility(View.GONE);
              img_battery.setColorFilter(CustomColors.black);
          }
      }else if (id==R.id.tv_callsms){
          textView_call.setVisibility(View.GONE);
          recyclerView_call_list.setVisibility(View.VISIBLE);
          img_call.setColorFilter(CustomColors.golden);
      }else if (id==R.id.rly_call){
          if (textView_call.getVisibility()==View.VISIBLE){
              textView_call.setVisibility(View.GONE);
              recyclerView_call_list.setVisibility(View.VISIBLE);
              img_call.setColorFilter(CustomColors.golden);
          }else {
              textView_call.setVisibility(View.VISIBLE);
              recyclerView_call_list.setVisibility(View.GONE);
              img_call.setColorFilter(CustomColors.black);
          }

      }else if (id==R.id.tv_connectivity){
          textView_connectivity.setVisibility(View.GONE);
          recyclerView_connectivitylist.setVisibility(View.VISIBLE);
          img_connectivity.setColorFilter(CustomColors.golden);
      }else if (id==R.id.rly_coonectivity){
          if (textView_connectivity.getVisibility()==View.VISIBLE){
              textView_connectivity.setVisibility(View.GONE);
              recyclerView_connectivitylist.setVisibility(View.VISIBLE);
              img_connectivity.setColorFilter(CustomColors.golden);
          }else {
              textView_connectivity.setVisibility(View.VISIBLE);
              recyclerView_connectivitylist.setVisibility(View.GONE);
              img_connectivity.setColorFilter(CustomColors.black);
          }

      }else if (id==R.id.tv_date_time){
          textView_date_and_time.setVisibility(View.GONE);
          recyclerView_date_and_timelist.setVisibility(View.VISIBLE);
          img_date_and_time.setColorFilter(CustomColors.golden);
      }else if (id==R.id.rly_date_time){
          if (textView_date_and_time.getVisibility()==View.VISIBLE){
              textView_date_and_time.setVisibility(View.GONE);
              recyclerView_date_and_timelist.setVisibility(View.VISIBLE);
              img_date_and_time.setColorFilter(CustomColors.golden);
          }else {
              textView_date_and_time.setVisibility(View.VISIBLE);
              recyclerView_date_and_timelist.setVisibility(View.GONE);
              img_date_and_time.setColorFilter(CustomColors.black);
          }

      }else if (id==R.id.tv_device_event){
          textView_deviceevent.setVisibility(View.GONE);
          recyclerView_deviceevent_list.setVisibility(View.VISIBLE);
          img_deviceevent.setColorFilter(CustomColors.golden);
      }else if (id==R.id.rly_device_invent){
          if (textView_deviceevent.getVisibility()==View.VISIBLE){
              textView_deviceevent.setVisibility(View.GONE);
              recyclerView_deviceevent_list.setVisibility(View.VISIBLE);
              img_deviceevent.setColorFilter(CustomColors.golden);
          }else {
              textView_deviceevent.setVisibility(View.VISIBLE);
              recyclerView_deviceevent_list.setVisibility(View.GONE);
              img_deviceevent.setColorFilter(CustomColors.black);
          }

      }else if (id==R.id.tv_location){
          textView_location.setVisibility(View.GONE);
          recyclerView_locationlist.setVisibility(View.VISIBLE);
          img_location.setColorFilter(CustomColors.golden);
      }else if (id==R.id.rly_location){
          if (textView_location.getVisibility()==View.VISIBLE){
              textView_location.setVisibility(View.GONE);
              recyclerView_locationlist.setVisibility(View.VISIBLE);
              img_location.setColorFilter(CustomColors.golden);
          }else {
              textView_location.setVisibility(View.VISIBLE);
              recyclerView_locationlist.setVisibility(View.GONE);
              img_location.setColorFilter(CustomColors.black);
          }

      }else if (id==R.id.tv_sensors){
          textView_sensors.setVisibility(View.GONE);
          recyclerView_sensorslist.setVisibility(View.VISIBLE);
          img_sensors.setColorFilter(CustomColors.golden);
      }else if (id==R.id.rly_sensors){
          if (textView_sensors.getVisibility()==View.VISIBLE){
              textView_sensors.setVisibility(View.GONE);
              recyclerView_sensorslist.setVisibility(View.VISIBLE);
              img_sensors.setColorFilter(CustomColors.golden);
          }else {
              textView_sensors.setVisibility(View.VISIBLE);
              recyclerView_sensorslist.setVisibility(View.GONE);
              img_sensors.setColorFilter(CustomColors.black);
          }

      }else if (id==R.id.tv_userInput){
          textView_userinpot.setVisibility(View.GONE);
          recyclerView_userinpot_list.setVisibility(View.VISIBLE);
          img_userinpot.setColorFilter(CustomColors.golden);
      }else if (id==R.id.rly_userinput){
          if (textView_userinpot.getVisibility()==View.VISIBLE){
              textView_userinpot.setVisibility(View.GONE);
              recyclerView_userinpot_list.setVisibility(View.VISIBLE);
              img_userinpot.setColorFilter(CustomColors.golden);
          }else {
              textView_userinpot.setVisibility(View.VISIBLE);
              recyclerView_userinpot_list.setVisibility(View.GONE);
              img_userinpot.setColorFilter(CustomColors.black);
          }

      }else if (id==R.id.back_icon_trigger){
          finish();
      }

    }


    public static void transparentStatusAndNavigation(Activity activity) {

        Window window = activity.getWindow();

        // make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(window,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
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
            setWindowFlag(window ,windowManager, false);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

    }
    private static void setWindowFlag(Window window,final int bits, boolean on) {
        Window win =window;
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}