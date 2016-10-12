package com.bridgelabz.myiplapp.interfaces;

import com.bridgelabz.myiplapp.data_model.TeamDataModel;
import com.bridgelabz.myiplapp.view_model.TeamViewModel;

import java.util.ArrayList;

/**
 * Created by Nadimuddin on 3/10/16.
 */
public interface UpdateTeamAdapter
{
    void updateAdapter(ArrayList<TeamViewModel> arrayViewList);
    //void updatePlayerAdapter(ArrayList<PlayerDataModel> arrayList);
}
