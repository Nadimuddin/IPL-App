package com.bridgelabz.myiplapp.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bridgelabz.myiplapp.R;

/**
 * Created by Nadimuddin on 22/9/16.
 */
public class ViewHolder extends RecyclerView.ViewHolder
{
    public RelativeLayout layout;
    public ImageView teamLogo;
    public TextView teamName;
    public TextView captain;
    public TextView coach;
    public TextView owner;
    public TextView homeVenue;

    public ImageView playerPic;
    public TextView playerName;
    public TextView battingStyle;
    public TextView bowlingStyle;
    public TextView dOB;
    public TextView nationality;
    public TextView role;

    public ViewHolder(View view)
    {
        super(view);

        //getting XML objects
        layout = (RelativeLayout)view.findViewById(R.id.teamContainer);
        teamLogo = (ImageView)view.findViewById(R.id.teamLogo);
        teamName = (TextView)view.findViewById(R.id.teamName);
        captain = (TextView)view.findViewById(R.id.captainName);
        coach = (TextView)view.findViewById(R.id.teamCoach);
        owner = (TextView)view.findViewById(R.id.teamOwner);
        homeVenue = (TextView)view.findViewById(R.id.teamHome);

        playerPic = (ImageView)view.findViewById(R.id.playerPic);
        playerName = (TextView)view.findViewById(R.id.playerName);
        battingStyle = (TextView)view.findViewById(R.id.battingStyle);
        bowlingStyle = (TextView)view.findViewById(R.id.bowlingStyle);
        dOB = (TextView)view.findViewById(R.id.dOB);
        nationality = (TextView)view.findViewById(R.id.nationality);
        role = (TextView)view.findViewById(R.id.role);
    }
}