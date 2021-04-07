package com.andromate.Services;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service
{
    private static BroadcastReceiver m_ScreenOffReceiver;

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        registerScreenOffReceiver();
    }

    @Override
    public void onDestroy()
    {
        unregisterReceiver(m_ScreenOffReceiver);
        m_ScreenOffReceiver = null;
    }

    private void registerScreenOffReceiver()
    {
        IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_REMOVED);
        registerReceiver(m_ScreenOffReceiver, filter);
    }
}