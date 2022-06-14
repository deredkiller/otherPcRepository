package com.example.myprojectmolegame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomScoreListAdapter extends ArrayAdapter<GameScore> {
    Context context;
    TextView scoreView,userNameView,gameModeView;
    List<GameScore> scoreList;

    public CustomScoreListAdapter( @NonNull Context context,  List<GameScore> objects) {
       super(context,R.layout.custom_score_list,objects);
       this.context=context;
       this.scoreList=objects;
    }

    @SuppressLint("ResourceAsColor")
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_score_list, parent, false);
        scoreView=view.findViewById(R.id.scoreView);
        userNameView=view.findViewById(R.id.userNameView);
        gameModeView=view.findViewById(R.id.gameModeName);
        GameScore score=scoreList.get(position);
        scoreView.setText(score.getScore()+"");
        userNameView.setText(score.getName()+"");
        gameModeView.setText(score.getGameMode()+"");
        return view;
    }



    }



