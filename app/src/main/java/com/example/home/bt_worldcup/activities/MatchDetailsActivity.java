package com.example.home.bt_worldcup.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.home.bt_worldcup.R;
import com.example.home.bt_worldcup.fragments.FactsFragment;
import com.example.home.bt_worldcup.fragments.LiveEventsFragment;
import com.example.home.bt_worldcup.fragments.StatisticsFragment;
import com.example.home.bt_worldcup.models.Match;
import com.example.home.bt_worldcup.models.TeamsInMatch;
import com.example.home.bt_worldcup.utils.PagerAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.home.bt_worldcup.activities.MainActivity.allTeams;
import static com.example.home.bt_worldcup.activities.MainActivity.mapImg;

public class MatchDetailsActivity extends AppCompatActivity {

    Toolbar toolbarMatchDetail;
    ViewPager pagerDetailMatch;
    TabLayout tabLayoutDetailMatch;
    PagerAdapter adapter;

    ImageView imgDetailHome, imgDetailAway;
    TextView txtDetailHome,txtDetailAway, txtDetailResult,txtDetailDate,txtStage, txtPlace, txtWinnerPen;
    LinearLayout detailMatch,backgroundDetailMatch,inDetailMatch;



    Integer[] img = {1,
            R.drawable.russia,R.drawable.saudiarabia,R.drawable.egypt,R.drawable.uruguay,R.drawable.portugal,
            R.drawable.spain,R.drawable.morocco,R.drawable.iran,R.drawable.france,R.drawable.australia,
            R.drawable.peru,R.drawable.denmark,R.drawable.argentina,R.drawable.iceland,R.drawable.croatia
            ,R.drawable.nigeria,R.drawable.brazil,R.drawable.switzerland,R.drawable.costarica,R.drawable.serbia
            ,R.drawable.germany,R.drawable.mexico,R.drawable.sweden,R.drawable.southkorea,R.drawable.belgium,
            R.drawable.panama,R.drawable.tunisia,R.drawable.england,R.drawable.poland,R.drawable.senegal,R.drawable.colombia,R.drawable.japan};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);
        addControls();
        actionBar();
        //onNavigationItemSelected();
        getData();



    }
    public void getData(){
        Match m = (Match) getIntent().getSerializableExtra("detailmatch");
        TeamsInMatch teamHome = (TeamsInMatch) getIntent().getSerializableExtra("hometeam");
        TeamsInMatch teamAway = (TeamsInMatch) getIntent().getSerializableExtra("awayteam");

        String temp = m.getDatetime();
        Log.d("FFFF",temp);
        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = null;
        try {
            date = formatDate.parse(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatDate1 = new SimpleDateFormat("dd-MM-yyyy");
        String newDate = formatDate1.format(date).replace("-",".");

        String place = "FIFA WORLD CUP\n"+m.getVenue()+", "+m.getLocation();
        txtPlace.setText(place);
        txtDetailHome.setText(m.getHomeTeamCountry());
        txtDetailAway.setText(m.getAwayTeamCountry());
        txtDetailDate.setText(newDate);
        if(teamHome != null && teamAway != null) {

            imgDetailHome.setImageResource(img[mapImg.get(teamHome.getCode())]);
            imgDetailAway.setImageResource(img[mapImg.get(teamAway.getCode())]);
            if(m.getStatus().equals("completed") || m.getStatus().equals("in progress")) {
                int goalsHome = teamHome.getGoals();
                int goalsAway = teamAway.getGoals();
                txtDetailResult.setText(goalsHome + " - " + goalsAway);
                int penHome = 0,  penAway = 0;
                if(teamHome != null && teamAway != null) {
                    penHome = Integer.parseInt(getIntent().getStringExtra("homePens"));
                    penAway= Integer.parseInt(getIntent().getStringExtra("awayPens"));
                    Log.d("HOMEAWAY",""+penHome+"   "+penAway);
                }
                String winnerPen = penHome > penAway ? m.getHomeTeamCountry() : m.getAwayTeamCountry();
                if(m.getStatus().equals("completed") && !m.getStageName().equals("First stage") && goalsHome == goalsAway){
                    detailMatch.setWeightSum(12);
                    backgroundDetailMatch.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            0,5f));
                    txtWinnerPen.setText(winnerPen+" win on penalties ("+penHome+" - "+penAway+")");
                    txtWinnerPen.setVisibility(View.VISIBLE);
                }
            }else{
                DateFormat hourFormat = new SimpleDateFormat("HH:mm");

                String hourMatch = hourFormat.format(date);
                txtDetailResult.setText(hourMatch);
            }
        }
        txtStage.setText(m.getStageName());



    }
    private void actionBar() {
        setSupportActionBar(toolbarMatchDetail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarMatchDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addControls(){
        toolbarMatchDetail = findViewById(R.id.toolbarDetailMatch);
        pagerDetailMatch = (ViewPager) findViewById(R.id.view_pager_detail_match);
        tabLayoutDetailMatch = (TabLayout) findViewById(R.id.tab_layout_detail_match);
        FragmentManager manager = getSupportFragmentManager();
        adapter = new PagerAdapter(manager);
        pagerDetailMatch.setAdapter(adapter);
        tabLayoutDetailMatch.setupWithViewPager(pagerDetailMatch);
        pagerDetailMatch.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutDetailMatch));

        tabLayoutDetailMatch.setTabsFromPagerAdapter(adapter);

        imgDetailHome = findViewById(R.id.detailImgHome);
        imgDetailAway = findViewById(R.id.detailImgAway);
        txtDetailHome = findViewById(R.id.detailHomeTeam);
        txtDetailAway = findViewById(R.id.detailAwayTeam);
        txtDetailResult = findViewById(R.id.detailResult);
        txtDetailDate = findViewById(R.id.detailDateMatch);
        txtStage = findViewById(R.id.detailStage);
        txtPlace = findViewById(R.id.detailPlace);

        txtWinnerPen = findViewById(R.id.detailMatchPen);
        detailMatch = findViewById(R.id.detailMatch);
        backgroundDetailMatch = findViewById(R.id.backgroundDetailMatch);
        inDetailMatch = findViewById(R.id.layout_detail_match);
    }
}
