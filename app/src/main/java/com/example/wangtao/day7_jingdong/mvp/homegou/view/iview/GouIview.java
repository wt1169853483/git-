package com.example.wangtao.day7_jingdong.mvp.homegou.view.iview;

/**
 * Created by wangtao on 2018/6/27.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public interface GouIview {
    void getDataGouSuccess(String json);
    void getDataGouError(Exception error);
}
