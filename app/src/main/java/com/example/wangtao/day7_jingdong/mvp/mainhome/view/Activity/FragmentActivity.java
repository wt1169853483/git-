package com.example.wangtao.day7_jingdong.mvp.mainhome.view.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.mvp.homefei.view.fragment.FeiFragment;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.adapter.MainAdapters;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.fragment.FaFragment;
import com.example.wangtao.day7_jingdong.mvp.homegou.view.fragmnets.GouFragment;
import com.example.wangtao.day7_jingdong.mvp.homeshou.view.Fragment.ShouFragment;
import com.example.wangtao.day7_jingdong.mvp.homewo.view.fragment.WoFragment;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

public class FragmentActivity extends AppCompatActivity {
    private RadioGroup group;
    private ViewPager viewPager;
    private RadioButton btnShou;
    private RadioButton btnFei;
    private RadioButton btnFa;
    private RadioButton btnGou;
    private RadioButton btnWo;
    private List<Fragment> listFragment=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        InitView();
        InitData();
    }
    private void InitView() {
        //获取组件
        group = findViewById(R.id.main_group);
        viewPager = findViewById(R.id.main_viewPager);
        btnShou = findViewById(R.id.main_RadioShou);
        btnFei = findViewById(R.id.main_RadioFei);
        btnFa = findViewById(R.id.main_RadioFa);
        btnGou = findViewById(R.id.main_RadioGou);
        btnWo = findViewById(R.id.main_RadioWo);
    }

    private void InitData() {
        listFragment.add(new ShouFragment());
        listFragment.add(new FeiFragment());
        listFragment.add(new FaFragment());
        listFragment.add(new GouFragment());
        listFragment.add(new WoFragment());

        MainAdapters adapters=new MainAdapters(getSupportFragmentManager(),listFragment);
        viewPager.setAdapter(adapters);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        group.check(R.id.main_RadioShou);
                        break;
                    case 1:
                        group.check(R.id.main_RadioFei);
                        break;
                    case 2:
                        group.check(R.id.main_RadioFa);
                        break;
                    case 3:
                        group.check(R.id.main_RadioGou);
                        break;
                    case 4:
                        group.check(R.id.main_RadioWo);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_RadioShou:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.main_RadioFei:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.main_RadioFa:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.main_RadioGou:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.main_RadioWo:
                        viewPager.setCurrentItem(4);
                        break;
                }
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
