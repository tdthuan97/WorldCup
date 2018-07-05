package com.example.home.bt_worldcup.activities;





import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.home.bt_worldcup.models.Weather;
import com.example.home.bt_worldcup.utils.BottomNavigationViewHelper;
import com.example.home.bt_worldcup.R;
import com.example.home.bt_worldcup.fragments.HomeFragment;
import com.example.home.bt_worldcup.fragments.LiveScoresFragment;
import com.example.home.bt_worldcup.fragments.NewsFragment;
import com.example.home.bt_worldcup.fragments.StadingsFragment;
import com.example.home.bt_worldcup.models.Group;
import com.example.home.bt_worldcup.models.Match;
import com.example.home.bt_worldcup.models.Team;
import com.example.home.bt_worldcup.models.TeamEvents;
import com.example.home.bt_worldcup.models.TeamGroup;
import com.example.home.bt_worldcup.models.TeamStatistics;
import com.example.home.bt_worldcup.models.TeamsInMatch;
import com.example.home.bt_worldcup.utils.AppConstant;
import com.example.home.bt_worldcup.utils.CheckConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //TeamPresenter teamPresenter = new TeamPresenter();

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    //ArrayList<Team> teams;
    ArrayList<Group> groups;
    ArrayList<Match> matches;
    public static ArrayList<Team> allTeams;
    public static HashMap<String,Integer> mapImg ;

    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    Format formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();

        actionBar();
        onNavigationItemSelected();

        if(CheckConnection.haveNetworkConnection(MainActivity.this)) {
            allTeams = getAllTeams();
            mapImg = new HashMap<String,Integer>();

            groups = getResultGroups();
            matches = getAllMatches();
        }


    }

    private void actionBar() {
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        setFragmentForTab(0);


    }

    private void onNavigationItemSelected() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        setFragmentForTab(0);
                        return true;
                    case R.id.navigation_livescores:
                        setFragmentForTab(1);
                        return true;
                    case R.id.navigation_news:
                        setFragmentForTab(2);
                        return true;
                    case R.id.navigation_standings:
                        setFragmentForTab(3);
                        return true;
                }
                return false;
            }
        });
    }

    private void setFragmentForTab(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new HomeFragment();
                toolbar.setTitle("2018 FIFA WORLD CUP RUSSIA");
                break;
            case 1:
                fragment = new LiveScoresFragment();
                toolbar.setTitle("LIVE SCORES");
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("matches", (ArrayList<? extends Parcelable>) matches);
                fragment.setArguments(bundle);
                Collections.sort(allTeams, new Comparator<Team>() {
                    public int compare(Team team1, Team team2) {
                        return team1.getId() - team2.getId();
                    }
                });
                int a = 0;
                for(int k = 1 ; a  < allTeams.size() ; k++){
                    mapImg.put(allTeams.get(a++).getFifa_code(),k);
                }
                break;
            case 2:
                fragment = new NewsFragment();
                toolbar.setTitle("NEWS");
                break;
            case 3:
                fragment = new StadingsFragment();
                toolbar.setTitle("STANDINGS");
                Bundle bundle1 = new Bundle();
                bundle1.putParcelableArrayList("result_groups", (ArrayList<? extends Parcelable>) groups);
                fragment.setArguments(bundle1);
                break;
        }
        if (fragment != null) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.commit();
        }
    }

    public ArrayList<Team> getAllTeams() {

        final ArrayList<Team> tempt = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,AppConstant.TeamsURL,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int id = 0, group_id = 0;
                    String counttry = "", fifa_code = "", group_letter = "";
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = (JSONObject) response.get(i);
                            id = jsonObject.getInt("id");
                            counttry = jsonObject.getString("country");
                            fifa_code = jsonObject.getString("fifa_code");
                            group_id = jsonObject.getInt("group_id");
                            group_letter = jsonObject.getString("group_letter");
                            tempt.add(new Team(id, counttry, fifa_code, group_id, group_letter));
                        } catch (Exception e) {
                            Log.d("ERROR"," ZZZZ");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString() + "xyz");
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);

        return tempt;
    }
    public ArrayList<Group> getResultGroups() {

        final ArrayList<Group> groupArrayList = new ArrayList<>();

        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,AppConstant.GroupsURL,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {

                    for(int i = 0 ; i < response.length() ; i++){
                        try {
                            JSONObject jsonObject = (JSONObject) response.get(i);
                            int id = jsonObject.getInt("id");
                            String letter = jsonObject.getString("letter");
                            ArrayList<TeamGroup> teamGroups = new ArrayList<>();
                            JSONArray jsonArray = jsonObject.getJSONArray("ordered_teams");
                            for(int k = 0 ; k < jsonArray.length(); k++) {
                                JSONObject teamsObject = jsonArray.getJSONObject(k);

                                int id_team = teamsObject.getInt("id");
                                String country = teamsObject.getString("country");
                                String fifa_code = teamsObject.getString("fifa_code");
                                int points = teamsObject.getInt("points");
                                int wins = teamsObject.getInt("wins");
                                int draws = teamsObject.getInt("draws");
                                int losses = teamsObject.getInt("losses");
                                int game_played = teamsObject.getInt("games_played");
                                int goals_for = teamsObject.getInt("goals_for");
                                int goals_against = teamsObject.getInt("goals_against");
                                int goals_differential = teamsObject.getInt("goal_differential");
                                teamGroups.add(new TeamGroup(id_team, country, fifa_code, points, wins, draws, losses, game_played, goals_for, goals_against, goals_differential));
                            }
                            groupArrayList.add(new Group(id,letter,teamGroups));
                        } catch (Exception e) {
                            Log.d("ERROR"," ZZZZ");
                            e.printStackTrace();
                        }


                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString() + "xyz");
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);

        return groupArrayList;
    }
    public ArrayList<Match> getAllMatches(){

        final ArrayList<Match> matchArrayList = new ArrayList<>();
        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,AppConstant.MatchesURL,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for(int i = 0 ; i < response.length() ; i++){
                        try {
                            JSONObject match = (JSONObject) response.get(i);
                            String venue = match.getString("venue");
                            String location = match.getString("location");
                            String status = match.getString("status");
                            Object time = match.get("time");
                            String fifa_id = match.getString("fifa_id");
                            Object o_weather = match.get("weather");
                            Weather weather = null;
                            if(o_weather.toString().length() != 4){
                                JSONObject tempt = match.getJSONObject("weather");
                                weather = new Weather(
                                        tempt.getString("humidity"),
                                        tempt.getString("temp_celsius"),
                                        tempt.getString("temp_farenheit"),
                                        tempt.getString("wind_speed"),
                                        tempt.getString("description"));
                            }
                            String attendance = match.getString("attendance");
                            if(attendance == "null") attendance = null;

                            Object o_officials = match.get("officials");
                            List<String> officials = new ArrayList<>();
                            if(o_officials.toString().length() != 4){
                                JSONArray array_officials = match.getJSONArray("officials");
                                if(array_officials != null) {
                                    for (int k = 0; k < array_officials.length(); k++) {
                                        officials.add(array_officials.getString(k));
                                    }
                                }
                            }


                            String stage_name = match.getString("stage_name");
                            String home_team_country = match.getString("home_team_country");
                            String away_team_country = match.getString("away_team_country");

                            String datetime = match.getString("datetime");
                            Date date = formatDate.parse(datetime);
                            cal.setTime(date); // sets calendar time/date
                            cal.add(Calendar.HOUR_OF_DAY, 7); // adds 7 hour
                            datetime = formatter.format(cal.getTime());

                            Object winner = match.get("winner");
                            Object winner_code = match.get("winner_code");

                            List<TeamEvents> home_team_events = new ArrayList<>();
                            List<TeamEvents> away_team_events = new ArrayList<>();

                            TeamsInMatch home_team = null;
                            if(home_team_country != "null") {
                                JSONObject o_home_team = match.getJSONObject("home_team");
                                        home_team = new TeamsInMatch(
                                        o_home_team.getString("country"),
                                        o_home_team.getString("code"),
                                        o_home_team.getInt("goals"),
                                        o_home_team.get("penalties")
                                );
                                JSONArray a_home_team_events = match.getJSONArray("home_team_events");
                                for(int k = 0 ; k < a_home_team_events.length() ; k++){
                                    JSONObject o_event = (JSONObject) a_home_team_events.get(k);
                                    home_team_events.add(new TeamEvents(
                                            o_event.getInt("id"),
                                            o_event.getString("type_of_event"),
                                            o_event.getString("player"),
                                            o_event.getString("time")));
                                }
                            }

                            TeamsInMatch away_team = null;
                            if(away_team_country != "null") {
                                JSONObject o_away_team = match.getJSONObject("away_team");
                                        away_team = new TeamsInMatch(
                                        o_away_team.getString("country"),
                                        o_away_team.getString("code"),
                                        o_away_team.getInt("goals"),
                                        o_away_team.get("penalties")
                                );
                                JSONArray a_away_team_events = match.getJSONArray("away_team_events");
                                for(int k = 0 ; k < a_away_team_events.length() ; k++){
                                    JSONObject o_event = (JSONObject) a_away_team_events.get(k);
                                    away_team_events.add(new TeamEvents(
                                            o_event.getInt("id"),
                                            o_event.getString("type_of_event"),
                                            o_event.getString("player"),
                                            o_event.getString("time")));
                                }
                            }



                            TeamStatistics home_team_statistics = null;
                            TeamStatistics away_team_statistics = null;
                            if(status.equals("completed")){
                                JSONObject o_home_team_statistics =  match.getJSONObject("home_team_statistics");
                                home_team_statistics = new TeamStatistics(
                                        o_home_team_statistics.getInt("ball_possession"),
                                        o_home_team_statistics.getInt("yellow_cards"),
                                        o_home_team_statistics.getInt("red_cards"),
                                        o_home_team_statistics.get("fouls_committed"));
                                JSONObject o_away_team_statistics =  match.getJSONObject("away_team_statistics");
                                away_team_statistics = new TeamStatistics(
                                        o_away_team_statistics.getInt("ball_possession"),
                                        o_away_team_statistics.getInt("yellow_cards"),
                                        o_away_team_statistics.getInt("red_cards"),
                                        o_away_team_statistics.get("fouls_committed"));
                            }
                            Object last_event_update_at = match.get("last_event_update_at");
                            Object last_score_update_at = match.get("last_score_update_at");
                            matchArrayList.add(new Match(venue,location,status,time.toString(),fifa_id,weather,attendance,officials,
                                    stage_name,home_team_country,away_team_country,datetime,winner.toString(),winner_code.toString(),home_team,away_team,
                                    home_team_events,away_team_events,home_team_statistics,away_team_statistics,last_event_update_at.toString(),last_score_update_at.toString()));
                        } catch (Exception e) {
                            Log.d("AAA"+i+" ",e.toString());
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString() + "huhuhu");
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);

        return matchArrayList;

    }
}

