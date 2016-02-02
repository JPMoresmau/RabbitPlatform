package com.github.jpmoresmau.rabbitplatform.framework.android;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import android.view.View;

import com.github.jpmoresmau.rabbitplatform.framework.TouchEvent;
import com.github.jpmoresmau.rabbitplatform.framework.TouchEventType;

/**
 * http://www.kilobolt.com/day-6-the-android-game-framework-part-ii.html
 */
public class MultiTouchHandler implements View.OnTouchListener{
    private static final int MAX_TOUCHPOINTS = 10;

    private boolean[] isTouched = new boolean[MAX_TOUCHPOINTS];
    private int[] touchX = new int[MAX_TOUCHPOINTS];
    private int[] touchY = new int[MAX_TOUCHPOINTS];
    private int[] id = new int[MAX_TOUCHPOINTS];
    private List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
    private List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
    private float scaleX;
    private float scaleY;

    public MultiTouchHandler(View view, float scaleX, float scaleY) {
        view.setOnTouchListener(this);

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this) {
            int action = event.getAction() & MotionEvent.ACTION_MASK;
            int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
            int pointerCount = event.getPointerCount();
            TouchEvent touchEvent;
            for (int i = 0; i < MAX_TOUCHPOINTS; i++) {
                if (i >= pointerCount) {
                    isTouched[i] = false;
                    id[i] = -1;
                    continue;
                }
                int pointerId = event.getPointerId(i);
                if (event.getAction() != MotionEvent.ACTION_MOVE && i != pointerIndex) {
                    // if it's an up/down/cancel/out event, mask the id to see if we should process it for this touch
                    // point
                    continue;
                }
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        touchEvent = new TouchEvent(TouchEventType.TOUCH_DOWN,pointerId,(int) (event.getX(i) * scaleX),(int) (event.getY(i) * scaleY));
                        touchX[i] = touchEvent.getX();
                        touchY[i] = touchEvent.getY();
                        isTouched[i] = true;
                        id[i] = pointerId;
                        touchEventsBuffer.add(touchEvent);
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL:
                        touchEvent = new TouchEvent(TouchEventType.TOUCH_UP,pointerId,(int) (event.getX(i) * scaleX),(int) (event.getY(i) * scaleY));
                        touchX[i] = touchEvent.getX();
                        touchY[i] = touchEvent.getY();
                        isTouched[i] = false;
                        id[i] = -1;
                        touchEventsBuffer.add(touchEvent);
                        break;

                     case MotionEvent.ACTION_MOVE:
                        touchEvent = new TouchEvent(TouchEventType.TOUCH_DRAGGED,pointerId,(int) (event.getX(i) * scaleX),(int) (event.getY(i) * scaleY));
                        touchX[i] = touchEvent.getX();
                        touchY[i] = touchEvent.getY();

                        isTouched[i] = true;
                        id[i] = pointerId;
                        touchEventsBuffer.add(touchEvent);
                        break;
                }
            }
            return true;
        }
    }

    public boolean isTouchDown(int pointer) {
        synchronized (this) {
            int index = getIndex(pointer);
            if (index < 0 || index >= MAX_TOUCHPOINTS)
                return false;
            else
                return isTouched[index];
        }
    }

    public int getTouchX(int pointer) {
        synchronized (this) {
            int index = getIndex(pointer);
            if (index < 0 || index >= MAX_TOUCHPOINTS)
                return 0;
            else
                return touchX[index];
        }
    }

    public int getTouchY(int pointer) {
        synchronized (this) {
            int index = getIndex(pointer);
            if (index < 0 || index >= MAX_TOUCHPOINTS)
                return 0;
            else
                return touchY[index];
        }
    }

    public List<TouchEvent> getTouchEvents() {
        synchronized (this) {
            int len = touchEvents.size();
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();
            return touchEvents;
        }
    }

    // returns the index for a given pointerId or -1 if no index.
    private int getIndex(int pointerId) {
        for (int i = 0; i < MAX_TOUCHPOINTS; i++) {
            if (id[i] == pointerId) {
                return i;
            }
        }
        return -1;
    }
}

