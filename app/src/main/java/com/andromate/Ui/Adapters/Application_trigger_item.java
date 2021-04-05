package com.andromate.Ui.Adapters;

import android.app.Dialog;
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

import com.andromate.CustomColors;
import com.andromate.Model.TriggerItemModel;
import com.andromate.R;

import java.util.List;

public class Application_trigger_item extends RecyclerView.Adapter<Application_trigger_item.ViewHolder> {

    private Context context;
    private List<TriggerItemModel> arrayList;

    public Application_trigger_item(Context context, List<TriggerItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Application_trigger_item.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.triggeritem_ui, parent, false);
        return new Application_trigger_item.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Application_trigger_item.ViewHolder holder, final int position) {
            TriggerItemModel triggerItemModel=arrayList.get(position);

            holder.imageView.setImageResource(triggerItemModel.getImage());
            holder.imageView.setColorFilter(CustomColors.white);
            holder.tv_title.setText(triggerItemModel.getTitle());


            holder.lly_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.tv_title.getText().toString().equals("App Install/Remove/\nupdate")){
                       showDialogtrigger();
                    }else{
                        Toast.makeText(context, "Nooooo", Toast.LENGTH_SHORT).show();
                    }
                }
            });


    }

    private void showDialogtrigger() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alerdailog_opttion_app_installed);
        ;
        dialog.show();
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
