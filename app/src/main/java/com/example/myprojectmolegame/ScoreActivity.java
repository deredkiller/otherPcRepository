package com.example.myprojectmolegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

public class ScoreActivity extends AppCompatActivity {
    private ListView scoreList;
    private DBHelper dataBase;
    private TextView userName,score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        dataBase= new DBHelper(this);
        scoreList=findViewById(R.id.scoreList);
        showScores();

    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    public void showScores() {

    }
}
