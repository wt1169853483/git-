package com.example.wangtao.day7_jingdong.mvp.mainhome.view.Activity;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.wangtao.day7_jingdong.R;
import com.umeng.socialize.UMShareAPI;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int i=3;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                 i--;
                 if (i == 0){
                     Intent intent=new Intent(MainActivity.this,FragmentActivity.class);
                     startActivity(intent);
                     timer.cancel();
                 }
            }
        },1000,1000);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}