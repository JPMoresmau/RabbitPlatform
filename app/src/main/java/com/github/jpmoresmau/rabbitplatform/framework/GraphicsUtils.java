package com.github.jpmoresmau.rabbitplatform.framework;

/**
 * Created by jpmoresmau on 2/2/16.
 */
public class GraphicsUtils {

    public static void drawCenter(Graphics g,Image i){
        int x=(g.getWidth()-i.getWidth())/2;
        int y=(g.getHeight()-i.getHeight())/2;
        g.drawImage(i, x, y);
    }
}
