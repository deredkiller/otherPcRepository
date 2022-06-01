package com.example.myprojectmolegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class modesActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnNormal, btnDungeon, btnGrowingHoles, btnHardcore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes);
        btnNormal = findViewById(R.id.btnNormalMode);
        btnDungeon = findViewById(R.id.btnDungeonMode);
        btnGrowingHoles = findViewById(R.id.btnGrowingMode);
        btnHardcore = findViewById(R.id.btnHardcoreMode);
        btnNormal.setTag("normal");
        btnDungeon.setTag("dungeon");
        btnGrowingHoles.setTag("growingHoles");
        btnHardcore.setTag("hardcore");
        btnNormal.setOnClickListener(this);
        btnGrowingHoles.setOnClickListener(this);
        btnHardcore.setOnClickListener(this);
        btnDungeon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getTag().equals("normal")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("NUM_OF_HOLES", 9);
            intent.putExtra("COLUMN", 3);
            intent.putExtra("ROW", 3);
            startActivity(intent);
        }
        if (view.getTag().equals("dungeon")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("NUM_OF_HOLES", 9);
            intent.putExtra("COLUMN", 3);
            intent.putExtra("ROW", 3);
            startActivity(intent);
        }
        if (view.getTag().equals("growingHoles")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("NUM_OF_HOLES", 9);
            intent.putExtra("COLUMN", 3);
            intent.putExtra("ROW", 3);
            intent.putExtra("growingBoard", true);
            startActivity(intent);
        }
        if (view.getTag().equals("hardcore")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("NUM_OF_HOLES", 10);
            intent.putExtra("COLUMN", 5);
            intent.putExtra("ROW", 5);
            startActivity(intent);


        }
    }

}