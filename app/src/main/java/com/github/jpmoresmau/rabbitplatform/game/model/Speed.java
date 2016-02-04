package com.github.jpmoresmau.rabbitplatform.game.model;

/**
 * Created by jpmoresmau on 2/4/16.
 */
public class Speed {

    private float counter=0;

    private float inc=500;
    private double increase=1.2;

    public double update(float deltaTime) {
        counter += deltaTime;
        if (counter > inc) {
            counter -= inc;
            //Log.d("Speed", "Increase:" + increase);
            return increase;

        }
        return 1;
    }
}
