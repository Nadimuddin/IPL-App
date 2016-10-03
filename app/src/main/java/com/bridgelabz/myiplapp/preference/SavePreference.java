package com.bridgelabz.myiplapp.preference;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nadimuddin on 26/9/16.
 */
public class SavePreference extends Application
{
    //static
    static String PREFERENCE = "IPL Preference";
    //static
    String BACKGROUND = "Background";
    //static
    String LOGO = "Logo";

    //static
    static Context mContext;
    //static
    static SharedPreferences preferences;
    static SavePreference mSavePref = new SavePreference();

    private SavePreference()
    {
    }

    /*public static SavePreference getInstance()
    {
        if(mSavePref == null)
            mSavePref = new SavePreference();
        return mSavePref;
    }*/

    public static SavePreference getInstance()
    {
        //mContext = context;
        //preferences=contxet.getSharedPreferences(PREFERENCE,0);
        if(mSavePref == null)
            mSavePref = new SavePreference();
        return mSavePref;
    }


    private Context getContext()
    {
        return getApplicationContext();
    }

    public SavePreference(Context context) {
        mContext = context;
        preferences=context.getSharedPreferences(PREFERENCE,0);
    }

    public String getPreference(String prefForWhat)
    {
        String str = null;
        if(prefForWhat.equals("Background"))
            str = preferences.getString(BACKGROUND, null);
        if(prefForWhat.equals("Logo"))
            str = preferences.getString(LOGO, null);
        return str;
    }

    public void setPreferences(String stringToSet, String prefOfWhat)
    {
        SharedPreferences.Editor editor = preferences.edit();
        if(prefOfWhat.equals("Background"))
            editor.putString(BACKGROUND, stringToSet);
        if(prefOfWhat.equals("Logo"))
            editor.putString(LOGO, stringToSet);
    }

    private SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
    }
}
