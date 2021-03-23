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
import com.andromate.Model.TriggerItemModel;
import com.andromate.R;
import com.andromate.Ui.Activity.AddAction;

import java.util.List;

public class AddActionItemsAdapters extends RecyclerView.Adapter<AddActionItemsAdapters.ViewHolder> {

    private Context context;
    private List<Add_Action_model> arrayList;

    public AddActionItemsAdapters(Context context, List<Add_Action_model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AddActionItemsAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.triggeritem_ui, parent, false);
        return new AddActionItemsAdapters.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddActionItemsAdapters.ViewHolder holder, final int position) {
        Add_Action_model add_action_model=arrayList.get(position);

            holder.imageView.setImageResource(add_action_model.getImage());
            holder.tv_title.setText(add_action_model.getTitle());


    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.item_image);
            tv_title=itemView.findViewById(R.id.item_text);



        }
    }
}
