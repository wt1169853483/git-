package com.example.wangtao.day7_jingdong.mvp.homewo.presenter;

import android.util.Log;

import com.example.wangtao.day7_jingdong.base.BasePresenter;
import com.example.wangtao.day7_jingdong.mvp.homeshou.model.ShouFragmnetModel;
import com.example.wangtao.day7_jingdong.mvp.homewo.model.WoFragmnetModel;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview.IShouFragment;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class WoFragmentPresenter extends BasePresenter<IShouFragment>{
    private static final String TAG = "ShouFragmentPresenter";
    private WoFragmnetModel woFragmnetModel;
    public WoFragmentPresenter(IShouFragment iview) {
        super(iview);
    }

    @Override
    protected void InitModel() {
          woFragmnetModel =new WoFragmnetModel();
    }

    public void shouPrement(String url) {
        woFragmnetModel.WoModel(url, new WoFragmnetModel.IncanCallCh() {
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
