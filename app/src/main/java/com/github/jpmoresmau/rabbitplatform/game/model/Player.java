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

    private int feetWidth=20;

    private boolean dead=false;

    private int idx=0;
    private float counter;
    private float counterRate=30;

    private Image[] walksImages=new Image[]{RAssets.bunny1_walk1,RAssets.bunny1_walk2};

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

    public void update(float deltaTime,int maxY,double increase){
        counter+=deltaTime;
        counterRate/=increase;
        if (counter>=counterRate){
            idx++;
            if (idx>=walksImages.length){
                idx=0;
            }
            counter=0;
        }
        gravity(deltaTime, maxY);
        jump();
        physics(deltaTime,maxY);
    }

    public float getVy() {
        return vy;
    }

    public Image getImage(int maxY){
        if (dead){
            return RAssets.bunny1_hurt;
        } else if (y<maxY){
            return RAssets.bunny1_jump;
        } else {
            return walksImages[idx];
        }
    }

    public boolean isJumping(){
        return vy<0;
    }

    public int getFeetWidth() {
        return feetWidth;
    }

    public void die(){
        rate=lowRate;
        dead=true;
    }

    public boolean isDead() {
        return dead;
    }
}
