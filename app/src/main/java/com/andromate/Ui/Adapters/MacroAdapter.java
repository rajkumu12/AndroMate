package com.andromate.Ui.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.andromate.Ui.Fragments.ActionFrag;
import com.andromate.Ui.Fragments.ConstraintsFrag;
import com.andromate.Ui.Fragments.Latest_Frag;
import com.andromate.Ui.Fragments.TopUsers;
import com.andromate.Ui.Fragments.Top_Rated_Frag;
import com.andromate.Ui.Fragments.Top_new_Frag;
import com.andromate.Ui.Fragments.TriggerFragments;

public class MacroAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MacroAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TriggerFragments triggerFragments = new TriggerFragments();
                return triggerFragments;
            case 1:
                ActionFrag actionFrag = new ActionFrag();
                return actionFrag;
            case 2:
                ConstraintsFrag constraintsFrag = new ConstraintsFrag();
                return constraintsFrag;
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