package com.bridgelabz.myiplapp.data_model;

import java.io.Serializable;

/**
 * Created by Nadimuddin on 28/9/16.
 */
public class PlayerModel implements Serializable
{
    String player_name;
    String player_batting_style;
    String player_bowling_style;
    String player_dob;
    String player_nationality;
    String player_role;
    String player_img_url;
    String background_image_name;

    public PlayerModel()
    {

    }

    public PlayerModel(String playerName, String battingStyle, String bowlingStyle, String dOB,
                       String nationality,String role, String imageURL)
    {

    }
    public String getBackgroundImageName()
    {
        return background_image_name;
    }

    public String getPlayerName()
    {
        return player_name;
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

    public String getNationality()
    {
        return player_nationality;
    }

    public String getRole()
    {
        return player_role;
    }
}