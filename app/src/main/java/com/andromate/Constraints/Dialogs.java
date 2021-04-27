package com.andromate.Constraints;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andromate.Model.ApplicationsInfo;
import com.andromate.Model.ContactModel;
import com.andromate.Model.Triggerlistmodel;
import com.andromate.R;
import com.andromate.Ui.Activity.AddMacroActivity;
import com.andromate.Ui.Adapters.ApplicationlistAdapters;
import com.andromate.Ui.Adapters.Call_SmsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static android.content.Context.SENSOR_SERVICE;

public class Dialogs {
    public static ProgressDialog progressDialog;
    public static Triggerlistmodel triggerlistmodel1;
    public static ApplicationlistAdapters triggerItemsAdapters;


    //region   Applications
    public static void showDialogtrigger(Context context, Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alerdailog_opttion_app_installed);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_app_ins_type);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);

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
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    dialog.dismiss();
                    dialogAppType(context, triggerlistmodel);
                }
            }
        });

        dialog.show();

        // Apply the newly created layout parameters to
    }

    public static void dialogAppType(Context context, Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_ui_slectapplication);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.grp_apptype);
        TextView tv_cancel = dialog.findViewById(R.id.tv_cancel_apptype);
        TextView tv_ok = dialog.findViewById(R.id.app_type_ok);


        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton rd_btn = dialog.findViewById(selectedId);
                if (rd_btn != null) {
                    if (rd_btn.getText().toString().equals("Select Application(s)")) {
                        progressDialog = new ProgressDialog(context);
                        progressDialog.setCancelable(false);
                        progressDialog.setMessage("Loading Applications");
                        progressDialog.show();
                        dialog.dismiss();

                        showApplistDialog(context, triggerlistmodel);
                    } else {
                        String triggerdesc = rd_btn.getText().toString().trim();
                        triggerlistmodel.setTriggerdescrption(triggerdesc);
                        AddMacroActivity.triggerlist.add(triggerlistmodel);
                        dialog.dismiss();
                    }
                } else {
                    Toast.makeText(context, "Choose valid option", Toast.LENGTH_SHORT).show();
                }

            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public static void showApplistDialog(Context context, Triggerlistmodel triggerlistmodel) {

        Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.alert_app_list_ui);
        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerview_list_of_App);
        EditText et_search = dialog.findViewById(R.id.et_search);
        TextView tv_cancel = dialog.findViewById(R.id.tv_cancel_selapp);
        TextView tv_ok = dialog.findViewById(R.id.tv_ok_selapp);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        CheckBox checkBox = dialog.findViewById(R.id.checkbox_nonlaunchable);

        getInstalledApps(false, recyclerView, context);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getInstalledApps2(false, recyclerView, context);
                } else {
                    getInstalledApps(false, recyclerView, context);
                }
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (triggerItemsAdapters != null) {
                    triggerItemsAdapters.getFilter().filter(s);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();


    }


    public static ArrayList<ApplicationsInfo> getInstalledApps(boolean getSysPackages, RecyclerView recyclerView, Context context) {
        ArrayList<ApplicationsInfo> res = new ArrayList<ApplicationsInfo>();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue;
            }
            ApplicationsInfo newInfo = new ApplicationsInfo();
            newInfo.setAppname(p.applicationInfo.loadLabel(context.getPackageManager()).toString());
            ;
            newInfo.setPname(p.packageName);
            Log.d("jfdkfjdkfjkfjf", "jjjjj" + p.packageName);
            newInfo.setVersionName(p.versionName);
            newInfo.setVersionCode(p.versionCode);
            newInfo.setIcon(p.applicationInfo.loadIcon(context.getPackageManager()));
            res.add(newInfo);
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        triggerItemsAdapters = new ApplicationlistAdapters(context, res);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(triggerItemsAdapters);
        return res;
    }

    public static ArrayList<ApplicationsInfo> getInstalledApps2(boolean getSysPackages, RecyclerView recyclerView, Context context) {
        ArrayList<ApplicationsInfo> res = new ArrayList<ApplicationsInfo>();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue;
            }
            if (context.getPackageManager().getLaunchIntentForPackage(p.packageName) == null) {
                ApplicationsInfo newInfo = new ApplicationsInfo();
                newInfo.setAppname(p.applicationInfo.loadLabel(context.getPackageManager()).toString());
                newInfo.setPname(p.packageName);
                Log.d("jfdkfjdkfjkfjf", "jjjjj" + p.packageName);
                newInfo.setVersionName(p.versionName);
                newInfo.setVersionCode(p.versionCode);
                newInfo.setIcon(p.applicationInfo.loadIcon(context.getPackageManager()));
                res.add(newInfo);
            }
        }
        triggerItemsAdapters = new ApplicationlistAdapters(context, res);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(triggerItemsAdapters);
        return res;
    }


//endregion

    //region app launch close

    public static void showLaunchCloseDialog(Context context, Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_launch_ui);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_launch_type);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        TextView tv_hide = dialog.findViewById(R.id.tv_hide);
        CheckBox check_mechanism = dialog.findViewById(R.id.chbx_mechanism);
        CheckBox check_interferening = dialog.findViewById(R.id.chbx_interfering);


        check_mechanism.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tv_hide.setVisibility(View.VISIBLE);
                    check_interferening.setVisibility(View.VISIBLE);
                } else {
                    tv_hide.setVisibility(View.GONE);
                    check_interferening.setVisibility(View.GONE);
                }
            }
        });

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
                    showLaunchApplication(context, triggerlistmodel);
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    dialog.dismiss();

                }
            }
        });

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();

    }


    public static void showLaunchApplication(Context context, Triggerlistmodel triggerlistmodel) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_launch_option);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_launch_type);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);


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

                if (rd_btn != null && rd_btn.getText().toString().equals("Select Application(s)")) {
                    Log.d("lhfjfhhff", "ddkj");
                    showApplistDialog(context, triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Enter Package Name")) {
                    Log.d("jkslkfjkflkfk", "kkkk");
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    showpackagedialog(context, triggerlistmodel);
                    dialog.dismiss();
                }
            }
        });
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }


    public static void showpackagedialog(Context context, Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.enterpackagename);
        EditText et_package = dialog.findViewById(R.id.et_packagename);
        ImageView image_expand = dialog.findViewById(R.id.selectpackage);

        TextView textVie_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        TextView textVie_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);


        image_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPackagelist(context, triggerlistmodel, et_package);
            }
        });

        textVie_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_package.getText().toString().equals("")) {
                    Toast.makeText(context, "Select or enter package manually", Toast.LENGTH_SHORT).show();
                } else {
                    triggerlistmodel.setTriggerdescrption(et_package.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }
            }
        });
        textVie_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.setCancelable(false);
        dialog.show();
    }


    public static void showPackagelist(Context context, Triggerlistmodel triggerlistmodel, EditText et_package) {
        Toast.makeText(context, "wait while loading apps", Toast.LENGTH_SHORT).show();
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.packagelistui);

        LinearLayout linearLayout = dialog.findViewById(R.id.lly_items_options);
        TextView textVie_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        TextView textVie_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);

        /*RadioGroup radioGroup=dialog.findViewById(R.id.rg_packagelist_option);*/
        boolean getSysPackages = false;
        /* RadioButton rb = new RadioButton(context);*/


        ArrayList<ApplicationsInfo> res = new ArrayList<ApplicationsInfo>();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue;
            }
            ApplicationsInfo newInfo = new ApplicationsInfo();
            newInfo.setAppname(p.applicationInfo.loadLabel(context.getPackageManager()).toString());
            ;
            newInfo.setPname(p.packageName);
            Log.d("jfdkfjdkfjkfjf", "jjjjj" + p.packageName);
            newInfo.setVersionName(p.versionName);
            newInfo.setVersionCode(p.versionCode);

            //rg is private member of class which refers to the radio group which I find
            //by id.

            newInfo.setIcon(p.applicationInfo.loadIcon(context.getPackageManager()));
            res.add(newInfo);
        }
        final RadioButton[] rb = new RadioButton[res.size()];
        RadioGroup rg = new RadioGroup(context); //create the RadioGroup
        rg.setOrientation(RadioGroup.VERTICAL);

        for (int i = 0; i < res.size(); i++) {
            ApplicationsInfo newInfo = res.get(i);

            rb[i] = new RadioButton(context);
            rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(newInfo.getAppname());

        }
        linearLayout.addView(rg);//you add the whole RadioGroup to the layout


        textVie_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = rg.getCheckedRadioButtonId();
                RadioButton rd_btn = dialog.findViewById(selectedId);
                if (rd_btn != null) {
                    et_package.setText(rd_btn.getText());
                    dialog.dismiss();
                }
              /*  Log.d("jflkdfjdklfjdkf","fnjdf"+rd_btn.getText().toString());
                Toast.makeText(context, "ooo"+rd_btn.getText().toString(), Toast.LENGTH_SHORT).show();*/
            }
        });


        textVie_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

        if (progressDialog != null) {
            progressDialog.dismiss();
        }

    }

    //endregion

    //region configuration
    public static void showConfigurations(Context context, Triggerlistmodel triggerlistmodel, String s) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.configurationui);
        dialog.setCancelable(true);

        TextView tv_tittle = dialog.findViewById(R.id.tv_tittle);
        tv_tittle.setText(s);

        dialog.show();

    }
    //endregion


    //region battery


    public static void showsbattery_option(Context context, Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_battery_select_option);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_launch_type);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);

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
                    triggerlistmodel.setTriggername("Battery");
                    dialog.dismiss();
                    showbatterypawoerDialog4(context, triggerlistmodel);
                }
            }
        });


        dialog.show();

        // Apply the newly created layout parameters to
    }

    private static void showbatterypawoerDialog4(Context context, Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_battery_level_trigger);
        TextView tv_percentage = dialog.findViewById(R.id.tv_id_percentage);
        SeekBar seekBar = dialog.findViewById(R.id.seek_change_percentage);
        RadioGroup rg_increase_de_type = dialog.findViewById(R.id.rg_increase_de_type);
        TextView textView_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView textView_ok = dialog.findViewById(R.id.tv_app_launch_ok);

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

                if (rd_btn != null && rd_btn.getText().toString().equals("Decrease to")) {
                    triggerlistmodel.setTriggerdescrption("<=" + tv_percentage.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Increase to")) {
                    triggerlistmodel.setTriggerdescrption(">=" + tv_percentage.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn == null) {
                    Toast.makeText(context, "Select one option", Toast.LENGTH_SHORT).show();
                }

            }
        });

        dialog.show();
    }
    //endregion

    //region batterystate
    public static void show_battery_state(Context context, Triggerlistmodel triggerlistmodel) {
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
                    triggerlistmodel.setTriggername("Battery Saver State");
                    triggerlistmodel.setTriggerdescrption(rd_btn.getText().toString());
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("bs", rd_btn.getText().toString().trim());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
        // Apply the newly created layout parameters to
    }


    public static void showstempreture_option(Context context, Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_battery_select_option);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_launch_type);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);

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
                    triggerlistmodel.setTriggername("Battery Temperature");
                    dialog.dismiss();
                    showbatteryTempDialog4(context, triggerlistmodel);
                }
            }
        });


        dialog.show();

        // Apply the newly created layout parameters to
    }


    private static void showbatteryTempDialog4(Context context, Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_battery_level_trigger);
        TextView tv_percentage = dialog.findViewById(R.id.tv_id_percentage);
        SeekBar seekBar = dialog.findViewById(R.id.seek_change_percentage);
        RadioGroup rg_increase_de_type = dialog.findViewById(R.id.rg_increase_de_type);
        TextView textView_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView textView_ok = dialog.findViewById(R.id.tv_app_launch_ok);

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

                if (rd_btn != null && rd_btn.getText().toString().equals("Decrease to")) {
                    triggerlistmodel.setTriggerdescrption("<=" + tv_percentage.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Increase to")) {
                    triggerlistmodel.setTriggerdescrption(">=" + tv_percentage.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn == null) {
                    Toast.makeText(context, "Select one option", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }


    public static void showsPower_option(Context context, Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_dialog_power);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_app_powerConnected_disconnected);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);

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
                Log.d("jkfjfkjfkfkf", "dd" + rd_btn.getText().toString());
                if (rd_btn != null && rd_btn.getText().toString().equals("Power Connected")) {
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    showpowertypeoption(context, triggerlistmodel);
                    dialog.dismiss();
                    ;
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Power Disconnected")) {
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn == null) {
                    Toast.makeText(context, "Select one of the option", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dialog.show();

        // Apply the newly created layout parameters to
    }

    private static void showpowertypeoption(Context context, Triggerlistmodel triggerlistmodel) {
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
                    triggerlistmodel.setTriggerdescrption("Any");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (wired.isChecked() && wireless.isChecked() && !wiredslow.isChecked()) {
                    triggerlistmodel.setTriggerdescrption(wired.getText().toString() + "+" + wireless.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (!wired.isChecked() && wireless.isChecked() && wiredslow.isChecked()) {
                    triggerlistmodel.setTriggerdescrption(wireless.getText().toString() + "+" + wiredslow.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (wired.isChecked() && !wireless.isChecked() && wiredslow.isChecked()) {
                    triggerlistmodel.setTriggerdescrption(wired.getText().toString() + "+" + wiredslow.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (wired.isChecked() && !wireless.isChecked() && !wiredslow.isChecked()) {
                    triggerlistmodel.setTriggerdescrption(wired.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (!wired.isChecked() && wireless.isChecked() && !wiredslow.isChecked()) {
                    triggerlistmodel.setTriggerdescrption(wireless.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (!wired.isChecked() && !wireless.isChecked() && wiredslow.isChecked()) {
                    triggerlistmodel.setTriggerdescrption(wiredslow.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Choose one", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dialog.show();


    }


    //endregion


    //region call_sms


    public static void showContacts(Context context, Triggerlistmodel triggerlistmodel, List<ContactModel> contactModelList, String heading) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_contactlistui);
        dialog.setCancelable(false);
        /*RadioGroup radioGroup = dialog.findViewById(R.id.rg_app_powerConnected_disconnected);*/
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        TextView tv_head = dialog.findViewById(R.id.tvhead);
        LinearLayout layout = dialog.findViewById(R.id.lly_contactlist);


        tv_head.setText(heading);

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < contactModelList.size(); i++) {
            ContactModel contactModel = contactModelList.get(i);

            CheckBox cb = new CheckBox(context);
            cb.setText(contactModel.getName());
            layout.addView(cb);
           SharedPreferences sharedpreferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);

            int finalI = i;
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        str.append(cb.getText() + ",");
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("number", contactModelList.get(finalI).getNumber());
                        /* Log.d("hfjdfjffff","jlhfjkdhfjkdfhkjf"+applicationsInfo.getPname());*/
                        editor.commit();
                    } else {

                    }
                }
            });
        }

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str != null) {
                    Log.d("Dailog", "jjfjf" + triggerlistmodel);
                    triggerlistmodel.setTriggerdescrption(String.valueOf(str));
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }
                /*int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton rd_btn = dialog.findViewById(selectedId);
                Log.d("jkfjfkjfkfkf","dd"+rd_btn.getText().toString());
                if (rd_btn != null && rd_btn.getText().toString().equals("Power Connected")) {
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    showpowertypeoption(context,triggerlistmodel);
                    dialog.dismiss();
                    ;
                }else if (rd_btn != null && rd_btn.getText().toString().equals("Power Disconnected")){
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }else if (rd_btn == null){
                    Toast.makeText(context, "Select one of the option", Toast.LENGTH_SHORT).show();
                }*/
            }
        });


        dialog.show();

        // Apply the newly created layout parameters to
    }


    public static void show_call_ended(Context context, Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_dialog_call_ended);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_app_contctstype);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);

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
                Log.d("jkfjfkjfkfkf", "dd" + rd_btn.getText().toString());
                if (rd_btn != null && rd_btn.getText().toString().equals("Select Contacts(s)")) {
                    Call_SmsAdapter call_smsAdapter = new Call_SmsAdapter(context);
                    call_smsAdapter.getContactlist(context, triggerlistmodel, rd_btn.getText().toString());
                    ;
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Select Number")) {
                    selectnumber(context, triggerlistmodel);
                    dialog.dismiss();
                    /* Toast.makeText(context, "one"+rd_btn.getText().toString(), Toast.LENGTH_SHORT).show();*/
                  /*  triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();*/
                } else if (rd_btn == null) {
                    Toast.makeText(context, "Select one of the option", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
        // Apply the newly created layout parameters to
    }


    public static void selectnumber(Context context, Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.customnumber_layout);
        dialog.setCancelable(false);
        EditText edit_mobilenumber = dialog.findViewById(R.id.et_mobilenumber);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                show_call_ended(context, triggerlistmodel);
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_mobilenumber.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter Mobile number", Toast.LENGTH_SHORT).show();

                } else {
                    triggerlistmodel.setTriggerdescrption(edit_mobilenumber.getText().toString().trim());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }
            }
        });

        dialog.show();

        // Apply the newly created layout parameters to
    }


    public static void Dialnumber(Context context, Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_dial_number);
        dialog.setCancelable(false);
        EditText edit_mobilenumber = dialog.findViewById(R.id.et_mobilenumber);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_dailnumber);

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

                if (edit_mobilenumber.getText().toString().isEmpty() && rd_btn != null) {
                    Toast.makeText(context, "Enter the number", Toast.LENGTH_SHORT).show();
                } else if (!edit_mobilenumber.getText().toString().isEmpty() && rd_btn == null) {
                    Toast.makeText(context, "Select any option", Toast.LENGTH_SHORT).show();
               /* if (edit_mobilenumber.getText().toString().isEmpty()){
                    Toast.makeText(context, "Enter Mobile number", Toast.LENGTH_SHORT).show();*/
                } else if (edit_mobilenumber.getText().toString().isEmpty() && rd_btn == null) {
                    Toast.makeText(context, "Enter number & Select option", Toast.LENGTH_SHORT).show();
                } else {
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption(edit_mobilenumber.getText().toString().trim());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
        // Apply the newly created layout parameters to
    }


    //end region


    //region connectivity

    public static void showbeacons_event(Context context, Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_dialog_beacon_event);
        dialog.setCancelable(false);

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
                dialog.dismiss();
            }
        });
        dialog.show();
        // Apply the newly created layout parameters to
    }

    public static void showbluetooth_event(Context context, Triggerlistmodel triggerlistmodel) {
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
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("bt", rd_btn.getText().toString());
                    editor.apply();
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Bluetooth Disabled")) {
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("bt", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
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

    private static void listofbluetooth(Context context, Triggerlistmodel triggerlistmodel) {
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

    private static void shwoDialodforblutoothlist(Context context, Triggerlistmodel triggerlistmodel, ArrayList list) {

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


    public static void showDataconnectivitychange(Context context, Triggerlistmodel triggerlistmodel) {

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
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("data_on", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("No Data Connection")) {
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("data_on", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }


    public static void showHeadphoneschange(Context context, Triggerlistmodel triggerlistmodel) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_headphones_event_change);
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
                if (rd_btn != null && rd_btn.getText().toString().equals("Headphones Inserted")) {
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    showHeadphonestype(context, triggerlistmodel);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("head", rd_btn.getText().toString());
                    editor.apply();
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Headphones Removed")) {
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("head", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();


    }


    public static void showHeadphonestype(Context context, Triggerlistmodel triggerlistmodel) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_headphonestype);
        dialog.setCancelable(false);

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
                    triggerlistmodel.setTriggerdescrption(rd_btn.getText().toString());

                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }


    public static void showhospotoption(Context context, Triggerlistmodel triggerlistmodel) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_hotspot_enabled_disabled);
        dialog.setCancelable(false);

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
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    public static void showMobileServiceStatus(Context context, Triggerlistmodel triggerlistmodel) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_mobile_service);
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
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("mob_ser", rd_btn.getText().toString());
                    editor.apply();

                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }


    public static void showRoaming(Context context, Triggerlistmodel triggerlistmodel) {
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
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("roaming", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }


    public static void showUSBDevice_con(Context context, Triggerlistmodel triggerlistmodel) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_usb_con_disc);
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
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("usb", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    public static void showVpn_state(Context context, Triggerlistmodel triggerlistmodel) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_vpn_state);
        dialog.setCancelable(false);

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
                    triggerlistmodel.setTriggerdescrption(rd_btn.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    public static void showwif_state(Context context, Triggerlistmodel triggerlistmodel) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_wifistate_change);
        dialog.setCancelable(false);

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
                if (rd_btn != null && rd_btn.getText().toString().equals("Wifi Enabled")) {
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Wifi Disabled")) {
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Connected to network")) {
                    getWifilist(context, triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Disconnected from network")) {
                    triggerlistmodel.setTriggerdescrption(rd_btn.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    public static void getWifilist(Context context, Triggerlistmodel triggerlistmodel) {
        WifiManager mainWifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        triggerlistmodel1 = triggerlistmodel;
        if (mainWifi.isWifiEnabled() == false) {
            // If wifi disabled then enable it
            Toast.makeText(context.getApplicationContext(), "wifi is disabled..making it enabled",
                    Toast.LENGTH_LONG).show();

            mainWifi.setWifiEnabled(true);
        }
        WifiReceiver receiverWifi = new WifiReceiver();
        context.registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        mainWifi.startScan();
        Toast.makeText(context, "Scanning", Toast.LENGTH_SHORT).show();
    }

    public static class WifiReceiver extends BroadcastReceiver {

        // This method call when number of wifi connections changed
        public void onReceive(Context c, Intent intent) {
            Log.d("fdff", "jdjd" + intent);
            WifiManager mainWifi = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
            List<ScanResult> wifiList;
            ArrayList<String> wifilists = new ArrayList<>();
            wifiList = mainWifi.getScanResults();
            /*   sb.append("\n        Number Of Wifi connections :"+wifiList.size()+"\n\n");*/

            for (int i = 0; i < wifiList.size(); i++) {
                wifilists.add(wifiList.get(i).toString());
               /* sb.append(new Integer(i+1).toString() + ". ");
                sb.append((wifiList.get(i)).toString());
                sb.append("\n\n");*/
            }
            ShowWifiAlert(c, wifilists, triggerlistmodel1);
        }

        private void ShowWifiAlert(Context c, ArrayList<String> wifiList, Triggerlistmodel triggerlistmodel1) {
            Dialog dialog = new Dialog(c);
            dialog.setContentView(R.layout.alert_wifilist);
            dialog.setCancelable(false);
            /*RadioGroup radioGroup = dialog.findViewById(R.id.rg_app_powerConnected_disconnected);*/
            TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
            TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
            TextView tv_head = dialog.findViewById(R.id.tvhead);
            LinearLayout layout = dialog.findViewById(R.id.lly_contactlist);


            /* tv_head.setText(heading);*/

            StringBuilder str = new StringBuilder();
            for (int i = 0; i < wifiList.size(); i++) {


                CheckBox cb = new CheckBox(c);
                cb.setText(wifiList.get(i));
                layout.addView(cb);


                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {

                            str.append(cb.getText() + ",");

                        } else {

                        }
                    }
                });

            }
            tv_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (str != null) {
                        Log.d("Dailog", "jjfjf" + triggerlistmodel1);
                        triggerlistmodel1.setTriggerdescrption(String.valueOf(str));
                        AddMacroActivity.triggerlist.add(triggerlistmodel1);
                        dialog.dismiss();
                    }
                /*int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton rd_btn = dialog.findViewById(selectedId);
                Log.d("jkfjfkjfkfkf","dd"+rd_btn.getText().toString());
                if (rd_btn != null && rd_btn.getText().toString().equals("Power Connected")) {
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    showpowertypeoption(context,triggerlistmodel);
                    dialog.dismiss();
                    ;
                }else if (rd_btn != null && rd_btn.getText().toString().equals("Power Disconnected")){
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }else if (rd_btn == null){
                    Toast.makeText(context, "Select one of the option", Toast.LENGTH_SHORT).show();
                }*/
                }
            });
            dialog.show();
        }
    }
    //end region
//region timedate

    public static void Showcal_event(Context c, Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.alert_dialog_calenderevent);
        dialog.setCancelable(false);
        /*RadioGroup radioGroup = dialog.findViewById(R.id.rg_app_powerConnected_disconnected);*/



        /* tv_head.setText(heading);*/


     /*   tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (str!=null){
                    Log.d("Dailog","jjfjf"+triggerlistmodel1);
                    triggerlistmodel1.setTriggerdescrption(String.valueOf(str));
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                }

            }
        });*/

        dialog.show();


    }


    //region device_event

    public static void Show_aitplane_opton(Context c, Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.alert_airplane_mode_change);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_airplane);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);


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
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    public static void Show_rotatechange(Context c, Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.alert_autorotate);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_airplane);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);


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
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dialog.show();


    }


    public static void Show_daydream(Context c, Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.alert_day_dream);
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
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("daydream", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dialog.show();
    }
    public static void Show_devicedock(Context c, Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.alert_device_docked);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_airplane);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);


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
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dialog.show();


    }


    public static void Show_gps_enable(Context c, Triggerlistmodel triggerlistmodel1) {
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
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("gps", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dialog.show();


    }

    public static void Show_music(Context c, Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.alert_music_sound);
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
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("music", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dialog.show();


    }

    public static void Show_prioritymode_don_disturb(Context c, Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.prioritymode);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_airplane);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);


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
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.show();
    }


    public static void Show_seimcard_change(Context c, Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.sim_card_cahnge);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_airplane);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);


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
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dialog.show();


    }
    public static void Show_screenOn_Off(Context c, Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.screenon_off);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_airplane);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);


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
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
    public static void Show_silentmode(Context c, Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.silent_mode_enable_disable);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_airplane);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
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
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.show();
    }
    //end region

    //region sensor
    public static void Show_sensor(Context c, Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.flip_devices);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_sen_flip_device);
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
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("flip", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dialog.show();


    }

    public static void Show_lightsensor(Context c, Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.light_sensors);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_sen_flip_device);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        TextView tv_sensor = dialog.findViewById(R.id.tv_sensors_value);
        EditText et_value = dialog.findViewById(R.id.et_input_value);


        loadseodrValue(c,tv_sensor);
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
                String point=et_value.getText().toString();
                if (point.isEmpty()){
                    Toast.makeText(c, "Enter value", Toast.LENGTH_SHORT).show();
                }else if (rd_btn != null && !rd_btn.getText().toString().equals("")) {
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                   SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("light", rd_btn.getText().toString());
                    editor.putString("point", et_value.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dialog.show();


    }

    private static void loadseodrValue(Context c, TextView tv_sensor) {
        SensorManager sensorManager = null;
        Sensor light = null;
        final float[] sensorValue = {-1};

        sensorManager = (SensorManager) c.getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                    if (event.values[0] < 3 || event.values[0] > 15) {
                        sensorValue[0] = event.values[0];
                    }
                    if (sensorValue[0] < 2) {
                       tv_sensor.setText("0");
                    } else {
                     tv_sensor.setText(String.valueOf(sensorValue[0])+"lx");
                    }
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        }, light, SensorManager.SENSOR_DELAY_NORMAL);
    }



    public static void show_proximity_sensor(Context c,Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.alert_proximity_sensor);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_sen_proxi_device);
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
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("porximity", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dialog.show();

    }




    public static void show_portrait(Context c,Triggerlistmodel triggerlistmodel1) {
        Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.alert_screen_orientation);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_sen_oo_device);
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
                    triggerlistmodel1.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel1.setTriggerdescrption("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("orientation", rd_btn.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(c, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dialog.show();

    }

    //end regiom
}


//end region



