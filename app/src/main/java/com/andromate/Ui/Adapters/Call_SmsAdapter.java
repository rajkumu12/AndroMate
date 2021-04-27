package com.andromate.Ui.Adapters;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andromate.Constraints.Dialogs;
import com.andromate.CustomColors;
import com.andromate.Model.ContactModel;
import com.andromate.Model.TriggerItemModel;
import com.andromate.Model.Triggerlistmodel;
import com.andromate.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

public class Call_SmsAdapter extends RecyclerView.Adapter<Call_SmsAdapter.ViewHolder> {

    private Context context;
    private List<TriggerItemModel> arrayList;
    ApplicationlistAdapters triggerItemsAdapters;
    ProgressDialog progressDialog;
    String triggername;
    String triggerdesc;
    Triggerlistmodel triggerlistmodel;
    List<ContactModel> contactModelList;
    SharedPreferences sharedpreferences;
    public static String TAG = "Call_SmsAdapter";

    public Call_SmsAdapter(Context context) {
        this.context = context;
    }

    public Call_SmsAdapter(Context context, List<TriggerItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        sharedpreferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public Call_SmsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.triggeritem_ui, parent, false);
        triggerlistmodel = new Triggerlistmodel();
        return new Call_SmsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Call_SmsAdapter.ViewHolder holder, final int position) {
        TriggerItemModel triggerItemModel = arrayList.get(position);

        holder.imageView.setImageResource(triggerItemModel.getImage());
        holder.imageView.setColorFilter(CustomColors.white);
        holder.tv_title.setText(triggerItemModel.getTitle());


        holder.lly_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.tv_title.getText().toString().equals("Call Active")) {
                    triggerlistmodel.setTriggername(holder.tv_title.getText().toString());
                    Dexter.withContext(context)
                            .withPermissions(
                                    Manifest.permission.READ_CONTACTS
                            ).withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            getContactlist(context,triggerlistmodel,holder.tv_title.getText().toString());
                        }
                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
                    }).check();
                } else if (holder.tv_title.getText().toString().equals("Call Ended")) {
                    triggerlistmodel.setTriggername(holder.tv_title.getText().toString());
                    Dialogs.show_call_ended(context, triggerlistmodel);

                }else if (holder.tv_title.getText().toString().equals("Call Incoming")) {
                    triggerlistmodel.setTriggername(holder.tv_title.getText().toString());
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("call", triggerItemModel.getTitle());
                   /* Log.d("hfjdfjffff","jlhfjkdhfjkdfhkjf"+applicationsInfo.getPname());*/
                    editor.commit();
                    Dialogs.show_call_ended(context, triggerlistmodel);

                }else if (holder.tv_title.getText().toString().equals("Call Missed")) {
                    triggerlistmodel.setTriggername(holder.tv_title.getText().toString());
                    Dexter.withContext(context)
                            .withPermissions(
                                    Manifest.permission.READ_CONTACTS
                            ).withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            getContactlist(context,triggerlistmodel,holder.tv_title.getText().toString());
                        }
                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
                    }).check();

                }else if (holder.tv_title.getText().toString().equals("Call Outgoing")) {
                    triggerlistmodel.setTriggername(holder.tv_title.getText().toString());
                    Dexter.withContext(context)
                            .withPermissions(
                                    Manifest.permission.READ_CONTACTS
                            ).withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            getContactlist(context,triggerlistmodel,holder.tv_title.getText().toString());
                        }
                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
                    }).check();
                }else if (holder.tv_title.getText().toString().equals("Dial Phone Number")) {
                    Dialogs.Dialnumber(context,triggerlistmodel);
                }else if (holder.tv_title.getText().toString().equals("SMS Received")) {
                    triggerlistmodel.setTriggername(holder.tv_title.getText().toString());
                    Dialogs.show_call_ended(context, triggerlistmodel);
                }
            }
        });
    }
    public void getContactlist(Context context,Triggerlistmodel triggerlistmodel,String heading) {
        ContentResolver cr = this.context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY + " ASC");
        /*    ContactCount = cur.getCount();*/
        if (cur.getCount() > 0) {
            contactModelList = new ArrayList<>();
            while (cur.moveToNext()) {
                ContactModel contactModel = new ContactModel();
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));

                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                Log.d(TAG, "hhfhfhfhf" + name);
                contactModel.setName(name);
                String phone = null;
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    System.out.println("name : " + name + ", ID : " + id);
                    // get the phone number
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    contactModel.setNumber(phone);
                    contactModelList.add(contactModel);
                    /*while (pCur.moveToNext()) {
                        System.out.println("phone" + phone);
                    }*/
                    pCur.close();
                }
            /*    if (phone == "" || name == "" || name.equals(phone)) {
                    if (phone.equals(""))
                        contactModel.setName("No Name");
                    contactModel.setNumber(phone);
                    contactModelList.add(contactModel);
                    if (name.equals("") || name.equals(phone))
                        contactModel.setName("No Name");
                    contactModel.setNumber(phone);
                    contactModelList.add(contactModel);

                } else {
                    if (TextUtils.equals(phone, null)) {
                        contactModel.setName(name);
                        contactModel.setNumber("No Number");
                        contactModelList.add(contactModel);
                    } else {
                        if (TextUtils.equals(phone, null)) {
                            contactModel.setName(name);
                            contactModel.setNumber(phone);
                            contactModelList.add(contactModel);
                    }
                }*/

            }

            Dialogs.showContacts(this.context, triggerlistmodel, contactModelList,heading);
        }

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
            imageView = itemView.findViewById(R.id.item_image);
            tv_title = itemView.findViewById(R.id.item_text);
            lly_view = itemView.findViewById(R.id.lly_view);
        }
    }
}
