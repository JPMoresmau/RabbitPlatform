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

    private int step=100;

    public Ground(int maxX,int maxY){
        this.maxX=maxX;
        this.maxY=maxY;
        components.add(new SolidComponent(0,0, RAssets.ground_grass));
        addComponents();
    }

    public List<GroundComponent> getComponents() {
        return components;
    }



    public void update(float deltaTime,double increase){
        rate *= increase;
       // Log.d("Ground","Increase:"+rate);
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
            addItem(last);
            components.add(last);
            x=last.getX()+last.getWidth();
        }
    }

    private void addItem(GroundComponent gc){
        int i=r.nextInt(20);
        if (i<5) {
            int w = gc.getWidth();
            Image img = RAssets.carrot;
            int rnd = r.nextInt(w - img.getWidth());
            gc.setItem(new Item(rnd, 250, img,5));
        } else if (i==19){
            int w = gc.getWidth();
            Image img = RAssets.carrot_gold;
            int rnd = r.nextInt(w - img.getWidth());
            gc.setItem(new Item(rnd, 250, img,20));
        }
    }

    private GroundComponent getNextComponent(){
        int i=r.nextInt(3);
        GroundComponent last=components.getLast();
        int x=last.getX()+last.getWidth();
        if (last instanceof SolidComponent){
            if (i%2==0){
                return new GroundComponent(x,-1000,150);
            } else {
                i=r.nextInt();
                return generateSolidComponent(i, x,last.getY());
            }
        } else {
            return generateSolidComponent(i,x,last.getY());
        }
    }

    private SolidComponent generateSolidComponent(int i,int x,int lastY){
        Image img=i%3==0?RAssets.ground_grass_small:RAssets.ground_grass;
        if (lastY<0){
            lastY=0;
        }
        int max=lastY==0?3:
                lastY==step?
                        4:5;
        if (lastY>step*4){
            lastY=step*3;
        }
        int j=r.nextInt(max);
        int y=lastY;
        switch (j){
            case 0 : y=lastY+(step*2); break;
            case 1 : y=lastY+step; break;
            case 2 : y=lastY; break;
            case 3 : y=lastY-step; break;
            case 4 : y=lastY-(step*2); break;
        }
        //Log.d("Ground","lastY:"+lastY);
        //Log.d("Ground","y:"+y);

        return new SolidComponent(x,y,img);
    }
}
