package com.taeksukim.android.musicplayer;

import android.util.Log;

/**
 * Created by TaeksuKim on 2017. 2. 2..
 */

public class Logger {
    //Debug mode : 정상동작 안될 경우 강제로 세팅한다.
    public final static boolean DEBUG_MODE = BuildConfig.DEBUG;

    public static void print(String string, String className) {

        if(DEBUG_MODE)
            Log.d(className, string);

    }
}
