package com.example.wangtao.day7_jingdong.mvp.mainhome.model;

import com.example.wangtao.day7_jingdong.mvp.mainhome.model.bean.ShouUserBean;
import com.example.wangtao.day7_jingdong.utils.https.http.HttpUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class ShouFragmnetModel {
          public void shouModel(String url, final IncanCallCh incanCallCh){
              HttpUtils httpUtile = HttpUtils.getHttpUtile();
              httpUtile.get(url);
              httpUtile.getHttpListener(new HttpUtils.HttpListener() {
                  @Override
                  public void getDataSuccess(String json) {
                      Gson gson=new Gson();
                      ShouUserBean shouUserBean = gson.fromJson(json, ShouUserBean.class);
                      List<ShouUserBean.DataBean> data = shouUserBean.getData();
                      incanCallCh.getSuccess(data);
                  }

                  @Override
                  public void getDataError(String error) {
                          incanCallCh.getError(error);
                  }
              });
          }
         public interface IncanCallCh{
                  void getSuccess(List<ShouUserBean.DataBean> data);
                  void getError(String error);
         };
}
