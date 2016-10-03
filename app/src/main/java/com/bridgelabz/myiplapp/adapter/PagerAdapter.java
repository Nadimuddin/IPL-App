package com.bridgelabz.myiplapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bridgelabz.myiplapp.fragment.PlayerFragment;
import com.bridgelabz.myiplapp.model.PlayerModel;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 28/9/16.
 */
public class PagerAdapter extends FragmentPagerAdapter
{
    ArrayList<PlayerFragment> mFragmentList = new ArrayList<>();
    ArrayList<PlayerModel> mPlayerList;
    int mTabCount;
    public PagerAdapter(FragmentManager fm, ArrayList<PlayerModel> playerList)
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
