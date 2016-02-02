package com.github.jpmoresmau.rabbitplatform.framework.android;


import java.util.List;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.view.View;

import com.github.jpmoresmau.rabbitplatform.framework.Input;
import com.github.jpmoresmau.rabbitplatform.framework.TouchEvent;

/**
 * http://www.kilobolt.com/day-6-the-android-game-framework-part-ii.html
 */
public class AndroidInput implements Input {
    private MultiTouchHandler touchHandler;

    public AndroidInput(Context context, View view, float scaleX, float scaleY) {
        touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
    }


    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }



    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }

}
