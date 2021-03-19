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

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragments extends Fragment implements View.OnClickListener {

    LinearLayout lly_add_macro;

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


        lly_add_macro.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        if (id==R.id.lly_add_macro){
            startActivity(new Intent(getContext(), AddMacroActivity.class));
        }
    }
}