package com.bridgelabz.myiplapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bridgelabz.myiplapp.data_model.PlayerDataModel;
import com.bridgelabz.myiplapp.data_model.TeamDataModel;
import com.bridgelabz.myiplapp.preference.SavePreference;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 1/10/16.
 */
public class DatabaseUtil extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "IPL_Database.db";
    private static String TABLE_NAME;
    private static String COL1;
    private static String COL2;
    private static String COL3;
    private static String COL4;
    private static String COL5;
    private static String COL6;
    private static String COL7;
    private static String KEY;

    SavePreference pref;

    //constructor
    public DatabaseUtil(Context context, String key)
    {
        super(context, DATABASE_NAME, null, 1);
        TABLE_NAME = key;
        KEY = key+"Table";


        pref = new SavePreference(context);
        String temp = pref.getStringPreference(KEY);

        if(temp == null || !temp.equals("table created"))
        {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            sqLiteDatabase.execSQL("Drop table if exists "+TABLE_NAME);
            if(key.equals("team_info"))
            {
                COL1 = "team_name";
                COL2 = "captain";
                COL3 = "coach";
                COL4 = "owner";
                COL5 = "home_venue";
                COL6 = "background_url";
                COL7 = "logo_url";
            }
            else
            {
                COL1 = "player_name";
                COL2 = "batting_style";
                COL3 = "bowling_style";
                COL4 = "dob";
                COL5 = "home_venue";
                COL6 = "player_image_url";
                COL7 = "role";
            }
            sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(" +
                    COL1+" text," +
                    COL2+" text," +
                    COL3+" text," +
                    COL4+" text," +
                    COL5+" text," +
                    COL6+" text," +
                    COL7+" text)");
            pref.setPreferences(KEY, "table created");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertDataForTeam(ArrayList<TeamDataModel> arrayList)
    {
        //get database to be writable
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        long result = 0;
        TeamDataModel model;

        //content values m
        ContentValues values = new ContentValues();

        for(int i=0; i<arrayList.size(); i++)
        {
            //get current object
            model = arrayList.get(i);

            /* putting data to DataModel class
             */
            values.put(COL1, model.getTeamName());
            values.put(COL2, model.getTeamCaptain());
            values.put(COL3, model.getTeamCoach());
            values.put(COL4, model.getTeamOwner());
            values.put(COL5, model.getTeamHomeVenue());
            values.put(COL6, model.getTeamBackgroundURL());
            values.put(COL7, model.getTeamLogo());

            //insert data
            result =  sqLiteDatabase.insert(TABLE_NAME, null, values);
            if(result == -1)
                break;
        }
        return result;
    }

    public long insertDataForPlayer(ArrayList<PlayerDataModel> arrayList)
    {
        //get database to be writable
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        long result = 0;
        PlayerDataModel model;
        ContentValues values = new ContentValues();

        for(int i=0; i<arrayList.size(); i++)
        {
            model = arrayList.get(i);
            values.put(COL1, model.getPlayerName());
            values.put(COL2, model.getBattingStyle());
            values.put(COL3, model.getBowlingStyle());
            values.put(COL4, model.getDOB());
            values.put(COL5, model.getNationality());
            values.put(COL6, model.getRole());
            values.put(COL7, model.getPlayerImgUrl());

            result =  sqLiteDatabase.insert(TABLE_NAME, null, values);
            if(result == -1)
                break;
        }
        return result;
    }

    public Cursor retrieveData(String table_name)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //execute a query in database
        Cursor result = sqLiteDatabase.rawQuery("select * from "+table_name, null);

        //return result of query
        return result;
    }
}
