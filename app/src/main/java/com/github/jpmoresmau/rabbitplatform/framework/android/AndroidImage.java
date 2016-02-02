package com.github.jpmoresmau.rabbitplatform.framework.android;

import android.graphics.Bitmap;

import com.github.jpmoresmau.rabbitplatform.framework.Image;
import com.github.jpmoresmau.rabbitplatform.framework.ImageFormat;

/**
 * http://www.kilobolt.com/day-6-the-android-game-framework-part-ii.html
 */
public class AndroidImage implements Image {
    private Bitmap bitmap;
    private ImageFormat format;

    public AndroidImage(Bitmap bitmap, ImageFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public ImageFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}