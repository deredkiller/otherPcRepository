package com.example.myprojectmolegame;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class growingThread extends Thread {
    Handler handler;
    private boolean isRun = true;
int time=0;
    public growingThread(Handler handler) {
        this.handler = handler;

    }
    public void setRun(boolean run) {
        isRun = run;
    }

    @Override
    public void run() {
        Log.d("working","yea");
        super.run();
        while (isRun) {
            Message msg = new Message();
            time++;
            Log.d("working",time+"yea");
            msg.obj = 0;
            handler.sendMessage(msg);
            SystemClock.sleep(30000);
        }
    }
}

