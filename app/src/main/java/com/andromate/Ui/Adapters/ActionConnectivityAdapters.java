package com.andromate.Ui.Adapters;

import android.content.Context;
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
import com.andromate.Constraints.DialogsActions;
import com.andromate.CustomColors;
import com.andromate.Model.ActionModelList;
import com.andromate.Model.TriggerItemModel;
import com.andromate.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class ActionConnectivityAdapters extends RecyclerView.Adapter<ActionConnectivityAdapters.ViewHolder> {

    private Context context;
    private List<TriggerItemModel> arrayList;
    ActionModelList triggerlistmodel;
    public ActionConnectivityAdapters(Context context, List<TriggerItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ActionConnectivityAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.triggeritem_ui, parent, false);
        triggerlistmodel = new ActionModelList();
        return new ActionConnectivityAdapters.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ActionConnectivityAdapters.ViewHolder holder, final int position) {
            TriggerItemModel triggerItemModel=arrayList.get(position);

            holder.imageView.setImageResource(triggerItemModel.getImage());
            holder.imageView.setColorFilter(CustomColors.white);
            holder.tv_title.setText(triggerItemModel.getTitle());
            holder.lly_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.tv_title.getText().toString().equals("Airplane Mode On/Off")){
                        DialogsActions.shwo_action_airplane_mode(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Android Wear")){
                        Toast.makeText(context, "Android wear is in progress", Toast.LENGTH_SHORT).show();
                    } else if (holder.tv_title.getText().toString().equals("Auto Sync On/Off")){
                        DialogsActions.shwo_action_airplane_mode_option(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Bluetooth Configure")){
                        DialogsActions.showbluetoothConfiguration(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Bluetooth tethering")){
                        DialogsActions.shwo_action_airplane_mode_option(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Connectivity Check")){
                        DialogsActions.shwo_action_airplane_mode_option(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("HotSpot On/Off")){
                        DialogsActions.shwo_action_airplane_mode_option(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Mobile Data On/Off")){
                        DialogsActions.shwo_action_airplane_mode_option(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Send Intent")){
                        DialogsActions.shwo_action_airplane_mode_option(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Sync Account")){
                        DialogsActions.shwo_action_airplane_mode_option(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("USB Tethering")){
                        DialogsActions.shwo_action_airplane_mode_option(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("USB Tethering")){
                        DialogsActions.shwo_action_airplane_mode_option(context,triggerlistmodel);
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
            imageView=itemView.findViewById(R.id.item_image);
            tv_title=itemView.findViewById(R.id.item_text);
            lly_view=itemView.findViewById(R.id.lly_view);

        }
    }
}
