package com.andromate.Ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andromate.Model.Add_Action_model;
import com.andromate.Model.MacroDetailModel;
import com.andromate.Model.MacroModel;
import com.andromate.R;

import java.util.List;

public class MacroItemsAdapter extends RecyclerView.Adapter<MacroItemsAdapter.ViewHolder> {

    private Context context;
    private List<MacroDetailModel> arrayList;

    public MacroItemsAdapter(Context context, List<MacroDetailModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MacroItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listmacro_ui, parent, false);
        return new MacroItemsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MacroItemsAdapter.ViewHolder holder, final int position) {
        MacroDetailModel macroModel=arrayList.get(position);

        holder.textView_category.setText(macroModel.getCategory());
        holder.tv_mac_name.setText(macroModel.getMacroname());
        holder.tv_mac_time.setText(macroModel.getActivetime());
        holder.tv_triggername.setText("Triggers:-"+macroModel.getTriggername());
        holder.tv_action.setText("Action:-"+macroModel.getActionname());
        holder.tv_constraints.setText("Constraints:-"+macroModel.getCons_name());





    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView textView_category,tv_mac_name,tv_mac_time,tv_triggername,tv_action,tv_constraints;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_category=itemView.findViewById(R.id.tv_category);
            tv_mac_name=itemView.findViewById(R.id.tv_macroname);
            tv_mac_time=itemView.findViewById(R.id.time);
            tv_triggername=itemView.findViewById(R.id.tv_triggers);
            tv_action=itemView.findViewById(R.id.tv_action);
            tv_constraints=itemView.findViewById(R.id.tv_constraints);





        }
    }
}

