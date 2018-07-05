package com.example.home.bt_worldcup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home.bt_worldcup.R;
import com.example.home.bt_worldcup.models.Group;
import com.example.home.bt_worldcup.models.TeamGroup;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Home on 6/24/2018.
 */

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ItemHolder>{

    Context context;
    ArrayList<Group> groups;
    Integer[]image = {1,
            R.drawable.russia,R.drawable.saudiarabia,R.drawable.egypt,R.drawable.uruguay,R.drawable.portugal,
            R.drawable.spain,R.drawable.morocco,R.drawable.iran,R.drawable.france,R.drawable.australia,
            R.drawable.peru,R.drawable.denmark,R.drawable.argentina,R.drawable.iceland,R.drawable.croatia
            ,R.drawable.nigeria,R.drawable.brazil,R.drawable.switzerland,R.drawable.costarica,R.drawable.serbia
            ,R.drawable.germany,R.drawable.mexico,R.drawable.sweden,R.drawable.southkorea,R.drawable.belgium,
            R.drawable.panama,R.drawable.tunisia,R.drawable.england,R.drawable.poland,R.drawable.senegal,R.drawable.colombia,R.drawable.japan};

    public GroupsAdapter(Context context, ArrayList<Group> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext().getApplicationContext()).inflate(R.layout.item_group,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Group group = groups.get(position);
        String letter = group.getLetter();
        ArrayList<TeamGroup> teamGroups = group.getTeamGroupArrayList();
        /*Collections.sort(teamGroups, new Comparator<TeamGroup>() {
            public int compare(TeamGroup team1, TeamGroup team2) {
                int point = team2.getPoints() - team1.getPoints();
                if(point == 0){
                    return team2.getGoal_differential() - team1.getGoal_differential();
                }
                return point;
            }
        });*/
        holder.txtgroup.setText("Group "+letter);
        for(int i = 0 ; i < teamGroups.size() ; i++){
            TeamGroup x = teamGroups.get(i);
            String dif = "";
            if(x.getGoal_differential() > 0)
                dif = "+"+x.getGoal_differential();
            else
                dif = ""+x.getGoal_differential();
            if(i == 0){
                holder.txtNo1.setText(x.getFifa_code());
                holder.imgNo1.setImageResource(image[x.getId()]);
                holder.mpNo1.setText(x.getGame_played()+"");
                holder.goNo1.setText(dif);
                holder.poNo1.setText(x.getPoints()+"");
            }
            if(i == 1){
                holder.txtNo2.setText(x.getFifa_code());
                holder.imgNo2.setImageResource(image[x.getId()]);
                holder.mpNo2.setText(x.getGame_played()+"");
                holder.goNo2.setText(dif);
                holder.poNo2.setText(x.getPoints()+"");
            }
            if(i == 2){
                holder.txtNo3.setText(x.getFifa_code());
                holder.imgNo3.setImageResource(image[x.getId()]);
                holder.mpNo3.setText(x.getGame_played()+"");
                holder.goNo3.setText(dif);
                holder.poNo3.setText(x.getPoints()+"");
            }
            if(i == 3){
                holder.txtNo4.setText(x.getFifa_code());
                holder.imgNo4.setImageResource(image[x.getId()]);
                holder.mpNo4.setText(x.getGame_played()+"");
                holder.goNo4.setText(dif);
                holder.poNo4.setText(x.getPoints()+"");
            }
        }
    }
    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView txtNo1,txtNo2,txtNo3,txtNo4;
        public ImageView imgNo1,imgNo2,imgNo3,imgNo4;
        public TextView mpNo1,mpNo2,mpNo3,mpNo4;
        public TextView goNo1,goNo2,goNo3,goNo4;
        public TextView poNo1,poNo2,poNo3,poNo4;
        public TextView txtgroup;

        public ItemHolder(View itemView) {
            super(itemView);

            txtgroup = itemView.findViewById(R.id.textviewgroup);

            txtNo1 = itemView.findViewById(R.id.textviewno1);
            imgNo1 = itemView.findViewById(R.id.imageno1);
            mpNo1 = itemView.findViewById(R.id.textviewmpno1);
            goNo1 = itemView.findViewById(R.id.textviewgoalsno1);
            poNo1 = itemView.findViewById(R.id.textviewpointsno1);

            txtNo2 = itemView.findViewById(R.id.textviewno2);
            imgNo2 = itemView.findViewById(R.id.imageno2);
            mpNo2 = itemView.findViewById(R.id.textviewmpno2);
            goNo2 = itemView.findViewById(R.id.textviewgoalsno2);
            poNo2 = itemView.findViewById(R.id.textviewpointsno2);

            txtNo3 = itemView.findViewById(R.id.textviewno3);
            imgNo3 = itemView.findViewById(R.id.imageno3);
            mpNo3 = itemView.findViewById(R.id.textviewmpno3);
            goNo3 = itemView.findViewById(R.id.textviewgoalsno3);
            poNo3 = itemView.findViewById(R.id.textviewpointsno3);

            txtNo4 = itemView.findViewById(R.id.textviewno4);
            imgNo4 = itemView.findViewById(R.id.imageno4);
            mpNo4 = itemView.findViewById(R.id.textviewmpno4);
            goNo4 = itemView.findViewById(R.id.textviewgoalsno4);
            poNo4 = itemView.findViewById(R.id.textviewpointsno4);

        }
    }
}
