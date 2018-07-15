package com.example.home.bt_worldcup.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.home.bt_worldcup.R;
import com.example.home.bt_worldcup.activities.MainActivity;
import com.example.home.bt_worldcup.models.Match;
import com.example.home.bt_worldcup.models.Team;
import com.example.home.bt_worldcup.utils.AppConstant;
import com.example.home.bt_worldcup.utils.CheckConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.home.bt_worldcup.activities.MainActivity.allTeams;
import static com.example.home.bt_worldcup.activities.MainActivity.mapImg;

/**
 * Created by Home on 6/26/2018.
 */

public class AMatchAdapter extends BaseAdapter{

    Context context;
    ArrayList<Match> matchArrayListDate;

    Integer[] img = {1,
            R.drawable.russia,R.drawable.saudiarabia,R.drawable.egypt,R.drawable.uruguay,R.drawable.portugal,
            R.drawable.spain,R.drawable.morocco,R.drawable.iran,R.drawable.france,R.drawable.australia,
            R.drawable.peru,R.drawable.denmark,R.drawable.argentina,R.drawable.iceland,R.drawable.croatia
            ,R.drawable.nigeria,R.drawable.brazil,R.drawable.switzerland,R.drawable.costarica,R.drawable.serbia
            ,R.drawable.germany,R.drawable.mexico,R.drawable.sweden,R.drawable.southkorea,R.drawable.belgium,
            R.drawable.panama,R.drawable.tunisia,R.drawable.england,R.drawable.poland,R.drawable.senegal,R.drawable.colombia,R.drawable.japan};


    public AMatchAdapter(Context context, ArrayList<Match> matchArrayListDate) {
        this.context = context;
        this.matchArrayListDate = matchArrayListDate;
    }

    @Override
    public int getCount() {
        return matchArrayListDate.size();
    }

    @Override
    public Object getItem(int i) {
        return matchArrayListDate.get(i);
    }

        @Override
        public long getItemId(int i) {
            return i;
        }
        public class ViewHolder{
        public TextView home,away,result,resultPen,timeMatch;
        public ImageView imghome,imgaway;
        LinearLayout resultNoPen,match;

    }
    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_match, null);
                viewHolder.home = view.findViewById(R.id.hometeam);
                viewHolder.imghome = view.findViewById(R.id.imghome);
                viewHolder.away = view.findViewById(R.id.awayteam);
                viewHolder.imgaway = view.findViewById(R.id.imgaway);
                viewHolder.result = view.findViewById(R.id.resultmatch);
                viewHolder.resultPen = view.findViewById(R.id.resultPen);
                viewHolder.resultNoPen = view.findViewById(R.id.resultNoPen);
                viewHolder.timeMatch = view.findViewById(R.id.timeMatch);
                viewHolder.match = view.findViewById(R.id.match);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            DateFormat hourFormat = new SimpleDateFormat("HH:mm");
            Match m = (Match) getItem(i);
            if(allTeams.size() > 0) {
                Date date = null;
                try {
                    date = formatDate.parse(m.getDatetime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //Calendar cal = Calendar.getInstance(); // creates calendar
                //cal.setTime(date); // sets calendar time/date
                //cal.add(Calendar.HOUR_OF_DAY, 7); // adds 7 hour
                String hourMatch = hourFormat.format(date);// returns new date object, one hour in the future
                if (m.getHomeTeam() != null && m.getAwayTeam() != null) {
                    int goalsHome = m.getHomeTeam().getGoals();
                    int goalsAway = m.getAwayTeam().getGoals();
                    viewHolder.home.setText(m.getHomeTeamCountry());
                    viewHolder.away.setText(m.getAwayTeamCountry());
                    if (m.getStatus().equals("future")) {
                        viewHolder.result.setText(hourMatch);
                        viewHolder.result.setBackgroundResource(R.drawable.border_result_future);
                    }else if(m.getStatus().equals("completed") ||m.getStatus().equals("in progress")) {
                        viewHolder.result.setText(goalsHome + " - " + goalsAway);
                        if(m.getStatus().equals("completed")) {
                            if (goalsHome == goalsAway && !m.getStageName().equals("First stage")) {
                                viewHolder.resultPen.setVisibility(View.VISIBLE);
                                viewHolder.resultNoPen.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                        0,2f));
                                int penHome = Integer.parseInt(m.getHomeTeam().getPenalties().toString());
                                int penAway = Integer.parseInt(m.getAwayTeam().getPenalties().toString());
                                String winnerPen = penHome > penAway ? m.getHomeTeamCountry() : m.getAwayTeamCountry();
                                viewHolder.resultPen.setText(winnerPen +" PSO ("+penHome+" - "+penAway+")");
                                if(penHome > penAway)
                                    viewHolder.home.setTypeface(null, Typeface.BOLD);
                                else
                                    viewHolder.away.setTypeface(null, Typeface.BOLD);

                            }else{
                                if(goalsHome > goalsAway)
                                    viewHolder.home.setTypeface(null, Typeface.BOLD);
                                if(goalsHome < goalsAway)
                                    viewHolder.away.setTypeface(null, Typeface.BOLD);
                            }
                            viewHolder.result.setBackgroundResource(R.drawable.border_result_completed);
                        }
                        if(m.getStatus().equals("in progress")) {
                            viewHolder.resultNoPen.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    0,2f));
                            viewHolder.timeMatch.setVisibility(View.VISIBLE);
                            viewHolder.timeMatch.setText(m.getTime().toString()+"'");
                            viewHolder.result.setTextColor(Color.parseColor("#ffffff"));
                            viewHolder.result.setBackgroundResource(R.drawable.border_result_onprogess);
                        }

                    }
                    viewHolder.imghome.setImageResource(img[mapImg.get(m.getHomeTeam().getCode())]);
                    viewHolder.imgaway.setImageResource(img[mapImg.get(m.getAwayTeam().getCode())]);
                } else if (m.getAwayTeam() == null && m.getHomeTeam() == null) {

                    viewHolder.home.setText("");
                    viewHolder.away.setText("");
                    viewHolder.result.setText(hourMatch);
                    viewHolder.imghome.setImageResource(R.drawable.white);
                    viewHolder.imgaway.setImageResource(R.drawable.white);
                }
            }


        return view;
    }
}
