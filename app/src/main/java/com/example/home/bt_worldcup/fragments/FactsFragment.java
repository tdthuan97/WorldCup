package com.example.home.bt_worldcup.fragments;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.home.bt_worldcup.R;
import com.example.home.bt_worldcup.models.Match;
import com.example.home.bt_worldcup.models.Weather;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FactsFragment extends Fragment {

    CoordinatorLayout facts;
    TextView txtStadium, txtCity, txtAtten, txtTemper, txtCondi, txtHumidity, txtWind, txtReferee,txtLineman1,txtLineman2,txtFourthOff;
    View v;
    Weather weather = null;
    Match m = null;
    public FactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_facts, container, false);
        // Inflate the layout for this fragment
        addControls();
        weather = (Weather) getActivity().getIntent().getSerializableExtra("weather");
        m = (Match) getActivity().getIntent().getSerializableExtra("detailmatch");
        if(weather != null && m != null){
            setData();
        }else{
            facts.setVisibility(View.INVISIBLE);
        }
        return v;
    }
    private void setData(){
        List<String> offcials = m.getOfficials();
        for(int i = 0 ; i < 4 ; i++){
            String t = offcials.get(i);
            int lastSpace = offcials.get(i).lastIndexOf(' ');
            if(i==0) txtReferee.setText(t.substring(0,lastSpace)+" "+t.substring(lastSpace,t.length()));
            if(i==1)txtLineman1.setText(t.substring(0,lastSpace)+" "+t.substring(lastSpace,t.length()));
            if(i==2)txtLineman2.setText(t.substring(0,lastSpace)+" "+t.substring(lastSpace,t.length()));
            if(i==3)txtFourthOff.setText(t.substring(0,lastSpace)+" "+t.substring(lastSpace,t.length()));
        }
        String referee = offcials.get(0);

        txtStadium.setText(m.getLocation());
        txtCity.setText(m.getVenue());
        txtAtten.setText(m.getAttendance());
        txtCondi.setText(weather.getDescription());
        txtTemper.setText(weather.getTempCelsius()+"℃");
        txtHumidity.setText(weather.getHumidity()+"%");
        txtWind.setText(weather.getWindSpeed()+" km/h");
    }
    private void addControls(){
        facts = v.findViewById(R.id.facts);
        txtStadium = v.findViewById(R.id.StadiumName);
        txtCity = v.findViewById(R.id.City);
        txtAtten = v.findViewById(R.id.attendance);
        txtTemper = v.findViewById(R.id.temperature);
        txtCondi = v.findViewById(R.id.conditions);
        txtHumidity = v.findViewById(R.id.humidity);
        txtWind = v.findViewById(R.id.wind);
        txtReferee = v.findViewById(R.id.mainreferee);
        txtLineman1 = v.findViewById(R.id.lineman1);
        txtLineman2 = v.findViewById(R.id.lineman2);
        txtFourthOff = v.findViewById(R.id.fourthofficials);
    }
}
