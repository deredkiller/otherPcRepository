package com.example.myprojectmolegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Settings extends AppCompatActivity {
    Switch backgroundMusic, scoreDataSaving, audioTaps,inGameBackgroundMusic;
    Intent intent, intentSettings;
    Boolean background,inGameBackground,autoSave,moleTapsAudio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        backgroundMusic = (Switch) findViewById(R.id.backgroundMusic);
        scoreDataSaving = (Switch) findViewById(R.id.scoreDataSaving);
        inGameBackgroundMusic =(Switch) findViewById(R.id.backgroundMusicInGame);
        audioTaps = (Switch) findViewById(R.id.audioTaps);
        intent = new Intent(Settings.this, Menu.class);
        intentSettings = new Intent(Settings.this, Settings.class);
        setChecked();
        backgroundMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    intent.putExtra("BACKGROUND", true);
                } else {
                    intent.putExtra("BACKGROUND", false);
                }

            }
        });


        scoreDataSaving.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    intent.putExtra("AUTO_SAVE", true);
                } else {
                    intent.putExtra("AUTO_SAVE", false);
                }

            }
        });

        audioTaps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    intent.putExtra("TAP_AUDIO", true);
                } else {
                    intent.putExtra("TAP_AUDIO", false);
                }

            }
        });

        inGameBackgroundMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    intent.putExtra("IN_GAME_BACKGROUND", true);
                } else {
                    intent.putExtra("IN_GAME_BACKGROUND", false);
                }

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(intent);
                return true;
            case R.id.item2:
                startActivity(intentSettings);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    public void setChecked(){
        backgroundMusic.setChecked(true);
        scoreDataSaving.setChecked(true);
        audioTaps.setChecked(true);
        inGameBackgroundMusic.setChecked(true);
        background=true;
        inGameBackground=true;
        autoSave=true;
        moleTapsAudio=true;
    }
}