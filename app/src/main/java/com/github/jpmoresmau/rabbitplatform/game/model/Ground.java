package com.github.jpmoresmau.rabbitplatform.game.model;

import android.util.Log;

import com.github.jpmoresmau.rabbitplatform.framework.Image;
import com.github.jpmoresmau.rabbitplatform.game.RAssets;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by jpmoresmau on 2/2/16.
 */
public class Ground {
    private LinkedList<GroundComponent> components=new LinkedList<>();

    private Random r=new Random(System.currentTimeMillis());

    private int maxX;
    private int maxY;

    private float rate=1;
    private int lastInc=0;

    private float counter=0;

    private float inc=500;
    private double increase=1.2;

    public Ground(int maxX,int maxY){
        this.maxX=maxX;
        this.maxY=maxY;
        components.add(new SolidComponent(0,0, RAssets.ground_grass));
        addComponents();
    }

    public List<GroundComponent> getComponents() {
        return components;
    }



    public void update(float deltaTime){
        //Log.d("Ground","deltaTime:"+deltaTime);
        counter+=deltaTime;
        if (counter>inc){
            counter-=inc;
            rate *= increase;
            Log.d("Ground","Increase:"+rate);
        }
        int dx=(int)Math.round(deltaTime*rate);
        for (Iterator<GroundComponent> it=components.iterator();it.hasNext();){
            GroundComponent gc=it.next();
            gc.setX(gc.getX()-dx);
            if (gc.getX()<-gc.getWidth()){
                it.remove();
            }
        }
        addComponents();
    }

    public int getRealMaxY(int x){
        for (GroundComponent gc:components){
            if (gc.getX()<=x && gc.getX()+gc.getWidth()>x){
                return maxY-gc.getY();
            }
        }
        return maxY;
    }

    private void addComponents(){
        GroundComponent last=components.getLast();
        int x=last.getX()+last.getWidth();
        while (x<maxX){
            last = getNextComponent();
            components.add(last);
            x=last.getX()+last.getWidth();
        }
    }

    private GroundComponent getNextComponent(){
        int i=r.nextInt();
        GroundComponent last=components.getLast();
        int x=last.getX()+last.getWidth();
        if (last instanceof SolidComponent){
            if (i%2==0){
                return new GroundComponent(x,-1000,150);
            } else {
                i=r.nextInt();
                return generateSolidComponent(i, x);
            }
        } else {
            return generateSolidComponent(i,x);
        }
    }

    private SolidComponent generateSolidComponent(int i,int x){
        Image img=i%3==0?RAssets.ground_grass_small:RAssets.ground_grass;
        return new SolidComponent(x,0,img);
    }
}
