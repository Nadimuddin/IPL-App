package com.bridgelabz.myiplapp.controller;

import android.content.Context;
import android.database.Cursor;

import com.bridgelabz.myiplapp.database.DatabaseUtil;
import com.bridgelabz.myiplapp.interfaces.GetPlayerList;
import com.bridgelabz.myiplapp.interfaces.UpdatePlayerAdapter;
import com.bridgelabz.myiplapp.data_model.PlayerDataModel;
import com.bridgelabz.myiplapp.preference.SavePreference;
import com.bridgelabz.myiplapp.utility.FirebaseUtil;
import com.bridgelabz.myiplapp.view_model.PlayerViewModel;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 5/10/16.
 */
public class PlayerController
{
    private static final String TAG = "PlayerController";

    //key for storing preference
    private static String PREF_KEY;

    //key for getting data from firebase
    String mKey;

    Context mContext;
    UpdatePlayerAdapter mUpdate;

    //constructor
    public PlayerController(Context context)
    {
        mContext = context;
    }

    //get data from firebase or local
    public void getData(final UpdatePlayerAdapter update, String key)
    {
        PREF_KEY = key;
        mKey = key;
        mUpdate = update;

        //initializing object of SharedPreference
        SavePreference pref = new SavePreference(mContext);

        //get preference for the key(PREF_KEY)
        String str = pref.getStringPreference(PREF_KEY);
        if(str == null || !str.equals("data saved in local"))
            getFromFirebase();
        else if(str.equals("data saved in local"))
            getFromLocalStorage();
    }

    private void getFromFirebase()
    {
        //initialing FireBaseUtil class
        FirebaseUtil fireBaseUtil = new FirebaseUtil(mContext);

        //get data from firebase
        fireBaseUtil.getFirebaseData("player", null, new GetPlayerList() {
            @Override
            public void getPlayerList(ArrayList<PlayerDataModel> arrayDataList)
            {
                setData(arrayDataList, mUpdate);
            }
        }, mKey);
    }

    private void getFromLocalStorage()
    {
        ArrayList<PlayerDataModel> arrayList = new ArrayList<>();

        DatabaseUtil databaseUtil = new DatabaseUtil(mContext, mKey);

        //retrieving data from local database
        Cursor cursor = databaseUtil.retrieveData(mKey);

        //add retrieved data to ArrayList of DataModel class
        while (cursor.moveToNext())
        {
            arrayList.add(new PlayerDataModel(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6)));
        }


        setData(arrayList, mUpdate);
    }

    public void setData(ArrayList<PlayerDataModel> playerDataList, UpdatePlayerAdapter update)
    {
        //initialize ArrayList of ViewModel
        ArrayList<PlayerViewModel> playerViewList = new ArrayList<>();
        for(int i=0; i<playerDataList.size(); i++)
        {
            //get current object of PlayerDataModel class
            PlayerDataModel dataModel = playerDataList.get(i);

            //add data to ViewModel class
            playerViewList.add(new PlayerViewModel(dataModel.getPlayerName(), dataModel.getBattingStyle(),
                    dataModel.getBowlingStyle(), dataModel.getDOB(), dataModel.getNationality(),
                    dataModel.getRole(), dataModel.getPlayerImgUrl()));
        }

        //update data to adapter
        update.updateAdapter(playerViewList);
    }
}
