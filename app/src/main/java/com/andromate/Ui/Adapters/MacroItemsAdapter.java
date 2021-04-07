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
import com.andromate.Model.MacroModel;
import com.andromate.R;

import java.util.List;

public class MacroItemsAdapter extends RecyclerView.Adapter<MacroItemsAdapter.ViewHolder> {

    private Context context;
    private List<MacroModel> arrayList;

    public MacroItemsAdapter(Context context, List<MacroModel> arrayList) {
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
        MacroModel macroModel=arrayList.get(position);




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

