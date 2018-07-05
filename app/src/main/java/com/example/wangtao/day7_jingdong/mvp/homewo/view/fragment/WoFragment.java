package com.example.wangtao.day7_jingdong.mvp.homewo.view.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.base.BaseActivity;
import com.example.wangtao.day7_jingdong.mvp.homewo.presenter.WoFragmentPresenter;
import com.example.wangtao.day7_jingdong.mvp.homewo.view.activity.MyActivity;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview.IShouFragment;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;


/**
 * Created by wangtao on 2018/6/11.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class WoFragment extends BaseActivity<WoFragmentPresenter> implements View.OnClickListener,IShouFragment{
    private static final String TAG = "WoFragment";
    private ImageView imageView;
    private UMShareAPI umShareAPI;
    private TextView textName;

    @Override
    protected int probildId() {
        return R.layout.fragment_wo_item;
    }

    @Override
    protected WoFragmentPresenter probildPresenter() {

        return new WoFragmentPresenter(this);
    }

    @Override
    protected void InitView(View view) {
        imageView = view.findViewById(R.id.wo_login_hold);
        textName = view.findViewById(R.id.fragment_wo_item_textName);
    }
    @Override
    protected void initViews() {
           imageView.setOnClickListener(this);
           textName.setOnClickListener(this);
    }




    @Override
    protected void InitData() {

    }
    @Override
    protected void NoitData() {

    }
    @Override
    protected void InitListener() {
           imageView.setOnClickListener(this);
    }
    //点击事件
    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.wo_login_hold:
                  umShareAPI = UMShareAPI.get(getActivity());
                  umShareAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, authListener);
                  break;
              case R.id.fragment_wo_item_textName:

                  Intent intent=new Intent(getContext(),MyActivity.class);
                  startActivity(intent);
                  break;
          }
    }

    @Override
    public void getDataSuccess(String json) {

    }

    @Override
    public void getDataError(String error) {

    }

    @Override
    public void getSuccessOne(String jsonone) {

    }

    @Override
    public void getErrorOne(String errorone) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Log.d(TAG, "onError: "+"QQ成功");

            Toast.makeText(getActivity(), "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Log.d(TAG, "onError: "+"QQ失败");
            Toast.makeText(getActivity(), "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getActivity(), "取消了", Toast.LENGTH_LONG).show();
        }
    };
}
