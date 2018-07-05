package com.example.wangtao.day7_jingdong.mvp.homeshou.model;

import android.util.Log;

import com.example.wangtao.day7_jingdong.mvp.homefei.model.FeiFragmnetModel;
import com.example.wangtao.day7_jingdong.utils.https.config.Url_Http;
import com.example.wangtao.day7_jingdong.utils.https.http.HttpUtils;
import com.example.wangtao.day7_jingdong.utils.https.http.OkHttpUtil;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class ShouFragmnetModel {
    private static final String TAG = "ShouFragmnetModel";
          public void shouModel(String url, final IncanCallCh incanCallCh){
              OkHttpUtil okHttp = OkHttpUtil.getOkHttp();
              okHttp.get(url, new OkHttpUtil.IncanCallbach() {
                  @Override
                  public void getSuccess(String json) {
                      if (incanCallCh != null){
                          incanCallCh.getDataSuccess(json);
                      }
                  }

                  @Override
                  public void getError(Exception error) {

                  }
              });

          }

    public interface IncanCallCh{
        void getDataSuccess(String data);
        void getDataError(String error);
    };


   /* public void shouFeiModel(final IncanCallCh incanCallCh){
        HttpUtils httpUtile = HttpUtils.getHttpUtile();
        httpUtile.get(Url_Http.shou_fei_url);
        httpUtile.getHttpListener(new HttpUtils.HttpListener() {
            @Override
            public void getDataSuccess(String json) {
                    incanCallCh.getSuccess(json);
            }

            @Override
            public void getDataError(String error) {
                  incanCallCh.getError(error);
            }
        });
    }*/

    public void shouFeiModel(final IncanCallFeiBach incanCallFeiBach){
     /*  HttpUtils httpUtile = HttpUtils.getHttpUtile();
       httpUtile.get(Url_Http.shou_fei_url);
       httpUtile.getHttpListener(new HttpUtils.HttpListener() {
           @Override
           public void getDataSuccess(String json) {
               Log.d(TAG, "getDataSuccess: Model++++++++++++++++++"+json);
                   incanCallFeiBach.getSuccessOne(json);
           }

           @Override
           public void getDataError(String error) {
                 incanCallFeiBach.getErrorOne(error);
           }
       });*/
        OkHttpUtil okHttp = OkHttpUtil.getOkHttp();
        okHttp.get(Url_Http.shou_fei_url, new OkHttpUtil.IncanCallbach() {
            @Override
            public void getSuccess(String json) {
                incanCallFeiBach.getSuccessOne(json);
            }

            @Override
            public void getError(Exception error) {
            }
        });
    }

    public interface IncanCallFeiBach{
        void getSuccessOne(String json);
        void getErrorOne(String error);
    };
}
