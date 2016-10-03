package com.bridgelabz.myiplapp.controller;

import android.content.Context;
import android.widget.Toast;

import com.bridgelabz.myiplapp.database.DatabaseUtil;
import com.bridgelabz.myiplapp.model.TeamModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 1/10/16.
 */
public class TeamController
{
    TeamModel mTeamModel;
    ArrayList<TeamModel> mArrayList;
    Context context;

    private Context getContext()
    {
        return getContext();
    }

    public TeamController(ArrayList<TeamModel> arrayList)
    {
        mArrayList = arrayList;
    }



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
                mArrayList.addAll(dataSnapshot.getValue(type));

                DatabaseUtil database = new DatabaseUtil(getContext());
                if(database.insertData(mArrayList) == -1)
                    Toast.makeText(getContext(), "Data not inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Data inserted", Toast.LENGTH_SHORT).show();

                adapter.notifyDataSetChanged();
            }

            //this will called when error occur while getting data from firebase
            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

    public String getTeamName()
    {
        return mTeamModel.getTeamName();
    }

    public String getTeamCoach()
    {
        return mTeamModel.getTeamCoach();
    }

    public String getCaptain()
    {
        return mTeamModel.getTeamCaptain();
    }

    public String getHomeVenue()
    {
        return mTeamModel.getTeamHomeVenue();
    }

    public String getOwner()
    {
        return mTeamModel.getTeamOwner();
    }
}