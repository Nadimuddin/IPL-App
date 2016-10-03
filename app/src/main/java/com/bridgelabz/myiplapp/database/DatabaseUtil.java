package com.bridgelabz.myiplapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bridgelabz.myiplapp.model.TeamModel;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 1/10/16.
 */
public class DatabaseUtil extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "IPL_Database.db";
    private static final String TABLE_NAME = "Ipl_team";
    private static final String COL1 = "team_name";
    private static final String COL2 = "captain";
    private static final String COL3 = "coach";
    private static final String COL4 = "owner";
    private static final String COL5 = "home_venue";

    ArrayList<TeamModel> mArrayList;
    public DatabaseUtil(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        /*
         * method to create table
         * it accepts SQL statement to create table
         */
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(" +
                COL1+" text," +
                COL2+" text," +
                COL3+" text," +
                COL4+" text," +
                COL5+" text)");
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertData(ArrayList<TeamModel> arrayList)
    {
        //get database to be writable
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        long result = 0;
        TeamModel model;
        ContentValues values = new ContentValues();

        for(int i=0; i<arrayList.size(); i++)
        {
            model = arrayList.get(i);
            values.put(COL1, model.getTeamName());
            values.put(COL2, model.getTeamCaptain());
            values.put(COL3, model.getTeamCoach());
            values.put(COL4, model.getTeamOwner());
            values.put(COL5, model.getTeamHomeVenue());

            result =  sqLiteDatabase.insert(TABLE_NAME, null, values);
            if(result == -1)
                break;
        }
        return result;
    }
}
