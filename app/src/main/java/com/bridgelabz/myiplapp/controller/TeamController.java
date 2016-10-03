package com.bridgelabz.myiplapp.controller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.bridgelabz.myiplapp.database.DatabaseUtil;
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
    TeamModel mTeamModel;
    DatabaseUtil DBUtil;
    ArrayList<TeamModel> mArrayList;
    Context mContext;
    FirebaseUtil FBaseUtil;
    String PREF_KEY = "TEAM";

    public TeamController(Context context)
    {
        mContext = context;
    }

    public ArrayList<TeamModel> getData(UpdateAdapter update)
    {
        SavePreference pref = new SavePreference(mContext);
        String str = pref.getPreference(PREF_KEY);
        mArrayList = new ArrayList<>();
        if(str == null || str.equals("not saved"))
        {
            Log.d(TAG, "getData: Data not saved");
            FBaseUtil = new FirebaseUtil(mContext);
            FBaseUtil.getFirebaseData(update);
            pref.setPreferences(PREF_KEY, "data saved");
        }
        else if(str.equals("data saved"))
        {
            Log.d(TAG, "getData: Data saved getting from local");
            DBUtil = new DatabaseUtil(mContext);
            Cursor cursor = DBUtil.retrieveData();
            while (cursor.moveToNext())
            {
                mArrayList.add(new TeamModel(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
            }
        }


        return mArrayList;
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