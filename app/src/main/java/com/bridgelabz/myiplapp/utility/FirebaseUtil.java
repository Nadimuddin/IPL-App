package com.bridgelabz.myiplapp.utility;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bridgelabz.myiplapp.interfaces.UpdatePlayerAdapter;
import com.bridgelabz.myiplapp.interfaces.UpdateTeamAdapter;
import com.bridgelabz.myiplapp.database.DatabaseUtil;
import com.bridgelabz.myiplapp.data_model.PlayerModel;
import com.bridgelabz.myiplapp.data_model.TeamModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 3/10/16.
 */
public class FirebaseUtil
{
    private static final String TAG = "FirebaseUtil";

    ArrayList<TeamModel> mTeamList;
    ArrayList<PlayerModel> mPlayerList;
    Context mContext;

    public FirebaseUtil(Context context)
    {
        mContext = context;
        mTeamList = new ArrayList<>();
        mPlayerList = new ArrayList<>();
    }

    public void getFirebaseData(final UpdateTeamAdapter updateTeam, final UpdatePlayerAdapter updatePlayer, final String key)
    {
        //get instance of firebase database project
        FirebaseDatabase firebaseDB = FirebaseDatabase.getInstance();

        //get reference of team_info child node
        DatabaseReference reference = firebaseDB.getReference(key);

        //set value event listener for firebase reference
        reference.addValueEventListener(new ValueEventListener()
        {
            DatabaseUtil database;
            /*
             * onDataChange method to read a static snapshot of the contents at given JSON object
             * This method is triggered once when the listener is attached
             * and again every time the data changes.
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(updatePlayer == null)
                {
                    //this is for indicating firebase what type of data we want
                    GenericTypeIndicator<ArrayList<TeamModel>> type =
                            new GenericTypeIndicator<ArrayList<TeamModel>>() {};

                    //get data from Firebase into model class
                    mTeamList.addAll(dataSnapshot.getValue(type));
                    Log.d(TAG, "onDataChange: data downloaded for firebase");

                    updateTeam.updateAdapter(mTeamList);

                    database = new DatabaseUtil(mContext, key);
                    if(database.insertDataForTeam(mTeamList) == -1)
                        Toast.makeText(mContext, "Data not inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(mContext, "Data inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    GenericTypeIndicator<ArrayList<PlayerModel>> type =
                            new GenericTypeIndicator<ArrayList<PlayerModel>>() {};

                    mPlayerList.addAll(dataSnapshot.getValue(type));

                    updatePlayer.updateAdapter(mPlayerList);

                    database = new DatabaseUtil(mContext, key);
                    if(database.insertDataForPlayer(mPlayerList) == -1)
                        Toast.makeText(mContext, "Data not inserted", Toast.LENGTH_SHORT).show();
                }

                database = new DatabaseUtil(mContext, key);
                if(database.insertDataForTeam(mTeamList) == -1)
                    Toast.makeText(mContext, "Data not inserted", Toast.LENGTH_SHORT).show();
            }

            //this will called when error occur while getting data from firebase
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
            }
        });
    }
}