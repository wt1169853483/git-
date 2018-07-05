package com.example.wangtao.day7_jingdong.app;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.wangtao.day7_jingdong.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.setLogEnabled(true);
        getMyAppImageLoder();
        UMConfigure.init(this,"5b2201d78f4a9d7ecf000088"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0

        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");


        Fresco.initialize(this);
    }

    public void getMyAppImageLoder() {
        ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }

    public static DisplayImageOptions getOption(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher_background) // resource or drawable
                .showImageForEmptyUri(R.drawable.ic_launcher_background) // resource or drawable
                .showImageOnFail(R.drawable.ic_launcher_background) // resource or drawable
                .resetViewBeforeLoading(true)  // default
                .delayBeforeLoading(1000)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.RGB_565) // default
                .displayer(new SimpleBitmapDisplayer()) // default
                .handler(new Handler()) // default
                .build();
        return options;


    }

}
