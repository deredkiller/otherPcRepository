package com.example.myprojectmolegame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
      if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)){
          Toast.makeText(context,"oh no you played so much that the battery low right now",Toast.LENGTH_LONG).show();

      }

    }
}