package com.bridgelabz.myiplapp.data_model;

/**
 * Created by Nadimuddin on 28/9/16.
 */
public class PlayerDataModel// implements Serializable
{
    String player_name;
    String player_batting_style;
    String player_bowling_style;
    String player_dob;
    String player_nationality;
    String player_role;
    String player_img_url;

    public PlayerDataModel() {
    }

    public PlayerDataModel(String playerName, String battingStyle, String bowlingStyle, String dOB,
                           String nationality, String role, String imageURL)
    {
        player_name = playerName;
        player_batting_style = battingStyle;
        player_bowling_style = bowlingStyle;
        player_dob = dOB;
        player_nationality= nationality;
        player_role = role;
        player_img_url = imageURL;
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