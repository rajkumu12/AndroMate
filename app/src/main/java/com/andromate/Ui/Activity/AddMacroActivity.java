package com.andromate.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andromate.R;

public class AddMacroActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView_back,add_macro;
    LinearLayout lly_trigger;
    LinearLayout lly_action;
    LinearLayout lly_constraints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        getSupportActionBar().hide();
        transparentStatusAndNavigation(this);
      /*  View decorView =getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);*/

        setContentView(R.layout.activity_add_macro);

        imageView_back=findViewById(R.id.back_icon_addmacros);
        lly_trigger=findViewById(R.id.lly_trigger);
        lly_action=findViewById(R.id.action_ui);
        lly_constraints=findViewById(R.id.lly_constraints);
        add_macro=findViewById(R.id.add_macro);
        imageView_back.setOnClickListener(this);
        lly_trigger.setOnClickListener(this);
        lly_action.setOnClickListener(this);
        lly_constraints.setOnClickListener(this);
        add_macro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.back_icon_addmacros){
            finish();
        }else if (id==R.id.lly_trigger){
            startActivity(new Intent(AddMacroActivity.this,Add_triggersActivity.class));
        }else if (id==R.id.action_ui){
            startActivity(new Intent(AddMacroActivity.this,AddAction.class));
        }else if (id==R.id.lly_constraints){
            startActivity(new Intent(AddMacroActivity.this,AddConstraintsActivity.class));
        }else if (id==R.id.add_macro){
            showAlertDialog();
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

    private void showAlertDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alert_dialog_macro_category);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);



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
              /*  rly_data.setVisibility(View.VISIBLE);
                rly_no_data.setVisibility(View.GONE);*/
            }
        });


        dialog.show();
    }

}