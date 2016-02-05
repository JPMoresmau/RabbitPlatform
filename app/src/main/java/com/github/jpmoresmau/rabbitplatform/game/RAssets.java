package com.github.jpmoresmau.rabbitplatform.game;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.github.jpmoresmau.rabbitplatform.framework.Image;

/**
 * Created by jpmoresmau on 2/1/16.
 */
public class RAssets {

    public static Image bunny1_ready;
    public static Image bunny1_jump;
    public static Image bunny1_stand;
    public static Image bunny1_hurt;
    public static Image bunny1_walk1;
    public static Image bunny1_walk2;
    public static Image ground_grass;
    public static Image ground_grass_small;

    public static Image forward;
    public static Image pause;
    public static Image paused;
    public static Image gameover;

    public static Image carrot;
    public static Image carrot_gold;

    public static int GREEN = 0x2C774C;
    public static int SKY = 0xade6ff;
    public static int ORANGE = 0xe86a1700;

    public static Typeface block_font;


    public static Paint scorePaint =new Paint();
    public static Paint aboutPaint =new Paint();
    public static Paint messagePaint =new Paint();

    public static void initPaints(){

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTypeface(RAssets.block_font);
        scorePaint.setTextSize(40);

        aboutPaint.setColor(RAssets.ORANGE);
        aboutPaint.setTypeface(RAssets.block_font);
        aboutPaint.setTextSize(40);

        messagePaint.setColor(RAssets.ORANGE);
        messagePaint.setTypeface(RAssets.block_font);
        messagePaint.setTextSize(80);
    }
}
