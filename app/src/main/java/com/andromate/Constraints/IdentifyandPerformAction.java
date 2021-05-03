package com.andromate.Constraints;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class IdentifyandPerformAction {

    public static void performAction(String actionname, String action_des, Context context) {
        Log.d("fjdfhdjfhhf","jdjdjdjdj"+action_des);
        switch (actionname) {
            case "App Enable/Disable":
                Toast.makeText(context, ""+actionname, Toast.LENGTH_SHORT).show();
                break;
            case "Clear App Data":
                cleardata(action_des);
                break;
            case "Kill Application":

                break;
            case "Kill Background\nProcess":

                break;
            case "Launch Application":

                break;
            case "Launch Shortcut":

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

                break;
            case "Take Screenshot":

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

    private static void cleardata(String action_des) {
        Log.d("jksdjksjdjd", "cleardata: "+action_des);
            try {
                // clearing app data
                String packageName = action_des;
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("adb shell pm clear "+packageName);

            } catch (Exception e) {
                e.printStackTrace();
            }

    }


}
