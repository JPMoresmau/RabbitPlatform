package com.github.jpmoresmau.rabbitplatform.framework;

/**
 * http://www.kilobolt.com/day-5-the-android-game-framework-part-i.html
 */
public abstract class Screen {
    private final Game game;

    public Screen(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public abstract void update(float deltaTime);

    public abstract void paint(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();

    public abstract void backButton();
}
