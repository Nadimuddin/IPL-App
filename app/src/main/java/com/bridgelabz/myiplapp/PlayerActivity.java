package com.bridgelabz.myiplapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bridgelabz.myiplapp.adapter.PlayerAdapter;
import com.bridgelabz.myiplapp.controller.PlayerController;
import com.bridgelabz.myiplapp.data_model.PlayerDataModel;
import com.bridgelabz.myiplapp.interfaces.UpdatePlayerAdapter;
import com.bridgelabz.myiplapp.view_model.PlayerViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 27/9/16.
 */
public class PlayerActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener,
        UpdatePlayerAdapter
{
    final String VALUE = "IPL_TEAM";
    TabLayout mTabLayout;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_container);

        //get intent i.e. team name
        String string = getIntent().getExtras().getString(VALUE);
        String key = string.replaceAll("\\s+","");

        /*
         * get XML layout
         */
        mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
        mViewPager = (ViewPager)findViewById(R.id.viewPager);

        PlayerController controller = new PlayerController(this);
        controller.getData(this, key);
    }

    @Override
    public void updateAdapter(ArrayList<PlayerViewModel> arrayList)
    {
        //initialize adapter
        PlayerAdapter adapter = new PlayerAdapter(getSupportFragmentManager(), arrayList);

        //set adapter on ViewPager
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab)
    {
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab)
    {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab)
    {
    }
}
