package com.bridgelabz.myiplapp;

/**
 * Created by Nadimuddin on 22/9/16.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bridgelabz.myiplapp.adapter.IPLAdapter;
import com.bridgelabz.myiplapp.controller.TeamController;
import com.bridgelabz.myiplapp.interfaces.UpdateTeamAdapter;
import com.bridgelabz.myiplapp.data_model.TeamModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    RecyclerView mRecyclerView;
    ArrayList<TeamModel> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mArrayList =new ArrayList<>();

        setContentView(R.layout.activity_main);

        //getting object of RecyclerView from XML
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        //set Layout for
        setLayoutForRecyclerView();

        //initializing controller
        TeamController controller = new TeamController(this);

        //get data & set to adapter
        controller.getData(new UpdateTeamAdapter() {
            @Override
            public void updateAdapter(ArrayList<TeamModel> arrayList)
            {
                IPLAdapter adapter =new IPLAdapter(arrayList);
                mRecyclerView.setAdapter(adapter);
            }

        }, "team_info");
    }

    //method for setting layout for RecyclerView
    private void setLayoutForRecyclerView()
    {
        //initialize LayoutManager for RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        //set Layout for RecyclerView
        mRecyclerView.setLayoutManager(layoutManager);
    }

}