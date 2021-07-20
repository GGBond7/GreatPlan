package com.greatplan.myapplication.Presenters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.greatplan.myapplication.Bean.LoginBean;
import com.greatplan.myapplication.Contract.BasePresenter;
import com.greatplan.myapplication.View.activitys.MainActivity;
import com.greatplan.myapplication.MyApp;
import com.greatplan.myapplication.Urls;
import com.greatplan.myapplication.Utils.NetUtils;
import com.greatplan.myapplication.Utils.SpUtils;
import com.greatplan.myapplication.View.activitys.LoginActivity;

import java.util.HashMap;

/**
 * @Author :jack
 * @Date :2021/7/12
 * @Effect :
 **/
public class LoginPresenter extends BasePresenter<LoginActivity> {
    private Context context;

    public LoginPresenter(Context context) {
        this.context = context;
    }

    //登陆方法
    public void LoginWay(String phone, String pwd) {
        if (NetUtils.isNetWork(MyApp.application)) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("phone", phone);
            hashMap.put("pwd", pwd);
            NetUtils.getInstance().postInfo(Urls.Url_Login, hashMap, new NetUtils.CallBackListener() {
                @Override
                public void Success(String json) {
                    LoginBean loginBean = new Gson().fromJson(json, LoginBean.class);
                    if (loginBean != null) {
                        //判断状态  是否为登陆成功
                        if (loginBean.getStatus().equals("0000")) {
                            Toast.makeText(MyApp.application, "登陆成功", Toast.LENGTH_LONG).show();
                            //登陆成功将用户信息存储
                            SpUtils.putSessionId("SessionId", loginBean.getResult().getSessionId());
                            SpUtils.putUserId("UserId", loginBean.getResult().getUserId());
                            SpUtils.putWhetherVip("WhetherVip", loginBean.getResult().getWhetherVip());
                            //登陆成功后跳转到首页
                            Intent intent = new Intent(MyApp.application, MainActivity.class);
                            context.startActivity(intent);
                            if (!TextUtils.isEmpty(loginBean.getResult().getSessionId()) && loginBean.getResult().getUserId() != 0){
                                //设置头参
                                NetUtils.getInstance().setHeader(loginBean.getResult().getUserId(), loginBean.getResult().getSessionId());
                            }
                        } else {
                            Toast.makeText(MyApp.application, loginBean.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void Error(Throwable throwable) {

                    Toast.makeText(MyApp.application, throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(MyApp.application, "无网", Toast.LENGTH_LONG).show();
        }
    }

    //RSA加密密码
    public void RSAEncryption(String password) {
        //判断网络
        if (NetUtils.isNetWork(MyApp.application)) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("password", password);
            NetUtils.getInstance().getInfo(Urls.Url_RSA, hashMap, new NetUtils.CallBackListener() {
                @Override
                public void Success(String json) {
                    //iView.RsaPWDData(json);
                }

                @Override
                public void Error(Throwable e) {
                    Toast.makeText(MyApp.application, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(MyApp.application, "无网", Toast.LENGTH_LONG).show();
        }
    }

}
