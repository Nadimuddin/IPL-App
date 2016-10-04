package com.bridgelabz.myiplapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bridgelabz.myiplapp.model.TeamModel;
import com.bridgelabz.myiplapp.preference.SavePreference;

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
    private static final String COL6 = "background_url";
    private static final String COL7 = "logo_url";
    private static final String KEY = "DATABASE_TABLE";

    SavePreference pref;

    public DatabaseUtil(Context context)
    {
        super(context, DATABASE_NAME, null, 1);

        pref = new SavePreference(context);
        String temp = pref.getPreference(KEY);

        if(temp == null || !temp.equals("table created"))
        {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            sqLiteDatabase.execSQL("Drop table if exists "+TABLE_NAME);
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
        /*
         * method to create table
         * it accepts SQL statement to create table
         */
        /*sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(" +
                COL1+" text," +
                COL2+" text," +
                COL3+" text," +
                COL4+" text," +
                COL5+" text," +
                COL6+" text," +
                COL7+" text)");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

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
            values.put(COL6, model.getTeamBackgroundURL());
            values.put(COL7, model.getTeamLogo());

            result =  sqLiteDatabase.insert(TABLE_NAME, null, values);
            if(result == -1)
                break;
        }
        return result;
    }

    public Cursor retrieveData()
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor result = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME, null);

        return result;
    }
}
