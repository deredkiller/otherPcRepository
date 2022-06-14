package com.example.myprojectmolegame;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class Controller {
    // TODO (maybe in model) - count points - or num of moles killed or time of game in seconds
    private int score = 0;
    private ThreadMolePopUp thread;
    private ThreadBomb threadBomb;
    private MainActivity activity;
    public int numOfHoles;
    private Model model;
    private int streak = 0;
    private DBHelper dataBase;
    private int holeNum, num = 0;



    public Controller(MainActivity activity) {
        this.activity = activity;
        numOfHoles = activity.getNumOfHoles();
        model = new Model(numOfHoles, activity.gameMode);
        dataBase = new DBHelper(activity);
        initiateThread();
    }

    // when the mole is clicked cheek if its a mole or an hole and if true then display an hole insted of the mole
    public void moleClicked(int id) {
        Log.d("yoyo", id + "");
        holeNum = id;
        int hit = model.hitElement(id);
        if (hit > 0) {
            activity.displayElement(id, Element.HOLE);
            activity.displayScore(Score());
        } else clearStreak();

    }


    public int Score() {
        Element element = model.getElementClicked();
        switch (element) {
            case MOLE:
                streak++;
                if (streak > 1) {
                    if (streak > 5) {
                        score = score + 5;
                        ;
                    } else {
                        score = score + streak;
                    }
                } else score++;
                return score;
            case CARROT:
                score = score + 5;
                streak++;
                if (streak > 1) {
                    if (streak > 5) {
                        score = score + 5;
                        ;
                    } else {
                        score = score + streak;
                    }
                } else score++;
                return score;

            case BOMB:
                score = score - 10;
                clearStreak();
                return score;

        }
        return score;
    }

    public void elementAppear() {
        int holeNum = model.generateElement();
        if (holeNum != -1) {
            if (model.getElement().equals(Element.BOMB)) {
                Log.d("yoyoyo", holeNum + "");
                startBombThread();
            }

            activity.displayElement(holeNum, model.getElement());
        } else {
            lose();
        }
    }

    // removing hole slot from the list and asking the mainActivity to display a mole
    public void moleAppear() {
        if (activity.gameMode.equals("hardcore")){
            thread.setTime(200);
        }
        int holeNum = model.generateMole();
        if (holeNum != -1) {
            activity.displayElement(holeNum, Element.MOLE);
        } else {
            lose();
        }

    }

    public void update() {
        model.updateNumOfHoles( activity.getNumOfHoles());
    }

    private void lose() {
        stopThread();
        activity.displayLose();
        model = new Model(numOfHoles, activity.gameMode);
    }

    private void initiateThread() {

        if (activity.gameMode.equals("dungeon")) {
            Handler handlerForMoleTimer = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    elementAppear();


                }
            };

            thread = new ThreadMolePopUp(handlerForMoleTimer);
            thread.start();
        } else {
            Handler handlerForMoleTimer = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    moleAppear();


                }
            };

            thread = new ThreadMolePopUp(handlerForMoleTimer);
            thread.start();
        }
    }

    public void stopThread() {
        thread.setRun(false);
    }

    public void startThread() {
        initiateThread();
    }

    public void clearScore() {
        score = 0;
        activity.clearScore();
    }

    public void clearStreak() {
        streak = 0;
    }

    public void insert(GameScore user) {
        dataBase.insert(user);
    }

    public void startBombThread() {
        if (num == 1) {
            num = 1;
            Handler handlerForBombTimer = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    activity.bombDisapear();

                }
            };

            threadBomb = new ThreadBomb(handlerForBombTimer);
            threadBomb.start();
        } else num++;

    }

}
