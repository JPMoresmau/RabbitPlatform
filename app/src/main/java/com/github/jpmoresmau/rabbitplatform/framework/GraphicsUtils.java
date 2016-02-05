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

    public static Rect drawCenter(Graphics g,String text,Paint paint){
        return drawCenter(g,text,paint,0,0);
    }

    public static Rect drawCenter(Graphics g,String text,Paint paint,int offsetx,int offsety){
        Rect r=new Rect();
        paint.getTextBounds(text,0,text.length(),r);
        int x=(g.getWidth()-r.width())/2;
        //int y=(g.getHeight())/2;
        int y = (int) ((g.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
        g.drawString(text, x+offsetx, y+offsety,paint);
        r.offsetTo(x+offsetx, y+offsety);
        return r;
    }

    public static boolean intersects(Image img1,int x1,int y1,Image img2, int x2,int y2){
        Rect rect1=new Rect();
        rect1.set(x1,y1,x1+img1.getWidth(),y1+img1.getWidth());
        Rect rect2=new Rect();
        rect2.set(x2, y2, x2 + img2.getWidth(), y2 + img2.getWidth());
        Rect rect3=new Rect();
        if (rect3.setIntersect(rect1,rect2)){
            return rect3.width()>img2.getWidth()/2 && rect3.height()>img2.getHeight()/2;
        }
        return false;
    }
}
