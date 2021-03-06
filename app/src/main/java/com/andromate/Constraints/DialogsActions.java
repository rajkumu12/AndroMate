package com.andromate.Constraints;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andromate.Model.ActionModelList;
import com.andromate.Model.ApplicationsInfo;
import com.andromate.Model.Triggerlistmodel;
import com.andromate.R;
import com.andromate.Ui.Activity.AddMacroActivity;
import com.andromate.Ui.Adapters.ApplicationlistAdapters;
import com.andromate.Ui.Adapters.ApplicationlistAdapters_Action;
import com.andromate.Ui.On;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DialogsActions {


    public static ProgressDialog progressDialog;
    public static Triggerlistmodel triggerlistmodel1;
    public static ApplicationlistAdapters_Action triggerItemsAdapters;

    //Region Action Applicationhor
    public static void showactionApplication(Context context, ActionModelList triggerlistmodel,String s) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_action_app_enb);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_app_ena);
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

                if (rd_btn != null && rd_btn.getText().toString().equals("Enabled")) {
                    triggerlistmodel.setActioname(rd_btn.getText().toString());
                    LoadApplicainlist(context, triggerlistmodel,s);

                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Disabled")) {
                    triggerlistmodel.setActioname(rd_btn.getText().toString());
                    LoadApplicainlist(context, triggerlistmodel,s);
                    dialog.dismiss();
                }
            }
        });
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }

    private static void LoadApplicainlist(Context context, ActionModelList triggerlistmodel,String s) {
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

        getInstalledApps(false, recyclerView, context,s);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getInstalledApps2(false, recyclerView, context,s);
                } else {
                    getInstalledApps(false, recyclerView, context,s);
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

    public static ArrayList<ApplicationsInfo> getInstalledApps(boolean getSysPackages, RecyclerView recyclerView, Context context,String s) {
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
        triggerItemsAdapters = new ApplicationlistAdapters_Action(context, res,s);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(triggerItemsAdapters);
        return res;
    }

    public static ArrayList<ApplicationsInfo> getInstalledApps2(boolean getSysPackages, RecyclerView recyclerView, Context context,String s) {
        ArrayList<ApplicationsInfo> res = new ArrayList<ApplicationsInfo>();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading Apps");
        progressDialog.setCancelable(false);
        progressDialog.show();
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
        progressDialog.dismiss();
        triggerItemsAdapters = new ApplicationlistAdapters_Action(context, res,s);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(triggerItemsAdapters);
        return res;
    }

    public static void showConfigurations(Context context, ActionModelList triggerlistmodel, String s) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.configurationui_action);
        dialog.setCancelable(true);

        TextView tv_tittle = dialog.findViewById(R.id.tv_tittle);
        tv_tittle.setText(s);

        dialog.show();

    }


    public static void show_clear_data(Context context, ActionModelList triggerlistmodel, String s) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_action_clear_app_data);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_app_ena);
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

                if (rd_btn != null && rd_btn.getText().toString().equals("Select Application")) {
                    LoadApplicainlist(context, triggerlistmodel,s);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Enter Package Name")) {

                    showpackagedialog(context, triggerlistmodel,s);

                }
            }
        });
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }


    public static void showpackagedialog(Context context, ActionModelList triggerlistmodel,String s) {
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
                   /* triggerlistmodel.setActioname(et_package.getText().toString());
                    AddMacroActivity.triggerlist.add(triggerlistmodel);*/
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

    public static void showPackagelist(Context context, ActionModelList triggerlistmodel, EditText et_package) {
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


    public static void show_kill_background(Context context, ActionModelList triggerlistmodel,String s) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_action_kill_back_processes);
        dialog.setCancelable(false);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_app_ena);
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

                if (rd_btn != null && rd_btn.getText().toString().equals("Select Application")) {
                    LoadApplicainlist(context, triggerlistmodel,s);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Enter Package Name")) {
                    showpackagedialog(context, triggerlistmodel,s);
                }
            }
        });
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }


    public static void show_launch_option(Context context, ActionModelList triggerlistmodel,String s) {
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

                if (rd_btn != null && rd_btn.getText().toString().equals("Select Application")) {
                    LoadApplicainlist(context, triggerlistmodel,s);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Enter Package Name")) {
                    showpackagedialog(context, triggerlistmodel,s);
                }
            }
        });
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }


    public static void loadApps_shortcut(Context context, ActionModelList actionModelList) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_applist_shortcut);
        dialog.setCancelable(false);
        LinearLayout linearLayout = dialog.findViewById(R.id.lly_shortcut);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);


        List<ApplicationsInfo> res = new ArrayList<ApplicationsInfo>();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((false) && (p.versionName == null)) {
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

        final RadioButton[] rb = new RadioButton[res.size()];
        RadioGroup rg = new RadioGroup(context); //create the RadioGroup
        rg.setOrientation(RadioGroup.VERTICAL);

        for (int i = 0; i < res.size(); i++) {
            ApplicationsInfo newInfo = res.get(i);

            rb[i] = new RadioButton(context);
            rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(newInfo.getAppname());

        }
        linearLayout.addView(rg);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rg.getCheckedRadioButtonId();
                RadioButton rd_btn = dialog.findViewById(selectedId);

                if (rd_btn != null) {
                    int id = 3;
                    int position = -1;
                    for (int i = 0; i < res.size(); i++) {
                        ApplicationsInfo applicationsInfo=res.get(i);
                        if (applicationsInfo.getAppname().equals(rd_btn.getText().toString())) {
                            position = i;
                            // break;  // uncomment to get the first instance
                        }
                    }
                    ApplicationsInfo applicationsInfo=res.get(position);
                    actionModelList.setActionDescription(applicationsInfo.getPname());
                    AddMacroActivity.actionlist.add(actionModelList);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Select a shortcut", Toast.LENGTH_SHORT).show();
                }
            }
        });

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();


    }

    public static  int getCategoryPos(String category,List<ApplicationsInfo> applicationsInfos) {
        return applicationsInfos.indexOf(category);
    }
    public static void logAllTaskerTasks(Context context, ActionModelList actionModelList) {
        Cursor c = context.getContentResolver().query(
                Uri.parse("content://net.dinglisch.android.tasker/tasks"),
                null, null, null, null);

        if (c != null) {
            int nameCol = c.getColumnIndex("name");
            int projNameCol = c.getColumnIndex("project_name");

            while (c.moveToNext()) {
                Log.d("onenene", "ldhfkjdhfjkf" + c.getString(projNameCol) + "/" + c.getString(nameCol));
            }
            c.close();
        } else {
            Toast.makeText(context, "No tasker or plugin found", Toast.LENGTH_SHORT).show();
        }
    }


    public static void show_declairationCamera(Context context, ActionModelList actionModelList) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_enable_camera_notice);
        dialog.setCancelable(false);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        CheckBox checkBox = dialog.findViewById(R.id.checkbox);


        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!checkBox.isChecked()) {
                    Toast.makeText(context, "Please check Terms & Conditions", Toast.LENGTH_SHORT).show();
                } else {
                    show_dialog_t_n_c(context, actionModelList);
                    dialog.dismiss();
                }
            }
        });


        dialog.show();
    }


    public static void show_dialog_t_n_c(Context context, ActionModelList actionModelList) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_enable_camera_notice_description);
        dialog.setCancelable(false);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        CheckBox checkBox = dialog.findViewById(R.id.checkbox);


        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public static void show_dialog_photoselector(Context context, ActionModelList actionModelList) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_photo_opener_option);
        dialog.setCancelable(false);
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
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    public static void show_dialog_share_lastphoto(Context context, ActionModelList actionModelList) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_share_last_photos);
        dialog.setCancelable(false);
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
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    public static void show_dialog_takephoto(Context context, ActionModelList actionModelList) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_take_photos);
        dialog.setCancelable(false);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_take_photos);

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
                if (rd_btn != null && rd_btn.getText().toString().equals("Front Facing")) {
                    show_dialog_showCam_icon(context, actionModelList);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Front Facing")) {
                    show_dialog_showflash(context, actionModelList);
                    dialog.dismiss();
                }

            }
        });

        dialog.show();

    }


    public static void show_dialog_showCam_icon(Context context, ActionModelList actionModelList) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_show_camera_icon);
        dialog.setCancelable(false);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_take_photos);

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
                if (rd_btn != null && rd_btn.getText().toString().equals("Show camera icon")) {

                    savefilename(context,actionModelList);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("hide camera icon")) {
                    savefilename(context,actionModelList);
                    dialog.dismiss();
                }

            }
        });

        dialog.show();

    }


    public static void show_dialog_showflash(Context context, ActionModelList actionModelList) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_flash_option);
        dialog.setCancelable(false);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_take_photos);

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
                if (rd_btn != null && rd_btn.getText().toString().equals("Flash Off")) {
                    show_dialog_showCam_icon(context, actionModelList);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Flash On")) {
                    show_dialog_showCam_icon(context, actionModelList);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Flash Auto")) {
                    show_dialog_showCam_icon(context, actionModelList);
                    dialog.dismiss();
                }

            }
        });

        dialog.show();

    }


    public static void savefilename(Context context,ActionModelList actionModelList){
        File dir_ = new File(context.getExternalFilesDir("Andromate"),"One");
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_file_saving_ui);
        dialog.setCancelable(false);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        TextView tvpath=dialog.findViewById(R.id.tv_folderpth);

        tvpath.setText(""+dir_);

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


        dialog.show();

    }


    public static void shwo_action_airplane_mode(Context context,ActionModelList actionModelList){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_airplanemode);
        dialog.setCancelable(false);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_take_photos);

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
                    shwo_action_airplane_mode_option(context, actionModelList);
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }


    public static void shwo_action_airplane_mode_option(Context context,ActionModelList actionModelList){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.select_toggle_airplanemodes);
        dialog.setCancelable(false);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_launch_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_launch_ok);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_take_photos);

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
                    dialog.dismiss();
                }

            }
        });

        dialog.show();
    }


    public static void showbluetoothConfiguration(Context context,ActionModelList actionModelList){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_action_bluetooth_configuration_data);
        dialog.setCancelable(false);
        TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
        TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
        RadioGroup radioGroup = dialog.findViewById(R.id.rg_app_ena);

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
                if (rd_btn != null && rd_btn.getText().toString().equals("Enable Bluetooth")) {
                    actionModelList.setActioname(rd_btn.getText().toString());
                    AddMacroActivity.actionlist.add(actionModelList);
                    dialog.dismiss();
                }else  if (rd_btn != null && rd_btn.getText().toString().equals("Disable Bluetooth")) {
                    actionModelList.setActioname(rd_btn.getText().toString());
                    AddMacroActivity.actionlist.add(actionModelList);


                    dialog.dismiss();
                }else if (rd_btn != null && rd_btn.getText().toString().equals("Toogle bluetooth")) {
                    actionModelList.setActioname(rd_btn.getText().toString());
                    AddMacroActivity.actionlist.add(actionModelList);
                    dialog.dismiss();

                }else if (rd_btn != null && rd_btn.getText().toString().equals("Connect Audio Device")){
                        loadBluthotDevices(context,actionModelList,rd_btn.getText().toString());
                        dialog.dismiss();
                }else if (rd_btn != null && rd_btn.getText().toString().equals("Disconnect Audio Device")){

                }

            }


        });

        dialog.show();
    }
    public static void loadBluthotDevices(Context context1, ActionModelList actionModelList1, String s) {
        int REQUEST_ENABLE_BT = 1;
        BluetoothAdapter btAdapter=BluetoothAdapter.getDefaultAdapter();

        CheckBluetoothState(REQUEST_ENABLE_BT,context1,actionModelList1,btAdapter,s);

    }
    public static void CheckBluetoothState(int REQUEST_ENABLE_BT, Context context1, ActionModelList actionModelList1, BluetoothAdapter btAdapter, String s) {
        // Checks for the Bluetooth support and then makes sure it is turned on
        // If it isn't turned on, request to turn it on
        // List paired devices
        if(btAdapter==null) {
            Toast.makeText(context1, "Bluetooth not Supported", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (btAdapter.isEnabled()) {
                ProgressDialog progressDialog=new ProgressDialog(context1);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Loading devices");
                progressDialog.show();
                Toast.makeText(context1, "Bluetooth is enabled", Toast.LENGTH_SHORT).show();

                ArrayList<String>bt_devicelist=new ArrayList<>();
                Set<BluetoothDevice> devices = btAdapter.getBondedDevices();

                for (BluetoothDevice device : devices) {
                   /* textview1.append("\n  Device: " + device.getName() + ", " + device);*/
                    Log.d("jfjfjjfjjjjj","dddd"+device.getName());
                   bt_devicelist.add(device.getName());
                }
                progressDialog.dismiss();
                btlist_device(bt_devicelist,context1,actionModelList1,s);
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                ((Activity) context1).startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }


    public static void btlist_device(ArrayList<String> bt_devicelist, Context context1, ActionModelList actionModelList1, String s){
            Dialog dialog = new Dialog(context1);
            dialog.setContentView(R.layout.alert_bt_devicelist);
            dialog.setCancelable(false);
            TextView tv_cancel = dialog.findViewById(R.id.tv_app_in_cancel);
            TextView tv_ok = dialog.findViewById(R.id.tv_app_in_ok);
           RadioGroup radioGroup=dialog.findViewById(R.id.lly_bt_device);
      RadioButton rbn = null;
        for (int i = 0; i < bt_devicelist.size(); i++) {
                 rbn = new RadioButton(context1);
                rbn.setId(View.generateViewId());
                rbn.setText((CharSequence) bt_devicelist.get(i));
                radioGroup.addView(rbn);
            }



            tv_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


        RadioButton finalRbn = rbn;
        tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actionModelList1.setActionDescription(s+"("+ finalRbn.getText().toString() +")");
                    AddMacroActivity.actionlist.add(actionModelList1);
                    ((Activity) context1).finish();
                }
            });

            dialog.show();


        }



    }
    //end region

