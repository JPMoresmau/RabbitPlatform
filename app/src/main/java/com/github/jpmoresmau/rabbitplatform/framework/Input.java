package com.github.jpmoresmau.rabbitplatform.framework;


import java.util.List;
/**
 * http://www.kilobolt.com/day-5-the-android-game-framework-part-i.html
 */
public interface Input {

    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<TouchEvent> getTouchEvents();
}