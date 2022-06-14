package com.example.myprojectmolegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private growingThread growingThread;
    private TextView scoreView, scoreNum;
    private Controller controller;
    private ImageView imgArray[];
    private ImageView imgPause;
    private LinearLayout linearLayoutBoard;
    private LinearLayout llMainDynamic;
    private LinearLayout LinearLayoutScore1, LinearLayoutPause;
    private ImageButton btnRetry;
    String pause, gameMode;
    int num=0;
    int column, row, numOfHoles, margin, vertical;
    Intent initBoardIntent;
    int holeImg, moleImg, carrotImg, bombImg;
    Intent musicIntent;
    int width;
    int height, size, horizontal,numb=0;
    int phase = 0;
    SharedPreferences sp;
    private MyReceiver receiver;
    private List<Integer> bomb = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        receiver = new MyReceiver();
        sp = getApplicationContext().getSharedPreferences("settingPref", Context.MODE_PRIVATE);
        musicIntent = new Intent(this, MyService.class);
        initBoardIntent = getIntent();
        gameMode = initBoardIntent.getStringExtra("GAME_MODE");
        holeImg = R.drawable.hole;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        numOfHoles = initBoardIntent.getIntExtra("NUM_OF_HOLES", 0);
        row = initBoardIntent.getIntExtra("ROW", 3);
        column = initBoardIntent.getIntExtra("COLUMN", 0);
        getGameMode();
        dynamicLayoutConstruction();
        controller = new Controller(this);
        scoreNum = findViewById(R.id.scoreNum);
        holeImg = R.drawable.hole;
        moleImg = R.drawable.mole;
        carrotImg = R.drawable.carrot;
        bombImg = R.drawable.bomb;


        setOnClicks();
//        makeBackroundVideo();

    }

    private void setOnClicks() {
        btnRetry = new ImageButton(this);
        btnRetry.setImageResource(R.drawable.reloading);
        btnRetry.setBackgroundResource(R.drawable.white);
        btnRetry.setTag("btnRetry");
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llMainDynamic.removeAllViews();
                if (gameMode.equals("growingHoles")){
                    controller.stopThread();
                    LinearLayoutScore1.addView(scoreView);
                    LinearLayoutScore1.addView(scoreNum);
                    LinearLayoutPause.addView(imgPause);
                    column = 3;
                    row = 3;
                    numOfHoles = 9;
                    vertical = 5;
                    margin = 5;
                    imgArray = new ImageView[9];
                    size = 4;
                    horizontal = 5;
                    dynamicLayoutConstruction();
                    controller.clearScore();
                    controller.clearStreak();
                    controller.startThread();
                    num=0;
                    startGrowingThread();
                }
else{
                    for (int i = 0; i < numOfHoles; i++) {
                        imgArray[i].setImageResource(R.drawable.hole);
                    }
                    llMainDynamic.addView(linearLayoutBoard);
                    LinearLayoutScore1.addView(scoreView);
                    LinearLayoutScore1.addView(scoreNum);
                    LinearLayoutPause.addView(imgPause);
                    scoreView.setText("score:");
                    controller.clearScore();
                    controller.clearStreak();
                    controller.startThread();

                }


                }
        });



        imgPause = findViewById(R.id.pause);
        imgPause.setTag("pause");
        imgPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag().equals("pause")) {
                    controller.stopThread();
                    if (gameMode.equals("growingHoles")){
                        stopGrowingThread();
                    }
                    imgPause.setTag("paused");
                    pause = "pause";
                } else {
                    controller.startThread();
                    if (gameMode.equals("growingHoles")){
                        startGrowing();
                    }
                    imgPause.setTag("pause");
                    pause = "paused";
                }
            }
        });


    }

    private void stopGrowingThread() {
        growingThread.setRun(false);
    }
    private void startGrowing() {
        growingThread.setRun(true);
    }


    @Override
    public void onClick(View view) {
        if (pause == "pause") {

        } else {
            controller.moleClicked((int) view.getTag());
        }


    }

//    private void makeBackroundVideo() {
//        VideoView videoview = (VideoView) findViewById(R.id.adsads);
//        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.diglet_loop);
//        videoview.setVideoURI(uri);
//        videoview.start();
//    }

    public void dynamicLayoutConstruction() {
        LinearLayoutPause = findViewById(R.id.linearLayoutPause);
        LinearLayoutScore1 = findViewById(R.id.linearLayoutScore1);
        LinearLayoutScore1.setTag("layoutScore");
        scoreView = findViewById(R.id.scoreView);
        llMainDynamic = findViewById(R.id.llDynamic);
        llMainDynamic.setOrientation(LinearLayout.VERTICAL);
        linearLayoutBoard = new LinearLayout(this);
        linearLayoutBoard.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams BoardParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//משנה עד כמה החורים קרובים ללמעלה של המסך
        BoardParams.setMargins(0, height / 100, 0, 0);
        linearLayoutBoard.setLayoutParams(BoardParams);
        //משנה כמה moles אתה יכול לעשים בlayout   ה verticaly
        LinearLayout.LayoutParams rowLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, height / size);
//        משנה את המרג'ינס לצדדים של האוריזונטל layout
        rowLayout.setMargins(width / margin, 1, 1, 1);
//        משנה גודל תמונה
        LinearLayout.LayoutParams elementLayout = new LinearLayout.LayoutParams(width / horizontal, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout rowInboard;
        int indexRun = 0;
        for (int i = 0; i < column; i++) {
            rowInboard = new LinearLayout(this);
            rowInboard.setLayoutParams(rowLayout);
            rowInboard.setOrientation(LinearLayout.HORIZONTAL);
            //ROWS OR width
            for (int j = 0; j < row; j++) {
                imgArray[indexRun] = new ImageView(this);
                imgArray[indexRun].setTag(indexRun);
                imgArray[indexRun].setLayoutParams(elementLayout);
                imgArray[indexRun].setImageResource(R.drawable.hole);
                imgArray[indexRun].setOnClickListener(this);
                rowInboard.addView(imgArray[indexRun]);
                indexRun++;
            }
            linearLayoutBoard.addView(rowInboard);

        }
        llMainDynamic.addView(linearLayoutBoard);
    }

    private void getGameMode() {
        Log.d("game",gameMode);
        if (gameMode.equals("normal")) {
            column = 3;
            row = 3;
            numOfHoles = 9;
            vertical = 5;
            margin = 5;
            imgArray = new ImageView[9];
            size = 4;
            horizontal = 5;
        }
        if (gameMode.equals("dungeon")) {
            column = 4;
            row = 4;
            numOfHoles = 16;
            vertical = 5;
            margin = 10;
            imgArray = new ImageView[16];
            size = 5;
            horizontal = 5;
        }
        if (gameMode.equals("hardcore")) {
            column = 10;
            row = 10;
            numOfHoles = 100;
            vertical = 13;
            margin = 1000;
            imgArray = new ImageView[100];
            size = 13;
            horizontal = 10;
        }
        if (gameMode.equals("growingHoles")) {
            column = 3;
            row = 3;
            numOfHoles = 9;
            margin = 5;
            imgArray = new ImageView[9];
            vertical = 4;
            size = 4;
            horizontal = 5;
            startGrowingThread();

        }


    }


    public void displayElement(int holeNum, Element element) {
        switch (element) {
            case BOMB:
                bomb.add(holeNum);
                imgArray[holeNum].setImageResource(bombImg);
                break;
            case HOLE:
                imgArray[holeNum].setImageResource(holeImg);
                break;
            case MOLE:
                imgArray[holeNum].setImageResource(moleImg);
                break;
            case CARROT:
                imgArray[holeNum].setImageResource(carrotImg);
                break;
        }
    }

    //todo change imagebutton to imgae view beacuase u click on the whole screen and it still retry
    public void displayLose() {
        if (gameMode.equals("growingHoles")){
            llMainDynamic.removeView(linearLayoutBoard);
            LinearLayoutPause.removeView(imgPause);
            LinearLayoutScore1.removeView(scoreNum);
            LinearLayoutScore1.removeView(scoreView);
            LinearLayout.LayoutParams retryParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            btnRetry.setLayoutParams(retryParams);
            llMainDynamic.addView(btnRetry);
            stopGrowingThread();
            displayDialog();
        }
        else {
            llMainDynamic.removeView(linearLayoutBoard);
            LinearLayoutPause.removeView(imgPause);
            LinearLayoutScore1.removeView(scoreNum);
            LinearLayoutScore1.removeView(scoreView);
            LinearLayout.LayoutParams retryParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            btnRetry.setLayoutParams(retryParams);
            llMainDynamic.addView(btnRetry);
            displayDialog();
        }


    }

    public void clearScore() {
        scoreNum.setText("" + 0);
    }


    public void displayScore(int score) {
        scoreNum.setText("" + score);
    }

    public void displayDialog() {
        boolean autoSave = sp.getBoolean("autoSave", true);
        if (autoSave) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("are you sure you want to upload this new score to the score board ")
                    .setCancelable(false)
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent oldIntent = getIntent();
                            GameScore user = new GameScore(oldIntent.getStringExtra("USERNAME"), Integer.parseInt((String) scoreNum.getText()),gameMode);
                            controller.insert(user);
                        }
                    })
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            //YOUR CODE HERE
                        }
                    }).setTitle("upload score?");
            //Creating dialog box
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            Intent oldIntent = getIntent();
            GameScore user = new GameScore(oldIntent.getStringExtra("USERNAME"), Integer.parseInt((String) scoreNum.getText()),gameMode);
            controller.insert(user);

        }
    }


    public int getNumOfHoles() {
        return numOfHoles;
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter HeadPhonesFilter = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        registerReceiver(receiver, HeadPhonesFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void bombDisapear() {
        controller.moleClicked(bomb.remove(0));
    }


    public void grow() {
        controller.stopThread();
        llMainDynamic.removeView(linearLayoutBoard);
        changeLayout();
        dynamicLayoutConstruction();
        controller.update();
        controller.startThread();


    }

    private void changeLayout() {
        Log.d("dady",phase+"");
        switch (phase) {
            case 0:
                column = 4;
                row = 4;
                numOfHoles = 16;
                vertical = 5;
                margin = 10;
                imgArray = new ImageView[16];
                size = 5;
                horizontal = 5;
                break;
            case 1:
                column = 5;
                row = 5;
                numOfHoles = 25;
                vertical = 7;
                margin = 100;
                imgArray = new ImageView[25];
                size = 7;
                horizontal = 5;
                break;
            case 2:
                column = 6;
                row = 6;
                numOfHoles = 36;
                vertical = 10;
                margin = 100;
                imgArray = new ImageView[36];
                size = 8;
                horizontal = 6;
                break;
            case 3:
                column = 7;
                row = 7;
                numOfHoles = 49;
                vertical = 10;
                margin = 100;
                imgArray = new ImageView[49];
                size = 9;
                horizontal = 7;
                break;
        }
        if (phase==3){
        }else{
            phase++;
        }


    }
    public void startGrowingThread() {
        Log.d("mam","is it working?");

              Log.d("mam","is it working?");
              Handler handlerForBombTimer = new Handler(Looper.myLooper()) {
                  @Override
                  public void handleMessage(@NonNull Message msg) {
                      super.handleMessage(msg);
                      if(num==1) {
                          grow();
                      }
                      else num++;


                  }
              };
              growingThread = new growingThread(handlerForBombTimer);
              growingThread.start();

          }

    }




