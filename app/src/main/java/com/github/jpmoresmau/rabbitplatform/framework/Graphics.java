package com.github.jpmoresmau.rabbitplatform.framework;


import android.graphics.Paint;
/**
 * http://www.kilobolt.com/day-5-the-android-game-framework-part-i.html
 */
public interface Graphics {

    public Image newImage(String fileName, ImageFormat format);

    public void clearScreen(int color);

    public void drawLine(int x, int y, int x2, int y2, int color);

    public void drawRect(int x, int y, int width, int height, int color);

    public void drawImage(Image image, int x, int y, int srcX, int srcY,
                          int srcWidth, int srcHeight);

    public void drawImage(Image Image, int x, int y);

    void drawString(String text, int x, int y, Paint paint);

    public int getWidth();

    public int getHeight();

    public void drawARGB(int i, int j, int k, int l);

}
