package com.example.wangtao.day7_jingdong.mvp.sousuo.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.base.BaseMainActtivity;
import com.example.wangtao.day7_jingdong.mvp.homewo.view.fragment.WoFlowlayout;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview.IShouFragment;
import com.example.wangtao.day7_jingdong.mvp.sousuo.adapters.SousuoBaseRecyclerAdapter;
import com.example.wangtao.day7_jingdong.mvp.sousuo.model.bean.SousuoBean;
import com.example.wangtao.day7_jingdong.mvp.sousuo.presenter.SousuoFragmentPresenter;
import com.example.wangtao.day7_jingdong.utils.https.config.Url_Http;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.List;

public class SouSuoActivity extends BaseMainActtivity<SousuoFragmentPresenter> implements View.OnClickListener,IShouFragment {
    private static final String TAG = "SouSuoActivity";
    private WoFlowlayout linearLayoutSouSuo;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private Button moren;
    private Button xiaoNum;
    private Button price;
    private SousuoBaseRecyclerAdapter baseRecyclerAdapter;
    private String searchText;
    private EditText resouEdit;

    @Override
    protected int probildId() {
        return R.layout.activity_sou_suo;
    }
    @Override
    protected SousuoFragmentPresenter probildPresenter() {
        return new SousuoFragmentPresenter(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void InitView() {
        linearLayoutSouSuo = findViewById(R.id.sousuo_layout_sousuo);
        linearLayout = findViewById(R.id.sou_suo_linearLayour);
        recyclerView = findViewById(R.id.sousuo_recyclerView);
        moren = findViewById(R.id.sou_suo_moren);
        xiaoNum = findViewById(R.id.sou_suo_xiaoNum);
        price = findViewById(R.id.sou_suo_price);
        resouEdit = findViewById(R.id.sousuo_editText_addresou);

    }



    @Override
    protected void InitListener() {
        moren.setOnClickListener(this);
        xiaoNum.setOnClickListener(this);
        price.setOnClickListener(this);
        resouEdit.setOnClickListener(this);

        linearLayoutSouSuo.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
       /* linearLayoutSouSuo.setVisibility(View.GONE);*/
        int childCount = linearLayoutSouSuo.getChildCount();
        if (childCount >0){
            for (int i = 0; i <childCount; i++) {
                final int finalI = i;
                final TextView childView= (TextView) linearLayoutSouSuo.getChildAt(i);
                childView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchText = childView.getText().toString();
                        // presenter.shouPrement(Url_Http.fei_sousuo+"?pscid="+finalI);
                        presenter.sousuoPrement(searchText,1+"",0+"");
                        linearLayoutSouSuo.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                });
            }
        }
    }


    @Override
    public void onClick(View v) {
              switch (v.getId()){
                  case R.id.sou_suo_moren:
                      presenter.sousuoPrement(searchText,1+"",0+"");
                      baseRecyclerAdapter.notifyDataSetChanged();
                      break;
                  case R.id.sou_suo_xiaoNum:
                      presenter.sousuoPrement(searchText,1+"",1+"");
                      baseRecyclerAdapter.notifyDataSetChanged();
                      break;
                  case R.id.sou_suo_price:
                      presenter.sousuoPrement(searchText,1+"",2+"");
                      baseRecyclerAdapter.notifyDataSetChanged();
                      break;
                  case R.id.sousuo_editText_addresou:
                      linearLayoutSouSuo.setVisibility(View.VISIBLE);
                      linearLayout.setVisibility(View.GONE);
                      break;
              }
    }

    @Override
    public void getDataSuccess(String json) {
       // Log.d(TAG, "getDataSuccess: ++++++++++++++++++++++++++++"+json);
        Gson gson=new Gson();
        SousuoBean sousuoBean = gson.fromJson(json, SousuoBean.class);
        List<SousuoBean.DataBean> data = sousuoBean.getData();
       // Log.d(TAG, "getDataSuccess: +++++++++++++++++++++data:"+data.size());
        baseRecyclerAdapter = new SousuoBaseRecyclerAdapter(data);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(baseRecyclerAdapter);

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
}
