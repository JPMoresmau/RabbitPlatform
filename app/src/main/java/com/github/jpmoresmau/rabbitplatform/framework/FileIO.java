package com.github.jpmoresmau.rabbitplatform.framework;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.SharedPreferences;
/**
 * http://www.kilobolt.com/day-5-the-android-game-framework-part-i.html
 */
public interface FileIO {
    public InputStream readFile(String file) throws IOException;

    public OutputStream writeFile(String file) throws IOException;

    public InputStream readAsset(String file) throws IOException;

    public SharedPreferences getSharedPref();
}
