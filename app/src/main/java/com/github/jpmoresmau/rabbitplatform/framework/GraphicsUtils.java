package com.github.jpmoresmau.rabbitplatform.framework;

import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jpmoresmau on 2/2/16.
 */
public class GraphicsUtils {

    public static void drawCenter(Graphics g,Image i){
        int x=(g.getWidth()-i.getWidth())/2;
        int y=(g.getHeight()-i.getHeight())/2;
        g.drawImage(i, x, y);
    }

    public static void drawCenter(Graphics g,String text,Paint paint){
        Rect r=new Rect();
        paint.getTextBounds(text,0,text.length(),r);
        int x=(g.getWidth()-r.width())/2;
        //int y=(g.getHeight())/2;

        int y = (int) ((g.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
        g.drawString(text, x, y,paint);
    }
}
