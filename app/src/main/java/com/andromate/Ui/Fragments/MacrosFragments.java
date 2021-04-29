package com.andromate.Ui.Fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andromate.Model.MacroModel;
import com.andromate.R;
import com.andromate.Ui.Activity.Add_triggersActivity;
import com.andromate.Ui.Adapters.MacroItemsAdapter;
import com.andromate.Ui.Adapters.TriggerItemsAdapters;
import com.andromate.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MacrosFragments extends Fragment {

    DBHelper dbHelper;

    List<MacroModel>arralist;
    RecyclerView macrolist_recy;
    public MacrosFragments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_macros_fragments, container, false);
        macrolist_recy=view.findViewById(R.id.reclerview_macro_list);

        dbHelper=new DBHelper(getContext());

     /*  Log.d("ljhfjkdhfjd","jdddd"+dbHelper.getAllDataMacro());

        arralist = new ArrayList<>();
        Cursor curse=dbHelper.getAllDataMacro();

        // moving our cursor to first position.
        if (curse.moveToFirst()) {
            do {
                MacroModel macroModel=new MacroModel();
                macroModel.setMacro_name(curse.getString(1));
                macroModel.setMacro_des(curse.getString(2));
               *//* Log.d("jffjjfjfj","hh"+curse.getString(0));
                Log.d("jffjjfjfj","hh"+curse.getString(1));
                Log.d("jffjjfjfj","hh"+curse.getString(2));*//*
                Log.d("jffjjfjfj","hh"+curse.getString(5));
                // on below line we are adding the data from cursor to our array list.
                arralist.add(macroModel);

            } while (curse.moveToNext());
            *//*Log.d("jffjjfjfj","hh"+arralist.size());*//*
            MacroItemsAdapter triggerItemsAdapters=new MacroItemsAdapter(getContext(),arralist);
            LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
            macrolist_recy.setLayoutManager(layoutManager2);
                            *//*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*//*
            macrolist_recy.setItemAnimator(new DefaultItemAnimator());
            macrolist_recy.setAdapter(triggerItemsAdapters);

            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        curse.close();*/

        return view;
    }
}