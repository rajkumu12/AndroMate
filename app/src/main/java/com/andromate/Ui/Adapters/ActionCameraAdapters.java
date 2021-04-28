package com.andromate.Ui.Adapters;

import android.Manifest;
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

import com.andromate.Constraints.DialogsActions;
import com.andromate.Constraints.Pemisssions;
import com.andromate.CustomColors;
import com.andromate.Model.ActionModelList;
import com.andromate.Model.TriggerItemModel;
import com.andromate.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.security.Permission;
import java.util.List;

public class ActionCameraAdapters extends RecyclerView.Adapter<ActionCameraAdapters.ViewHolder> {

    private Context context;
    private List<TriggerItemModel> arrayList;
    ActionModelList triggerlistmodel;
    public ActionCameraAdapters(Context context, List<TriggerItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ActionCameraAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.triggeritem_ui, parent, false);
        triggerlistmodel = new ActionModelList();
        return new ActionCameraAdapters.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ActionCameraAdapters.ViewHolder holder, final int position) {
            TriggerItemModel triggerItemModel=arrayList.get(position);

            holder.imageView.setImageResource(triggerItemModel.getImage());
            holder.imageView.setColorFilter(CustomColors.white);
            holder.tv_title.setText(triggerItemModel.getTitle());
            holder.lly_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.tv_title.getText().toString().equals("Camera Enable/\n" +
                            "Disable")){
                        DialogsActions.show_declairationCamera(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Open Last Photo")){

                        Dexter.withContext(context)
                                .withPermissions(
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                ).withListener(new MultiplePermissionsListener() {
                            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}
                            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
                        }).check();

                        DialogsActions.show_dialog_photoselector(context,triggerlistmodel);


                    }else if (holder.tv_title.getText().toString().equals("Share Last Photo")){
                        DialogsActions.show_dialog_share_lastphoto(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Take Picture")){
                        Dexter.withContext(context)
                                .withPermissions(
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                ).withListener(new MultiplePermissionsListener() {
                            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}
                            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
                        }).check();

                        DialogsActions.show_dialog_takephoto(context,triggerlistmodel);
                    }else if (holder.tv_title.getText().toString().equals("Take Screenshot")){


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
