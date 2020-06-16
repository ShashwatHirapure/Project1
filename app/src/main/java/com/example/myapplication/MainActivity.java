package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBHelper database;
    Cursor getdata;
    List<Integer> id, points, level, progress;
    List<String> name;
    RecyclerView rv_streak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new DBHelper(MainActivity.this);
        getdata = database.getAllData();
        rv_streak = findViewById(R.id.rv_streak);
    //  database.update_level(1,1);  //Update Level (id.level)
      //  database.update_streak(1,1);//Update Streak (id.Streak)
       //  database.update_points(1,50);//Update Points (id.Points)
        //database.putdata(1,"Test1",0,0,0);//Add Rituals
       // database.putdata(2,"Test2",50,1,1);//Add Rituals
        if (getdata != null) {
            id = new ArrayList<>();
            name = new ArrayList<>();
            points = new ArrayList<>();
            level = new ArrayList<>();
            progress = new ArrayList<>();
            while (getdata.moveToNext()) {
                id.add(Integer.valueOf(getdata.getString(0)));
                name.add(getdata.getString(1));
                points.add(Integer.valueOf(getdata.getString(2)));
                level.add(Integer.valueOf(getdata.getString(3)));
                progress.add(Integer.valueOf(getdata.getString(4)));
            }

            Adapter();

        }
    }

    //ADAPTER//
    public void Adapter() {
        RecyclerView.Adapter adapter = new RecyclerView.Adapter<MainActivity.Viewholder>() {

            @NonNull
            @Override
            public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemstreak, parent, false);
                return new Viewholder(v);
            }

            @Override
            public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {
                holder.tv_ritual_name.setText(name.get(position));
                holder.tv_level.setText("Level " + level.get(position));
               // holder.cv_item.setTag("" + id.get(position));
                int lev = level.get(position);
                int pb_max=21;
                if (level.get(position) == 1) {
                    holder.lav.setAnimation("level01.json");
                    pb_max=3;

                } else if (level.get(position) == 2) {
                    holder.lav.setAnimation("level02.json");
                    pb_max=5;
                } else if (level.get(position) == 3) {
                    holder.lav.setAnimation("level03.json");
                    pb_max=7;
                } else if (level.get(position) == 4) {
                    holder.lav.setAnimation("level04.json");
                    pb_max=10;
                } else if (level.get(position) == 5) {
                    holder.lav.setAnimation("level05.json");
                    pb_max=15;
                } else if (level.get(position) == 6) {
                    holder.lav.setAnimation("level06.json");
                    pb_max=21;
                }
                holder.progressBar.setMax(pb_max);
                holder.progressBar.setProgress(progress.get(position));

                holder.cv_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       System.out.println(points.get(holder.getAdapterPosition())+"=========adapterpos======="+ holder.getAdapterPosition());
                 //IMP FOR NEXT ACTIVITY
                        startActivity(new Intent(MainActivity.this,Rewards.class).putExtra("id", String.valueOf(id.get(holder.getAdapterPosition())))
                                .putExtra("point",points.get(holder.getAdapterPosition()))
                                .putExtra("progress",progress.get(holder.getAdapterPosition())));
                    }
                });

            }

            @Override
            public int getItemCount() {
                return id.size();
            }
        };
        rv_streak.setHasFixedSize(true);
        rv_streak.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rv_streak.setAdapter(adapter);
    }

    //VIEW HOLDER
    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tv_ritual_name, tv_level;
        LottieAnimationView lav;
        CardView cv_item;
ProgressBar progressBar;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_ritual_name = itemView.findViewById(R.id.tv_Ritual_Name);
            lav = itemView.findViewById(R.id.lav);
            tv_level = itemView.findViewById(R.id.tv_level);
            cv_item = itemView.findViewById(R.id.cv_item);
progressBar=itemView.findViewById(R.id.progressBar);
        }
    }
}
