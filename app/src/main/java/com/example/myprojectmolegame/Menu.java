package com.example.myprojectmolegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class Menu extends AppCompatActivity implements View.OnClickListener {
    Button btnPlay, btnScore, btnInstructions, btnSettings, btnStop;
    String userName;
    Intent musicIntent;


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        musicIntent = new Intent(this, MyService.class);
        checkSettings();
        setContentView(R.layout.activity_menue);
        btnPlay = findViewById(R.id.btnPlay);
        btnScore = findViewById(R.id.btnScore);
        btnInstructions = findViewById(R.id.btnInstructions);
        btnSettings = findViewById(R.id.btnSettings);
        btnPlay.setOnClickListener(this);
        btnScore.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnInstructions.setOnClickListener(this);


    }

    private void checkSettings() {
        Intent oldIntent = getIntent();
        boolean check = oldIntent.getBooleanExtra("BACKGROUND", true);
        Log.d("checkSettings: ", check+"");
        if (check == true) {
            Log.d("checkSettings: ", "fuck");
            startService(musicIntent);
        }
        else {
            Log.d("checkSettings: ", "why");
            stopService(musicIntent);
        }

        check = oldIntent.getBooleanExtra("AUTO_SAVE", true);
        if (check == true) {

        }
        check = oldIntent.getBooleanExtra("TAP_AUDIO", true);
        if (check == true) {

        }
        check = oldIntent.getBooleanExtra("IN_GAME_BACKGROUND", true);
        if (check == true) {

        }

    }

    @Override
    public void onClick(View view) {
        if (view == btnPlay) {
            Intent intent = new Intent(this, MainActivity.class);
            Intent oldIntent = getIntent();
            userName = oldIntent.getStringExtra("USERNAME");
            intent.putExtra("USERNAME", userName);
            startActivity(intent);
        }
        if (view == btnScore) {
            Intent intent = new Intent(this, ScoreActivity.class);
            startActivity(intent);
        }
        if (view == btnSettings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
        if (view == btnInstructions) {
            Intent intent = new Intent(this, Instructions.class);
            startActivity(intent);
        }
        if (view == btnStop) {
            onPause();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        stopService(musicIntent);
    }
}