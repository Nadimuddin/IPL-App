package com.bridgelabz.myiplapp.utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bridgelabz.myiplapp.view_holder.ViewHolder;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Nadimuddin on 26/9/16.
 */
public class DownloadImage
{
    private static final String TAG = "DownloadImage";
    ImageUtil imageUtil;
    public void downloadImage(final String imageURL, final ViewHolder holder)
    {
        imageUtil = new ImageUtil();

        StorageReference imageRef;

        //get instance of FirebaseStorage
        FirebaseStorage storage = FirebaseStorage.getInstance();

        //get reference of Firebase Storage
        StorageReference storageRef = storage.getReference();

        //get reference of child node
        imageRef = storageRef.child(imageURL);

        final long ONE_MEGABYTE = 1024 * 1024;

        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>()
        {
            @Override
            public void onSuccess(byte[] bytes)
            {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                Log.e(TAG, "onSuccess: Image downloaded");

                /*
                 *if image URL contains word 'Background'
                 * then save image in Background folder
                 * & set image to background
                 */
                if(imageURL.contains("Background"))
                {
                    imageUtil.saveToInternalStorage(bitmap, "Background",
                            imageURL.substring(imageURL.indexOf('/') + 1));
                    holder.layout.setBackground(new BitmapDrawable(bitmap));
                }

                /*
                 *if image URL contains word 'Logo'
                 * then save image in Logo folder
                 * & set image to Logo imageView
                 */
                else if(imageURL.contains("Logo"))
                {
                    imageUtil.saveToInternalStorage(bitmap, "Logo",
                            imageURL.substring(imageURL.indexOf('/') + 1));
                    holder.teamLogo.setImageBitmap(bitmap);
                }

                /*
                 *if image URL contains character '_'
                 * then save image in folder of corresponding team name
                 */
                else if(imageURL.contains("_"))
                {
                    imageUtil.saveToInternalStorage(bitmap, imageURL.substring(0, imageURL.indexOf('/')),
                            imageURL.substring(imageURL.indexOf('/') + 1));
                    holder.playerPic.setImageBitmap(bitmap);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Log.e("Download", "onFailure: ",e );
            }
        });
    }
}