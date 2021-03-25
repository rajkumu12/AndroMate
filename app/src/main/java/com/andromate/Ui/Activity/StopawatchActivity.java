package com.andromate.Ui.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.ValueIterator;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andromate.Model.StopWatches_Model;
import com.andromate.R;
import com.andromate.Ui.Adapters.NotificationsAdapters;
import com.andromate.Ui.Adapters.StopWatchesAdapters;

import java.util.ArrayList;
import java.util.List;

public class StopawatchActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout back_stopwatch;
    ImageView alert_open;
    List<StopWatches_Model>stopwatcharraylist;
    RecyclerView recy_stopwatch;
    RelativeLayout rly_no_data,rly_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        transparentStatusAndNavigation(this);
        setContentView(R.layout.activity_stopawatch);
        back_stopwatch=findViewById(R.id.back_stopwatch);
        alert_open=findViewById(R.id.btn_alert_dialog);
        recy_stopwatch=findViewById(R.id.recy_item_stopwatches);
        rly_no_data=findViewById(R.id.rly_nodata);
        rly_data=findViewById(R.id.rly_stowatch_list);


        alert_open.setOnClickListener(this);

        loadData();

        back_stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData() {
       stopwatcharraylist=new ArrayList<>();
       stopwatcharraylist.add(new StopWatches_Model("sdkfffff"));
       stopwatcharraylist.add(new StopWatches_Model("sdkfffff"));
        StopWatchesAdapters stopWatchesAdapters = new StopWatchesAdapters(StopawatchActivity.this,stopwatcharraylist);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(StopawatchActivity.this);
        recy_stopwatch.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recy_stopwatch.setItemAnimator(new DefaultItemAnimator());
        recy_stopwatch.setAdapter(stopWatchesAdapters);

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

    private void showAlertDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alert_dialog_alarm);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);


        EditText et_name=dialog.findViewById(R.id.et_etname1);
        TextView cancel=dialog.findViewById(R.id.tv_cancel);
        TextView ok=dialog.findViewById(R.id.tv_ok);



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                rly_data.setVisibility(View.VISIBLE);
                rly_no_data.setVisibility(View.GONE);
            }
        });


        dialog.show();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.btn_alert_dialog){
            showAlertDialog();
        }
    }
}