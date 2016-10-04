package com.bridgelabz.myiplapp.controller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.bridgelabz.myiplapp.database.DatabaseUtil;
import com.bridgelabz.myiplapp.interfaces.UpdateAdapter;
import com.bridgelabz.myiplapp.model.TeamModel;
import com.bridgelabz.myiplapp.preference.SavePreference;
import com.bridgelabz.myiplapp.utility.FirebaseUtil;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 1/10/16.
 */
public class TeamController
{
    public static final String TAG = "TeamController";
    Context mContext;

    //key for storing preference
    private static final String PREF_KEY = "TEAM";

    //constructor
    public TeamController(Context context)
    {
        mContext = context;
    }

    //get data from firebase or local
    public void getData(UpdateAdapter update)
    {
        //initializing object of SharedPreference
        SavePreference pref = new SavePreference(mContext);

        //get preference for the key
        String str = pref.getPreference(PREF_KEY);

        //if data is not present in local database the fetch data from firebase
        if(str == null || !str.equals("data saved in local"))
        {
            Log.d(TAG, "getData: Data not saved");

            //initialing FireBaseUtil class
            FirebaseUtil fireBaseUtil = new FirebaseUtil(mContext);

            //get data from firebase
            fireBaseUtil.getFirebaseData(update);

            /*
             *set preference that data is saved in local
             *so that next time data will be load from local database
             */
            pref.setPreferences(PREF_KEY, "data saved in local");
            pref.getPreference(PREF_KEY);
        }
        else if(str.equals("data saved in local"))
        {
            //initializing array list of TeamModel
            ArrayList<TeamModel> arrayList = new ArrayList<>();

            //initializing DatabaseUtil class
            DatabaseUtil dataBaseUtil = new DatabaseUtil(mContext);

            //retrieving data from local database
            Cursor cursor = dataBaseUtil.retrieveData();
            while (cursor.moveToNext())
            {
                arrayList.add(new TeamModel(cursor.getString(0),
                                            cursor.getString(1),
                                            cursor.getString(2),
                                            cursor.getString(3),
                                            cursor.getString(4),
                                            cursor.getString(5),
                                            cursor.getString(6)));
            }
            update.updateAdapter(arrayList);
        }
    }

    /*public String getTeamName()
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
    }*/
}