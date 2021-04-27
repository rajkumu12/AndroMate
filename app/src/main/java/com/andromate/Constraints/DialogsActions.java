package com.andromate.Constraints;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

public class DialogsActions {


    public static ProgressDialog progressDialog;
    public static Triggerlistmodel triggerlistmodel1;
    public static ApplicationlistAdapters triggerItemsAdapters;

    //Region Action Application
    public static void showactionApplication(Context context, ActionModelList triggerlistmodel) {

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
                    LoadApplicainlist(context,triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Disabled")) {
                    LoadApplicainlist(context,triggerlistmodel);
                    dialog.dismiss();
                }
            }
        });
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }

    private static void LoadApplicainlist(Context context, ActionModelList triggerlistmodel) {

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
        ProgressDialog progressDialog=new ProgressDialog(context);
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
        triggerItemsAdapters = new ApplicationlistAdapters(context, res);
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



    public static void show_clear_data(Context context, ActionModelList triggerlistmodel) {

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
                    LoadApplicainlist(context,triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Enter Package Name")) {

                    showpackagedialog(context,triggerlistmodel);

                }
            }
        });
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }




    public static void showpackagedialog(Context context, ActionModelList triggerlistmodel) {
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


    public static void show_kill_background(Context context, ActionModelList triggerlistmodel) {
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
                    LoadApplicainlist(context,triggerlistmodel);
                    dialog.dismiss();
                } else if (rd_btn != null && rd_btn.getText().toString().equals("Enter Package Name")) {

                    showpackagedialog(context,triggerlistmodel);

                }
            }
        });
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }
    //end region
}
