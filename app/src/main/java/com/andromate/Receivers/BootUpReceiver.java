package com.andromate.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.content.ContextCompat;

import com.andromate.Constraints.FServices.ForegroundService;

public class BootUpReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent serviceIntent = new Intent(context, ForegroundService.class);
            serviceIntent.putExtra("inputExtra", "Androidmate running in background");
            ContextCompat.startForegroundService(context, serviceIntent);
        }

    }

}