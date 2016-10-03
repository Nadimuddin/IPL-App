package com.bridgelabz.myiplapp.model;

/**
 * Created by Nadimuddin on 22/9/16.
 */
public class TeamModel {
    String team_name;
    String team_captain;
    String team_coach;
    String team_owner;
    String team_home_venue;
    String team_background;
    String team_img_url;

    public TeamModel() {    }

    public String getTeamName()
    {
        return team_name;
    }

    public String getTeamCaptain()
    {
        return "Captain: "+team_captain;
    }

    public String getTeamCoach()
    {
        return "Coach: "+team_coach;
    }

    public String getTeamOwner()
    {
        return "Owner: "+team_owner;
    }

    public String getTeamHomeVenue()
    {
        return "Home venue: "+team_home_venue;
    }

    public String getTeamLogo()
    {
        return team_img_url;
    }

    public String getTeamBackgroundURL()
    {
        return team_background;
    }
}