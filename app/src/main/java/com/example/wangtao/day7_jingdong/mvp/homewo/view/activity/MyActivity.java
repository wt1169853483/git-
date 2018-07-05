package com.example.wangtao.day7_jingdong.mvp.homewo.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wangtao.day7_jingdong.R;

public class MyActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private EditText textName;
    private EditText textPas;
    private Button login;
    public static int i=0;
    private String name;
    private String paw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //获取组件
        textName = findViewById(R.id.my_activity_userName);
        textPas = findViewById(R.id.my_activity_pwd);
        login = findViewById(R.id.my_activity_loginBtn);

        initListener();
    }

    private void initListener() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = textName.getText().toString();
                paw = textPas.getText().toString();
                Log.d(TAG, "initListener:+++++++++++++++++++++++++ "+name+"        "+paw);
                  if (name.equalsIgnoreCase("wangtao") && paw.equalsIgnoreCase("1234")){
                      Toast.makeText(MyActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                      i=1;
                      finish();
                  }else{
                      Toast.makeText(MyActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                       i=0;
                  }
            }
        });
    }
}
