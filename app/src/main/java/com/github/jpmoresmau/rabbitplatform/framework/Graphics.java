package com.github.jpmoresmau.rabbitplatform.framework;


import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * http://www.kilobolt.com/day-5-the-android-game-framework-part-i.html
 */
public interface Graphics {

    Image newImage(String fileName, ImageFormat format);

    void clearScreen(int color);

    void drawLine(int x, int y, int x2, int y2, int color);

    void drawRect(int x, int y, int width, int height, int color);

    void drawImage(Image image, int x, int y, int srcX, int srcY,
                          int srcWidth, int srcHeight);

    void drawImage(Image Image, int x, int y);

    void drawString(String text, int x, int y, Paint paint);

    int getWidth();

    int getHeight();

    void drawARGB(int i, int j, int k, int l);

    Typeface loadTypeface(String path);
}
