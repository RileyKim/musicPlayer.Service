package com.taeksukim.android.musicplayer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TaeksuKim on 2017. 2. 1..
 */

public class DataLoader {

    //1.데이터 컨텐츠 URI정의
    private final static Uri URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    //2. 데이터에서 가져올 데이터 컬럼명을 String배열에 담는다.
    //3. 컬럼명은 Content Uri 의 패키지에 들어있다.
    private final static String PROJ[] = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST
    };

    //datas를 두개의 activity에서 고유하기 위해 static 형태로 변경
    private static List<Music> datas = new ArrayList<>();


    //static 변수인 datas를 체크해서 null이면 load를 실행
    public static List<Music> get(Context context){
        if(datas == null || datas.size() == 0){
            load(context);
        }

        return datas;
    }

    //load 함수는 get 함수를 통해서 접근한다.
    public static void load(Context context){

        // 1. 데이터에 접근하기위해 ContentResolver 를 불러온다.
        ContentResolver resolver = context.getContentResolver();

        //2. Content Resilver로 쿼리한 데이터를 커서에 담는다
        Cursor cursor = resolver.query(URI, PROJ, null, null, null);

        //3. Cursor에 담긴 데이터를 반복문을 돌면서 꺼낸다.
        if(cursor != null) {
            while(cursor.moveToNext()) {

                Music music = new Music();

                //데이터 처리

                music.id = getValue(cursor, PROJ[0]);
                music.album_id = getValue(cursor, PROJ[1]);
                music.title = getValue(cursor, PROJ[2]);
                music.artist = getValue(cursor, PROJ[3]);

                music.album_image = getAlbumImageSimple(music.album_id);
                music.uri = getMusicUri(music.id);

                //주석처리..
                //music.bitmap_image = getAlbumImageBitmap(music.album_id);

                datas.add(music);



            }
            //처리 후 커서를 닫아준다
            cursor.close();
        }

    }

    private static String getValue(Cursor cursor, String columnName) {
        int idx = cursor.getColumnIndex(columnName);
        return cursor.getString(idx);
    }

    //음악 id로 uri를 가져오는 함수
    private static Uri getMusicUri(String music_id){

        Uri content_uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        return Uri.withAppendedPath(content_uri, music_id);
    }

    // 가장 간단하게 앨범 이미지를 가져오는 방법
    //문제점 : 실제 앨범 데이터만 있어서 이미지를 불러오지 못하는 경우가 있다.

    //앨범 URI 생성
    private static Uri getAlbumImageSimple(String album_id) {

        return Uri.parse("content://media/external/audio/albumart/" + album_id);

    }

}
