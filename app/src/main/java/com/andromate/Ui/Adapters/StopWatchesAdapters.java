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
import com.andromate.Model.StopWatches_Model;
import com.andromate.R;

import java.util.List;

public class StopWatchesAdapters extends RecyclerView.Adapter<StopWatchesAdapters.ViewHolder> {

    private Context context;
    private List<StopWatches_Model> arrayList;

    public StopWatchesAdapters(Context context, List<StopWatches_Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public StopWatchesAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stopwatches_item_ui, parent, false);
        return new StopWatchesAdapters.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final StopWatchesAdapters.ViewHolder holder, final int position) {
        StopWatches_Model stopWatches_model=arrayList.get(position);




    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);





        }
    }
}
