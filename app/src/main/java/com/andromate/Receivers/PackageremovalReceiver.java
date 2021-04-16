package com.andromate.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.legacy.content.WakefulBroadcastReceiver;

public class PackageremovalReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, ""+intent, Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")){
            Toast.makeText(context, "UnInstalled", Toast.LENGTH_SHORT).show();
        }else if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")){
            Toast.makeText(context, "Installed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "from service", Toast.LENGTH_SHORT).show();
        }


    }
}
