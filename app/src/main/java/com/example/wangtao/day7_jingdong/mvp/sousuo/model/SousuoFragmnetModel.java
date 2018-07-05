package com.example.wangtao.day7_jingdong.mvp.sousuo.model;

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
public class SousuoFragmnetModel {
    private static final String TAG = "SousuoFragmnetModel";
/*
    https://www.zhaoapi.cn/product/searchProducts?keywords=手机&page=1&sort=0
    https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1*/
          public void SousuoModel(String keywords,String page,String sort, final IncanCallCh incanCallCh){
              String url= Url_Http.fei_sousuo+"?keywords="+keywords+"&page="+1+"&sort="+sort;
              OkHttpUtil okHttp = OkHttpUtil.getOkHttp();
              Log.d(TAG, "SousuoModel:+++++++++++++++++++= "+url);
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
}
