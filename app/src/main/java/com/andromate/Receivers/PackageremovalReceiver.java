package com.andromate.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import androidx.legacy.content.WakefulBroadcastReceiver;

import com.andromate.Constraints.IdentifyandPerformAction;
import com.andromate.Constraints.Pemisssions;
import com.andromate.db.DBHelper;

public class PackageremovalReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedpreferences =context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        String appevent = sharedpreferences.getString("appevent", "");
        DBHelper dbHelper=new DBHelper(context);
        Toast.makeText(context, "iiiii"+intent.getAction(), Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")){

        }else if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")){
            if (!appevent.isEmpty() && appevent.equals("Application Installed")) {
                Cursor cursor = dbHelper.checktrigger(appevent);
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        String id = cursor.getString(1);
                        dbHelper.updatemacro(Pemisssions.currenttime(), id);
                        dbHelper.getAction(id);
                        Cursor cursor2 = dbHelper.getAction(id);
                        if (cursor2 != null && cursor2.moveToFirst()) {
                            do {
                                String actionname = cursor2.getString(2);
                                String actionname_des = cursor2.getString(3);
                                Log.d("iiiiiiiiiiiii", "uid" + actionname_des);
                                IdentifyandPerformAction.performAction(actionname,actionname_des,context);
                            } while (cursor2.moveToNext());
                        }
                    } while (cursor.moveToNext());
                }
            }
        }else {
            Toast.makeText(context, "from service", Toast.LENGTH_SHORT).show();
        }


    }
}
