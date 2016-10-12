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
import com.bridgelabz.myiplapp.data_model.TeamDataModel;
import com.bridgelabz.myiplapp.view_holder.ViewHolder;
import com.bridgelabz.myiplapp.utility.DownloadImage;
import com.bridgelabz.myiplapp.utility.ImageUtil;
import com.bridgelabz.myiplapp.view_model.TeamViewModel;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 22/9/16.
 */
public class TeamAdapter extends RecyclerView.Adapter<ViewHolder>
{
    private static final String TAG = "TeamAdapter";
    final String KEY = "IPL_TEAM";
    ArrayList<TeamViewModel> mArrayViewList;
    ImageUtil mImageUtil;
    ViewHolder mHolder;

    //constructor
    public TeamAdapter(ArrayList<TeamViewModel> arrayViewList)
    {
        mArrayViewList = arrayViewList;
        mImageUtil = new ImageUtil();
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

        //get current TeamViewModel object
        final TeamViewModel teamViewModel = mArrayViewList.get(position);
        mHolder = holder;


        //get image for background
        Bitmap bitmap = getBackgroundImage(teamViewModel.getTeamBackgroundURL());

        /* if null gets from local storage then download image from firebase
         * otherwise load image from local storage   */
        if(bitmap != null)
            holder.layout.setBackground(new BitmapDrawable(bitmap));
        else
            firebase.downloadImage(teamViewModel.getTeamBackgroundURL(), holder);


        //get team logo
        Bitmap bitmapImage = getTeamLogo(teamViewModel.getLogoURL());

        /* if null gets from local storage then download image from firebase
         * otherwise load image from local memory   */
        if(bitmapImage != null)
            holder.teamLogo.setImageBitmap(bitmapImage);
        else
            firebase.downloadImage(teamViewModel.getLogoURL(), holder);


        //set team name
        holder.teamName.setText(teamViewModel.getTeamName());

        //set name of captain
        holder.captain.setText(teamViewModel.getCaptain());

        //set name of coach
        holder.coach.setText(teamViewModel.getCoach());

        //set name of team owner
        holder.owner.setText(teamViewModel.getOwner());

        //set home venue of team
        holder.homeVenue.setText(teamViewModel.getHomeVenue());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mHolder.layout.getContext(), teamViewModel.getTeamName()+" position: "+position,
                        Toast.LENGTH_SHORT).show();

                //Intent to open another activity to show players of team
                Intent intent = new Intent(mHolder.layout.getContext(), PlayerActivity.class);

                //pass name of team to another activity
                intent.putExtra(KEY, teamViewModel.getTeamName());

                //start new activity to show player's info
                mHolder.layout.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mArrayViewList.size();
    }

    private Bitmap getBackgroundImage(String backgroundURL)
    {
        //extract image name from URL
        String backgroundImgName = backgroundURL.substring(backgroundURL.indexOf('/')+1);

        //get image for background
        Bitmap bitmap = mImageUtil.getImage("Background", backgroundImgName);
        return bitmap;
    }

    private Bitmap getTeamLogo(String logoURL)
    {
        //extract logo name from URL
        String logoName = logoURL.substring(logoURL.indexOf('/')+1);

        //get team logo
        Bitmap bitmap = mImageUtil.getImage("Logo", logoName);

        //return bitmap image of logo
        return bitmap;
    }

}