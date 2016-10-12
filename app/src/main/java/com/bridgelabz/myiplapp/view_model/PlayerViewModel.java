package com.bridgelabz.myiplapp.view_model;

import java.io.Serializable;

/**
 * Created by Nadimuddin on 6/10/16.
 */
public class PlayerViewModel implements Serializable
{
    String mPlayerName;
    String mBattingStyle;
    String mBowlingStyle;
    String mDOB;
    String mNationality;
    String mRole;
    String mImageURL;

    public PlayerViewModel(String playerName, String battingStyle, String bowlingStyle, String dOB,
                           String nationality, String role, String  imageURL)
    {
        mPlayerName = playerName;
        mBattingStyle = battingStyle;
        mBowlingStyle = bowlingStyle;
        mDOB = dOB;
        mNationality = nationality;
        mRole = role;
        mImageURL = imageURL;
    }

    public String getPlayerName()
    {
        return mPlayerName;
    }

    public String getBowlingStyle() {
        return mBowlingStyle;
    }

    public String getBattingStyle() {
        return mBattingStyle;
    }

    public String getDOB() {
        return mDOB;
    }

    public String getNationality() {
        return mNationality;
    }

    public String getRole() {
        return mRole;
    }

    public String getImageURL() {
        return mImageURL;
    }


}
