package com.example.home.bt_worldcup.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.home.bt_worldcup.R;
import com.example.home.bt_worldcup.activities.MatchDetailsActivity;
import com.example.home.bt_worldcup.models.Match;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Home on 6/26/2018.
 */

public class MatchesAdapter  extends RecyclerView.Adapter<MatchesAdapter.ItemHolder>{
    Context context;
    ArrayList<ArrayList<Match>> AllMatchesDate;
    AMatchAdapter aMatchAdapter;

    public MatchesAdapter(Context context, ArrayList<ArrayList<Match>> allMatchesDate) {
        this.context = context;
        AllMatchesDate = allMatchesDate;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_matches_day,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        final ArrayList<Match> matchesDate = AllMatchesDate.get(position);
        String stagename = matchesDate.get(0).getStageName();
        String stage = "";
        if(stagename.equals("Final"))
            stage = "Final";
        else if(stagename.equals("Play-off for third place"))
            stage = "Play-off for third place";
        else if(stagename.equals("Semi-finals"))
            stage = "Semi-finals";
        else if(stagename.equals("Quarter-finals"))
            stage = "Quarter-finals";
        else if(stagename.equals("Round of 16"))
            stage = "Round of 16";

        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateFormat formatDate1 = new SimpleDateFormat("dd-MM-yyyy");

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        String yesterday = formatDate1.format(cal.getTime());
        String today = formatDate1.format(Calendar.getInstance().getTime());
        cal.add(Calendar.DATE, +2);
        String tomorrow = formatDate1.format(cal.getTime());

        try {
            Date tempt = formatDate.parse(matchesDate.get(0).getDatetime());
            String dateMatch = formatDate1.format(tempt).toString();
            if(dateMatch.equals(today))
                dateMatch = "Today";
            else if(dateMatch.equals(yesterday))
                dateMatch = "Yesterday";
            else if(dateMatch.equals(tomorrow))
                dateMatch = "Tomorrow";
            holder.date.setText(dateMatch+"  "+stage);
            aMatchAdapter = new AMatchAdapter(context,matchesDate);
            holder.listViewMatchesDate.setAdapter(aMatchAdapter);
            setListViewHeightBasedOnChildren(holder.listViewMatchesDate);

            holder.listViewMatchesDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(matchesDate.get(i).getHomeTeamCountry() != "null" && matchesDate.get(i).getAwayTeamCountry() != "null") {
                        Intent intent = new Intent(context, MatchDetailsActivity.class);
                        intent.putExtra("detailmatch", (Serializable) matchesDate.get(i));
                        intent.putExtra("hometeam", (Serializable)matchesDate.get(i).getHomeTeam());
                        intent.putExtra("awayteam", (Serializable)matchesDate.get(i).getAwayTeam());
                        intent.putExtra("homePens",matchesDate.get(i).getHomeTeam().getPenalties().toString());
                        intent.putExtra("awayPens",matchesDate.get(i).getAwayTeam().getPenalties().toString());
                        if(matchesDate.get(i).getStatus().equals("completed") ||matchesDate.get(i).getStatus().equals("in progress") ) {
                            intent.putExtra("homeevents", (Serializable) matchesDate.get(i).getHomeTeamEvents());
                            intent.putExtra("awayevents", (Serializable) matchesDate.get(i).getAwayTeamEvents());
                            intent.putExtra("statisticshometeam", (Serializable)matchesDate.get(i).getHomeTeamStatistics());
                            intent.putExtra("statisticsawayteam", (Serializable)matchesDate.get(i).getAwayTeamStatistics());
                            intent.putExtra("weather", (Serializable)matchesDate.get(i).getWeather());
                        }
                        context.startActivity(intent);
                    }
                }
            });

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return AllMatchesDate.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        TextView date;
        ListView listViewMatchesDate;

        public ItemHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.textviewDay);
            listViewMatchesDate = itemView.findViewById(R.id.listviewmatches);
        }
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
