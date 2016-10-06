package com.bridgelabz.myiplapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bridgelabz.myiplapp.adapter.PagerAdapter;
import com.bridgelabz.myiplapp.controller.PlayerController;
import com.bridgelabz.myiplapp.interfaces.UpdatePlayerAdapter;
import com.bridgelabz.myiplapp.data_model.PlayerModel;
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
public class PlayerActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener
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
        controller.getData(new UpdatePlayerAdapter() {
            @Override
            public void updateAdapter(ArrayList<PlayerModel> arrayList) {
                PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), arrayList);
                mViewPager.setAdapter(adapter);
            }
        }, key);
        //getFirebaseData(key);
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

    private void getFirebaseData(String key)
    {
        //get instance of firebase database object
        FirebaseDatabase firebaseDB = FirebaseDatabase.getInstance();

        //get reference of child node
        DatabaseReference reference = firebaseDB.getReference(key);

        //reference.keepSynced(true);

        //set value event listener for firebase reference
        reference.addValueEventListener(new ValueEventListener() {

            /*
             * onDataChange method to read a static snapshot of the contents at given JSON object
             * This method is triggered once when the listener is attached
             * and again every time the data changes.
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                //this is for indicating firebase what type of data we want
                GenericTypeIndicator<ArrayList<PlayerModel>> type =
                        new GenericTypeIndicator<ArrayList<PlayerModel>>() {};

                //get data from Firebase into model class
                ArrayList<PlayerModel> arrayList = dataSnapshot.getValue(type);

                PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), arrayList);
                mViewPager.setAdapter(adapter);

                //setup view pager
                mTabLayout.setupWithViewPager(mViewPager);
            }

            //this method will called when error occur while getting data from firebase
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
