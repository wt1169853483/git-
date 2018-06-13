package com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview;

import com.example.wangtao.day7_jingdong.base.iview;
import com.example.wangtao.day7_jingdong.mvp.mainhome.model.bean.ShouUserBean;

import java.util.List;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public interface IShouFragment extends iview {
      void getDataSuccess(List<ShouUserBean.DataBean> data);
      void getDataError(String error);
}
