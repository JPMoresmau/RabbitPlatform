package com.github.jpmoresmau.rabbitplatform.framework;

/**
 * http://www.kilobolt.com/day-5-the-android-game-framework-part-i.html
 */
public interface Music {
    public void play();

    public void stop();

    public void pause();

    public void setLooping(boolean looping);

    public void setVolume(float volume);

    public boolean isPlaying();

    public boolean isStopped();

    public boolean isLooping();

    public void dispose();

    void seekBegin();
}