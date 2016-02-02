package com.github.jpmoresmau.rabbitplatform.framework.android;

import android.media.SoundPool;

import com.github.jpmoresmau.rabbitplatform.framework.Sound;

/**
 * http://www.kilobolt.com/day-6-the-android-game-framework-part-ii.html
 */
public class AndroidSound  implements Sound {
    private int soundId;
    private SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }

}
