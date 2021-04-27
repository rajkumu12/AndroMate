package com.andromate.Ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andromate.Constraints.Dialogs;
import com.andromate.Constraints.DialogsActions;
import com.andromate.Model.ActionModelList;
import com.andromate.Model.Triggerlistmodel;
import com.andromate.R;

import java.util.List;

public class Actionlists_items_Adapter extends RecyclerView.Adapter<Actionlists_items_Adapter.ViewHolder> {

    private Context context;
    private List<ActionModelList> arrayList;

    public Actionlists_items_Adapter(Context context, List<ActionModelList> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Actionlists_items_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tirgger_items_ui_list, parent, false);
        return new Actionlists_items_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Actionlists_items_Adapter.ViewHolder holder, final int position) {
        ActionModelList add_action_model = arrayList.get(position);
        holder.textView_trigger.setText(add_action_model.getActioname());
        holder.textView_des.setText(add_action_model.getActionDescription());
        holder.rly_trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogsActions.showConfigurations(context,add_action_model,add_action_model.getActioname());
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
