package com.example.home.bt_worldcup.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.home.bt_worldcup.R;
import com.example.home.bt_worldcup.adapters.MatchesAdapter;
import com.example.home.bt_worldcup.models.Match;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Home on 6/24/2018.
 */

public class LiveScoresFragment extends Fragment {

    RecyclerView recyclerView;
    MatchesAdapter matchesAdapter;
    ArrayList<ArrayList<Match>> AllMatchesDate;

    public LiveScoresFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_livescores, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewMatches);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        AllMatchesDate = new ArrayList<>();

        Bundle bundle = getArguments();
        if(bundle != null) {
            DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            DateFormat formatDate1 = new SimpleDateFormat("yyyy-MM-dd");
            Date ttt = new Date();
            String today = formatDate1.format(ttt);
            int pos = 0;
            boolean getNextMatch = false;
            ArrayList<Match> allmatches=  bundle.getParcelableArrayList("matches");
            if(allmatches.size() > 0) {
                ArrayList<String> setDate = new ArrayList<>();
                Date startDate = new Date(2018,5, 14);
                Date endDate = new Date(2018,6,16);
                LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
                    setDate.add("20"+date.toString().substring(2, date.toString().length()));
                }
                try {
                    String temp = allmatches.get(0).getDatetime();
                    Log.d("DDDD",temp);
                    Date date = formatDate.parse(temp);

                    String newDate = formatDate1.format(date);
                    int k = 0;
                    for(int i = 0 ; i < setDate.size() ;i++ ){
                        String tempDate = setDate.get(i);
                        ArrayList<Match> matchesDate = new ArrayList<>();
                        while(newDate.equals(tempDate)){

                            matchesDate.add(allmatches.get(k++));
                            if(k  < allmatches.size()) {
                                newDate = allmatches.get(k).getDatetime();
                                Date ddd = formatDate.parse(newDate);
                                newDate = formatDate1.format(ddd);
                            }else
                                break;
                        }
                        if(matchesDate.size() > 0) {
                            if(!getNextMatch){
                                if(matchesDate.get(0).getStatus().equals("in progress") ||
                                        (matchesDate.get(0).getStatus().equals("future"))) {
                                    pos = AllMatchesDate.size();
                                    getNextMatch = true;
                                }
                            }
                            AllMatchesDate.add(matchesDate);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                matchesAdapter = new MatchesAdapter(getContext().getApplicationContext(),AllMatchesDate);
                recyclerView.setAdapter(matchesAdapter);
                recyclerView.getLayoutManager().scrollToPosition(pos);
            }

        }
        return view;
    }

}
