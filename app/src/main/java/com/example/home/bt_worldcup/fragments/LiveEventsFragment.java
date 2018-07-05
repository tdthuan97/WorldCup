package com.example.home.bt_worldcup.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home.bt_worldcup.R;
import com.example.home.bt_worldcup.adapters.EventAdapter;
import com.example.home.bt_worldcup.models.TeamEvents;
import com.example.home.bt_worldcup.models.TeamsInMatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveEventsFragment extends Fragment {

    RecyclerView recyclerViewEvents;
    View v;
    List<TeamEvents> homeEvents = null, awayEvents = null;
    ArrayList<TeamEvents> eventsMatch;
    EventAdapter eventAdapter;


    public LiveEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_liveevents, container, false);
        addControls();
        eventsMatch = new ArrayList<>();
        TeamsInMatch teamHome = (TeamsInMatch) getActivity().getIntent().getSerializableExtra("hometeam");
        TeamsInMatch teamAway = (TeamsInMatch) getActivity().getIntent().getSerializableExtra("awayteam");
        homeEvents = (List<TeamEvents>) getActivity().getIntent().getSerializableExtra("homeevents");
        awayEvents = (List<TeamEvents>) getActivity().getIntent().getSerializableExtra("awayevents");
        int goalsHome = 0,goalsAway = 0;
        if (homeEvents != null && awayEvents != null) {
            for (int i = 0; i < homeEvents.size(); i++) {

                TeamEvents event = new TeamEvents(
                        homeEvents.get(i).getId(),
                        homeEvents.get(i).getTypeOfEvent(),
                        homeEvents.get(i).getPlayer(),
                        teamHome.getCountry(),
                        teamHome.getCode(),
                        homeEvents.get(i).getTime());
                eventsMatch.add(event);
            }
            for (int i = 0; i < awayEvents.size(); i++) {
                TeamEvents event = new TeamEvents(
                        awayEvents.get(i).getId(),
                        awayEvents.get(i).getTypeOfEvent(),
                        awayEvents.get(i).getPlayer(),
                        teamAway.getCountry(),
                        teamAway.getCode(),
                        awayEvents.get(i).getTime());
                eventsMatch.add(event);
            }
            Collections.sort(eventsMatch, new Comparator<TeamEvents>() {
                public int compare(TeamEvents man1, TeamEvents man2) {
                    return getTime(man1.getTime())-getTime(man2.getTime());
                }
            });
        }
        for(int i = 0 ; i < eventsMatch.size() ;i++) {

            if(i > 0){
                if (duplicatedEvent(eventsMatch.get(i-1),eventsMatch.get(i))) {
                    eventsMatch.remove(i);
                    i--;
                    continue;
                }
            }
            if(eventsMatch.get(i).getCountry().equals(teamHome.getCountry())){
                if (eventsMatch.get(i).getTypeOfEvent().equals("goal-own"))
                    goalsAway++;
                else if (eventsMatch.get(i).getTypeOfEvent().contains("goal"))
                    goalsHome++;
            }else if(eventsMatch.get(i).getCountry().equals(teamAway.getCountry())){
                if (eventsMatch.get(i).getTypeOfEvent().equals("goal-own"))
                    goalsHome++;
                else if (eventsMatch.get(i).getTypeOfEvent().contains("goal"))
                    goalsAway++;
            }
            eventsMatch.get(i).setGoalsHome(goalsHome);
            eventsMatch.get(i).setGoalsAway(goalsAway);
        }
        Collections.reverse(eventsMatch);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewEvents.setLayoutManager(layoutManager);
        eventAdapter = new EventAdapter(getContext(),eventsMatch,teamHome.getCountry(),teamAway.getCountry());
        recyclerViewEvents.setAdapter(eventAdapter);
        return v;
    }
    private void addControls(){
        recyclerViewEvents = v.findViewById(R.id.recyclerLiveEvents);
    }
    private int getTime(String time){
        int result = 0;
        String[]a = time.split("\\+");
        for(int i = 0 ; i < a.length;i++)
            result += Integer.parseInt(a[i].replace("'",""));
        return result;
    }
    private boolean duplicatedEvent(TeamEvents event1, TeamEvents event2){
        int t1 = getTime(event1.getTime()), t2 = getTime(event2.getTime());
        if(event1.getTypeOfEvent().equals(event2.getTypeOfEvent()) && (t1 + 1 == t2) && event1.getPlayer().equals(event2.getPlayer()))
            return true;
        return false;
    }
}
