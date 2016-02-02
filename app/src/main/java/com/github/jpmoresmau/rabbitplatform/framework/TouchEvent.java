package com.github.jpmoresmau.rabbitplatform.framework;

/**
 * http://www.kilobolt.com/day-5-the-android-game-framework-part-i.html
 */
public class TouchEvent {


    private TouchEventType type;
    private int x, y;
    private int pointer;

    public TouchEvent(TouchEventType type, int pointer, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.pointer = pointer;
    }

    public TouchEventType getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPointer() {
        return pointer;
    }
}
