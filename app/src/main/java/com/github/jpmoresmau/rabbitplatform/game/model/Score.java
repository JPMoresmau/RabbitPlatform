package com.github.jpmoresmau.rabbitplatform.game.model;

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
}
