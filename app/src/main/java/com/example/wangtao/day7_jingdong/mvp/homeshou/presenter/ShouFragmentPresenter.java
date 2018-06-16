package com.example.wangtao.day7_jingdong.mvp.homeshou.presenter;

import com.example.wangtao.day7_jingdong.base.BasePresenter;
import com.example.wangtao.day7_jingdong.mvp.homeshou.model.ShouFragmnetModel;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview.IShouFragment;

/**
 * Created by wangtao on 2018/6/13.
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

}
