package com.andromate.Constraints;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
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

public  class Dialogs {
  public static ProgressDialog progressDialog;

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
                    dialogAppType(context,triggerlistmodel);
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

                        showApplistDialog(context,triggerlistmodel);
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

        getInstalledApps(false, recyclerView,context);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getInstalledApps2(false, recyclerView,context);
                } else {
                    getInstalledApps(false, recyclerView,context);
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


    public static ArrayList<ApplicationsInfo> getInstalledApps(boolean getSysPackages, RecyclerView recyclerView,Context context) {
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

    public static ArrayList<ApplicationsInfo> getInstalledApps2(boolean getSysPackages, RecyclerView recyclerView,Context context) {
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
                    showLaunchApplication(context,triggerlistmodel);
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



    public static void showLaunchApplication(Context context,Triggerlistmodel triggerlistmodel) {

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
                    showApplistDialog(context,triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Enter Package Name")) {
                    Log.d("jkslkfjkflkfk", "kkkk");
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    showpackagedialog(context,triggerlistmodel);
                    dialog.dismiss();
                }
            }
        });
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }


    public static void showpackagedialog(Context context,Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.enterpackagename);
        EditText et_package = dialog.findViewById(R.id.et_packagename);
        ImageView image_expand = dialog.findViewById(R.id.selectpackage);

        TextView textVie_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        TextView textVie_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);


        image_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPackagelist(context,triggerlistmodel,et_package);
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


    public static void showPackagelist(Context context,Triggerlistmodel triggerlistmodel,EditText et_package) {
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

        TextView tv_tittle=dialog.findViewById(R.id.tv_tittle);
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
                    showbatterypawoerDialog4(context,triggerlistmodel);
                }
            }
        });


        dialog.show();

        // Apply the newly created layout parameters to
    }

    private static void showbatterypawoerDialog4(Context context,Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_battery_level_trigger);
        TextView tv_percentage=dialog.findViewById(R.id.tv_id_percentage);
        SeekBar seekBar=dialog.findViewById(R.id.seek_change_percentage);
        RadioGroup rg_increase_de_type=dialog.findViewById(R.id.rg_increase_de_type);
        TextView textView_cancel=dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView textView_ok=dialog.findViewById(R.id.tv_app_launch_ok);

        dialog.setCancelable(false);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_percentage.setText(progress+"%");

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

                if (rd_btn!=null && rd_btn.getText().toString().equals("Decrease to")){
                    triggerlistmodel.setTriggerdescrption("<="+tv_percentage.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }else if (rd_btn!=null && rd_btn.getText().toString().equals("Increase to")){
                    triggerlistmodel.setTriggerdescrption(">="+tv_percentage.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }else if (rd_btn==null){
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
                    showbatteryTempDialog4(context,triggerlistmodel);
                }
            }
        });


        dialog.show();

        // Apply the newly created layout parameters to
    }



    private static void showbatteryTempDialog4(Context context,Triggerlistmodel triggerlistmodel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_battery_level_trigger);
        TextView tv_percentage=dialog.findViewById(R.id.tv_id_percentage);
        SeekBar seekBar=dialog.findViewById(R.id.seek_change_percentage);
        RadioGroup rg_increase_de_type=dialog.findViewById(R.id.rg_increase_de_type);
        TextView textView_cancel=dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView textView_ok=dialog.findViewById(R.id.tv_app_launch_ok);

        dialog.setCancelable(false);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_percentage.setText(progress+"%");

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

                if (rd_btn!=null && rd_btn.getText().toString().equals("Decrease to")){
                    triggerlistmodel.setTriggerdescrption("<="+tv_percentage.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }else if (rd_btn!=null && rd_btn.getText().toString().equals("Increase to")){
                    triggerlistmodel.setTriggerdescrption(">="+tv_percentage.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }else if (rd_btn==null){
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
        CheckBox wired=dialog.findViewById(R.id.rb_app_power_wired);
        CheckBox wireless=dialog.findViewById(R.id.rb_app_wireless);
        CheckBox wiredslow=dialog.findViewById(R.id.rb_app_power_wiredslow);


        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (wired.isChecked() && wireless.isChecked() && wiredslow.isChecked()){
                   triggerlistmodel.setTriggerdescrption("Any");
                   AddMacroActivity.triggerlist.add(triggerlistmodel);
                   dialog.dismiss();
               }else if (wired.isChecked()&& wireless.isChecked() && !wiredslow.isChecked()){
                   triggerlistmodel.setTriggerdescrption(wired.getText().toString()+"+"+wireless.getText().toString());
                   AddMacroActivity.triggerlist.add(triggerlistmodel);
                   dialog.dismiss();
               }else if (!wired.isChecked()&& wireless.isChecked() && wiredslow.isChecked()){
                   triggerlistmodel.setTriggerdescrption(wireless.getText().toString()+"+"+wiredslow.getText().toString());
                   AddMacroActivity.triggerlist.add(triggerlistmodel);
                   dialog.dismiss();
               }else if (wired.isChecked()&& !wireless.isChecked() && wiredslow.isChecked()){
                   triggerlistmodel.setTriggerdescrption(wired.getText().toString()+"+"+wiredslow.getText().toString());
                   AddMacroActivity.triggerlist.add(triggerlistmodel);
                   dialog.dismiss();
               }else if (wired.isChecked()&& !wireless.isChecked() && !wiredslow.isChecked()){
                   triggerlistmodel.setTriggerdescrption(wired.getText().toString());
                   AddMacroActivity.triggerlist.add(triggerlistmodel);
                   dialog.dismiss();
               }else if (!wired.isChecked()&& wireless.isChecked() && !wiredslow.isChecked()){
                   triggerlistmodel.setTriggerdescrption(wireless.getText().toString());
                   AddMacroActivity.triggerlist.add(triggerlistmodel);
                   dialog.dismiss();
               }else if (!wired.isChecked()&& !wireless.isChecked() && wiredslow.isChecked()){
                   triggerlistmodel.setTriggerdescrption(wiredslow.getText().toString());
                   AddMacroActivity.triggerlist.add(triggerlistmodel);
                   dialog.dismiss();
               }else {
                   Toast.makeText(context, "Choose one", Toast.LENGTH_SHORT).show();
               }
            }
        });


        dialog.show();


    }


    //endregion



    //region call_sms


    public static void showContacts(Context context, Triggerlistmodel triggerlistmodel, List<ContactModel> contactModelList,String heading) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_contactlistui);
        dialog.setCancelable(false);
        /*RadioGroup radioGroup = dialog.findViewById(R.id.rg_app_powerConnected_disconnected);*/
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        TextView tv_head=dialog.findViewById(R.id.tvhead);
        LinearLayout layout=dialog.findViewById(R.id.lly_contactlist);


        tv_head.setText(heading);

        StringBuilder str = new StringBuilder();
        for (int i=0;i<contactModelList.size();i++){
            ContactModel contactModel=contactModelList.get(i);

            CheckBox cb = new CheckBox(context);
            cb.setText(contactModel.getName());
            layout.addView(cb);


            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){

                        str.append(cb.getText()+",");

                    }else {

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

                if (str!=null){
                    Log.d("Dailog","jjfjf"+triggerlistmodel);
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
                Log.d("jkfjfkjfkfkf","dd"+rd_btn.getText().toString());
                if (rd_btn != null && rd_btn.getText().toString().equals("Select Contacts(s)")) {
                    Call_SmsAdapter call_smsAdapter=new Call_SmsAdapter(context);
                    call_smsAdapter.getContactlist(context,triggerlistmodel,rd_btn.getText().toString());
                    ;dialog.dismiss();
                }else if (rd_btn != null && rd_btn.getText().toString().equals("Select Number")){
                    selectnumber(context,triggerlistmodel);
                    dialog.dismiss();
                   /* Toast.makeText(context, "one"+rd_btn.getText().toString(), Toast.LENGTH_SHORT).show();*/
                  /*  triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();*/
                }else if (rd_btn == null){
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
        EditText edit_mobilenumber=dialog.findViewById(R.id.et_mobilenumber);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                show_call_ended(context,triggerlistmodel);
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_mobilenumber.getText().toString().isEmpty()){
                    Toast.makeText(context, "Enter Mobile number", Toast.LENGTH_SHORT).show();

                }else {
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
        EditText edit_mobilenumber=dialog.findViewById(R.id.et_mobilenumber);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        RadioGroup radioGroup=dialog.findViewById(R.id.rg_dailnumber);

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

                if (edit_mobilenumber.getText().toString().isEmpty() && rd_btn !=null){
                    Toast.makeText(context, "Enter the number", Toast.LENGTH_SHORT).show();
                }else if (!edit_mobilenumber.getText().toString().isEmpty() && rd_btn ==null){
                    Toast.makeText(context, "Select any option", Toast.LENGTH_SHORT).show();
               /* if (edit_mobilenumber.getText().toString().isEmpty()){
                    Toast.makeText(context, "Enter Mobile number", Toast.LENGTH_SHORT).show();*/
                }else if (edit_mobilenumber.getText().toString().isEmpty() && rd_btn ==null){
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
        RadioGroup radioGroup=dialog.findViewById(R.id.rg_bluetooth_event);

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

        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        RadioGroup radioGroup=dialog.findViewById(R.id.rg_bluetooth_event);

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
                if (rd_btn !=null && rd_btn.getText().toString().equals("Bluetooth Enabled")){
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }else if (rd_btn !=null && rd_btn.getText().toString().equals("Bluetooth Disabled")){
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }else if (rd_btn !=null && rd_btn.getText().toString().equals("Device Connected")){
                        listofbluetooth(context,triggerlistmodel);
                    dialog.dismiss();
                }else if (rd_btn !=null && rd_btn.getText().toString().equals("Device Disconnected")){
                    listofbluetooth(context,triggerlistmodel);
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Select valid option", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.show();
        // Apply the newly created layout parameters to
    }

    private static void listofbluetooth(Context context, Triggerlistmodel triggerlistmodel) {
        BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
        ArrayList<String>arrayList=new ArrayList<>();

        if(bAdapter==null){
              /*  Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                context.startActivityForResult(enableIntent, REQUEST_ENABLE_CODE);
           */
            Toast.makeText(context,"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
        }
        else{
            Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
            ArrayList list = new ArrayList();
            if(pairedDevices.size()>0){
                for(BluetoothDevice device: pairedDevices){
                    String devicename = device.getName();
                    String macAddress = device.getAddress();
                    Log.d("hhdsjkdhnsajlkdhskl","ddd"+devicename);
                    list.add("Name: "+devicename+"MAC Address: "+macAddress);
                }
                shwoDialodforblutoothlist(context,triggerlistmodel,list);
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
        RadioGroup radioGroup=dialog.findViewById(R.id.rg_blutooth_list);
        for (int i=0;i<list.size();i++){
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

        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        RadioGroup radioGroup=dialog.findViewById(R.id.rg_bluetooth_event);

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
              if(rd_btn !=null && rd_btn.getText().toString().equals("Data Available")){
                  triggerlistmodel.setTriggername(rd_btn.getText().toString());
                  triggerlistmodel.setTriggerdescrption("");
                  AddMacroActivity.triggerlist.add(triggerlistmodel);
                  dialog.dismiss();
              }else if (rd_btn !=null && rd_btn.getText().toString().equals("No Data Connection")){
                  triggerlistmodel.setTriggername(rd_btn.getText().toString());
                  triggerlistmodel.setTriggerdescrption("");
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

        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        RadioGroup radioGroup=dialog.findViewById(R.id.rg_bluetooth_event);

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
                if(rd_btn !=null && rd_btn.getText().toString().equals("Headphones Inserted")){
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }else if (rd_btn !=null && rd_btn.getText().toString().equals("Headphones Removed")){
                    triggerlistmodel.setTriggername(rd_btn.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();


    }

    //end region




}
