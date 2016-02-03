package com.github.jpmoresmau.rabbitplatform.game.model;

import android.util.Log;

import com.github.jpmoresmau.rabbitplatform.framework.Image;
import com.github.jpmoresmau.rabbitplatform.game.RAssets;

/**
 * Created by jpmoresmau on 2/2/16.
 */
public class Player {
    private int x;
    private int y;
    private float vx;
    private float vy;

    private boolean jump;
    private long jumpStart;

    private float rate=20;

    private float highRate =20;
    private long lowDelay=100;
    private float lowRate=10;
    private long middleDelay=300;
    private float middleRate=15;
    private float jumpVelocity=-5.0f;

    public Player(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    private void gravity(float deltaTime,int maxY){
        if (y<maxY){
            vy += deltaTime/rate;
            if (vy<0.1 && vy>-0.1){
                Log.d("Player","vy"+vy+",y:"+y+",rate:"+rate);
            }
        } else {
            if (vy>0){
                stopJump();
            }
            vy=0;
        }
    }

    public void addJump(){
        jump=true;
        rate= highRate;
        jumpStart=System.currentTimeMillis();
        Log.d("Player","addJump");
    };

    public void stopJump(){
        long diff=System.currentTimeMillis()-jumpStart;
        if (diff<lowDelay){
            rate=lowRate;
        } else if (diff<middleDelay){
            rate=middleRate;
        }
        jumpStart=0;
        Log.d("Player","stopJump:"+diff+"->rate:"+rate);
    }

    private void jump(){
        if (vy==0 && jump){
            Log.d("Player","jump");
            vy=jumpVelocity;
            jump=false;
        }

    }

    private void physics(float deltaTime,int maxY){
        x =  (int)(Math.round(x+(deltaTime * vx)));
        y =  Math.min(maxY,(int)(Math.round(y+(deltaTime * vy))));

    }

    public void update(float deltaTime,int maxY){
        gravity(deltaTime, maxY);
        jump();
        physics(deltaTime,maxY);
    }

    public float getVy() {
        return vy;
    }

    public Image getImage(int maxY){
         if (y<maxY){
            return RAssets.bunny1_jump;
        } else {
            return RAssets.bunny1_ready;
        }
    }

    public boolean isJumping(){
        return vy<0;
    }
}
