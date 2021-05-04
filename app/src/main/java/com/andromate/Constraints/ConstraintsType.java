package com.andromate.Constraints;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.andromate.Model.ActionModelList;
import com.andromate.Model.ConstraintsListModelList;
import com.andromate.Model.Triggerlistmodel;
import com.andromate.R;
import com.andromate.Ui.Activity.AddMacroActivity;
import com.andromate.Ui.Adapters.ApplicationlistAdapters_Action;

import java.util.ArrayList;
import java.util.Set;

public class ConstraintsType {


    public static ProgressDialog progressDialog;
    public static ConstraintsListModelList triggerlistmodel1;
    public static ApplicationlistAdapters_Action triggerItemsAdapters;


    public static void showConfigurations(Context context, ConstraintsListModelList triggerlistmodel, String s) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.configurationui_action);
        dialog.setCancelable(true);

        TextView tv_tittle = dialog.findViewById(R.id.tv_tittle);
        tv_tittle.setText(s);

        dialog.show();

    }





    public static void showbatterydailog(Context context, ConstraintsListModelList triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.constraints_battery_level_alert);
        TextView tv_percentage = dialog.findViewById(R.id.tv_id_percentage);
        SeekBar seekBar = dialog.findViewById(R.id.seek_change_percentage);
        RadioGroup rg_increase_de_type = dialog.findViewById(R.id.rg_increase_de_type);
        TextView textView_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView textView_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        tv_percentage.setText(seekBar.getProgress() + "%");
        dialog.setCancelable(false);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_percentage.setText(progress + "%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });


        textView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        textView_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rg_increase_de_type.getCheckedRadioButtonId();
                RadioButton rd_btn = dialog.findViewById(selectedId);

                if (rd_btn != null && rd_btn.getText().toString().equals("Less than")) {
                    triggerlistmodel.setConstraintsDescription("Battery <" + tv_percentage.getText().toString());
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Greater than")) {
                    triggerlistmodel.setConstraintsDescription("Battery >" + tv_percentage.getText().toString());
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("=")){
                    triggerlistmodel.setConstraintsDescription("Battery =" + tv_percentage.getText().toString());
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn == null) {
                    Toast.makeText(context, "Select one option", Toast.LENGTH_SHORT).show();
                }

            }
        });

        dialog.show();
    }



    public static void show_battery_state(Context context, ConstraintsListModelList triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_batterysaver_state);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_app_batterystate_type);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        SharedPreferences sharedpreferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton rd_btn = dialog.findViewById(selectedId);
                if (rd_btn != null && !rd_btn.getText().toString().equals("")) {
                    triggerlistmodel.setConstraintsname("Battery Saver State");
                    triggerlistmodel.setConstraintsDescription(rd_btn.getText().toString());
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("bs", rd_btn.getText().toString().trim());
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
        // Apply the newly created layout parameters to
    }


    public static void show_powerConnected(Context context, ConstraintsListModelList triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_powerconnected_discc);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_increase_de_type);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        SharedPreferences sharedpreferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton rd_btn = dialog.findViewById(selectedId);
                if (rd_btn != null && rd_btn.getText().toString().equals("Power Connected")) {
                    triggerlistmodel.setConstraintsname(rd_btn.getText().toString());
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("pc", rd_btn.getText().toString().trim());
                    dialog.dismiss();
                    showpowertypeoption(context,triggerlistmodel);
                }else if (rd_btn != null && rd_btn.getText().toString().equals("Power Disconnected")) {
                    triggerlistmodel.setConstraintsname(rd_btn.getText().toString());
                    triggerlistmodel.setConstraintsDescription(rd_btn.getText().toString());
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("pc", rd_btn.getText().toString().trim());
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                    ((Activity)context).finish();
                }
            }
        });
        dialog.show();
        // Apply the newly created layout parameters to
    }

    private static void showpowertypeoption(Context context, ConstraintsListModelList triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_dialog_power_type);
        dialog.setCancelable(false);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        CheckBox wired = dialog.findViewById(R.id.rb_app_power_wired);
        CheckBox wireless = dialog.findViewById(R.id.rb_app_wireless);
        CheckBox wiredslow = dialog.findViewById(R.id.rb_app_power_wiredslow);


        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wired.isChecked() && wireless.isChecked() && wiredslow.isChecked()) {
                    triggerlistmodel.setConstraintsDescription("Any");
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (wired.isChecked() && wireless.isChecked() && !wiredslow.isChecked()) {
                    triggerlistmodel.setConstraintsDescription(wired.getText().toString() + "+" + wireless.getText().toString());
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (!wired.isChecked() && wireless.isChecked() && wiredslow.isChecked()) {
                    triggerlistmodel.setConstraintsDescription(wireless.getText().toString() + "+" + wiredslow.getText().toString());
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (wired.isChecked() && !wireless.isChecked() && wiredslow.isChecked()) {
                    triggerlistmodel.setConstraintsDescription(wired.getText().toString() + "+" + wiredslow.getText().toString());
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (wired.isChecked() && !wireless.isChecked() && !wiredslow.isChecked()) {
                    triggerlistmodel.setConstraintsDescription(wired.getText().toString());
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (!wired.isChecked() && wireless.isChecked() && !wiredslow.isChecked()) {
                    triggerlistmodel.setConstraintsDescription(wireless.getText().toString());
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (!wired.isChecked() && !wireless.isChecked() && wiredslow.isChecked()) {
                    triggerlistmodel.setConstraintsDescription(wiredslow.getText().toString());
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Choose one", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dialog.show();


    }

    public static void showbluetooth_event(Context context, ConstraintsListModelList triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_dialog_bluetooth_event);
        dialog.setCancelable(false);
        SharedPreferences sharedpreferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_bluetooth_event);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton rd_btn = dialog.findViewById(selectedId);
                if (rd_btn != null && rd_btn.getText().toString().equals("Bluetooth Enabled")) {
                    triggerlistmodel.setConstraintsname(rd_btn.getText().toString());
                    triggerlistmodel.setConstraintsDescription("");
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("btc", rd_btn.getText().toString());
                    editor.apply();
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Bluetooth Disabled")) {
                    triggerlistmodel.setConstraintsname(rd_btn.getText().toString());
                    triggerlistmodel.setConstraintsDescription("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("btc", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Device Connected")) {
                    listofbluetooth(context, triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Device Disconnected")) {
                    listofbluetooth(context, triggerlistmodel);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Select valid option", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.show();
        // Apply the newly created layout parameters to
    }
    private static void listofbluetooth(Context context, ConstraintsListModelList triggerlistmodel) {
        BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
        ArrayList<String> arrayList = new ArrayList<>();

        if (bAdapter == null) {
              /*  Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                context.startActivityForResult(enableIntent, REQUEST_ENABLE_CODE);
           */
            Toast.makeText(context, "Bluetooth Not Supported", Toast.LENGTH_SHORT).show();
        } else {
            Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
            ArrayList list = new ArrayList();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    String devicename = device.getName();
                    String macAddress = device.getAddress();
                    Log.d("hhdsjkdhnsajlkdhskl", "ddd" + devicename);
                    list.add("Name: " + devicename + "MAC Address: " + macAddress);
                }
                shwoDialodforblutoothlist(context, triggerlistmodel, list);
               /* lstvw = (ListView) findViewById(R.id.deviceList);
                aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                lstvw.setAdapter(aAdapter);*/
            }
        }
    }
    private static void shwoDialodforblutoothlist(Context context, ConstraintsListModelList triggerlistmodel, ArrayList list) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_bluetooth_list);
        dialog.setCancelable(false);

        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_blutooth_list);
        for (int i = 0; i < list.size(); i++) {
            RadioButton rbn = new RadioButton(context);
            rbn.setId(View.generateViewId());
            rbn.setText((CharSequence) list.get(i));
            radioGroup.addView(rbn);
        }


       /* tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton rd_btn = dialog.findViewById(selectedId);
                dialog.dismiss();
            }
        });*/
        dialog.show();


    }


    public static void Show_gps_enable(Context c, ConstraintsListModelList triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.gps_enable_dis);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_airplane);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        SharedPreferences sharedpreferences = c.getSharedPreferences("myapp", Context.MODE_PRIVATE);

        /* tv_head.setText(heading);*/


        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton rd_btn = dialog.findViewById(selectedId);
                if (rd_btn != null && !rd_btn.getText().toString().equals("")) {
                    triggerlistmodel1.setConstraintsname(rd_btn.getText().toString());
                    triggerlistmodel1.setConstraintsDescription("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("gps", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.constraintslist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dialog.show();


    }

    public static void showRoaming(Context context, ConstraintsListModelList triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_roaming_enb_dis);
        dialog.setCancelable(false);
        SharedPreferences sharedpreferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_bluetooth_event);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton rd_btn = dialog.findViewById(selectedId);
                if (rd_btn != null && !rd_btn.getText().toString().equals("")) {
                    triggerlistmodel.setConstraintsname(rd_btn.getText().toString());
                    triggerlistmodel.setConstraintsDescription("");

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("roaming", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }



    public static void lactionmode(Context context, ConstraintsListModelList triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.locationmode);
        dialog.setCancelable(false);
        SharedPreferences sharedpreferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        CheckBox cb_l0c_ser_off=dialog.findViewById(R.id.cb_location_off);
        CheckBox cb_l0c_device=dialog.findViewById(R.id.cb_device);
        CheckBox cb_l0c_batterysaving=dialog.findViewById(R.id.cb_battery_saving);
        CheckBox cb_l0c_highaccuracy=dialog.findViewById(R.id.cb_highAccuracy);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cb_l0c_ser_off.isChecked()) {

                    triggerlistmodel.setConstraintsDescription(cb_l0c_highaccuracy.getText().toString());
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("locatonmode", cb_l0c_ser_off.getText().toString());
                    editor.apply();
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                }else if (cb_l0c_ser_off.isChecked() && cb_l0c_device.isChecked()) {
                    triggerlistmodel.setConstraintsDescription(cb_l0c_highaccuracy.getText().toString()+", "+cb_l0c_device.isChecked());
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("locatonmode", cb_l0c_ser_off.getText().toString());
                    editor.apply();
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Select Any", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }


    public static void showDataconnectivitychange(Context context, ConstraintsListModelList triggerlistmodel) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_dataconnectivity_change);
        dialog.setCancelable(false);
        SharedPreferences sharedpreferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_bluetooth_event);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton rd_btn = dialog.findViewById(selectedId);
                if (rd_btn != null && rd_btn.getText().toString().equals("Data Available")) {
                    triggerlistmodel.setConstraintsname(rd_btn.getText().toString());
                    triggerlistmodel.setConstraintsDescription("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("data_on", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("No Data Connection")) {
                    triggerlistmodel.setConstraintsname(rd_btn.getText().toString());
                    triggerlistmodel.setConstraintsDescription("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("data_on", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.constraintslist.add(triggerlistmodel);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

}
