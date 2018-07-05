package com.example.wangtao.day7_jingdong.mvp.homegou.presenter;

import com.example.wangtao.day7_jingdong.base.BasePresenter;
import com.example.wangtao.day7_jingdong.mvp.homegou.model.GouFragmnetModel;
import com.example.wangtao.day7_jingdong.mvp.homewo.model.WoFragmnetModel;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview.IShouFragment;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class GouFragmentPresenter extends BasePresenter<IShouFragment>{
    private static final String TAG = "ShouFragmentPresenter";
    private GouFragmnetModel gouFragmnetModel;
    public GouFragmentPresenter(IShouFragment iview) {
        super(iview);
    }

    @Override
    protected void InitModel() {
          gouFragmnetModel =new GouFragmnetModel();
    }

    public void GouPrement(String url) {
        gouFragmnetModel.GouModel(url, new GouFragmnetModel.IncanCallCh() {
            @Override
            public void getDataSuccess(String data) {
                if (iview != null) {
                    iview.getDataSuccess(data);
                }
            }

            @Override
            public void getDataError(String error) {
                if (iview != null) {
                    iview.getDataSuccess(error);
                }
            }
        });


    }
}
