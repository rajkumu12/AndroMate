package com.andromate.Ui.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andromate.CustomColors;
import com.andromate.Model.TriggerItemModel;
import com.andromate.R;
import com.andromate.Ui.Adapters.TriggerItemsAdapters;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ConstraintsFrag extends Fragment implements View.OnClickListener{

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

    public ConstraintsFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ConstraintsFrag.
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_constraints, container, false);
        findViews(view);

        loadpowerbattery();
        loadnotifications();
        loadconnectivity();
        loaddatetime();
        loaddevicestate();
        loadphone();
        loadsensor();
        loadspeaker();
        return view;
    }
    private void loadpowerbattery() {
        battery_powerlist=new ArrayList<>();
        battery_powerlist.add(new TriggerItemModel("Battery Level",R.drawable.battery_hori_icon));
        battery_powerlist.add(new TriggerItemModel("Battery Saver State",R.drawable.battery_icon));
        battery_powerlist.add(new TriggerItemModel("Battery Temperature",R.drawable.thermometer));
        battery_powerlist.add(new TriggerItemModel("Power Button Toggle",R.drawable.power));
        battery_powerlist.add(new TriggerItemModel("Power Connected/\n" +
                "Disconnected",R.drawable.powe_connected));



        TriggerItemsAdapters triggerItemsAdapters=new TriggerItemsAdapters(getContext(),battery_powerlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(),2);
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



        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(getContext(), notificationlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(), 2);
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


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(getContext(), connectivitylist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(), 2);
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


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(getContext(), datetimelist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(), 2);
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


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(getContext(), devicestatelist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(), 2);
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


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(getContext(), phonelist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(), 2);
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

        TriggerItemsAdapters triggerItemsAdapters=new TriggerItemsAdapters(getContext(),sensorlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(),2);
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


        TriggerItemsAdapters triggerItemsAdapters = new TriggerItemsAdapters(getContext(), screen_speakerlist);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(), 2);
        recyclerView_screen_speaker.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_screen_speaker.setItemAnimator(new DefaultItemAnimator());
        recyclerView_screen_speaker.setAdapter(triggerItemsAdapters);
    }
    private void findViews(View view) {

        recyclerView_battery_power =view.findViewById(R.id.recy_battery_power);
        recyclerView_notification = view.findViewById(R.id.recy_notifications);
        ;
        recyclerView_connectivity =view. findViewById(R.id.recy_connectivity);
        ;
        recyclerViewc_datetime = view.findViewById(R.id.recy_date_time);
        ;
        recyclerView_devicestate = view.findViewById(R.id.recy_device_state);
        ;
        recyclerView_phone = view.findViewById(R.id.recy_phone);
        ;
        recyclerView_sensor =view. findViewById(R.id.recy_sensors);
        ;
        recyclerView_screen_speaker =view.findViewById(R.id.recy_speaker);
        ;

        tv_battery_power = view.findViewById(R.id.tv_battery_power);
        tv_notification =view.findViewById(R.id.tv_notifications);
        tv_connectivity =view. findViewById(R.id.tv_connectivity);
        tv_datetime = view.findViewById(R.id.tv_date_time);
        tv_devicestate =view. findViewById(R.id.tv_device_state);
        tv__phone = view.findViewById(R.id.tv_phone);
        tv_sensor =view. findViewById(R.id.tv_sensors);
        tv_screen_speaker = view.findViewById(R.id.tv_speaker);
        ;


        img_battery_power = view.findViewById(R.id.icon_battery);
        img_notification =view. findViewById(R.id.icon_bell);
        img_connectivity =view. findViewById(R.id.icon_coonectivity);
        img_datetime = view.findViewById(R.id.icon_date_time);
        img_devicestate =view. findViewById(R.id.device_state);
        img_phone =view. findViewById(R.id.icon_phone);
        img_sensor =view. findViewById(R.id.icon_sensors);
        img_screen_speaker = view.findViewById(R.id.icon_speake);
        ;


        tv_battery_power.setOnClickListener(this);
        tv_notification.setOnClickListener(this);
        tv_connectivity.setOnClickListener(this);
        tv_datetime.setOnClickListener(this);
        tv_devicestate.setOnClickListener(this);
        tv__phone.setOnClickListener(this);
        tv_sensor.setOnClickListener(this);
        tv_screen_speaker.setOnClickListener(this);


        img_battery_power.setOnClickListener(this);
        img_notification.setOnClickListener(this);
        img_connectivity.setOnClickListener(this);
        img_datetime.setOnClickListener(this);
        img_devicestate.setOnClickListener(this);
        img_phone.setOnClickListener(this);
        img_sensor.setOnClickListener(this);
        img_screen_speaker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
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
            case R.id.icon_battery:
                hideexpnded(tv_battery_power, recyclerView_battery_power, img_battery_power);
                break;
            case R.id.icon_bell:
                hideexpnded(tv_notification, recyclerView_notification, img_notification);
                break;
            case R.id.icon_coonectivity:
                hideexpnded(tv_connectivity, recyclerView_connectivity, img_connectivity);
                break;
            case R.id.icon_date_time:
                hideexpnded(tv_datetime, recyclerViewc_datetime, img_datetime);
                break;
            case R.id.device_state:
                hideexpnded(tv_devicestate, recyclerView_devicestate, img_devicestate);
                break;
            case R.id.icon_phone:
                hideexpnded(tv__phone, recyclerView_phone, img_phone);
                break;
            case R.id.icon_sensors:
                hideexpnded(tv_sensor,recyclerView_sensor,img_sensor);
                break;
            case R.id.icon_speake:
                hideexpnded(tv_screen_speaker, recyclerView_screen_speaker, img_screen_speaker);
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