package com.example.wangtao.day7_jingdong.mvp.homewo.model;

import com.example.wangtao.day7_jingdong.utils.https.config.Url_Http;
import com.example.wangtao.day7_jingdong.utils.https.http.HttpUtils;
import com.example.wangtao.day7_jingdong.utils.https.http.OkHttpUtil;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class WoFragmnetModel {
    private static final String TAG = "WoFragmnetModel";
          public void WoModel(String url, final IncanCallCh incanCallCh){
              HttpUtils httpUtile = HttpUtils.getHttpUtile();
              httpUtile.get(url);
              httpUtile.getHttpListener(new HttpUtils.HttpListener() {
                  @Override
                  public void getDataSuccess(String data) {
                     /* Gson gson=new Gson();
                      ShouUserBean shouUserBean = gson.fromJson(json, ShouUserBean.class);
                      List<ShouUserBean.DataBean> data = shouUserBean.getData();
                      incanCallCh.getSuccess(data);*/
                     incanCallCh.getDataSuccess(data);
                  }


                  @Override
                  public void getDataError(String error) {
                          incanCallCh.getDataError(error);
                  }
              });
          }

    public interface IncanCallCh{
        void getDataSuccess(String data);
        void getDataError(String error);
    };
}
