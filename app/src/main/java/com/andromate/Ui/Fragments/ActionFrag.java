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
public class ActionFrag extends Fragment {



    public ActionFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_action, container, false);

        return view;
    }
}