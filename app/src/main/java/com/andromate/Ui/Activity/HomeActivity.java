package com.andromate.Ui.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andromate.Constraints.FServices.ForegroundService;
import com.andromate.R;
import com.andromate.Services.ExampleJobService;
import com.andromate.Ui.Fragments.HomeFragments;
import com.andromate.Ui.Fragments.InviteFragment;
import com.andromate.Ui.Fragments.MacrosFragments;
import com.andromate.Ui.Fragments.SettingsFragments;
import com.andromate.Ui.Fragments.TemplatesFragments;
import com.andromate.Ui.On;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener,BottomNavigationView.OnNavigationItemSelectedListener {
    public static BottomNavigationView navigation;
    DrawerLayout drawerLayout;
    RelativeLayout image_hamburger;
    ImageView img_bell;
    TextView tv_heading;
    public static String TAG="HomeActivity";
    LinearLayout lly_nav_invite,lly_home;
    public static String id1 = "test_channel_01";
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        getSupportActionBar().hide();
        transparentStatusAndNavigation(this);
        /* View decorView =getWindow().getDecorView();*/
       /* decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
*/
        setContentView(R.layout.activity_home);
        startService();
        drawerLayout = findViewById(R.id.drawer);
        image_hamburger = findViewById(R.id.hamburger_icon);
        navigation = findViewById(R.id.bottom_navigation);
        img_bell = findViewById(R.id.notifications_bell);
        lly_nav_invite = findViewById(R.id.nvd_invite_friend);
        lly_home = findViewById(R.id.nvd_home);
        tv_heading = findViewById(R.id.tv_heading);
        permission();

        loadFragment(new HomeFragments());
        tv_heading.setText("ANDROMATE");

        image_hamburger.setOnClickListener(this);
        navigation.setOnNavigationItemSelectedListener(this);
        img_bell.setOnClickListener(this);
        lly_nav_invite.setOnClickListener(this);
        lly_home.setOnClickListener(this);


        // Job Scheduler

    }

    @SuppressLint({"WrongConstant", "SetTextI18n"})
    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.hamburger_icon){
            if (!drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.openDrawer(Gravity.START);
            else {
                drawerLayout.closeDrawer(Gravity.START);
            }
        }else if (id==R.id.notifications_bell){
            startActivity(new Intent(HomeActivity.this,NotificationsActivity.class));
        }else if (id==R.id.nvd_invite_friend){
            loadFragment(new InviteFragment());
            tv_heading.setText("Invite friend");
            drawerLayout.closeDrawer(Gravity.START);
        }else if (id==R.id.nvd_home){
            loadFragment(new HomeFragments());
            tv_heading.setText("Home");
            navigation.setSelectedItemId(R.id.navigation_home);
            drawerLayout.closeDrawer(Gravity.START);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();
        if (id == R.id.navigation_home) {
            fragment = new HomeFragments();
            tv_heading.setText("ANDROMATE");
        } else if (id == R.id.navigation_macros) {
            fragment = new MacrosFragments();
            tv_heading.setText("Macros");
        } else if (id == R.id.navigation_templates) {
            fragment = new TemplatesFragments();
            tv_heading.setText("Templates");
        } else if (id == R.id.navigation_settings) {
            tv_heading.setText("Settings");
            fragment = new SettingsFragments();
        }
        return loadFragment(fragment);
    }
    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           /* Intent service = new Intent(this, MyService.class);
            startForegroundService(service);*/
        } else {
           /* Intent service = new Intent(this, MyService.class);
            startService(service);*/
        }

    }

    void  permission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String packageName = getApplicationContext().getPackageName();
            PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
            if(!pm.isIgnoringBatteryOptimizations(packageName)) {
                Intent intent = new Intent();
                intent.setAction(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("package:" + packageName));
                startActivity(intent);
            }
        }
    }



    public void startService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("inputExtra", "Androidmate running in background");
        ContextCompat.startForegroundService(this, serviceIntent);
    }
}