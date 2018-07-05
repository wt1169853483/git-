package com.example.wangtao.day7_jingdong.utils.https.http;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by wangtao on 2018/6/21.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class OkHttpUtil {
     //单利模式
     private static OkHttpUtil okHttp=new OkHttpUtil();
     private OkHttpClient okHttpClient;
     private Handler handler;

    public OkHttpUtil() {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
          handler =new Handler(Looper.myLooper());
          okHttpClient=new OkHttpClient.Builder()
                  .writeTimeout(2000, TimeUnit.MILLISECONDS)
                  .connectTimeout(2000, TimeUnit.MILLISECONDS)
                  .readTimeout(2000, TimeUnit.MILLISECONDS)
                  .addInterceptor(httpLoggingInterceptor)
                  .build();
    }

    public static OkHttpUtil getOkHttp() {
        if(okHttp == null){
             synchronized (OkHttpUtil.class){
                   if (okHttp == null){
                       okHttp=new OkHttpUtil();
                   }
             }
        }
        return okHttp;
    }
    //封装post
    public void  get(String url, final IncanCallbach incanCallbach){
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (incanCallbach != null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            incanCallbach.getError(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (incanCallbach != null){
                    if (response != null && response.isSuccessful()){
                        final String json = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                incanCallbach.getSuccess(json);
                            }
                        });
                    }
                }
            }
        });
    }


     //封装post
    public void  post(String url, Map<String,String> map, final IncanCallbach incanCallbach){
        FormBody.Builder formBody=new FormBody.Builder();
        for (String key: map.keySet()) {
              formBody.add(key,map.get(key));
        }
        FormBody build = formBody.build();

        Request request=new Request.Builder()
                .post(build)
                .url(url)
                .build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                    if (incanCallbach != null){
                          handler.post(new Runnable() {
                              @Override
                              public void run() {
                                  incanCallbach.getError(e);
                              }
                          });
                    }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (incanCallbach != null){
                   if (response != null && response.isSuccessful()){
                       final String json = response.body().toString();
                       handler.post(new Runnable() {
                           @Override
                           public void run() {
                               incanCallbach.getSuccess(json);
                           }
                       });
                   }
                }
            }
        });
    }
    public interface IncanCallbach{
          void getSuccess(String json);
          void getError(Exception error);
    }
}
