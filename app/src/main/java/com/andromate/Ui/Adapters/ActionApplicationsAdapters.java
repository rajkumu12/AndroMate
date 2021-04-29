package com.andromate.Ui.Adapters;

import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andromate.Constraints.DialogsActions;
import com.andromate.CustomColors;
import com.andromate.Model.ActionModelList;
import com.andromate.Model.TriggerItemModel;
import com.andromate.Model.Triggerlistmodel;
import com.andromate.R;

import java.util.List;

public class ActionApplicationsAdapters extends RecyclerView.Adapter<ActionApplicationsAdapters.ViewHolder> {

    private Context context;
    private List<TriggerItemModel> arrayList;
    ActionModelList triggerlistmodel;
    public ActionApplicationsAdapters(Context context, List<TriggerItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ActionApplicationsAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.triggeritem_ui, parent, false);
        triggerlistmodel = new ActionModelList();
        return new ActionApplicationsAdapters.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ActionApplicationsAdapters.ViewHolder holder, final int position) {
            TriggerItemModel triggerItemModel=arrayList.get(position);

            holder.imageView.setImageResource(triggerItemModel.getImage());
            holder.imageView.setColorFilter(CustomColors.white);
            holder.tv_title.setText(triggerItemModel.getTitle());

            holder.lly_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.tv_title.getText().toString().equals("App Enable/Disable")){
                        DialogsActions.showactionApplication(context,triggerlistmodel);

                    }else if (holder.tv_title.getText().toString().equals("Clear App Data")){
                        DialogsActions.show_clear_data(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Kill Application")){
                        Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT).show();
                    }else if (holder.tv_title.getText().toString().equals("Kill Background\nProcess")){
                        DialogsActions.show_kill_background(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Launch Application")){
                        DialogsActions.show_launch_option(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Launch Shortcut")){
                        DialogsActions.loadApps_shortcut(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Launch and Press")){
                        Toast.makeText(context, "message: "+holder.tv_title.getText().toString(), Toast.LENGTH_SHORT).show();
                    }else if (holder.tv_title.getText().toString().equals("Locale/Tasker Plugin")){
                        DialogsActions.logAllTaskerTasks(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Open Website/HTTP\nGET")){
                        Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT).show();
                    }else if (holder.tv_title.getText().toString().equals("Shell Script")){
                        Toast.makeText(context, "Work is in progress", Toast.LENGTH_SHORT).show();
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
