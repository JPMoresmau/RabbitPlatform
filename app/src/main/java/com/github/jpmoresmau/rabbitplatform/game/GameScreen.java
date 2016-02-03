package com.github.jpmoresmau.rabbitplatform.game;

import android.graphics.Color;
import android.graphics.Paint;

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

import java.util.List;

/**
 * Created by jpmoresmau on 2/1/16.
 */
public class GameScreen extends Screen {
    private Player player;
    private Ground ground;
    private Score score;

    private int minX=5;
    private int maxY=0;

    private boolean paused=false;

    private boolean over=false;

    private Paint p=new Paint();


    public GameScreen(Game game) {

        super(game);
        Graphics g = getGame().getGraphics();
        maxY=g.getHeight()-RAssets.ground_grass.getHeight();
        init();

        p.setColor(Color.WHITE);
        p.setTypeface(RAssets.block_font);
        p.setTextSize(40);
    }

    private void init(){
        Graphics g = getGame().getGraphics();
        ground=new Ground(g.getWidth(),maxY);
        int x=minX+RAssets.ground_grass.getWidth()/2;

        player=new Player(x,maxY);

        score=new Score();
    }

    @Override
    public void update(float deltaTime) {

        List<TouchEvent> touchEvents = getGame().getInput().getTouchEvents();
        for (TouchEvent event : touchEvents) {
            if (isControlEvent(event)){
                if (event.getType().equals(TouchEventType.TOUCH_DOWN)){
                    paused=!paused;
                    if (over){
                        over=false;
                        init();
                        return;
                    }
                }
            } else if (!paused && event.getType().equals(TouchEventType.TOUCH_DOWN)) {
                player.addJump();
            } else if (!paused && event.getType().equals(TouchEventType.TOUCH_UP)) {
                player.stopJump();
            }
        }
        if (!paused) {
            score.update(deltaTime);
            ground.update(deltaTime);
            int maxY=ground.getRealMaxY(player.getX());
            player.update(deltaTime, maxY);
            if (!player.isJumping() && (player.getY()>maxY || player.getY()>=getGame().getGraphics().getHeight())){
                // game over!!
                over=true;
                paused=true;
            }
        }
    }

    private boolean isControlEvent(TouchEvent event){
        return event.getX()>=5 && event.getX()<=5+RAssets.forward.getWidth()
                && event.getY()>=5 && event.getY()<=5+RAssets.forward.getHeight();
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = getGame().getGraphics();

        g.clearScreen(RAssets.SKY);

        Image ctrl=paused?RAssets.forward:RAssets.pause;
        g.drawImage(ctrl,5,5);

        g.drawString(score.getFormattedScore(),g.getWidth()-150,40,p);


        for (GroundComponent gc:ground.getComponents()){
            if (gc.getImage()!=null) {
                g.drawImage(gc.getImage(), minX + gc.getX(), maxY - gc.getY());
            }
        }

        Image img=player.getImage(maxY);
        g.drawImage(img, player.getX()-img.getWidth()/2, player.getY()-img.getHeight());

        if (over){
            GraphicsUtils.drawCenter(g,RAssets.gameover);
        } else if (paused){
            GraphicsUtils.drawCenter(g,RAssets.paused);
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
