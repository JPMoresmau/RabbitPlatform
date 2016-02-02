package com.github.jpmoresmau.rabbitplatform.framework;

/**
 * http://www.kilobolt.com/day-5-the-android-game-framework-part-i.html
 */

public interface Game {

    Audio getAudio();

    Input getInput();

    FileIO getFileIO();

    Graphics getGraphics();

    void setScreen(Screen screen);

    Screen getCurrentScreen();

     Screen getInitScreen();
}
