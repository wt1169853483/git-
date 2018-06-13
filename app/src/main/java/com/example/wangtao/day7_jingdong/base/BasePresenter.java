package com.example.wangtao.day7_jingdong.base;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public abstract class BasePresenter <V extends iview>{
          protected  V iview;

    public BasePresenter(V iview) {
        this.iview = iview;
        InitModel();
    }

    protected abstract void InitModel();

    void OnDestroy(){
          iview =null;
    }

}
