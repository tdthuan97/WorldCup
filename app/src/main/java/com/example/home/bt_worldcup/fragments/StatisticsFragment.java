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

import com.akaita.android.circularseekbar.CircularSeekBar;
import com.example.home.bt_worldcup.R;
import com.example.home.bt_worldcup.models.Match;
import com.example.home.bt_worldcup.models.TeamStatistics;
import com.example.home.bt_worldcup.models.TeamsInMatch;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {
    View v;
    TextView txtBallProHome, txtBallProAway, txtFoulsHome, txtFoulsAway, txtYellowHome, txtYellowAway, txtRedHome, txtRedAway;
    PieChart pieChart;
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
        List<Entry> prossessions = new ArrayList<>();
        prossessions.add(new Entry(homeSta.getBallPossession(),0));
        prossessions.add(new Entry(awaySta.getBallPossession(),1));
        PieDataSet dataSet = new PieDataSet(prossessions,null);
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("");
        xVals.add("");
        PieData data = new PieData(xVals, dataSet);
        dataSet.setColors(new int[]{R.color.colorPrimary,R.color.cardview_shadow_start_color},getActivity());
        pieChart.setDrawSliceText(false);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleRadius(80f);
        pieChart.setData(data);
        pieChart.setEnabled(false);


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
        pieChart = (PieChart) v.findViewById(R.id.piechartBall);
        pieChart.setUsePercentValues(true);
    }

}
