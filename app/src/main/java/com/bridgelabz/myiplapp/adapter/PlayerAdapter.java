package com.bridgelabz.myiplapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bridgelabz.myiplapp.fragment.PlayerFragment;
import com.bridgelabz.myiplapp.view_model.PlayerViewModel;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 28/9/16.
 */
public class PlayerAdapter extends FragmentPagerAdapter
{
    ArrayList<PlayerFragment> mFragmentList = new ArrayList<>();
    ArrayList<PlayerViewModel> mPlayerList;

    //to count number of tabs
    int mTabCount;

    //constructor
    public PlayerAdapter(FragmentManager fm, ArrayList<PlayerViewModel> playerList)
    {
        super(fm);
        mPlayerList = playerList;
        mTabCount = playerList.size();

        //add fragments to ArrayList
        for(int i=0; i<mTabCount; i++)
            mFragmentList.add(PlayerFragment.getInstance(playerList.get(i)));
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return mTabCount;
    }

}
