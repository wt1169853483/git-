package com.example.wangtao.day7_jingdong.mvp.homeshou.presenter;

import com.example.wangtao.day7_jingdong.base.BasePresenter;
import com.example.wangtao.day7_jingdong.mvp.homeshou.model.ShouFragmnetModel;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview.IShouFragment;
import com.umeng.commonsdk.debug.I;

/**
 * Created by wangtao on 2018/7/5.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class ShouFragmentPresenter extends BasePresenter<IShouFragment>{
    private ShouFragmnetModel shouFragmnetModel;
    public ShouFragmentPresenter(IShouFragment iview) {
        super(iview);
    }

    @Override
    protected void InitModel() {
        shouFragmnetModel =new ShouFragmnetModel();
    }

    public void shouPrement(String url){
        shouFragmnetModel.shouModel(url, new ShouFragmnetModel.IncanCallCh() {
            @Override
            public void getDataSuccess(String data) {
                if (iview !=null){
                    iview.getDataSuccess(data);
                }
            }

            @Override
            public void getDataError(String error) {
                if (iview !=null){
                    iview.getDataError(error);
                }
            }
        });

          shouFragmnetModel.shouFeiModel(new ShouFragmnetModel.IncanCallFeiBach() {
              @Override
              public void getSuccessOne(String json) {
                    if (iview != null){
                         iview.getSuccessOne(json);
                    }
              }

              @Override
              public void getErrorOne(String error) {
                  if (iview != null){
                      iview.getErrorOne(error);
                  }
              }
          });


    }
}
