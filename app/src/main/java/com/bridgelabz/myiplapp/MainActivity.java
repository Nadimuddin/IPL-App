package com.bridgelabz.myiplapp;

/**
 * Created by Nadimuddin on 22/9/16.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bridgelabz.myiplapp.adapter.TeamAdapter;
import com.bridgelabz.myiplapp.controller.TeamController;
import com.bridgelabz.myiplapp.data_model.TeamDataModel;
import com.bridgelabz.myiplapp.interfaces.UpdateTeamAdapter;
import com.bridgelabz.myiplapp.view_model.TeamViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UpdateTeamAdapter
{
    RecyclerView mRecyclerView;
    ArrayList<TeamDataModel> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing ArrayList
        mArrayList =new ArrayList<>();

        //getting object of RecyclerView from XML
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        //set Layout for
        setLayoutForRecyclerView();

        //initializing controller
        TeamController controller = new TeamController(this);

        //get data & set to adapter
        controller.getData(this, "team_info");
    }

    //method for setting layout for RecyclerView
    private void setLayoutForRecyclerView()
    {
        //initialize LayoutManager for RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        //set Layout for RecyclerView
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void updateAdapter(ArrayList<TeamViewModel> arrayViewList)
    {
        //initialize adapter
        TeamAdapter adapter =new TeamAdapter(arrayViewList);

        //set adapter to RecyclerView
        mRecyclerView.setAdapter(adapter);
    }
}