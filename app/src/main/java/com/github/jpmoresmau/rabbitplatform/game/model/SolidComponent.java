package com.github.jpmoresmau.rabbitplatform.game.model;

import com.github.jpmoresmau.rabbitplatform.framework.Image;

/**
 * Created by jpmoresmau on 2/2/16.
 */
public class SolidComponent extends GroundComponent {
    private Image img;

    public SolidComponent(int x, int y, Image img) {
        super(x, y, img.getWidth());
        this.img = img;
    }

    public Image getImage() {
        return img;
    }

    public void setImage(Image img) {
        this.img = img;
    }
}
