package com.bridgelabz.myiplapp.utility;

import android.content.Context;
import android.util.Log;

import com.bridgelabz.myiplapp.data_model.PlayerDataModel;
import com.bridgelabz.myiplapp.data_model.TeamDataModel;
import com.bridgelabz.myiplapp.interfaces.GetPlayerList;
import com.bridgelabz.myiplapp.interfaces.GetTeamList;
import com.bridgelabz.myiplapp.database.DatabaseUtil;
import com.bridgelabz.myiplapp.interfaces.UpdateTeamAdapter;
import com.bridgelabz.myiplapp.preference.SavePreference;
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

    ArrayList<TeamDataModel> mTeamList;
    ArrayList<PlayerDataModel> mPlayerList;
    ImageUtil mImageUtil;
    SavePreference mPref;
    Context mContext;
    private static final String TEAM_KEY = "Team";
    private static String PLAYER_KEY;

    public FirebaseUtil(Context context)
    {
        mContext = context;
        mTeamList = new ArrayList<>();
        mPlayerList = new ArrayList<>();

        //initializing object of SharedPreference
        mPref = new SavePreference(mContext);
        mImageUtil = new ImageUtil();
    }

    public void getFirebaseData(final String  selector, final GetTeamList getTeamList,
                                final GetPlayerList getPlayerList, final String key)
    {
        //get instance of firebase database project
        FirebaseDatabase firebaseDB = FirebaseDatabase.getInstance();

        //get reference of team_info child node
        DatabaseReference reference = firebaseDB.getReference(key);

        //set value event listener for firebase reference
        reference.addValueEventListener(new ValueEventListener()
        {
            DatabaseUtil database;
            /* onDataChange method to read a static snapshot of the contents at given JSON object
             * This method is triggered once when the listener is attached
             * and again every time the data changes. */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                int count = mPref.getIntPreferences(key+"Count");
                Log.i(TAG, "onDataChange: preference value for"+key+"Count: "+mPref.getIntPreferences(key+"Count"));
                if(count > 0)
                {
                    mPref.setPreferences(key + "Table", "delete table");
                    Log.i(TAG, "onDataChange: "+key+"Table value: "+mPref.getStringPreference(key+"Table"));
                    //Log.i(TAG, "onDataChange: updatePlayer: "+updatePlayer);
                }


                if(selector == "team" || selector == null)
                {
                    //this is for indicating firebase what type of data we want
                    GenericTypeIndicator<ArrayList<TeamDataModel>> type =
                            new GenericTypeIndicator<ArrayList<TeamDataModel>>() {};

                    mTeamList.clear();

                    //get data from Firebase into model class
                    mTeamList.addAll(dataSnapshot.getValue(type));
                    Log.d(TAG, "onDataChange: data downloaded for team"+mTeamList.get(0).getTeamCaptain());

                    getTeamList.getTeamList(mTeamList);

                    database = new DatabaseUtil(mContext, key);

                    /* set preference that data is saved in local
                     * so that next time data will be load from local database  */
                    if(database.insertDataForTeam(mTeamList) != -1)
                        mPref.setPreferences(TEAM_KEY, "data saved in local");
                    else
                        mPref.setPreferences(TEAM_KEY, "data not save");
                }
                else if(selector == "player")
                {
                    GenericTypeIndicator<ArrayList<PlayerDataModel>> type =
                            new GenericTypeIndicator<ArrayList<PlayerDataModel>>() {};

                    mPlayerList.clear();

                    mPlayerList.addAll(dataSnapshot.getValue(type));
                    Log.d(TAG, "onDataChange: data downloaded for player");

                    getPlayerList.getPlayerList(mPlayerList);

                    database = new DatabaseUtil(mContext, key);

                    PLAYER_KEY = key;
                    if(database.insertDataForPlayer(mPlayerList) != -1)
                        mPref.setPreferences(PLAYER_KEY, "data saved in local");
                    else
                        mPref.setPreferences(PLAYER_KEY, "data not save");
                }
                mPref.setPreferences(key+"Count",++count);
            }

            //this will called when error occur while getting data from firebase
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
            }
        });
    }
}