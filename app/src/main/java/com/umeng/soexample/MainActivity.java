package com.umeng.soexample;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.soexample.share_auth.AuthActivity;
import com.umeng.soexample.share_auth.UserinfoActivity;

import java.util.Map;


/**
 * Created by umeng on 15/9/14.
 */
public class MainActivity extends Activity implements UMShareListener,UMAuthListener {

    private Button shareButton, authbutton, getinfoButton;


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.umeng_share) {
//                Intent intent = new Intent(MainActivity.this, ShareMenuActivity.class);
//                startActivity(intent);

                //分享
                new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.QQ)
                        .withTitle("爱一起分享")
                        .withText("这是爱一起的分享")
                        .withMedia(new UMImage(MainActivity.this,"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png"))
                        .withTargetUrl("http://www.baidu.com")//用户点击跳转的连接
                        .setCallback(MainActivity.this)
                        .share();

                //登录

//                UMShareAPI shareAPI = UMShareAPI.get(MainActivity.this);
//                shareAPI.doOauthVerify(MainActivity.this,SHARE_MEDIA.QQ,MainActivity.this);
                //登录成功之后我们需要的信息是：uid：用户id ，sex 0，1  name，arUrl：头像图片的url


            } else if (view.getId() == R.id.umeng_auth) {
                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                startActivity(intent);
            } else if (view.getId() == R.id.umeng_getinfo) {
                Intent intent = new Intent(MainActivity.this, UserinfoActivity.class);
                startActivity(intent);
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);
        Config.dialogSwitch = true;
//          如想让你的app在android 6.0系统上也能运行的话，需要动态获取权限，没有权限的话分享sdk会出错，参考一下代码做动态获取权限,适配安卓6.0系统
//          你需要最新的android.support.v4包，或者v13的包可也以
//            if(Build.VERSION.SDK_INT>=23){
//                String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
//                ActivityCompat.requestPermissions(this,mPermissionList,REQUEST_PERM);
//            }
        authbutton = (Button) findViewById(R.id.umeng_auth);
        shareButton = (Button) findViewById(R.id.umeng_share);
        getinfoButton = (Button) findViewById(R.id.umeng_getinfo);
        authbutton.setOnClickListener(clickListener);
        shareButton.setOnClickListener(clickListener);
        getinfoButton.setOnClickListener(clickListener);
        //

    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {//分享成功
        Toast.makeText(this,"分享成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {//分享失败
        Toast.makeText(this,"分享失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {//取消分享
        Toast.makeText(this,"取消分享",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {//授权登录成功
        Toast.makeText(this,"授权登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {//授权失败
        Toast.makeText(this,"授权失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {//取消授权
        Toast.makeText(this,"取消授权",Toast.LENGTH_SHORT).show();
    }

    /**
     * 重新回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}


