package com.taeksukim.android.musicplayer;


import android.media.MediaPlayer;

/**
 * Created by TaeksuKim on 2017. 2. 24..
 */

public class App {

    public static MediaPlayer player = null;


    //액션 플래그
    public static final String ACTION_PLAY = "com.taeksukim.android.musicplayer.Action.play";

    //플레이어 상태 플래그
    public static final int PLAY = 0;
    public static final int PAUSE = 1;
    public static final int STOP = 2;

    //현재 플레이어 상태
    public static int playStatus = STOP;

    // 현재 음악 위치
    public static int position = 0;

}
