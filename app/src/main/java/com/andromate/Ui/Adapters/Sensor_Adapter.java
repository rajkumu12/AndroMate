package com.andromate.Ui.Adapters;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andromate.Constraints.Dialogs;
import com.andromate.CustomColors;
import com.andromate.Model.ContactModel;
import com.andromate.Model.TriggerItemModel;
import com.andromate.Model.Triggerlistmodel;
import com.andromate.R;
import com.andromate.Ui.Activity.AddMacroActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

public class Sensor_Adapter extends RecyclerView.Adapter<Sensor_Adapter.ViewHolder> {

    private Context context;
    private List<TriggerItemModel> arrayList;
    ApplicationlistAdapters triggerItemsAdapters;
    ProgressDialog progressDialog;
    String triggername;
    String triggerdesc;
    Triggerlistmodel triggerlistmodel;
    List<ContactModel> contactModelList;
    public static String TAG = "Call_SmsAdapter";
    SharedPreferences sharedpreferences;
    public Sensor_Adapter(Context context) {
        this.context = context;
    }

    public Sensor_Adapter(Context context, List<TriggerItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        sharedpreferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public Sensor_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.triggeritem_ui, parent, false);
        triggerlistmodel = new Triggerlistmodel();
        return new Sensor_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Sensor_Adapter.ViewHolder holder, final int position) {
        TriggerItemModel triggerItemModel = arrayList.get(position);

        holder.imageView.setImageResource(triggerItemModel.getImage());
        holder.imageView.setColorFilter(CustomColors.white);
        holder.tv_title.setText(triggerItemModel.getTitle());


        holder.lly_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tv_title.getText().toString().equals("Activity Recognition")) {
                    Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT).show();

                } else if (holder.tv_title.getText().toString().equals("Flip Device")) {
                        Dialogs.Show_sensor(context,triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("Light Sensor")) {
                        Dialogs.Show_lightsensor(context,triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("Proximity Sensor")) {
                    Dialogs.show_proximity_sensor(context,triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("Screen Orientation")) {
                    Dialogs.show_portrait(context,triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("Shake Device")) {
                    triggerlistmodel.setTriggername(holder.tv_title.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("shaking", holder.tv_title.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    ((Activity)context).finish();
                }

            }
        });


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv_title;
        LinearLayout lly_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            tv_title = itemView.findViewById(R.id.item_text);
            lly_view = itemView.findViewById(R.id.lly_view);
        }
    }
}
