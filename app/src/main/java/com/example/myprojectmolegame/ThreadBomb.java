package com.example.myprojectmolegame;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class ThreadBomb extends Thread {
    Handler handler;
    private boolean isRun = true;


    public void setRun(boolean run) {
        isRun = run;
    }


    public ThreadBomb(Handler handler) {
        this.handler = handler;

    }

    @Override
    public void run() {
        Log.d("yo",1+"");
        super.run();
        while (isRun) {
            Message msg = new Message();
            msg.obj = 0;
            handler.sendMessage(msg);
            SystemClock.sleep(3000);
            setRun(false);
        }
    }
}


