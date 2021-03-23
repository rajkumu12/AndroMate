package com.andromate.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.andromate.R;

public class AddConstraintsActivity extends AppCompatActivity {

    ImageView image_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        transparentStatusAndNavigation(this);
        setContentView(R.layout.activity_add_constraints);

        image_back=findViewById(R.id.back_icon_constraints);

        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
}