package com.example.myprojectmolegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Settings extends MenuForAllActivity implements View.OnClickListener {
    Switch backgroundMusic, scoreDataSaving;
    SharedPreferences sp;
    Button resetBtn;
    DBHelper dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        dataBase= new DBHelper(this);
        backgroundMusic = (Switch) findViewById(R.id.backgroundMusic);
        scoreDataSaving = (Switch) findViewById(R.id.scoreDataSaving);
        resetBtn=findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(this);
        setChecked();
        sp=getSharedPreferences("settingPref" , Context.MODE_PRIVATE);
        backgroundMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("backgroundSettings",true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("backgroundSettings",false);
                    editor.commit();
                }

            }
        });


        scoreDataSaving.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("autoSave",true);
                    editor.commit();

                } else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("autoSave",false);
                    editor.commit();
                }

            }
        });


    }




    public void setChecked(){
        sp=getApplicationContext().getSharedPreferences("settingPref" , Context.MODE_PRIVATE);
        boolean autoSave = sp.getBoolean("autoSave", true);
        boolean backSound = sp.getBoolean("backgroundSettings", true);
        backgroundMusic.setChecked(backSound);
        scoreDataSaving.setChecked(autoSave);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("autoSave",true);
        editor.putBoolean("backgroundSettings",true);
        dataBase.deleteById();


    }
}