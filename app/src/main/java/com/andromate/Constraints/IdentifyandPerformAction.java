package com.andromate.Constraints;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Environment;
import android.os.Parcelable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.Toast;

import com.andromate.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class IdentifyandPerformAction {

    public static void performAction(String actionname, String action_des, Context context) throws IOException, InterruptedException {
        Log.d("fjdfhdjfhhf","jdjdjdjdj"+action_des);
        switch (actionname) {
            case "App Enable/Disable":
               appenabledisable(action_des);
                break;
            case "Clear App Data":
                cleardata(action_des);
                break;
            case "Kill Application":
                killApplication(action_des,context);
                break;
            case "Kill Background\nProcess":
              killbackground(action_des,context);
                break;
            case "Launch Application":
                    launchApplication(action_des,context);
                break;
            case "Launch Shortcut":
                lauchShortcutof_app(action_des,context);
                break;
            case "Launch and Press":

                break;
            case "Locale/Tasker Plugin":

                break;
            case "Open Website/HTTP\nGET":

                break;
            case "Shell Script":

                break;
            case "Camera Enable/\n" +
                    "Disable":

                break;
            case "Open Last Photo":

                break;
            case "Share Last Photo":

                break;
            case "Take Picture":
                takePicture(action_des,actionname,context);
                break;
            case "Take Screenshot":
                takeScreenshot(action_des,actionname,context);
                break;
            case "Break From Loop":

                break;
            case "Continue Loop":

                break;
            case "If Confirmed then":

                break;
            case "If clause":

                break;
            case "Repeat actions":

                break;
            case "Airplane Mode On/Off":

                break;
            case "Android Wear":

                break;
            case "Auto Sync On/Off":

                break;
            case "Bluetooth Configure":

                break;
            case "Bluetooth tethering":

                break;
            case "Connectivity Check":

                break;
            case "HotSpot On/Off":

                break;
            case "Mobile Data On/Off":

                break;
            case "Send Intent":

                break;
            case "Sync Account":

                break;
            case "USB Tethering":

                break;
            case "Wifi Configuration":

                break;
            case "Calendar Event":

                break;
            case "Day of Week/Month":

                break;
            case "Day/Time Trigger":

                break;
            case "Regular Interval":

                break;
            case "Stopwatch":

                break;
            case "Android Shortcut":

                break;
            case "Expand/Collapse\n" +
                    "Status Bar":

                break;
            case "Fill Clipboard":

                break;
            case "Launch Home Screen":

                break;
            case "Press Back Button":

                break;
            case "Reboot/Power Off":

                break;
            case "Speak Text":

                break;
            case "Torch On/Off":

                break;

            case "UI Interaction":

                break;
            case "Vibrate":

                break;
            case "Voice Search":

                break;
            case "Ambient Display":

                break;
            case "Auto rotate On/Off":

                break;
            case "Battery Saver":

                break;
            case "Car Mode":

                break;
            case "Daydream/\nScreenSaver":

                break;
            case "Demo Mode":

                break;
            case "Immersive Node":

                break;
            case "Keyboard-Set\nDefault":

                break;
            case "Secure Setting":

                break;
            case "Set Quick Setting Tile State":

                break;
            case "Set Screen Lock":

                break;
            case "Set Wallpaper":

                break;
            case "System Setting":

                break;
            case "File Operation V2":

                break;
            case "Open File":

                break;
            case "Write to File":

                break;
            case "Cell Tower Change":

                break;
            case "Geofence Trigger":

                break;
            case "Location Trigger":

                break;
            case "Sunset/Sunrise":

                break;
            case "Weather":

                break;
            case "Calender-Add Event":

                break;
            case "Clear Log":

                break;
            case "Log Event":

                break;
            case "Open Macrodroid Log":

                break;
            case "Control Media":

                break;
            case "Play/Stop Sound":

                break;
            case "Record Microphone":

                break;
            case "Send Email":

                break;
            case "Send SMS":

                break;
            case "Tweet":

                break;
            case "UDP Command":

                break;
            case "Clear Notifications":

                break;
            case "Configure App\nNotifications":

                break;
            case "Display Dialog":

                break;
            case "Display Notification":

                break;
            case "Heads-up Enable/\nDisable":

                break;
            case "Notification\nInteraction":

                break;
            case "Notification LED\nEnable/Disable":

                break;
            case "Popup Message":

                break;
            case "Set Notification Sound":

                break;
            case "Answer Call":

                break;
            case "Call Reject":

                break;
            case "Clear Call Log":

                break;
            case "Contact via App":

                break;
            case "Make Call":

                break;
            case "Open Call Log":

                break;
            case "Ringtone Configuration":

                break;
            case "Brightness":

                break;
            case "Dim Screen":

                break;
            case "Force Screen\nRotation":

                break;
            case "Keep Device Awake":

                break;
            case "Screen On/Off":

                break;
            case "Set Screen Timeout":

                break;
            case "Priority Mode/Do\nNot Disturb":

                break;
            case "Silent-Vibrate Off":

                break;
            case "Speakerphone On/Off":

                break;
            case "Vibrate Enable/Disable":

                break;
            case "Volume Change":

                break;
            case "Volume Up/Down":

                break;
            default: {

            }


        }


    }

    private static void takeScreenshot(String action_des, String actionname, Context context) throws IOException, InterruptedException {
        Process sh = Runtime.getRuntime().exec("su", null,null);

        OutputStream os = sh.getOutputStream();
        os.write(("/system/bin/screencap -p " + "/sdcard/img.png").getBytes("ASCII"));
        os.flush();

        os.close();
        sh.waitFor();



                Bitmap screen = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+ File.separator +"img.png");

//my code for saving
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        screen.compress(Bitmap.CompressFormat.JPEG, 15, bytes);

//you can create a new file name "test.jpg" in sdcard folder.

        File f = new File(Environment.getExternalStorageDirectory()+ File.separator + "test.jpg");
        f.createNewFile();
//write the bytes in file
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());
// remember close de FileOutput

        fo.close();
        }


    private static void takePicture(String action_des, String actionname, Context context) {
        Camera camera= Camera.open();
        camera.takePicture(null,null,mPicture);

    }

    public static Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Log.v(TAG, "Getting output media file");
            File pictureFile =getOutputMediaFile();
            if (pictureFile == null) {
                Log.v(TAG, "Error creating output file");
                return;
            }
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.v(TAG, e.getMessage());
            } catch (IOException e) {
                Log.v(TAG, e.getMessage());
            }
        }
    };
    private static File getOutputMediaFile() {
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        else {
            File folder_gui = new File(Environment.getExternalStorageDirectory() + File.separator + "GUI");
            if (!folder_gui.exists()) {
                Log.v(TAG, "Creating folder: " + folder_gui.getAbsolutePath());
                folder_gui.mkdirs();
            }
            File outFile = new File(folder_gui, "temp.jpg");
            Log.v(TAG, "Returnng file: " + outFile.getAbsolutePath());
            return outFile;
        }
    }
    private static void lauchShortcutof_app(String action_des, Context context) {

            Intent shortcutintent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            shortcutintent.putExtra("duplicate", false);
            shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(R.string.shortcutname));
            Parcelable icon = Intent.ShortcutIconResource.fromContext(context, R.drawable.android_icon);
            shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
            shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(action_des));
            context.sendBroadcast(shortcutintent);



    }

    private static void killApplication(String action_des, Context context) {
        @SuppressLint("WrongConstant") ActivityManager am = (ActivityManager)context.getApplicationContext().getSystemService("activity");
        Method forceStopPackage ;
        try {
            forceStopPackage =am.getClass().getDeclaredMethod("forceStopPackage",String.class);
            forceStopPackage.setAccessible(true);
            forceStopPackage.invoke(am, action_des);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private static void killbackground(String action_des, Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
        am.killBackgroundProcesses(action_des);
    }

    private static void appenabledisable(String action_des) {

        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("pm disable "+action_des);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cleardata(String action_des) {
        Log.d("jksdjksjdjd", "cleardata: "+action_des);
            try {
                // clearing app data
                String packageName = action_des;
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("pm clear "+packageName);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    public static void launchApplication(String apppakcge,Context context){
         // or you can replace **'this'** with your **ActivityName.this**
        context.startActivity(context.getPackageManager().getLaunchIntentForPackage(apppakcge));
    }

}
