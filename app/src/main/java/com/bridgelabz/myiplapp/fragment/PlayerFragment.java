package com.bridgelabz.myiplapp.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bridgelabz.myiplapp.R;
import com.bridgelabz.myiplapp.model.PlayerModel;
import com.bridgelabz.myiplapp.utility.DownloadImage;
import com.bridgelabz.myiplapp.utility.ImageUtil;

/**
 * Created by Nadimuddin on 28/9/16.
 */
public class PlayerFragment extends Fragment
{
    ImageView mPlayerPic;
    TextView mPlayerName;
    TextView mBattingStyle;
    TextView mBowlingStyle;
    TextView mDOB;
    TextView mNationality;
    TextView mRole;
    View mView;
    ImageUtil mImageUtil = new ImageUtil();

    public static PlayerFragment getInstance(PlayerModel model)
    {
        PlayerFragment playerFragment = new PlayerFragment();
        Bundle bundle=new Bundle();

        //put object of PlayerModel to bundle
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

        /*
         *getting XML objects
         */
        mPlayerPic = (ImageView)mView.findViewById(R.id.playerPic);
        mPlayerName = (TextView)mView.findViewById(R.id.playerName);
        mBattingStyle = (TextView)mView.findViewById(R.id.battingStyle);
        mBowlingStyle = (TextView)mView.findViewById(R.id.bowlingStyle);
        mDOB = (TextView)mView.findViewById(R.id.dOB);
        mNationality = (TextView)mView.findViewById(R.id.nationality);
        mRole = (TextView)mView.findViewById(R.id.role);

        //get arguments in bundle
        Bundle bundle=getArguments();
        PlayerModel playerModel= (PlayerModel) bundle.getSerializable("VALUE");

        setPlayerInfo(playerModel);

        return mView;
    }

    //set player info on textView in layout
    public void setPlayerInfo(PlayerModel model)
    {
        mPlayerPic.setImageBitmap(getPlayerPic(model.getPlayerImgUrl()));
        mPlayerName.setText(model.getPlayerName());
        mBattingStyle.setText(model.getBattingStyle());
        mBowlingStyle.setText(model.getBowlingStyle());
        mDOB.setText(model.getDOB());
        mNationality.setText(model.getNationality());
        mRole.setText(model.getRole());
    }

    private Bitmap getPlayerPic(String imageURL)
    {
        Bitmap bitmap;
        DownloadImage firebase = new DownloadImage();
        String folderName = imageURL.substring(0, imageURL.indexOf('/'));
        String picName = imageURL.substring(imageURL.indexOf('/')+1);

        //get image from local memory
        bitmap = mImageUtil.getImage(folderName, picName);

        /*
         *if image is present in local memory
         * then return bitmap of that image
         * otherwise download image from firebase
         */
        if(bitmap != null)
            return bitmap;
        else
        {
            //download image from firebase
            firebase.downloadImage(imageURL, null);

            //get image from local memory
            bitmap = mImageUtil.getImage(folderName, picName);
        }

        return bitmap;
    }
}