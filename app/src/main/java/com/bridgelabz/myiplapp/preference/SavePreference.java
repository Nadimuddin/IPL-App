package com.bridgelabz.myiplapp.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nadimuddin on 26/9/16.
 */
public class SavePreference
{
    //key for shared preference
    private static final String PREFERENCE = "IPL Preference";

    static Context mContext;

    static SharedPreferences preferences;

    //constructor
    public SavePreference(Context context)
    {
        mContext = context;

        //get shared preference
        preferences=context.getSharedPreferences(PREFERENCE,mContext.MODE_PRIVATE);
    }

    //get String preference value
    public String getStringPreference(String key)
    {
        return preferences.getString(key, null);
    }

    //get int preference value
    public int getIntPreferences(String key)
    {
        return preferences.getInt(key, 0);
    }

    public void setPreferences(String key, String stringToSet)
    {
        //initialize editor to edit preference value
        SharedPreferences.Editor editor = preferences.edit();

        //set preference value for the key
        editor.putString(key, stringToSet);

        //apply changes to value
        editor.apply();
    }

    public void setPreferences(String key, int valueToSet)
    {
        //initialize editor to edit preference value
        SharedPreferences.Editor editor = preferences.edit();

        //set preference value for the key
        editor.putInt(key, valueToSet);

        //apply changes to value
        editor.apply();
    }
}
