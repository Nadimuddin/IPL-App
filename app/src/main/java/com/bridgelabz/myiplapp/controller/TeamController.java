package com.bridgelabz.myiplapp.controller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.bridgelabz.myiplapp.database.DatabaseUtil;
import com.bridgelabz.myiplapp.interfaces.GetTeamList;
import com.bridgelabz.myiplapp.interfaces.UpdateTeamAdapter;
import com.bridgelabz.myiplapp.data_model.TeamDataModel;
import com.bridgelabz.myiplapp.preference.SavePreference;
import com.bridgelabz.myiplapp.utility.FirebaseUtil;
import com.bridgelabz.myiplapp.view_model.TeamViewModel;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 1/10/16.
 */
public class TeamController
{
    public static final String TAG = "TeamController";
    Context mContext;
    ArrayList<TeamDataModel> mArrayList;
    UpdateTeamAdapter mUpdateTeam;
    SavePreference mPref;

    //key for getting data from firebase
    String mKey;

    //key for storing preference
    private static final String PREF_KEY = "Team";

    //constructor
    public TeamController(Context context)
    {
        mContext = context;
    }

    //get data from firebase or local
    public void getData(UpdateTeamAdapter update, String key)
    {
        mUpdateTeam = update;
        mKey = key;

        //initializing object of SharedPreference
        mPref = new SavePreference(mContext);

        //get preference for the key
        String str = mPref.getStringPreference(PREF_KEY);


        //if data is not present in local database the fetch data from firebase
        if(str == null || !str.equals("data saved in local"))
            getFromFirebase();
        else if(str.equals("data saved in local"))
            getFromLocalStorage();
    }

    private void getFromFirebase()
    {
        //initializing FireBaseUtil class
        FirebaseUtil fireBaseUtil = new FirebaseUtil(mContext);

        mPref.setPreferences("update", "team");

        //get data from firebase
        fireBaseUtil.getFirebaseData("team", new GetTeamList() {
            @Override
            public void getTeamList(ArrayList<TeamDataModel> arrayList)
            {
                setData(arrayList, mUpdateTeam);
                mArrayList = arrayList;
            }
        }, null,  mKey);
    }

    private void getFromLocalStorage()
    {
        //initializing array list of TeamDataModel
        ArrayList<TeamDataModel> arrayList = new ArrayList<>();

        //initializing DatabaseUtil class
        DatabaseUtil dataBaseUtil = new DatabaseUtil(mContext, mKey);

        //retrieving data from local database
        Cursor cursor = dataBaseUtil.retrieveData(mKey);
        while (cursor.moveToNext())
        {
            arrayList.add(new TeamDataModel(cursor.getString(0),
                                            cursor.getString(1),
                                            cursor.getString(2),
                                            cursor.getString(3),
                                            cursor.getString(4),
                                            cursor.getString(5),
                                            cursor.getString(6)));
        }

        setData(arrayList, mUpdateTeam);
    }

    public void setData(ArrayList<TeamDataModel> arrayDataList, UpdateTeamAdapter update)
    {
        ArrayList<TeamViewModel> teamViewList = new ArrayList<>();
        for(int i=0; i<arrayDataList.size(); i++)
        {
            TeamDataModel dataModel = arrayDataList.get(i);
            teamViewList.add(new TeamViewModel(dataModel.getTeamName(), dataModel.getTeamCaptain(),
                    dataModel.getTeamCoach(), dataModel.getTeamOwner(), dataModel.getTeamHomeVenue(),
                    dataModel.getTeamBackgroundURL(), dataModel.getTeamLogo()));
        }

        //update data to adapter
        update.updateAdapter(teamViewList);
    }
}