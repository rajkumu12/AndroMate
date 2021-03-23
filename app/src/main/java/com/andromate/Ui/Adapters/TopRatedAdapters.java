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
import com.andromate.Model.Top_Rated_Model;
import com.andromate.R;

import java.util.List;

public class TopRatedAdapters extends RecyclerView.Adapter<TopRatedAdapters.ViewHolder> {

    private Context context;
    private List<Top_Rated_Model> arrayList;

    public TopRatedAdapters(Context context, List<Top_Rated_Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public TopRatedAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.toprated_item_ui, parent, false);
        return new TopRatedAdapters.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final TopRatedAdapters.ViewHolder holder, final int position) {



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
