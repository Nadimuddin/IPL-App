package com.bridgelabz.myiplapp.utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Nadimuddin on 26/9/16.
 */
public class ImageUtil
{
    String IPL_FOLDER = "IPL";
    String mRoot;
    private static final String TAG = "ImageUtil";

    public String saveToInternalStorage(Bitmap bitmap, String folderName, String fileName)
    {
        File directory = null;

        //get directory of 'Pictures' folder
        mRoot = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        try {
            directory = new File(mRoot+"/"+IPL_FOLDER+"/"+folderName);
            if(!directory.exists())
                //create directory
                directory.mkdirs();

            //create file in given directory
            File file = new File(directory, fileName);
            if(file.exists())
                file.delete();

            file.createNewFile();

            OutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            Log.i(TAG, "saveToInternalStorage: Image stored in local directory:"+directory+"/"+fileName);
            out.flush();
            out.close();

        }
        catch (IOException e)
        {
            Log.e(TAG, "saveToInternalStorage:", e);
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }

    public Bitmap getImage(String folderName, String fileName)
    {
        //get directory of 'Pictures' folder
        mRoot = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();

        //get bitmap image from the directory
        Bitmap bitmap = BitmapFactory.decodeFile(mRoot+"/"+IPL_FOLDER+"/"+folderName+"/"+fileName);

        return bitmap;
    }

}
