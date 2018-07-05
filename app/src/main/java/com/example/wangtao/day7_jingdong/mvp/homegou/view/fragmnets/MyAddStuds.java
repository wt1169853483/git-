package com.example.wangtao.day7_jingdong.mvp.homegou.view.fragmnets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangtao.day7_jingdong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangtao on 2018/6/27.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class MyAddStuds extends LinearLayout {
    private int number=1;
    @BindView(R.id.gou_item_child_addStud_jian)
    TextView gouItemChildAddStudJian;
    @BindView(R.id.gou_item_child_addStud_Nubs)
    TextView gouItemChildAddStudNubs;
    @BindView(R.id.gou_item_child_addStud_jia)
    TextView gouItemChildAddStudJia;

    public MyAddStuds(Context context) {
        super(context, null);
    }

    public MyAddStuds(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.fragment_gou_item_addstudro, this);
        ButterKnife.bind(view);
    }

    @OnClick({R.id.gou_item_child_addStud_jian, R.id.gou_item_child_addStud_jia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gou_item_child_addStud_jian:
                if (number > 0){
                    --number;
                    gouItemChildAddStudNubs.setText(number+"");
                    if (onNumberBachListener != null){
                         onNumberBachListener.getNumberSeuccess(number);
                    }
                 }
                break;
            case R.id.gou_item_child_addStud_jia:
                ++number;
                gouItemChildAddStudNubs.setText(number+"");
                if (onNumberBachListener != null){
                         onNumberBachListener.getNumberSeuccess(number);
                }
                break;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        gouItemChildAddStudNubs.setText(number+"");
    }

    //接口回调
    private OnNumberBachListener onNumberBachListener;

    public void setonNumberBachListener(OnNumberBachListener onNumberBachListener){
           this.onNumberBachListener =onNumberBachListener;
    }

    public interface OnNumberBachListener{
          void getNumberSeuccess(int num);
    }
}
