package com.andromate.Ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andromate.Constraints.Dialogs;
import com.andromate.Model.Triggerlistmodel;
import com.andromate.R;

import java.util.List;

public class Triggelists_items_Adapter extends RecyclerView.Adapter<Triggelists_items_Adapter.ViewHolder> {

    private Context context;
    private List<Triggerlistmodel> arrayList;

    public Triggelists_items_Adapter(Context context, List<Triggerlistmodel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Triggelists_items_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tirgger_items_ui_list, parent, false);
        return new Triggelists_items_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Triggelists_items_Adapter.ViewHolder holder, final int position) {
        Triggerlistmodel add_action_model = arrayList.get(position);
        holder.textView_trigger.setText(add_action_model.getTriggername());
        holder.textView_des.setText(add_action_model.getTriggerdescrption());
        holder.rly_trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogs.showConfigurations(context,add_action_model);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView_trigger;
        TextView textView_des;
        RelativeLayout rly_trigger;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_trigger=itemView.findViewById(R.id.tv_trigggers);
            textView_des=itemView.findViewById(R.id.tv_descriptiontriggers);

            rly_trigger=itemView.findViewById(R.id.rly_view);

        }
    }
}
