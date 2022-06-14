package com.example.myprojectmolegame;


import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MenuForAllActivity extends AppCompatActivity {
    Intent intent,intentSettings;


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        intent = new Intent(this, com.example.myprojectmolegame.Menu.class);
        intentSettings = new Intent(this, Settings.class);
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
}
