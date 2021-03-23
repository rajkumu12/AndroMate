package com.andromate.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.TextView;

import com.andromate.Model.TriggerItemModel;
import com.andromate.R;

import java.util.ArrayList;

public class AddAction extends AppCompatActivity implements View.OnClickListener {
    ArrayList<TriggerItemModel> applicationlist;
    ArrayList<TriggerItemModel>cameraphotolist;
    ArrayList<TriggerItemModel>conditionlist;
    ArrayList<TriggerItemModel>connectivitylist;
    ArrayList<TriggerItemModel>datetimelist;
    ArrayList<TriggerItemModel>deviceactionlist;
    ArrayList<TriggerItemModel>devicesettinglist;
    ArrayList<TriggerItemModel>fileslist;
    ArrayList<TriggerItemModel>locationslist;
    ArrayList<TriggerItemModel>logginglist;
    ArrayList<TriggerItemModel>medialists;
    ArrayList<TriggerItemModel>messaginglist;
    ArrayList<TriggerItemModel>notificationslist;
    ArrayList<TriggerItemModel>phonelist;
    ArrayList<TriggerItemModel>screenlist;
    ArrayList<TriggerItemModel>volumelist;


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

    TextView tv_application, tv_cameraphoto,tv_ondition,tv_connectivity,tv_datetime,
            tv_deviceaction,tv_devicesetting,tv_files,tv_locations,tv_logging,tv_media,tv_messaging,tv_notifications,tv_phone,tv_screen,tv_volume;
    ImageView img_application, img_cameraphoto,img_ondition,img_connectivity,img_datetime,
            img_deviceaction,img_devicesetting,img_files,img_locations,img_logging,img_media,img_messaging,img_notifications,img_phone,img_screen,img_volume;

    ImageView imageView_back;
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

       /* loadApplications();
        loadcameraphoto();
        loadondition();
        loadconnectivity;
        loaddatetime;
        loadApplications();
        loadApplications();
        loadApplications();
        loadApplications();
        loadApplications();
        loadApplications();
        loadApplications();
        loadApplications();
        loadApplications();
        loadApplications();
        loadApplications();*/





    }

    private void findViews() {

        imageView_back=findViewById(R.id.back_icon_add_action);
         recyclerView_application=findViewById(R.id.recy_application);
        recyclerView_cameraphoto=findViewById(R.id.recy_camera_photo);
        recyclerView_ondition=findViewById(R.id.recy_loop);
        recyclerViewc_connectivity=findViewById(R.id.recy_connectivity);
        recyclerView_datetime=findViewById(R.id.recy_datetime);
        recyclerView_deviceaction=findViewById(R.id.recy_device_action);
        recyclerView_devicesetting=findViewById(R.id.recy_device_setting);
        recyclerView_files=findViewById(R.id.recy_files);
        recyclerView_locations=findViewById(R.id.recy_location);
        recyclerView_logging=findViewById(R.id.recy_logging);
        recyclerViewc_media=findViewById(R.id.recy_media);
        recyclerView_messaging=findViewById(R.id.recy_messaging);
        recyclerView_notifications=findViewById(R.id.recy_notifications);
        recyclerView_phone=findViewById(R.id.recy_phone);
        recyclerView_screenlist=findViewById(R.id.recy_screen);
       recyclerView_volume=findViewById(R.id.recy_volume);

        tv_application=findViewById(R.id.tv_applic);
        tv_cameraphoto=findViewById(R.id.tv_camera_photo);
        tv_ondition=findViewById(R.id.tv_loop_icons);
        tv_connectivity=findViewById(R.id.tv_connectivity);
        tv_datetime=findViewById(R.id.tv_date_time);
        tv_deviceaction=findViewById(R.id.tv_device_action);
        tv_devicesetting=findViewById(R.id.tv_device_setting);
        tv_files=findViewById(R.id.tv_files);
        tv_locations=findViewById(R.id.tv_location);
        tv_logging=findViewById(R.id.tv_logging);
        tv_media=findViewById(R.id.tv_media);
        tv_messaging=findViewById(R.id.tv_messaging);
        tv_notifications=findViewById(R.id.tv_notifications);
        tv_phone=findViewById(R.id.tv_phone);
        tv_screen=findViewById(R.id.tv_screen);
        tv_volume=findViewById(R.id.tv_volume);

        img_application=findViewById(R.id.icon_applications);
        img_cameraphoto=findViewById(R.id.Camera_Photo);
        img_ondition=findViewById(R.id.icon_loop);
        img_connectivity=findViewById(R.id.icon_connectivity);
        img_datetime=findViewById(R.id.icon_date_time);
        img_deviceaction=findViewById(R.id.icon_device_action);
        img_devicesetting=findViewById(R.id.icon_device_setting);
        img_files=findViewById(R.id.icon_files);
        img_locations=findViewById(R.id.icon_location);
        img_logging=findViewById(R.id.icon_logging);
        img_media=findViewById(R.id.icon_media);
        img_messaging=findViewById(R.id.icon_messaging);
        img_notifications=findViewById(R.id.icon_bell_not);
        img_phone=findViewById(R.id.icon_phone);
        img_screen=findViewById(R.id.icon_screen2);
        img_volume=findViewById(R.id.icon_volume);;


        imageView_back.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.back_icon_add_action){
            finish();
        }
    }
}