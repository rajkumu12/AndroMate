package com.andromate.Ui.Adapters;

import android.app.Activity;
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

import com.andromate.Constraints.ConstraintsType;
import com.andromate.Constraints.Dialogs;
import com.andromate.CustomColors;
import com.andromate.Model.ConstraintsListModelList;
import com.andromate.Model.TriggerItemModel;
import com.andromate.R;
import com.andromate.Ui.Activity.AddMacroActivity;

import java.util.List;

public class Constraints_ConnectivityItemsAdapters extends RecyclerView.Adapter<Constraints_ConnectivityItemsAdapters.ViewHolder> {

    private Context context;
    private List<TriggerItemModel> arrayList;
    ConstraintsListModelList constraintsListModelList;

    public Constraints_ConnectivityItemsAdapters(Context context, List<TriggerItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Constraints_ConnectivityItemsAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.triggeritem_ui, parent, false);
        constraintsListModelList=new ConstraintsListModelList();
        return new Constraints_ConnectivityItemsAdapters.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Constraints_ConnectivityItemsAdapters.ViewHolder holder, final int position) {
            TriggerItemModel triggerItemModel=arrayList.get(position);

            holder.imageView.setImageResource(triggerItemModel.getImage());
            holder.imageView.setColorFilter(CustomColors.white);
            holder.tv_title.setText(triggerItemModel.getTitle());
            holder.lly_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.tv_title.getText().toString().equals("Bluetooth State")){
                        ConstraintsType.showbluetooth_event(context,constraintsListModelList);
                    }else if (holder.tv_title.getText().toString().equals("Cell Towers")){
                        Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT).show();
                    }else if (holder.tv_title.getText().toString().equals("GPS State")){
                        ConstraintsType.Show_gps_enable(context,constraintsListModelList);
                    }else if (holder.tv_title.getText().toString().equals("is Roaming")){
                        ConstraintsType.showRoaming(context,constraintsListModelList);
                    }else if (holder.tv_title.getText().toString().equals("Location Mode")){
                        constraintsListModelList.setConstraintsname(holder.tv_title.getText().toString());
                        ConstraintsType.lactionmode(context,constraintsListModelList);
                    }else if (holder.tv_title.getText().toString().equals("Mobile data On/Off")){
                        ConstraintsType.showDataconnectivitychange(context,constraintsListModelList);
                    }/*else if (holder.tv_title.getText().toString().equals("RMobile Service Status")){
                        Dialogs.showRoaming(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Wifi HotSpot State")){
                        Dialogs.showUSBDevice_con(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Wifi State")){
                        triggerlistmodel.setTriggername(holder.tv_title.getText().toString());
                        Dialogs.showVpn_state(context,triggerlistmodel);
                    }*/

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
