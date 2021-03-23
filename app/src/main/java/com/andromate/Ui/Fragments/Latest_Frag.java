package com.andromate.Ui.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andromate.Model.Top_Rated_Model;
import com.andromate.R;
import com.andromate.Ui.Adapters.TopRatedAdapters;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Latest_Frag extends Fragment {
    List<Top_Rated_Model> topratedlist;
    RecyclerView recy_toprated;

    public Latest_Frag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_latest_, container, false);
        recy_toprated=view.findViewById(R.id.recy_toplatest);

        loadData();

        return view;
    }

    private void loadData() {
        topratedlist=new ArrayList<>();
        topratedlist.add(new Top_Rated_Model("alkjfddjdd"));
        topratedlist.add(new Top_Rated_Model("alkjfddjdd"));
        topratedlist.add(new Top_Rated_Model("alkjfddjdd"));
        topratedlist.add(new Top_Rated_Model("alkjfddjdd"));
        topratedlist.add(new Top_Rated_Model("alkjfddjdd"));
        topratedlist.add(new Top_Rated_Model("alkjfddjdd"));
        topratedlist.add(new Top_Rated_Model("alkjfddjdd"));
        topratedlist.add(new Top_Rated_Model("alkjfddjdd"));
        topratedlist.add(new Top_Rated_Model("alkjfddjdd"));
        topratedlist.add(new Top_Rated_Model("alkjfddjdd"));

        TopRatedAdapters stopWatchesAdapters = new TopRatedAdapters(getContext(),topratedlist);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        recy_toprated.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recy_toprated.setItemAnimator(new DefaultItemAnimator());
        recy_toprated.setAdapter(stopWatchesAdapters);

    }
}