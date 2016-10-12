package com.bridgelabz.myiplapp.view_model;

/**
 * Created by Nadimuddin on 6/10/16.
 */
public class TeamViewModel
{
    String mTeamName;
    String mCaptain;
    String mCoach;
    String mOwner;
    String mHomeVenue;
    String mTeamBackgroundURL;
    String mLogoURL;

    public TeamViewModel(String teamName, String captain, String coach, String owner, String homeVenue,
                         String teamBackgroundURL, String logoURL)
    {
        mTeamName = teamName;
        mCaptain = captain;
        mCoach = coach;
        mOwner = owner;
        mHomeVenue = homeVenue;
        mTeamBackgroundURL = teamBackgroundURL;
        mLogoURL = logoURL;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public String getCaptain() {
        return "Captain: "+mCaptain;
    }

    public String getCoach() {
        return "Coach: "+mCoach;
    }

    public String getOwner() {
        return "Owner: "+mOwner;
    }

    public String getHomeVenue() {
        return "Home venue: "+mHomeVenue;
    }

    public String getTeamBackgroundURL()
    {
        return mTeamBackgroundURL;
    }

    public String getLogoURL()
    {
        return mLogoURL;
    }
}