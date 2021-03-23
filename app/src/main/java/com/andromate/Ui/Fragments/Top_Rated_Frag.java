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
import com.andromate.Ui.Activity.StopawatchActivity;
import com.andromate.Ui.Adapters.StopWatchesAdapters;
import com.andromate.Ui.Adapters.TopRatedAdapters;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Top_Rated_Frag extends Fragment {


    List<Top_Rated_Model>topratedlist;
    RecyclerView recy_toprated;

    public Top_Rated_Frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Top_Rated_Frag.
     */




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_top__rated_, container, false);

        recy_toprated=view.findViewById(R.id.recy_toprated);

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