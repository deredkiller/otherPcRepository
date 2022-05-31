package com.example.myprojectmolegame;

public class GameScore {

    String name;
    long id;
    int score;

    public GameScore(String name, int score, long id) {
        this.name = name;
        this.score = score;
        this.id=id;
    }
    public GameScore(String name, int score) {
        this.name = name;
        this.score = score;
    }


    public String getName (){
        return name;
    }
    public int getScore(){
        return score;
    }
    public long setId (long id){
        this.id=id;
        return id;
    }

    @Override
    public String toString() {
        return "ModelUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", score=" + score +
                '}';
    }
}


