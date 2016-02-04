package com.github.jpmoresmau.rabbitplatform.game.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.jpmoresmau.rabbitplatform.framework.Game;
import com.github.jpmoresmau.rabbitplatform.framework.android.AndroidGame;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
 * Created by jpmoresmau on 2/3/16.
 */
public class Score {
    private int score=0;

    private float counter=0;

    private float inc=50;

    private Game game;

    public Score(Game game) {
        this.game = game;
    }

    public void update(float df){
        counter+=df;
        if (counter>inc){
            counter-=inc;
            score++;
        }
    }

    public int getScore() {
        return score;
    }

    public String getFormattedScore(){
        return String.format("%04d",score);

    }

    public boolean gameOver(){
        if (score>getHighScore()) {
            SharedPreferences sharedPref = ((AndroidGame)game).getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("high.score", score);
            editor.commit();
            return true;
        }
        return false;
    }

    public int getHighScore(){
        SharedPreferences sharedPref = ((AndroidGame)game).getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getInt("high.score",0);
    }
}
