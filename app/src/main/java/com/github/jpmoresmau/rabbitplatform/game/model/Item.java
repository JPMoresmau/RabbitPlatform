package com.github.jpmoresmau.rabbitplatform.game.model;

import com.github.jpmoresmau.rabbitplatform.framework.Image;

/**
 * Created by jpmoresmau on 2/5/16.
 */
public class Item {
    private int x;
    private int y;
    private Image image;

    private int score;

    public Item(int x, int y, Image image,int score) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.score=score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public int getScore() {
        return score;
    }
}
