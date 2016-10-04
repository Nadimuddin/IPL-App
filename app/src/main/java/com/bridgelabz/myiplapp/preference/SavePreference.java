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

    public String getPreference(String key)
    {
        //get preference value for the given kye
        return preferences.getString(key, null);
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
}
