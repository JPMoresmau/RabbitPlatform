package com.github.jpmoresmau.rabbitplatform.game;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.github.jpmoresmau.rabbitplatform.R;
import com.github.jpmoresmau.rabbitplatform.framework.Game;
import com.github.jpmoresmau.rabbitplatform.framework.Graphics;
import com.github.jpmoresmau.rabbitplatform.framework.GraphicsUtils;
import com.github.jpmoresmau.rabbitplatform.framework.Image;
import com.github.jpmoresmau.rabbitplatform.framework.Screen;
import com.github.jpmoresmau.rabbitplatform.framework.TouchEvent;
import com.github.jpmoresmau.rabbitplatform.framework.TouchEventType;
import com.github.jpmoresmau.rabbitplatform.game.model.Ground;
import com.github.jpmoresmau.rabbitplatform.game.model.GroundComponent;
import com.github.jpmoresmau.rabbitplatform.game.model.Player;
import com.github.jpmoresmau.rabbitplatform.game.model.Score;
import com.github.jpmoresmau.rabbitplatform.game.model.Speed;

import java.util.List;

/**
 * Created by jpmoresmau on 2/1/16.
 */
public class GameScreen extends Screen {
    private Player player;
    private Speed speed;
    private Ground ground;
    private Score score;

    private int minX=5;
    private int maxY=0;

    private boolean paused=false;
    private boolean scored=false;
    private boolean highscored=false;


    public GameScreen(Game game) {

        super(game);
        Graphics g = getGame().getGraphics();
        maxY=g.getHeight()-RAssets.ground_grass.getHeight();
        init();

    }

    private void init(){
        scored=false;
        highscored=false;
        speed=new Speed();
        Graphics g = getGame().getGraphics();
        ground=new Ground(g.getWidth(),maxY);
        int x=minX+RAssets.ground_grass.getWidth()/2;

        player=new Player(x,maxY);

        score=new Score(getGame());
    }

    @Override
    public void update(float deltaTime) {

        List<TouchEvent> touchEvents = getGame().getInput().getTouchEvents();
        for (TouchEvent event : touchEvents) {
            if (event.getType().equals(TouchEventType.TOUCH_DOWN)) {
                if (isControlEvent(event)) {
                    paused = !paused;
                    scored = false;
                    if (player.isDead()) {
                        init();
                        return;
                    }
                } else if (isScoreEvent(event)){
                    paused = true;
                    scored = true;
                } else {
                    player.addJump();
                }
            } else if (!paused && event.getType().equals(TouchEventType.TOUCH_UP)) {
                player.stopJump();
            }
        }
        if (!paused) {
            score.update(deltaTime);
            double inc=speed.update(deltaTime);
            ground.update(deltaTime,inc);
            //int maxY=ground.getRealMaxY(player.getX());
            int maxY1=ground.getRealMaxY(player.getX()-player.getFeetWidth());
            int maxY2=ground.getRealMaxY(player.getX()+player.getFeetWidth());
            int realMax=Math.min(maxY1,maxY2);
            //Log.d("GameScreen","maxY1:"+maxY1);
            //Log.d("GameScreen","maxY2:"+maxY2);
            if (player.getY()>maxY2){
                // forgot to jump
                player.die();
                highscored=score.gameOver();
                paused=true;
            } else {
                player.update(deltaTime, realMax, inc);
                if (!player.isJumping() && (player.getY() > maxY1 || player.getY() > maxY2 || player.getY() > this.maxY || player.getY() >= getGame().getGraphics().getHeight())) {
                    Log.d("GameScreen", "over:player.getY():" + player.getY());
                    Log.d("GameScreen", "over:maxY1:" + maxY1);
                    Log.d("GameScreen", "over:maxY2:" + maxY2);
//                Log.d("GameScreen","over:player.getY()>realMax:"+(player.getY()>realMax));
//                Log.d("GameScreen","over:this.maxY:"+realMax);
//                Log.d("GameScreen","over:player.getY()>=getGame().getGraphics().getHeight():"+(player.getY()>=getGame().getGraphics().getHeight()));
                    // game over!!
                    player.die();
                    highscored = score.gameOver();
                    paused = true;
                }
            }
        } else if (player.isDead()){
            int maxY=ground.getRealMaxY(player.getX());
            player.update(deltaTime, maxY, 1);
        }
    }

    private boolean isControlEvent(TouchEvent event){
        return event.getX()>=5 && event.getX()<=5+RAssets.forward.getWidth()
                && event.getY()>=5 && event.getY()<=5+RAssets.forward.getHeight();
    }

    private boolean isScoreEvent(TouchEvent event){
        Graphics g = getGame().getGraphics();
        return event.getX()>=g.getWidth()-150
                && event.getY()<=40;
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = getGame().getGraphics();

        g.clearScreen(RAssets.SKY);

        Image ctrl=paused?RAssets.forward:RAssets.pause;
        g.drawImage(ctrl,5,5);

        g.drawString(score.getFormattedScore(),g.getWidth()-150,40, RAssets.scorePaint);


        for (GroundComponent gc:ground.getComponents()){
            if (gc.getImage()!=null) {
                g.drawImage(gc.getImage(), minX + gc.getX(), maxY - gc.getY());
            }
        }
        int maxY1=ground.getRealMaxY(player.getX());
        Image img=player.getImage(maxY1);
        g.drawImage(img, player.getX()-img.getWidth()/2, player.getY()-img.getHeight());

        if (player.isDead()){
            Rect r=GraphicsUtils.drawCenter(g, getGame().getResourceString(R.string.game_over),RAssets.messagePaint);
            //GraphicsUtils.drawCenter(g,RAssets.gameover);
            if (highscored){
                GraphicsUtils.drawCenter(g, getGame().getResourceString(R.string.best_scored),RAssets.messagePaint,0,r.height()+10);
            }
        } else if (paused){
            //GraphicsUtils.drawCenter(g,RAssets.paused);
            GraphicsUtils.drawCenter(g,getGame().getResourceString(R.string.paused),RAssets.messagePaint);
        }
        if (scored){
            int x=5+RAssets.forward.getWidth();
            g.drawString(getGame().getResourceString(R.string.written),x, 100,RAssets.aboutPaint);
            g.drawString(getGame().getResourceString(R.string.framework),x, 150,RAssets.aboutPaint);
            g.drawString(getGame().getResourceString(R.string.art),x, 200,RAssets.aboutPaint);
            String s=String.format(getGame().getResourceString(R.string.best), score.getHighScore());
            g.drawString(s,x, 250,RAssets.aboutPaint);
        }
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
