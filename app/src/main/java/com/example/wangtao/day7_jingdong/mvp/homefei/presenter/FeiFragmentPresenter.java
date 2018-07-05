package com.example.wangtao.day7_jingdong.mvp.homefei.presenter;

import com.example.wangtao.day7_jingdong.base.BasePresenter;
import com.example.wangtao.day7_jingdong.mvp.homefei.model.FeiFragmnetModel;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview.IShouFragment;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class FeiFragmentPresenter extends BasePresenter<IShouFragment>{
    private FeiFragmnetModel feiFragmnetModel;
    public FeiFragmentPresenter(IShouFragment iview) {
        super(iview);
    }

    @Override
    protected void InitModel() {
          feiFragmnetModel =new FeiFragmnetModel();
    }

    public void shouPrement(String url){
            feiFragmnetModel.shouModel(url, new FeiFragmnetModel.IncanCallChFei() {
                @Override
                public void getSuccess(String json) {
                    if (iview !=null){
                        iview.getDataSuccess(json);
                    }
                }

                @Override
                public void getError(String error) {
                    if (iview !=null){
                        iview.getDataError(error);
                    }
                }
            });


    }
    public void shouChildPrement(String url) {
        feiFragmnetModel.shouChildModel(url, new FeiFragmnetModel.IncanChildCallChFei() {
            @Override
            public void getChildSuccess(String json) {
                if (iview != null) {
                    iview.getSuccessOne(json);
                }
            }

            @Override
            public void getChildError(String error) {
                if (iview != null) {
                    iview.getErrorOne(error);
                }
            }
        });

    }
}
