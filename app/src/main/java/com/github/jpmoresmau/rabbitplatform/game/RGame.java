package com.github.jpmoresmau.rabbitplatform.game;

import com.github.jpmoresmau.rabbitplatform.framework.Screen;
import com.github.jpmoresmau.rabbitplatform.framework.android.AndroidGame;

/**
 * Created by jpmoresmau on 2/1/16.
 */
public class RGame extends AndroidGame {
    @Override
    public Screen getInitScreen() {
        return new LoadingScreen(this);
    }

    @Override
    public void onBackPressed() {
        getCurrentScreen().backButton();
    }
}
