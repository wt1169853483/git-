package com.example.wangtao.day7_jingdong.mvp.sousuo.presenter;

import com.example.wangtao.day7_jingdong.base.BasePresenter;
import com.example.wangtao.day7_jingdong.mvp.homewo.model.WoFragmnetModel;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview.IShouFragment;
import com.example.wangtao.day7_jingdong.mvp.sousuo.model.SousuoFragmnetModel;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class SousuoFragmentPresenter extends BasePresenter<IShouFragment>{
    private static final String TAG = "ShouFragmentPresenter";
    private SousuoFragmnetModel sousuoFragmnetModel;
    public SousuoFragmentPresenter(IShouFragment iview) {
        super(iview);
    }

    @Override
    protected void InitModel() {
          sousuoFragmnetModel =new SousuoFragmnetModel();
    }

    public void sousuoPrement(String keywords,String page,String sort) {
        sousuoFragmnetModel.SousuoModel(keywords,page,sort,new SousuoFragmnetModel.IncanCallCh() {
            @Override
            public void getDataSuccess(String data) {
                if (iview != null) {
                    iview.getDataSuccess(data);
                }
            }

            @Override
            public void getDataError(String error) {
                if (iview != null) {
                    iview.getDataError(error);
                }
            }
        });
    }
}
