package com.example.myprojectmolegame;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class ThreadMolePopUp extends Thread {
    Handler handler;
    private boolean isRun = true;
    private int time = 0;

    public void setRun(boolean run) {
        isRun = run;
    }
    public void setTime(int time){
        this.time=time;
    }


    public ThreadMolePopUp(Handler handler) {
        this.handler = handler;

    }

    @Override
    public void run() {
        super.run();
        while (isRun) {
            if (time < 30) {
                time++;
                Message msg = new Message();
                msg.obj = 0;
                handler.sendMessage(msg);
                SystemClock.sleep(1000);
            } else {
                if (time < 70) {
                    time++;
                    Message msg = new Message();
                    msg.obj = 0;
                    handler.sendMessage(msg);
                    SystemClock.sleep(750);
                } else {
                    if (time < 120) {
                        time++;
                        Message msg = new Message();
                        msg.obj = 0;
                        handler.sendMessage(msg);
                        SystemClock.sleep(500);
                    } else {
                        if (time < 170) {
                            time++;
                            Message msg = new Message();
                            msg.obj = 0;
                            handler.sendMessage(msg);
                            SystemClock.sleep(400);
                        } else {
                            if (time < 220) {
                                time++;
                                Message msg = new Message();
                                msg.obj = 0;
                                handler.sendMessage(msg);
                                SystemClock.sleep(300);
                            } else {
                                if (time < 295) {
                                    time++;
                                    Message msg = new Message();
                                    msg.obj = 0;
                                    handler.sendMessage(msg);
                                    SystemClock.sleep(200);
                                } else {
                                    if (time < 395) {
                                        time++;
                                        Message msg = new Message();
                                        msg.obj = 0;
                                        handler.sendMessage(msg);
                                        SystemClock.sleep(150);
                                    } else {
                                        if (time > 395) {
                                            time++;
                                            Message msg = new Message();
                                            msg.obj = 0;
                                            handler.sendMessage(msg);
                                            SystemClock.sleep(100);
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}


