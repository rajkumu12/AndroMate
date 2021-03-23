package com.andromate.Ui.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.andromate.Ui.Fragments.Latest_Frag;
import com.andromate.Ui.Fragments.TopUsers;
import com.andromate.Ui.Fragments.Top_Rated_Frag;
import com.andromate.Ui.Fragments.Top_new_Frag;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Top_new_Frag top_new_frag = new Top_new_Frag();
                return top_new_frag;
            case 1:
                Top_Rated_Frag top_rated_frag = new Top_Rated_Frag();
                return top_rated_frag;
            case 2:
                Latest_Frag latest_frag = new Latest_Frag();

                return latest_frag;
            case 3:
                TopUsers topUsers = new TopUsers();
                return topUsers;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}