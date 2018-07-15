package com.example.home.bt_worldcup.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.home.bt_worldcup.R;
import com.example.home.bt_worldcup.models.Match;
import com.example.home.bt_worldcup.models.TeamStatistics;
import com.example.home.bt_worldcup.models.TeamsInMatch;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {
    View v;
    TextView txtBallProHome, txtBallProAway, txtFoulsHome, txtFoulsAway, txtYellowHome, txtYellowAway, txtRedHome, txtRedAway;

    LinearLayout statistics;
    TeamStatistics homeSta = null ,awaySta = null;
    public StatisticsFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_statistics, container, false);
        addControls();
        // Inflate the layout for this fragment
        homeSta = (TeamStatistics) getActivity().getIntent().getSerializableExtra("statisticshometeam");
        awaySta = (TeamStatistics) getActivity().getIntent().getSerializableExtra("statisticsawayteam");
        if(homeSta != null && awaySta != null){
            setData();
        }else{
            statistics.setVisibility(View.INVISIBLE);
        }

        return v;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private  void setData(){

        txtBallProHome.setText(homeSta.getBallPossession()+"");
        txtBallProAway.setText(awaySta.getBallPossession()+"");
        txtYellowHome.setText(homeSta.getYellowCards()+"");
        txtYellowAway.setText(awaySta.getYellowCards()+"");
        txtRedHome.setText(homeSta.getRedCards()+"");
        txtRedAway.setText(awaySta.getRedCards()+"");
        if(homeSta.getFoulsCommitted() == null){
            txtFoulsHome.setText("0");
        }else
            txtFoulsHome.setText(homeSta.getFoulsCommitted().toString());
        if(awaySta.getFoulsCommitted() == null){
            txtFoulsAway.setText("0");
        }else
            txtFoulsAway.setText(awaySta.getFoulsCommitted().toString());

    }
    private void addControls(){
        statistics = v.findViewById(R.id.statistics);
        txtBallProHome = v.findViewById(R.id.ballpossessionHome);
        txtBallProAway = v.findViewById(R.id.ballpossessionAway);
        txtFoulsHome = v.findViewById(R.id.foulshome);
        txtFoulsAway = v.findViewById(R.id.foulsaway);
        txtYellowHome = v.findViewById(R.id.yellohome);
        txtYellowAway = v.findViewById(R.id.yelloaway);
        txtRedHome = v.findViewById(R.id.redhome);
        txtRedAway = v.findViewById(R.id.redaway);
    }

}
