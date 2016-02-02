package com.github.jpmoresmau.rabbitplatform.game.model;

import com.github.jpmoresmau.rabbitplatform.framework.Image;

/**
 * Created by jpmoresmau on 2/2/16.
 */
public class GroundComponent {
    private int x;
    private int y;
    private int width;

    public GroundComponent(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return null;
    }

    public int getWidth() {
        return width;
    }
}
