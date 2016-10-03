package com.bridgelabz.myiplapp.utility;

import android.content.Context;
import android.widget.Toast;

import com.bridgelabz.myiplapp.MainActivity;
import com.bridgelabz.myiplapp.controller.UpdateAdapter;
import com.bridgelabz.myiplapp.database.DatabaseUtil;
import com.bridgelabz.myiplapp.model.TeamModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 3/10/16.
 */
public class FirebaseUtil
{
    ArrayList<TeamModel> mArrayList;
    Context mContext;

    public FirebaseUtil(Context context)
    {
        mContext = context;
        mArrayList = new ArrayList<>();
    }


    public void getFirebaseData(final UpdateAdapter update)
    {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //get instance of firebase database project
        FirebaseDatabase firebaseDB = FirebaseDatabase.getInstance();

        //get reference of team_info child node
        DatabaseReference reference = firebaseDB.getReference("team_info");

        reference.keepSynced(true);

        //set value event listener for firebase reference
        reference.addValueEventListener(new ValueEventListener()
        {
            /*
             * onDataChange method to read a static snapshot of the contents at given JSON object
             * This method is triggered once when the listener is attached
             * and again every time the data changes.
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                //this is for indicating firebase what type of data we want
                GenericTypeIndicator<ArrayList<TeamModel>> type =
                        new GenericTypeIndicator<ArrayList<TeamModel>>() {};

                //get data from Firebase into model class
                mArrayList.addAll(dataSnapshot.getValue(type));

                update.updateAdapter(mArrayList);

                DatabaseUtil database = new DatabaseUtil(mContext);
                if(database.insertData(mArrayList) == -1)
                    Toast.makeText(mContext, "Data not inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(mContext, "Data inserted", Toast.LENGTH_SHORT).show();
            }

            //this will called when error occur while getting data from firebase
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
            }
        });
    }
}