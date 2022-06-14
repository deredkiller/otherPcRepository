package com.example.myprojectmolegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ScoreActivity extends MenuForAllActivity {
    private ListView scoreList;
    private DBHelper dataBase;
    private ArrayList<GameScore> scores;
    CustomScoreListAdapter scoreAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        dataBase = new DBHelper(this);
        scoreList = findViewById(R.id.scoreList);
        showScores();

    }




    public void showScores() {
        scores = dataBase.selectAll();
        scoreAdapter = new CustomScoreListAdapter(this,scores);
        scoreList.setAdapter(scoreAdapter);
    }


}

