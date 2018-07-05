package com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview;

import com.example.wangtao.day7_jingdong.base.iview;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public interface IShouFragment extends iview {
      void getDataSuccess(String json);
      void getDataError(String error);

      void getSuccessOne(String jsonone);
      void getErrorOne(String errorone);
}
