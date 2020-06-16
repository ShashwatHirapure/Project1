package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Rewards extends AppCompatActivity {

    private ProgressBar pb3,pb5,pb7,pb10,pb15,pb21;
    private TextView pbtv3,pbtv5,pbtv7,pbtv10,pbtv15,pbtv21;
    private int ID,points;
    DBHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
database=new DBHelper(Rewards.this);
        pb3 = findViewById(R.id.pb3);
        pb5 = findViewById(R.id.pb5);
        pb7 = findViewById(R.id.pb7);
        pb10 = findViewById(R.id.pb10);
        pb15 = findViewById(R.id.pb15);
        pb21 = findViewById(R.id.pb21);

        pbtv3 = findViewById(R.id.pbtv3);
        pbtv5 = findViewById(R.id.pbtv5);
        pbtv7 = findViewById(R.id.pbtv7);
        pbtv10 = findViewById(R.id.pbtv10);
        pbtv15 = findViewById(R.id.pbtv15);
        pbtv21 = findViewById(R.id.pbtv21);




        Intent intent = getIntent();
        final int progress = intent.getIntExtra("progress",0);
        ID = Integer.valueOf(intent.getStringExtra("id"));
        points=intent.getIntExtra("point",0);

        pb3.setProgress(progress);
        pb5.setProgress(progress);
        pb7.setProgress(progress);
        pb10.setProgress(progress);
        pb15.setProgress(progress);
        pb21.setProgress(progress);

        pbtv3.setText(completion(points));
        pbtv5.setText(completion(points));
        pbtv7.setText(completion(points));
        pbtv10.setText(completion(points));
        pbtv15.setText(completion(points));
        pbtv21.setText(completion(points));


        final Button collect = findViewById(R.id.collect);
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectRewards(progress);
            }
        });


        int points = 0;
        if(progress<3){
            pbtv15.setEnabled(false);
            pbtv21.setEnabled(false);
            pbtv7.setEnabled(false);
            pbtv5.setEnabled(false);
            pbtv10.setEnabled(false);



            pb5.setProgress(0);
            pbtv5.setText(completion(100));

            pb7.setProgress(0);
            pbtv7.setText(completion(150));

            pb10.setProgress(0);
            pbtv10.setText(completion(200));

            pb15.setProgress(0);
            pbtv15.setText(completion(300));

            pb21.setProgress(0);
            pbtv21.setText(completion(350));
             }
        if(progress<5){
            pbtv15.setEnabled(false);
            pbtv21.setEnabled(false);
            pbtv10.setEnabled(false);
            pbtv7.setEnabled(false);



            pb7.setProgress(0);
            pbtv7.setText(completion(150));

            pb10.setProgress(0);
            pbtv10.setText(completion(200));

            pb15.setProgress(0);
            pbtv15.setText(completion(300));

            pb21.setProgress(0);
            pbtv21.setText(completion(350));
        }
        if(progress<7){
            pbtv15.setEnabled(false);
            pbtv21.setEnabled(false);
            pbtv10.setEnabled(false);

            pb10.setProgress(0);
            pbtv10.setText(completion(200));

            pb15.setProgress(0);
            pbtv15.setText(completion(300));

            pb21.setProgress(0);
            pbtv21.setText(completion(350));
        }
        if(progress<10){
            pbtv15.setEnabled(false);
            pbtv21.setEnabled(false);
            pb15.setProgress(0);
            pbtv15.setText(completion(300));

            pb21.setProgress(0);
            pbtv21.setText(completion(350));
        }
        if(progress<15){
           pbtv21.setEnabled(false);
            pb21.setProgress(0);
            pbtv21.setText(completion(350));
        }

    }

    private void collectRewards(int progress)
    {
        int points = 0;
        if(progress>=3){
            points+=50;
            database.update_points(ID,points);
        }
        if(progress>=5){
            points+=100;
            database.update_points(ID,points);
        }
        if(progress>=7){
            points+=150;
            database.update_points(ID,points);
        }
        if(progress>=10){
            points+=200;
            database.update_points(ID,points);
        }
        if(progress>=15){
            points+=300;
            database.update_points(ID,points);
        }
        if(progress>=21){
            points+=350;
            database.update_points(ID,points);
        }
        if(points>1)
        Toast.makeText(this, points+" Points Received", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "No points to collect", Toast.LENGTH_LONG).show();
    }

    private String completion(int points)
    {
        return points+"\npoints";
    }
}
