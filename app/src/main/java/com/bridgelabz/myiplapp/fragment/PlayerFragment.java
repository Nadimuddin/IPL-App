package com.bridgelabz.myiplapp.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bridgelabz.myiplapp.R;
import com.bridgelabz.myiplapp.utility.DownloadImage;
import com.bridgelabz.myiplapp.utility.ImageUtil;
import com.bridgelabz.myiplapp.view_holder.ViewHolder;
import com.bridgelabz.myiplapp.view_model.PlayerViewModel;

/**
 * Created by Nadimuddin on 28/9/16.
 */
public class PlayerFragment extends Fragment
{
    View mView;
    ImageUtil mImageUtil = new ImageUtil();

    public static PlayerFragment getInstance(PlayerViewModel model)
    {
        PlayerFragment playerFragment = new PlayerFragment();
        Bundle bundle=new Bundle();

        //put object of PlayerDataModel to bundle
        bundle.putSerializable("VALUE",model);

        //set argument to this fragment
        playerFragment.setArguments(bundle);

        //return object of this fragment
        return  playerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //inflate layout for this fragment
        mView = inflater.inflate(R.layout.tabs, container, false);

        //calling Holder to get views
        ViewHolder holder = new ViewHolder(mView);

        //get arguments in bundle
        Bundle bundle=getArguments();
        PlayerViewModel playerViewModel = (PlayerViewModel) bundle.getSerializable("VALUE");

        setPlayerInfo(playerViewModel, holder);

        return mView;
    }

    //set player info on textView in layout
    public void setPlayerInfo(PlayerViewModel model, ViewHolder holder)
    {
        holder.playerPic.setImageBitmap(getPlayerPic(model.getImageURL(), holder));
        holder.playerName.setText(model.getPlayerName());
        holder.battingStyle.setText(model.getBattingStyle());
        holder.bowlingStyle.setText(model.getBowlingStyle());
        holder.dOB.setText(model.getDOB());
        holder.nationality.setText(model.getNationality());
        holder.role.setText(model.getRole());
    }

    private Bitmap getPlayerPic(String imageURL, ViewHolder holder)
    {
        Bitmap bitmap;
        DownloadImage firebase = new DownloadImage();
        String folderName = imageURL.substring(0, imageURL.indexOf('/'));
        String picName = imageURL.substring(imageURL.indexOf('/')+1);

        //get image from local memory
        bitmap = mImageUtil.getImage(folderName, picName);

        /* if image is present in local memory
         * then return bitmap of that image
         * otherwise download image from firebase */
        if(bitmap != null)
            return bitmap;
        else
        {
            //download image from firebase
            firebase.downloadImage(imageURL, holder);

            //get image from local memory
            bitmap = mImageUtil.getImage(folderName, picName);
        }

        return bitmap;
    }
}