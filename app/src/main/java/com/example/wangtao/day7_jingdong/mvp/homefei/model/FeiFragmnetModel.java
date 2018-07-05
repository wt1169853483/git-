package com.example.wangtao.day7_jingdong.mvp.homefei.model;

import android.util.Log;

import com.example.wangtao.day7_jingdong.utils.https.config.Url_Http;
import com.example.wangtao.day7_jingdong.utils.https.http.HttpUtils;
import com.example.wangtao.day7_jingdong.utils.https.http.OkHttpUtil;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class FeiFragmnetModel {
    private static final String TAG = "FeiFragmnetModel";
          public void shouModel(String url, final IncanCallChFei incanCallCh){

              OkHttpUtil okHttp = OkHttpUtil.getOkHttp();
              okHttp.get(url, new OkHttpUtil.IncanCallbach() {
                  @Override
                  public void getSuccess(String json) {
                       if (incanCallCh != null){
                           incanCallCh.getSuccess(json);
                       }
                  }

                  @Override
                  public void getError(Exception error) {

                  }
              });

          }

    public void shouChildModel(String url, final IncanChildCallChFei incanChildCallChFei){
        OkHttpUtil okHttp = OkHttpUtil.getOkHttp();
       //Log.d(TAG, "shouChildModel:**************** "+url);
        if (url.equalsIgnoreCase("0")){
            url=Url_Http.fei_RightURl+"?cid="+1;
           // Log.d(TAG, "shouChildModel:****************** "+url);
        }else {
            url = Url_Http.fei_RightURl + "?cid=" + url;
        }
        okHttp.get(url, new OkHttpUtil.IncanCallbach() {
            @Override
            public void getSuccess(String json) {
                   incanChildCallChFei.getChildSuccess(json);
            }

            @Override
            public void getError(Exception error) {
                  //incanChildCallChFei.getChildError(error);
            }
        });
    }

         public interface IncanCallChFei{
                  void getSuccess(String json);
                  void getError(String error);
         };


       public interface IncanChildCallChFei{
          void getChildSuccess(String json);
          void getChildError(String error);
    };
}
