package com.andromate.Ui.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andromate.R;
import com.andromate.Ui.Adapters.MyAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class TemplatesFragments extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    public TemplatesFragments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_templates_fragments, container, false);

        tabLayout=view.findViewById(R.id.tablayout);
        viewPager=view.findViewById(R.id.viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("Top New"));
        tabLayout.addTab(tabLayout.newTab().setText("Top Rated"));
        tabLayout.addTab(tabLayout.newTab().setText("Latest"));
        tabLayout.addTab(tabLayout.newTab().setText("Top Users"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(getContext(),getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }
}