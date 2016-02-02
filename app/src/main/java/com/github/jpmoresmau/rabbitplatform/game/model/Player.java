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

    private Long lastLong=null;
    private boolean longJump(){
        if (jump){
            if (lastLong==null){
                lastLong=System.currentTimeMillis();
                return true;
            } else {
                return System.currentTimeMillis()-lastLong<200;
            }
        } else {
            lastLong=null;
        }

        return false;
    }

    private void gravity(float deltaTime,int maxY){
        if (y<maxY){
            vy += deltaTime/16;
        } else {
            if (vy>0){
                stopJump();
            }
            vy=0;
        }
    }

    public void addJump(){
        jump=true;
        lastLong=null;
    };

    public void stopJump(){
        jump=false;
        lastLong=null;
    }

    private void jump(){
        if (vy==0 && jump){
            vy=-6.0f;
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
}
