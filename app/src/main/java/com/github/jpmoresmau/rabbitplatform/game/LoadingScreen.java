package com.github.jpmoresmau.rabbitplatform.game;

import com.github.jpmoresmau.rabbitplatform.framework.Game;
import com.github.jpmoresmau.rabbitplatform.framework.Graphics;
import com.github.jpmoresmau.rabbitplatform.framework.GraphicsUtils;
import com.github.jpmoresmau.rabbitplatform.framework.Image;
import com.github.jpmoresmau.rabbitplatform.framework.ImageFormat;
import com.github.jpmoresmau.rabbitplatform.framework.Screen;

import java.lang.reflect.Field;

/**
 * Created by jpmoresmau on 2/1/16.
 */
public class LoadingScreen extends Screen {
    private Image loading; // 185*32

    public LoadingScreen(Game game) {
        super(game);
        Graphics g = getGame().getGraphics();
        loading = g.newImage("loading.png", ImageFormat.RGB565);

    }

    @Override
    public void update(float deltaTime) {
        Graphics g = getGame().getGraphics();
        try {

            for (Field f:RAssets.class.getDeclaredFields()){
                if (f.getType().equals(Image.class)){
                    String name=f.getName();
                    f.set(null,g.newImage(name+".png",ImageFormat.RGB565));
                }
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        getGame().setScreen(new GameScreen(getGame()));


    }


    @Override
    public void paint(float deltaTime) {
        Graphics g = getGame().getGraphics();

        g.clearScreen(RAssets.GREEN);
        GraphicsUtils.drawCenter(g,loading);
    }


    @Override
    public void pause() {


    }


    @Override
    public void resume() {


    }


    @Override
    public void dispose() {


    }


    @Override
    public void backButton() {


    }
}

