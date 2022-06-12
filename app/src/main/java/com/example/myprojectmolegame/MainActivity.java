package com.example.myprojectmolegame;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO more activities:
//    TODO clear the hole list in on click find out how the game show lose after a retry becuase its showing lose when there 1 mole left
//    TODO understand why when u retry it display lose before the last mole is displayed
    // 1. sha'ar - list of game options (new game, continue, settings (maybe), instructions etc.)
    // 2. settings - (... holes in rows or in circles or something else , number of holes)
    // 3. instructions
    // 4. choose - easy, normal, hard (notice model-view-controller)
    // 5. records - after we establish a database TODO
    private TextView scoreView,scoreNum;
    private Controller controller;
    private ImageView imgArray[] ;
    private ImageView imgPause;
    private LinearLayout linearLayoutBoard;
    private LinearLayout llMainDynamic;
    private LinearLayout LinearLayoutScore1, LinearLayoutPause;
    private ImageButton btnRetry;
    String pause,gameMode;
    int column,row,numOfHoles,margin,vertical;
    Intent initBoardIntent;
    int image;
    int width;
    int height,size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initBoardIntent = getIntent();
        image = R.drawable.hole;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        numOfHoles=initBoardIntent.getIntExtra("NUM_OF_HOLES",0);
        row=initBoardIntent.getIntExtra("ROW",3);
        column=initBoardIntent.getIntExtra("COLUMN",0);
        dynamicLayoutConstruction();
        controller = new Controller(this);
        scoreNum=findViewById(R.id.scoreNum);



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
                Log.d("bro",numOfHoles+" "+column+" "+row);
                for (int i = 0; i < numOfHoles; i++) {
                    imgArray[i].setImageResource(R.drawable.hole);
                }
                llMainDynamic.addView(linearLayoutBoard);
                LinearLayoutScore1.addView(scoreView);
                LinearLayoutScore1.addView(scoreNum);
                LinearLayoutPause.addView(imgPause);
                scoreView.setText("score:");
                controller.startThread();
                controller.clearScore();
                controller.clearStreak();

            }

        });


        imgPause = findViewById(R.id.pause);
        imgPause.setTag("pause");
        imgPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag().equals("pause")) {
                    controller.stopThread();
                    imgPause.setTag("paused");
                    pause="pause";
                } else {
                    controller.startThread();
                    imgPause.setTag("pause");
                    pause="paused";
                }
            }
        });


    }


    @Override
    public void onClick(View view) {
        Log.d("hello", "onClick: " + view.getTag().toString());
        if (pause=="pause"){

        }
        else{
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
        getGameMode();
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
        LinearLayout.LayoutParams elementLayout = new LinearLayout.LayoutParams(width / 5, LinearLayout.LayoutParams.WRAP_CONTENT);
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
        gameMode=initBoardIntent.getStringExtra("GAME_MODE");
        if(gameMode.equals("normal")){
            column=3;
            row=3;
            numOfHoles=9;
            margin=5;
            imgArray=new ImageView[9];
            vertical=4;
            size=4;
        }
        if(gameMode.equals("dungeon")) {
            column=4;
            row=4;
            numOfHoles=16;
            vertical=5;
            margin=10;
            imgArray=new ImageView[16];
            size=5;
        }

    }


    public void displayElement(int holeNum, Element element) {
        Log.d("yo",""+ holeNum);
        image=R.drawable.hole;
        if (element == Element.MOLE) image = R.drawable.mole;
        imgArray[holeNum].setImageResource(image);
    }

    //todo change imagebutton to imgae view beacuase u click on the whole screen and it still retry
    public void displayLose() {
        llMainDynamic.removeView(linearLayoutBoard);
        LinearLayoutPause.removeView(imgPause);
        LinearLayoutScore1.removeView(scoreNum);
        LinearLayoutScore1.removeView(scoreView);
        LinearLayout.LayoutParams retryParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btnRetry.setLayoutParams(retryParams);
        llMainDynamic.addView(btnRetry);
        displayDialog();

    }

    public void clearScore() {
        scoreNum.setText(""+0);
    }


    public void displayScore(int score) {
        scoreNum.setText(""+score);
    }

    public void displayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("are you sure you want to upload this new score to the score board ")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent oldIntent = getIntent();
                        GameScore user= new GameScore(oldIntent.getStringExtra("USERNAME"),Integer.parseInt((String) scoreNum.getText()));
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
    }


    public int getNumOfHoles() {

        return numOfHoles;
    }


}

