package com.andromate.Ui.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.andromate.R;
import com.andromate.Ui.Activity.AddMacroActivity;
import com.andromate.Ui.Activity.AddMacroActivity_second;
import com.andromate.Ui.Activity.ExportInport_Activity;
import com.andromate.Ui.Activity.StopawatchActivity;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragments extends Fragment implements View.OnClickListener {

    LinearLayout lly_add_macro;
    LinearLayout lly_stopwatch;
    LinearLayout lly_export_import;
    LinearLayout lly_macro_wizard;

    public HomeFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment HomeFragments.
     *
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_home_fragments, container, false);

        lly_add_macro=view.findViewById(R.id.lly_add_macro);
        lly_stopwatch=view.findViewById(R.id.lly_stopwatch);
        lly_export_import=view.findViewById(R.id.lly_expo_impo);
        lly_macro_wizard=view.findViewById(R.id.lly_addmacrowizard);


        lly_add_macro.setOnClickListener(this);
        lly_stopwatch.setOnClickListener(this);
        lly_export_import.setOnClickListener(this);
        lly_macro_wizard.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        if (id==R.id.lly_add_macro){
            startActivity(new Intent(getContext(), AddMacroActivity.class));
        }else if (id==R.id.lly_stopwatch){
            startActivity(new Intent(getContext(), StopawatchActivity.class));
        }else if (id==R.id.lly_expo_impo){
            startActivity(new Intent(getContext(), ExportInport_Activity.class));
        }else if (id==R.id.lly_addmacrowizard){
            startActivity(new Intent(getContext(), AddMacroActivity_second.class));
        }
    }
}