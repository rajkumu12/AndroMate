package com.andromate.Ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andromate.R;
import com.andromate.Ui.Fragments.HomeFragments;
import com.andromate.Ui.Fragments.InviteFragment;
import com.andromate.Ui.Fragments.MacrosFragments;
import com.andromate.Ui.Fragments.SettingsFragments;
import com.andromate.Ui.Fragments.TemplatesFragments;
import com.andromate.Ui.On;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener,BottomNavigationView.OnNavigationItemSelectedListener {
    public static BottomNavigationView navigation;
    DrawerLayout drawerLayout;
    ImageView image_hamburger;
    ImageView img_bell;
    TextView tv_heading;

    LinearLayout lly_nav_invite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.drawer);
        image_hamburger=findViewById(R.id.hamburger_icon);
        navigation = findViewById(R.id.bottom_navigation);
        img_bell = findViewById(R.id.notifications_bell);
        lly_nav_invite = findViewById(R.id.nvd_invite_friend);
        tv_heading=findViewById(R.id.tv_heading);


        loadFragment(new HomeFragments());

        image_hamburger.setOnClickListener(this);
        navigation.setOnNavigationItemSelectedListener(this);
        img_bell.setOnClickListener(this);
        lly_nav_invite.setOnClickListener(this);
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
            tv_heading.setText("Invite friends");
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
}