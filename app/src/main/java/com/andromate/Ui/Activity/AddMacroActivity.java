package com.andromate.Ui.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andromate.Model.ActionModelList;
import com.andromate.Model.Triggerlistmodel;
import com.andromate.R;
import com.andromate.Ui.Adapters.Actionlists_items_Adapter;
import com.andromate.Ui.Adapters.Triggelists_items_Adapter;
import com.andromate.db.DBHelper;
import com.google.android.material.tabs.TabLayout;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class AddMacroActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView add_macro;
    RelativeLayout imageView_back;
    LinearLayout lly_trigger;
    LinearLayout lly_action;
    LinearLayout lly_constraints;
    TextView tv_notrigger,tv_dummy_action;

    public static String types;
    public static List<Triggerlistmodel>triggerlist;
    public static List<ActionModelList>actionlist;

    RecyclerView recyclerView_triggerlist,recyclerview_actionlist;
    RelativeLayout rly_macronotes;
    EditText et_description;
    DBHelper mydb;
    EditText editText_macroname;
    RelativeLayout rly_interface;

    String backstate="";
    public static String TAG="AddMacroActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        getSupportActionBar().hide();
        transparentStatusAndNavigation(this);
      /*  View decorView =getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);*/

        setContentView(R.layout.activity_add_macro);

        mydb = new DBHelper(this);
        recyclerView_triggerlist=findViewById(R.id.recy_trigger_item);
        recyclerview_actionlist=findViewById(R.id.recy_action_item);
        tv_notrigger=findViewById(R.id.tv_notrigger);
        editText_macroname=findViewById(R.id.et_macroname);
        rly_macronotes=findViewById(R.id.rly_macronotes);
        et_description=findViewById(R.id.et_description);
        rly_interface=findViewById(R.id.rly_interface);
        triggerlist=new ArrayList<>();
        actionlist=new ArrayList<>();


        imageView_back=findViewById(R.id.back_icon_addmacros);
        lly_trigger=findViewById(R.id.lly_trigger);
        lly_action=findViewById(R.id.action_ui);
        lly_constraints=findViewById(R.id.lly_constraints);
        add_macro=findViewById(R.id.add_macro);
        imageView_back.setOnClickListener(this);
        lly_trigger.setOnClickListener(this);
        lly_action.setOnClickListener(this);
        lly_constraints.setOnClickListener(this);
        add_macro.setOnClickListener(this);
        rly_macronotes.setOnClickListener(this);
        rly_interface.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.back_icon_addmacros){
            if (triggerlist.size() !=0){
                backstate="b";
                saveDialog();
            }else {
                finish();
            }

        }else if (id==R.id.lly_trigger){
            types="trigger";
            startActivity(new Intent(AddMacroActivity.this,Add_triggersActivity.class));
        }else if (id==R.id.action_ui){
            types="action";
            startActivity(new Intent(AddMacroActivity.this,AddAction.class));
        }else if (id==R.id.lly_constraints){
            types="constraints";
            startActivity(new Intent(AddMacroActivity.this,AddConstraintsActivity.class));
        }else if (id==R.id.add_macro){
            backstate="";
            saveDialog();
        }else if (id==R.id.rly_macronotes){
            et_description.setVisibility(View.VISIBLE);
        }else if (id==R.id.rly_interface){

            showAlertDialog();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadTriggerlistitem();
        loadActionlistItem();
    }

    private void loadActionlistItem() {

         Actionlists_items_Adapter triggelists_items_adapter=new Actionlists_items_Adapter(this,actionlist);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(AddMacroActivity.this);
        recyclerView_triggerlist.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_triggerlist.setItemAnimator(new DefaultItemAnimator());
        recyclerView_triggerlist.setAdapter(triggelists_items_adapter);

        if (triggerlist.size() !=0){
            tv_notrigger.setVisibility(View.GONE);
            recyclerView_triggerlist.setVisibility(View.VISIBLE);
        }




    }

    private void loadTriggerlistitem() {


        Triggelists_items_Adapter triggelists_items_adapter=new Triggelists_items_Adapter(this,triggerlist);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(AddMacroActivity.this);
        recyclerView_triggerlist.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_triggerlist.setItemAnimator(new DefaultItemAnimator());
        recyclerView_triggerlist.setAdapter(triggelists_items_adapter);

        if (triggerlist.size() !=0){
            tv_notrigger.setVisibility(View.GONE);
            recyclerView_triggerlist.setVisibility(View.VISIBLE);
        }

    }

    public static void transparentStatusAndNavigation(Activity activity) {

        Window window = activity.getWindow();

        // make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(window,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            int visibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            visibility = visibility | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            window.getDecorView().setSystemUiVisibility(visibility);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            int windowManager = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            windowManager = windowManager | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            setWindowFlag(window ,windowManager, false);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

    }
    private static void setWindowFlag(Window window,final int bits, boolean on) {
        Window win =window;
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void showAlertDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alert_dialog_macro_category);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);



        TextView cancel=dialog.findViewById(R.id.tv_cancel);
        TextView ok=dialog.findViewById(R.id.tv_ok);



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
              /*  rly_data.setVisibility(View.VISIBLE);
                rly_no_data.setVisibility(View.GONE);*/
            }
        });


        dialog.show();
    }



    void saveDialog(){
        String macroname=editText_macroname.getText().toString();
        String macrodesc=et_description.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(AddMacroActivity.this);
        builder.setTitle("Save Macro")
                .setMessage("Do you want to save macro")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (macroname.isEmpty()){
                            Toast.makeText(AddMacroActivity.this, "Enter macroname", Toast.LENGTH_SHORT).show();
                        }else if (macrodesc.isEmpty()){
                            Toast.makeText(AddMacroActivity.this, "Enter description", Toast.LENGTH_SHORT).show();
                        }else {
                            StringBuilder triggersname = new StringBuilder("");
                            for (int i=0;i<triggerlist.size();i++){
                                Triggerlistmodel triggerlistmodel=triggerlist.get(i);
                                triggersname.append(triggerlistmodel.getTriggername()).append(",");
                            }
                            String commaseparatedlist = triggersname.toString();
                            Log.d(TAG,"ttt"+commaseparatedlist);
                            mydb.insertDataMacro(macroname,macrodesc,"true","non",commaseparatedlist,"","",
                                    "","","");
                            finish();
                        }
                        mydb.getAllDataMacro();
                        Log.d("hhhffhhfhfh","jjjj"+mydb.getAllDataMacro());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (backstate.equals("b")){
                            finish();
                        }else {
                            dialog.cancel();
                        }

                    }
                });
        //Creating dialog box
        AlertDialog dialog  = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (triggerlist.size()!=0){
            backstate="b";
            saveDialog();
        }else {
            super.onBackPressed();
        }

    }
}