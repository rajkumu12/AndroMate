package com.andromate.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Notification;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.andromate.Model.NotificationsModel;
import com.andromate.R;
import com.andromate.Ui.Adapters.NotificationsAdapters;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {
    RecyclerView recyclerView_notifications;
    private ArrayList<NotificationsModel> notificationslist;
    RelativeLayout img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        transparentStatusAndNavigation(this);
        getSupportActionBar().hide();

      /*  View decorView =getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
*/
        setContentView(R.layout.activity_notifications);

        recyclerView_notifications=findViewById(R.id.recy_notifications);

        img_back=findViewById(R.id.back_icon_id);

        loadNotifications();



        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadNotifications() {
        notificationslist=new ArrayList<>();
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));

        NotificationsAdapters notificationsAdapters = new NotificationsAdapters(NotificationsActivity.this,notificationslist);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(NotificationsActivity.this);
        recyclerView_notifications.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_notifications.setItemAnimator(new DefaultItemAnimator());
        recyclerView_notifications.setAdapter(notificationsAdapters);


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