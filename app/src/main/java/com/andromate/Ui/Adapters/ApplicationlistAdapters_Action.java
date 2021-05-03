package com.andromate.Ui.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andromate.Model.ActionModelList;
import com.andromate.Model.ApplicationsInfo;
import com.andromate.Model.Triggerlistmodel;
import com.andromate.R;
import com.andromate.Ui.Activity.AddMacroActivity;

import java.util.ArrayList;
import java.util.List;

public class ApplicationlistAdapters_Action extends RecyclerView.Adapter<ApplicationlistAdapters_Action.ViewHolder> implements Filterable {

    private Context context;
    private List<ApplicationsInfo> arrayList;
    private List<ApplicationsInfo> copylist;
    ActionModelList triggerlistmodel;
    SharedPreferences sharedpreferences;
    String s;
    public ApplicationlistAdapters_Action(Context context, List<ApplicationsInfo> arrayList,String s) {
        this.context = context;
        this.arrayList = arrayList;
        this.copylist = arrayList;
        this.s=s;
        sharedpreferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
    }
    @NonNull
    @Override
    public ApplicationlistAdapters_Action.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.applist_ui, parent, false);
        triggerlistmodel=new ActionModelList();
        return new ApplicationlistAdapters_Action.ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull final ApplicationlistAdapters_Action.ViewHolder holder, final int position) {
        ApplicationsInfo applicationsInfo=arrayList.get(position);

        holder.tv_appname.setText(applicationsInfo.getAppname());
        holder.image_appicon.setImageDrawable(applicationsInfo.getIcon());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    triggerlistmodel.setActioname(s);
                    triggerlistmodel.setActionDescription(applicationsInfo.getPname());
                    AddMacroActivity.actionlist.add(triggerlistmodel);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("key", applicationsInfo.getPname());
                    Log.d("hfjdfjffff","jlhfjkdhfjkdfhkjf"+applicationsInfo.getPname());
                    editor.commit();
                }else {
                    triggerlistmodel.setActioname(applicationsInfo.getAppname());
                    AddMacroActivity.actionlist.remove(triggerlistmodel);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



        public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_appicon;
        TextView tv_appname;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_appicon=itemView.findViewById(R.id.app_icon);
            tv_appname=itemView.findViewById(R.id.tv_text_appname);
            checkBox=itemView.findViewById(R.id.ch1);


        }
    }


        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    String charString = constraint.toString();

                    if (charString.isEmpty()){
                        arrayList = copylist;
                    }else{

                        List<ApplicationsInfo> filterList = new ArrayList<>();

                        for (ApplicationsInfo data : copylist){

                            if (data.getAppname().toLowerCase().contains(charString)){
                                filterList.add(data);
                            }
                        }

                        arrayList = filterList;

                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = arrayList;

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    arrayList = (List<ApplicationsInfo>) results.values;
                    notifyDataSetChanged();
                }
            };

        }
    }

