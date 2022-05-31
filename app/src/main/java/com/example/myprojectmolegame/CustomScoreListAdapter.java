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

public class CustomScoreListAdapter extends ArrayAdapter<ScoreActivity> {
    Context context;
    TextView scoreView,userNameView;

    @SuppressLint("ResourceAsColor")
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_score_list, parent, false);
        scoreView=view.findViewById(R.id.scoreView);
        userNameView=view.findViewById(R.id.userNameView);
        
        return view;
    }


    public CustomScoreListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<ScoreActivity> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
    }


}
