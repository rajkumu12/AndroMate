package com.andromate.Constraints;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.WindowManager;
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
}
