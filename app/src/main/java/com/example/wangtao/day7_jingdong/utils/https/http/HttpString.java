package com.example.wangtao.day7_jingdong.utils.https.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wangtao on 2018/6/12.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class HttpString {
    public static String IntoString(InputStream inputStream){
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer=new StringBuffer();
        String content=null;
        try{
            while ((content=bufferedReader.readLine())!=null){
                stringBuffer.append(content);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
