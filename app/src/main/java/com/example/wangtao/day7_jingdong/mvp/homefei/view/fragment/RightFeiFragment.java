package com.example.wangtao.day7_jingdong.mvp.homefei.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.base.BaseActivity;
import com.example.wangtao.day7_jingdong.mvp.homefei.adapter.MyRecycleAdapter;
import com.example.wangtao.day7_jingdong.mvp.homefei.model.bean.FeiRightBean;
import com.example.wangtao.day7_jingdong.mvp.homefei.presenter.FeiFragmentPresenter;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview.IShouFragment;
import com.example.wangtao.day7_jingdong.utils.https.config.Url_Http;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wangtao on 2018/6/16.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class RightFeiFragment extends BaseActivity<FeiFragmentPresenter> implements View.OnClickListener,IShouFragment{

    private TextView textView;
    private RecyclerView recyclerView;
    private int url;
    private static final String TAG = "RightFeiFragment";
    @Override
    protected int probildId() {
        return R.layout.item_recycle_fir;
    }
    @Override
    protected FeiFragmentPresenter probildPresenter() {
        return new FeiFragmentPresenter(this);
    }
    @Override
    protected void InitView(View view) {
//        textView = view.findViewById(R.id.right_text);
        recyclerView = view.findViewById(R.id.recycle_gilde);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            }
    @Override
    protected void InitListener() {

    }


    @Override
    protected void InitData() {

    }

    @Override
    protected void initViews() {
        Bundle arguments = getArguments();
        url = arguments.getInt("urls");
        //presenter.shouPrement();
        presenter.shouPrement(Url_Http.fei_RightURl+"?cid="+url);
        Toast.makeText(getContext(), ""+url, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void NoitData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getDataSuccess(String json) {
        Gson gson=new Gson();
        FeiRightBean feiRightBean = gson.fromJson(json, FeiRightBean.class);
        List<FeiRightBean.DataBean> data = feiRightBean.getData();

        //textView.setText(data.get(1).getName());
        MyRecycleAdapter adapter = new MyRecycleAdapter(getContext(),data);
        recyclerView.setAdapter(adapter);

//        for (int i = 0; i <data.size() ; i++) {
//            List<FeiRightBean.DataBean.ListBean> list = data.get(i).getList();
//            Log.d(TAG, "getDataSuccess: ___________________"+list.size());
//            /*for (int j = 0; j <list.size() ; j++) {
//                String name = list.get(j).getName();
//                Log.d(TAG, "getDataSuccess:_______+=============== "+name);
//            }*/
//        }
    }

    @Override
    public void getDataError(String error) {

    }
}
