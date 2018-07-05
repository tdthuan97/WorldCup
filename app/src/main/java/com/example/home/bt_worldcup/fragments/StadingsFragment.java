package com.example.home.bt_worldcup.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.home.bt_worldcup.R;
import com.example.home.bt_worldcup.activities.MainActivity;
import com.example.home.bt_worldcup.adapters.GroupsAdapter;
import com.example.home.bt_worldcup.models.Group;
import com.example.home.bt_worldcup.models.Team;
import com.example.home.bt_worldcup.models.TeamGroup;
import com.example.home.bt_worldcup.presenters.TeamPresenter;
import com.example.home.bt_worldcup.utils.AppConstant;
import com.example.home.bt_worldcup.utils.CheckConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Home on 6/24/2018.
 */

public class StadingsFragment extends Fragment{

    Spinner spinner;
    RecyclerView recyclerView;
    ArrayList<Group> groups;
    GroupsAdapter groupsAdapter;
    ArrayAdapter<CharSequence> adapterSpinner;


    public StadingsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stadings, container, false);
        spinner = view.findViewById(R.id.spinnerSearchStage);
        recyclerView = view.findViewById(R.id.recyclerviewGroupsResult);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapterSpinner = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.stage_result, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        Bundle bundle = getArguments();
        if(bundle != null) {
            groups =  bundle.getParcelableArrayList("result_groups");
            groupsAdapter = new GroupsAdapter(getActivity().getApplicationContext(),groups);
            recyclerView.setAdapter(groupsAdapter);

        }


        /*DividerItemDecoration itemDecoration = new DividerItemDecoration(this,layoutManager.getOrientation());
        //recyclerView.addItemDecoration(itemDecoration);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        //Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.custom_divider);
        //itemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());*/


        return view;
    }
    public ArrayList<Group> getResultGroups() {

        final ArrayList<Group> groupArrayList = new ArrayList<>();

        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,AppConstant.GroupsURL,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {

                    for(int i = 0 ; i < response.length() ; i++){
                        try {
                            JSONObject jsonObject = (JSONObject) response.get(i);
                            JSONObject groupObject = jsonObject.getJSONObject("group");
                            int id = groupObject.getInt("id");
                            String letter = groupObject.getString("letter");
                            ArrayList<TeamGroup> teamGroups = new ArrayList<>();
                            JSONArray jsonArray = groupObject.getJSONArray("teams");
                            for(int k = 0 ; k < jsonArray.length(); k++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(k);
                                JSONObject teamsObject = jsonObject1.getJSONObject("team");
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
                CheckConnection.ShowToast_Short(getActivity(), error.toString() + "xyz");
            }
        });
        requestQueue.add(jsonArrayRequest);

        return groupArrayList;
    }
}
