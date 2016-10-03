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

    public TeamModel(String teamName, String captain, String coach, String owner, String homeVenue,
                     String backgroundUrl, String logoUrl)
    {
        team_name = teamName;
        team_captain = captain;
        team_coach = coach;
        team_owner = owner;
        team_home_venue = homeVenue;
        team_background = backgroundUrl;
        team_img_url = logoUrl;
    }



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