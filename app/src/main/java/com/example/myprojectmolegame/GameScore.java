package com.example.myprojectmolegame;

public class GameScore {

    String name;
    long id;
    int score,position;
    String gameMode;

    public GameScore(String name, int score, long id,String gameMode) {
        this.name = name;
        this.score = score;
        this.id=id;
        this.gameMode=gameMode;

    }
    public GameScore(String name, int score, long id) {
        this.name = name;
        this.score = score;
        this.id = id;
        this.gameMode = gameMode;
    }

    public GameScore(String name, int score,String gameMode) {
        this.name = name;
        this.score = score;
        this.gameMode=gameMode;
    }


    public String getName (){
        return name;
    }
    public int getScore(){
        return score;
    }

    public long getId(){
      return id;
    }
    public long setId (long id){
        this.id=id;
        return id;
    }
    public void setGameMode(String gameMode){
        this.gameMode=gameMode;
    }

    @Override
    public String toString() {
        return "ModelUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", score=" + score +
                '}';
    }

    public String getGameMode() {
        return gameMode;
    }
}


