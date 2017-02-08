package com.taeksukim.android.musicplayer;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Player 액티비티와 adapter를 공유하기 위해 static으로 선언했다.
    //이게 최선인지 고민을 해볼 필요가 있다.
    //public static MusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버전 체크해서 마시말로우보다 낮으면 런타임 권한 체크를 하지 않는다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkpermission();
        }else {
            init();
        }
    }



    private final int REQ_CODE = 100;
    //1. 권한 체크

    @TargetApi(Build.VERSION_CODES.M)// Taeget 지정 애니메이션
    private void checkpermission() {
        //1.1 런타임 권한 체크
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                ) {
            //1.2 요청할 권한 목록 작성
            String permArr[] = {Manifest.permission.READ_EXTERNAL_STORAGE
                   };
            //1.3 시스템에 권한 요청
            requestPermissions(permArr, REQ_CODE);

        } else {
            init();
        }
    }


    //2. 권한 체크 후 콜백 < 사용자가 확인 후 시스템이 호출하는 함수
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQ_CODE) {
            //2.1 배열에 넘긴 런타임 권한을 체크해서 승인이 됬으면
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED
                    ){
                //2.2 프로그램 실행
                init();
            } else {

                Message.show("권한을 허용하지 않으시면 프로그램을 실행할 수 없습니다.",this);// 사용자가 거절하는 경우가 많으므로 알려주는 문구를 넣어주어야 한다.
                // 선택 : 1 종료, 2 권한체크 다시 물어보기
                // checkPermission();
                finish();
                //
            }
        }
    }

    private void init() {

        Message.show("프로그램을 실행합니다.", this);

        listInit();
    }

    private void listInit() {
        //3.2라사이클 뷰
        RecyclerView listView = (RecyclerView) findViewById(R.id.listView);
        MusicAdapter adapter = new MusicAdapter(this);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
    }
}



