package com.andromate.Ui.Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
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

import java.util.List;

public class DeviceEvent_Adapter extends RecyclerView.Adapter<DeviceEvent_Adapter.ViewHolder> {

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

    public DeviceEvent_Adapter(Context context) {
        this.context = context;
    }

    public DeviceEvent_Adapter(Context context, List<TriggerItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        sharedpreferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public DeviceEvent_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.triggeritem_ui, parent, false);
        triggerlistmodel = new Triggerlistmodel();
        return new DeviceEvent_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DeviceEvent_Adapter.ViewHolder holder, final int position) {
        TriggerItemModel triggerItemModel = arrayList.get(position);

        holder.imageView.setImageResource(triggerItemModel.getImage());
        holder.imageView.setColorFilter(CustomColors.white);
        holder.tv_title.setText(triggerItemModel.getTitle());


        holder.lly_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tv_title.getText().toString().equals("Airplane Mode\n" +
                        "Changed")) {
                    Dialogs.Show_aitplane_opton(context, triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("Auto Rotate Changed")) {
                    Dialogs.Show_rotatechange(context, triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("Autosync Changed")) {
                    Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT).show();
                } else if (holder.tv_title.getText().toString().equals("Clipboard Changed")) {
                    Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT).show();
                } else if (holder.tv_title.getText().toString().equals("Daydream On/Off")) {
                    Dialogs.Show_daydream(context, triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("Device Boot")) {
                    triggerlistmodel.setTriggername(holder.tv_title.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("db", holder.tv_title.getText().toString());
                    editor.apply();
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                    ((Activity) context).finish();
                } else if (holder.tv_title.getText().toString().equals("Device Docked/\n" +
                        "Undocked")) {
                    Dialogs.Show_devicedock(context, triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("Failed Login Attempt")) {

                } else if (holder.tv_title.getText().toString().equals("GPS Enabled/\n" +
                        "Disabled")) {
                    Dialogs.Show_gps_enable(context, triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("Intent Received")) {
                    Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT).show();
                } else if (holder.tv_title.getText().toString().equals("Logcat Message")) {
                    Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT).show();
                } else if (holder.tv_title.getText().toString().equals("Music/Sound Playing")) {
                    Dialogs.Show_music(context, triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("Notification")) {
                    Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT).show();
                } else if (holder.tv_title.getText().toString().equals("Priority Mode/Do Not\n" +
                        "Disturb")) {
                    Dialogs.Show_prioritymode_don_disturb(context, triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("SIM Card Change")) {
                    Dialogs.Show_seimcard_change(context, triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("Screen On/Off")) {
                    Dialogs.Show_screenOn_Off(context, triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("Screen Unlocked")) {
                    triggerlistmodel.setTriggername(holder.tv_title.getText().toString());
                    triggerlistmodel.setTriggerdescrption("");
                    AddMacroActivity.triggerlist.add(triggerlistmodel);
                } else if (holder.tv_title.getText().toString().equals("Silent Mode Enabled/\n" +
                        "DIsabled")) {
                    Dialogs.Show_silentmode(context, triggerlistmodel);
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
