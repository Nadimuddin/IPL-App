package com.bridgelabz.myiplapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Nadimuddin on 22/9/16.
 */
public class TeamViewHolder extends RecyclerView.ViewHolder
{
    public RelativeLayout layout;
    public ImageView teamLogo;
    public TextView teamName;
    public TextView captain;
    public TextView coach;
    public TextView owner;
    public TextView homeVenue;

    public TeamViewHolder(View view)
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
    }
}