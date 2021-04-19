package com.andromate.Ui.Adapters;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

public class Connectivity_Adapter extends RecyclerView.Adapter<Connectivity_Adapter.ViewHolder> {

    private Context context;
    private List<TriggerItemModel> arrayList;
    ApplicationlistAdapters triggerItemsAdapters;
    ProgressDialog progressDialog;
    String triggername;
    String triggerdesc;
    Triggerlistmodel triggerlistmodel;
    List<ContactModel> contactModelList;
    public static String TAG = "Call_SmsAdapter";

    public Connectivity_Adapter(Context context) {
        this.context = context;
    }

    public Connectivity_Adapter(Context context, List<TriggerItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Connectivity_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.triggeritem_ui, parent, false);
        triggerlistmodel = new Triggerlistmodel();
        return new Connectivity_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Connectivity_Adapter.ViewHolder holder, final int position) {
        TriggerItemModel triggerItemModel = arrayList.get(position);

        holder.imageView.setImageResource(triggerItemModel.getImage());
        holder.imageView.setColorFilter(CustomColors.white);
        holder.tv_title.setText(triggerItemModel.getTitle());


        holder.lly_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tv_title.getText().toString().equals("Android Wear")){
                    Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT).show();
                }else if (holder.tv_title.getText().toString().equals("Bluetooth Event")){
                        Dialogs.showbluetooth_event(context,triggerlistmodel);
                }else if (holder.tv_title.getText().toString().equals("Data Connectivity\n" +
                        "Change")){
                        Dialogs.showDataconnectivitychange(context,triggerlistmodel);
                }else if (holder.tv_title.getText().toString().equals("Headphones Insert/\n" +
                        "Remove")){
                    Dialogs.showHeadphoneschange(context,triggerlistmodel);
                }else if (holder.tv_title.getText().toString().equals("Hotspot Enabled/\n" +
                        "Disabled")){

                }else if (holder.tv_title.getText().toString().equals("IP Address Change")){

                }else if (holder.tv_title.getText().toString().equals("Mobile Service Status")){

                }else if (holder.tv_title.getText().toString().equals("Roaming Started/\n" +
                        "Stopped")){

                }else if (holder.tv_title.getText().toString().equals("USB Device Connect/\n" +
                        "Disconnect")){

                }else if (holder.tv_title.getText().toString().equals("VPN State Change")){

                }else if (holder.tv_title.getText().toString().equals("Webhook (Url)")){

                }else if (holder.tv_title.getText().toString().equals("Wifi SSID Transition")){

                }else if (holder.tv_title.getText().toString().equals("Wifi State Change")){

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
