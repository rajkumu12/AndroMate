package com.andromate.Ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andromate.Constraints.ConstraintsType;
import com.andromate.CustomColors;
import com.andromate.Model.ConstraintsListModelList;
import com.andromate.Model.TriggerItemModel;
import com.andromate.R;

import java.util.List;

public class Constraints_batteryPowerItemsAdapters extends RecyclerView.Adapter<Constraints_batteryPowerItemsAdapters.ViewHolder> {

    private Context context;
    private List<TriggerItemModel> arrayList;
    ConstraintsListModelList constraintsListModelList;

    public Constraints_batteryPowerItemsAdapters(Context context, List<TriggerItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Constraints_batteryPowerItemsAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.triggeritem_ui, parent, false);
        constraintsListModelList=new ConstraintsListModelList();
        return new Constraints_batteryPowerItemsAdapters.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Constraints_batteryPowerItemsAdapters.ViewHolder holder, final int position) {
            TriggerItemModel triggerItemModel=arrayList.get(position);

            holder.imageView.setImageResource(triggerItemModel.getImage());
            holder.imageView.setColorFilter(CustomColors.white);
            holder.tv_title.setText(triggerItemModel.getTitle());
            holder.lly_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.tv_title.getText().toString().equals("Battery Level")){
                        constraintsListModelList.setConstraintsname(holder.tv_title.getText().toString());
                        ConstraintsType.showbatterydailog(context,constraintsListModelList);
                    }else if (holder.tv_title.getText().toString().equals("Battery Saver State")){
                        constraintsListModelList.setConstraintsname(holder.tv_title.getText().toString());
                        ConstraintsType.show_battery_state(context,constraintsListModelList);
                    }else if (holder.tv_title.getText().toString().equals("Battery Temperature")){
                        constraintsListModelList.setConstraintsname(holder.tv_title.getText().toString());
                        ConstraintsType.showbatterydailog(context,constraintsListModelList);
                    }else if (holder.tv_title.getText().toString().equals("Power Connected/\n" +
                            "Disconnected")){

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
