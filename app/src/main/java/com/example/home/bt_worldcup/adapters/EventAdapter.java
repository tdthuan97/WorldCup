package com.example.home.bt_worldcup.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home.bt_worldcup.R;
import com.example.home.bt_worldcup.activities.MatchDetailsActivity;
import com.example.home.bt_worldcup.fragments.LiveEventsFragment;
import com.example.home.bt_worldcup.models.TeamEvents;

import java.util.ArrayList;

import static com.example.home.bt_worldcup.activities.MainActivity.mapImg;
/**
 * Created by Home on 7/3/2018.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ItemHolder>{
    Context context;
    ArrayList<TeamEvents> teamEventsArrayList;
    String home,away;

    Integer[] img = {1,
            R.drawable.russia,R.drawable.saudiarabia,R.drawable.egypt,R.drawable.uruguay,R.drawable.portugal,
            R.drawable.spain,R.drawable.morocco,R.drawable.iran,R.drawable.france,R.drawable.australia,
            R.drawable.peru,R.drawable.denmark,R.drawable.argentina,R.drawable.iceland,R.drawable.croatia
            ,R.drawable.nigeria,R.drawable.brazil,R.drawable.switzerland,R.drawable.costarica,R.drawable.serbia
            ,R.drawable.germany,R.drawable.mexico,R.drawable.sweden,R.drawable.southkorea,R.drawable.belgium,
            R.drawable.panama,R.drawable.tunisia,R.drawable.england,R.drawable.poland,R.drawable.senegal,R.drawable.colombia,R.drawable.japan};


    public EventAdapter(Context context, ArrayList<TeamEvents> teamEventsArrayList, String home, String away) {
        this.context = context;
        this.teamEventsArrayList = teamEventsArrayList;
        this.home = home;
        this.away = away;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event,parent,false);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        TeamEvents event = teamEventsArrayList.get(position);
        int goalsHome = event.getGoalsHome(),goalsAway = event.getGoalsAway();
        String nameEvent = event.getTypeOfEvent();
        holder.imgCountry.setImageResource(img[mapImg.get(event.getCode_team())]);
        holder.txtTime.setText(event.getTime());
        holder.txtPlayer.setText(event.getPlayer());
        if(nameEvent.equals("goal-own")) {
            holder.txtEvent.setText("Own goal");
            holder.imgEvent.setImageResource(R.drawable.owngoal);
            holder.txtDes.setText(event.getCountry()+" score an own goal!");
            holder.txtGoal.setText(goalsHome+" - "+goalsAway);
            holder.txtGoal.setVisibility(View.VISIBLE);
        }else if(nameEvent.contains("substitution")) {
            holder.imgEvent.setImageResource(R.drawable.substitution);
            if(nameEvent.equals("substitution-in")){
                holder.txtEvent.setText("Substitution-in");
                holder.txtDes.setText(event.getCountry()+" substitution-in a player");
            }
            else if(nameEvent.equals("substitution-out")){
                holder.txtEvent.setText("Substitution-out");
                holder.txtDes.setText(event.getCountry()+" substitution-out a player");
            }
            holder.txtGoal.setVisibility(View.GONE);
        }else if(nameEvent.contains("goal")){
            holder.imgEvent.setImageResource(R.drawable.goalball);
            if(nameEvent.equals("goal-penalty")) {
                holder.txtEvent.setText("Penalty goal");
                holder.txtDes.setText(event.getCountry() + " successfully convert the penalty!");
            }else if (nameEvent.equals("goal")){
                holder.txtEvent.setText("Goal!");
                holder.txtDes.setText(event.getCountry() + " score!");
            }
            holder.txtGoal.setText(goalsHome+" - "+goalsAway);
            //holder..setTextColor(Color.parseColor("#4c2cc1"));

            holder.txtGoal.setVisibility(View.VISIBLE);
        }else if(nameEvent.equals("yellow-card")){
            holder.txtEvent.setText("Yellow card");
            holder.imgEvent.setImageResource(R.drawable.yellowcard);
            holder.txtDes.setText(event.getCountry()+" have a player yellow carded");
            holder.txtGoal.setVisibility(View.GONE);
        }else if(nameEvent.equals("red-card")){
            holder.txtEvent.setText("Red card");
            holder.imgEvent.setImageResource(R.drawable.red_card);
            holder.txtDes.setText(event.getCountry()+" have a player red carded");
            holder.txtGoal.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return teamEventsArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgCountry, imgEvent;
        public TextView txtTime,txtEvent, txtPlayer,txtDes,txtGoal;

        public ItemHolder(View itemView) {
            super(itemView);
            imgCountry = itemView.findViewById(R.id.eventCountry);
            imgEvent = itemView.findViewById(R.id.eventImg);
            txtTime = itemView.findViewById(R.id.eventTime);
            txtEvent = itemView.findViewById(R.id.eventName);
            txtPlayer = itemView.findViewById(R.id.eventPlayer);
            txtDes = itemView.findViewById(R.id.eventDescription);
            txtGoal = itemView.findViewById(R.id.eventGoal);

        }
    }
}
