package com.bridgelabz.myiplapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bridgelabz.myiplapp.PlayerActivity;
import com.bridgelabz.myiplapp.R;
import com.bridgelabz.myiplapp.model.TeamModel;
import com.bridgelabz.myiplapp.view_holder.ViewHolder;
import com.bridgelabz.myiplapp.utility.DownloadImage;
import com.bridgelabz.myiplapp.utility.ImageUtil;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 22/9/16.
 */
public class IPLAdapter extends RecyclerView.Adapter<ViewHolder>
{
    private static final String TAG = "IPLAdapter";
    final String KEY = "IPL_TEAM";
    ArrayList<TeamModel> mArrayList;
    ViewHolder mHolder;

    //constructor
    public IPLAdapter(ArrayList<TeamModel> arrayList)
    {
        mArrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();

        //inflate layout for displaying team list
        View view = LayoutInflater.from(context).inflate(R.layout.team_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        //initialize object for downloading image from firebase
        DownloadImage firebase = new DownloadImage();

        //initializing object to save image in local storage
        ImageUtil imageUtil = new ImageUtil();

        //get current TeamModel object
        final TeamModel teamModel = mArrayList.get(position);

        mHolder = holder;

        //get URL for background image
        String backgroundURL = teamModel.getTeamBackgroundURL();

        //extract image name from URL
        String backgroundImgName = backgroundURL.substring(backgroundURL.indexOf('/')+1);

        //
        Bitmap bitmap = imageUtil.getImage("Background", backgroundImgName);
        if(bitmap != null)
            //set background image
            holder.layout.setBackground(new BitmapDrawable(bitmap));
        else
            firebase.downloadImage(backgroundURL, holder);


        String logoURL = teamModel.getTeamLogo();
        String logoName = logoURL.substring(logoURL.indexOf('/')+1);

        Bitmap bitmapImage = imageUtil.getImage("Logo", logoName);
        if(bitmapImage != null)
            //set image logo
            holder.teamLogo.setImageBitmap(bitmapImage);
        else
            firebase.downloadImage(logoURL, holder);


        //set team name
        holder.teamName.setText(teamModel.getTeamName());

        //set name of captain
        holder.captain.setText(teamModel.getTeamCaptain());

        //set name of coach
        holder.coach.setText(teamModel.getTeamCoach());

        //set name of team owner
        holder.owner.setText(teamModel.getTeamOwner());

        //set home venue of team
        holder.homeVenue.setText(teamModel.getTeamHomeVenue());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mHolder.layout.getContext(), teamModel.getTeamName()+" position: "+position,
                        Toast.LENGTH_SHORT).show();

                //Intent to open another activity to show players of team
                Intent intent = new Intent(mHolder.layout.getContext(), PlayerActivity.class);

                //pass name of team to another activity
                intent.putExtra(KEY, teamModel.getTeamName());

                //start new activity to show player's info
                mHolder.layout.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mArrayList.size();
    }

}