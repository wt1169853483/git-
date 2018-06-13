package com.example.wangtao.day7_jingdong.utils.https.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wangtao on 2018/6/12.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class HttpUtils  {


    private MyHander myHander =new MyHander();
    private HttpListener httpListener;
    private static final String TAG = "HttpUtile";

    private static HttpUtils httpUtile=new HttpUtils();
    public static HttpUtils getHttpUtile() {
        if(httpUtile == null){
            httpUtile=new HttpUtils();
        }
        return httpUtile;
    }
    //封装get
    public void get(final String url){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url1=new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                    connection.setConnectTimeout(3000);

                    if (connection.getResponseCode() == 200){
                        Log.d(TAG, "HttpUtile-------------- "+"请求成功");
                        InputStream inputStream = connection.getInputStream();
                        String json = HttpString.IntoString(inputStream);
                        Log.d(TAG, "HttpUtile-------------- "+json);
                        Message msg = myHander.obtainMessage();
                        msg.what=0;
                        msg.obj=json;
                        myHander.sendMessage(msg);
                    }else{
                        Log.d(TAG, "HttpUtile-------------- "+"请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "HttpUtile-------------- "+e.getMessage());
                }
            }
        }.start();
    }

    class MyHander extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String json= (String) msg.obj;
                    httpListener.getDataSuccess(json);
                    break;
                case 1:
                    httpListener.getDataError("访问失败");
                    break;
            }
        }
    }

    //回调
    public interface HttpListener{
        void getDataSuccess(String json);
        void getDataError(String error);
    }
    public void getHttpListener(HttpListener httpListener){
        this.httpListener =httpListener;
    }
}
