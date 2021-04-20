package com.andromate.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AppCloseLaunchedPackage  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        /*Toast.makeText(context, ""+intent, Toast.LENGTH_SHORT).show();*/
        if (intent.getAction().equals("android.intent.action.PACKAGE_FIRST_LAUNCH")){
            Toast.makeText(context, "App launched", Toast.LENGTH_SHORT).show();
        }else if (intent.getAction().equals("android.intent.action.PACKAGE_DATA_CLEARED")){
            Toast.makeText(context, "App closed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "from service", Toast.LENGTH_SHORT).show();
        }


    }
}
