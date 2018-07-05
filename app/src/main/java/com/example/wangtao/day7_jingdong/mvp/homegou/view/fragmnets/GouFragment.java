package com.example.wangtao.day7_jingdong.mvp.homegou.view.fragmnets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.base.BaseActivity;
import com.example.wangtao.day7_jingdong.mvp.homegou.adapters.gouExpandAdapter;
import com.example.wangtao.day7_jingdong.mvp.homegou.model.bean.GouUtilBean;
import com.example.wangtao.day7_jingdong.mvp.homegou.presenter.GouFragmentPresenter;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview.IShouFragment;
import com.example.wangtao.day7_jingdong.utils.https.config.Url_Http;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by wangtao on 2018/6/11.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class GouFragment extends BaseActivity<GouFragmentPresenter> implements View.OnClickListener, IShouFragment {

    @BindView(R.id.gou_item_expandableListView)
    ExpandableListView gouItemExpandableListView;
    @BindView(R.id.gou_item_checkedBox)
    CheckBox gouItemCheckedBox;
    @BindView(R.id.quanxian)
    TextView quanxian;
    @BindView(R.id.gou_item_sumPrice)
    TextView Sumprice;
    @BindView(R.id.gou_item_btnjiesuan)
    Button buttonJieSuan;
    Unbinder unbinder;
    private ExpandableListView expandableListView;
    private gouExpandAdapter expandAdapter;

    @Override
    protected int probildId() {
        return R.layout.fragment_gou_item;
    }

    @Override
    protected GouFragmentPresenter probildPresenter() {
        return new GouFragmentPresenter(this);
    }

    @Override
    protected void InitView(View view) {
        expandableListView = view.findViewById(R.id.gou_item_expandableListView);

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void NoitData() {

    }

    @Override
    protected void InitData() {
        presenter.GouPrement(Url_Http.gou_Url + "?uid=" + 71);
    }

    @Override
    protected void InitListener() {

    }

    //点击事件
    @Override
    public void onClick(View v) {

    }

    @Override
    public void getDataSuccess(String json) {
        Gson gson = new Gson();
        GouUtilBean gouUtilBean = gson.fromJson(json, GouUtilBean.class);
        List<GouUtilBean.DataBean> data = gouUtilBean.getData();

        expandAdapter = new gouExpandAdapter(data, getActivity());
        expandableListView.setAdapter(expandAdapter);
        refreshSelectedAndTotalPriceAndTotalNumber();
        expandAdapter.setOnShopingListener(new gouExpandAdapter.OnShopingListener() {
            @Override
            public void getListGroupParamChanged(int groupPoition) {
                boolean onParamGourpSelledSelected = expandAdapter.isOnParamGourpSelledSelected(groupPoition);
                expandAdapter.ChangedParamGrourpSelledSelected(groupPoition, !onParamGourpSelledSelected);
                expandAdapter.notifyDataSetChanged();
                refreshSelectedAndTotalPriceAndTotalNumber();
            }

            @Override
            public void getListchildParamChanged(int groupPoition, int childPoition) {
                expandAdapter.ChangedParamChildSelected(groupPoition, childPoition);
                expandAdapter.notifyDataSetChanged();
                refreshSelectedAndTotalPriceAndTotalNumber();
            }

            @Override
            public void getListchildNumberChanged(int groupPoition, int childPoition, int number) {
                expandAdapter.changedParamChildNumber(groupPoition,childPoition,number);
                expandAdapter.notifyDataSetChanged();
               refreshSelectedAndTotalPriceAndTotalNumber();
            }
        });


        //展开二级列表
        for (int i = 0; i < data.size(); i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setGroupIndicator(null);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
   public void refreshSelectedAndTotalPriceAndTotalNumber(){
          //设置全选
       Boolean  isAllProductsSelected=expandAdapter.isAllProductsSelected();
       gouItemCheckedBox.setChecked(isAllProductsSelected);
       //设置数量
        double v=expandAdapter.changedAllPrices();
        Sumprice.setText("总价:￥"+v);

        //设置数量
       int i = expandAdapter.changedAllNumber();
       buttonJieSuan.setText("结算("+i+")");
   }
    @OnClick(R.id.gou_item_checkedBox)
    public void onViewClicked() {
           //设置 底部全选按钮
        //   时候所有得商品都被选中
       Boolean  isAllProductsSelected=expandAdapter.isAllProductsSelected();
        expandAdapter.changeAllProductsStatus(!isAllProductsSelected);
       expandAdapter.notifyDataSetChanged();
       refreshSelectedAndTotalPriceAndTotalNumber();
    }
}
