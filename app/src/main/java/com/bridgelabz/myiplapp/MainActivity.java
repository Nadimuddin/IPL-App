package com.bridgelabz.myiplapp;

/**
 * Created by Nadimuddin on 22/9/16.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bridgelabz.myiplapp.adapter.IPLAdapter;
import com.bridgelabz.myiplapp.database.DatabaseUtil;
import com.bridgelabz.myiplapp.model.TeamModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    RecyclerView mRecyclerView;
    ArrayList<TeamModel> mTeamList;
    IPLAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTeamList =new ArrayList<>();


        setContentView(R.layout.activity_main);

        //getting object of RecyclerView from XML
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        //set Layout for
        setLayoutForRecyclerView();

        adapter = new IPLAdapter(mTeamList);
        mRecyclerView.setAdapter(adapter);

        //get data from Firebase Database
        getFirebaseData();
    }

    //method for setting layout for RecyclerView
    private void setLayoutForRecyclerView()
    {
        //initialize LayoutManager for RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        //set Layout for RecyclerView
        mRecyclerView.setLayoutManager(layoutManager);
    }

    //method to get data from Firebase
    private void getFirebaseData()
    {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //get instance of firebase database project
        FirebaseDatabase firebaseDB = FirebaseDatabase.getInstance();

        //get reference of team_info child node
        DatabaseReference reference = firebaseDB.getReference("team_info");

        reference.keepSynced(true);

        //set value event listener for firebase reference
        reference.addValueEventListener(new ValueEventListener()
        {
            /*
             * onDataChange method to read a static snapshot of the contents at given JSON object
             * This method is triggered once when the listener is attached
             * and again every time the data changes.
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                //this is for indicating firebase what type of data we want
                GenericTypeIndicator<ArrayList<TeamModel>> type =
                        new GenericTypeIndicator<ArrayList<TeamModel>>() {};

                //get data from Firebase into model class
                mTeamList.addAll(dataSnapshot.getValue(type));

                DatabaseUtil database = new DatabaseUtil(MainActivity.this);
                if(database.insertData(mTeamList) == -1)
                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();

                adapter.notifyDataSetChanged();
            }

            //this will called when error occur while getting data from firebase
            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }
}
