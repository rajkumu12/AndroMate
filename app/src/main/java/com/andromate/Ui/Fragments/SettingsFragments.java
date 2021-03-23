package com.andromate.Ui.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andromate.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SettingsFragments extends Fragment {



    public SettingsFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment SettingsFragments.
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_settings_fragments, container, false);


        return view;
    }
}