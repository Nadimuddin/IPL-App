package com.bridgelabz.myiplapp.model;

import java.io.Serializable;

/**
 * Created by Nadimuddin on 28/9/16.
 */
public class PlayerModel implements Serializable
{
    public String background_image_name;
    public String player_batting_style;
    public String player_bowling_style;
    public String player_dob;
    public String player_img_url;
    public String player_name;
    public String player_nationality;
    public String player_role;

    public String getBackgroundImageName()
    {
        return background_image_name;
    }

    public String getBattingStyle()
    {
        return player_batting_style;
    }

    public String getBowlingStyle()
    {
        return player_bowling_style;
    }

    public String getDOB()
    {
        return player_dob;
    }

    public String getPlayerImgUrl()
    {
        return player_img_url;
    }

    public String getPlayerName()
    {
        return player_name;
    }

    public String getNationality()
    {
        return player_nationality;
    }

    public String getRole()
    {
        return player_role;
    }
}