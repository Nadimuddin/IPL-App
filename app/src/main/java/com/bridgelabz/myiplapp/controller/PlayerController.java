package com.bridgelabz.myiplapp.controller;

import android.content.Context;
import android.database.Cursor;

import com.bridgelabz.myiplapp.database.DatabaseUtil;
import com.bridgelabz.myiplapp.interfaces.UpdatePlayerAdapter;
import com.bridgelabz.myiplapp.data_model.PlayerModel;
import com.bridgelabz.myiplapp.preference.SavePreference;
import com.bridgelabz.myiplapp.utility.FirebaseUtil;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 5/10/16.
 */
public class PlayerController
{
    private static final String TAG = "PlayerController";

    //key for storing preference
    private static String PREF_KEY;
    Context mContext;
    public PlayerController(Context context)
    {
        mContext = context;
    }

    public void getData(UpdatePlayerAdapter update, String key)
    {
        PREF_KEY = key;
        //initializing object of SharedPreference
        SavePreference pref = new SavePreference(mContext);

        //get preference for the key
        String str = pref.getPreference(PREF_KEY);
        if(str == null || !str.equals("data saved in local"))
        {
            //initialing FireBaseUtil class
            FirebaseUtil fireBaseUtil = new FirebaseUtil(mContext);

            //get data from firebase
            fireBaseUtil.getFirebaseData(null, update, key);

            pref.setPreferences(PREF_KEY, "data saved in local");
        }
        else if(str.equals("data saved in local"))
        {
            ArrayList<PlayerModel> arrayList = new ArrayList<>();

            DatabaseUtil databaseUtil = new DatabaseUtil(mContext, key);

            //retrieving data from local database
            Cursor cursor = databaseUtil.retrieveData(key);

            while (cursor.moveToNext())
            {
                arrayList.add(new PlayerModel(cursor.getString(0), cursor.getString(1),
                                                cursor.getString(2), cursor.getString(3),
                                                cursor.getString(4), cursor.getString(5),
                                                cursor.getString(6)));
            }
            update.updateAdapter(arrayList);
        }
    }
}
