package com.andromate.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.andromate.R;

public class AddMacroActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView_back;
    LinearLayout lly_trigger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_macro);

        imageView_back=findViewById(R.id.back_icon_addmacros);
        lly_trigger=findViewById(R.id.lly_trigger);
        imageView_back.setOnClickListener(this);
        lly_trigger.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.back_icon_addmacros){
            finish();
        }else if (id==R.id.lly_trigger){
            startActivity(new Intent(AddMacroActivity.this,Add_triggersActivity.class));
        }
    }
}