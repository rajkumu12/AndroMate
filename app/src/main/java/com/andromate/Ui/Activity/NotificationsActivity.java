package com.andromate.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.andromate.Model.NotificationsModel;
import com.andromate.R;
import com.andromate.Ui.Adapters.NotificationsAdapters;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {
    RecyclerView recyclerView_notifications;
    private ArrayList<NotificationsModel> notificationslist;
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_notifications);

        recyclerView_notifications=findViewById(R.id.recy_notifications);

        img_back=findViewById(R.id.back_icon_id);

        loadNotifications();



        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadNotifications() {
        notificationslist=new ArrayList<>();
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));
        notificationslist.add(new NotificationsModel("one"));

        NotificationsAdapters notificationsAdapters = new NotificationsAdapters(NotificationsActivity.this,notificationslist);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(NotificationsActivity.this);
        recyclerView_notifications.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_notifications.setItemAnimator(new DefaultItemAnimator());
        recyclerView_notifications.setAdapter(notificationsAdapters);


    }
}