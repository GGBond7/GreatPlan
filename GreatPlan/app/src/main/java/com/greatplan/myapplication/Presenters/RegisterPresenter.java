package com.greatplan.myapplication.Presenters;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.greatplan.myapplication.Bean.RegisterBean;
import com.greatplan.myapplication.Contract.BasePresenter;
import com.greatplan.myapplication.Urls;
import com.greatplan.myapplication.Utils.NetUtils;
import com.greatplan.myapplication.View.activitys.LoginActivity;
import com.greatplan.myapplication.View.activitys.RegisterActivity;

import java.util.HashMap;

/**
 * @Author :jack
 * @Date :2021/7/14
 * @Effect :
 **/
public class RegisterPresenter extends BasePresenter<RegisterActivity> {
    Context context;

    public RegisterPresenter(Context context) {
        this.context = context;
    }
    //注册方法
    public void RegisterWay(String phone,String nickName,String pass){
        //判断网络
        if(NetUtils.isNetWork(context)){
            HashMap<String,Object> map=new HashMap<>();
            map.put("phone",phone);
            map.put("nickName",nickName);
            map.put("pwd",pass);
            NetUtils.getInstance().postInfo(Urls.Url_Register, map, new NetUtils.CallBackListener() {
                @Override
                public void Success(String json) {
                    RegisterBean registerBean = new Gson().fromJson(json, RegisterBean.class);
                    if(registerBean!=null){
                        if(registerBean.getStatus().equals("0000")){
                            Toast.makeText(context,registerBean.getMessage(),Toast.LENGTH_LONG).show();
                            //注册成功后跳转到登录页面
                            Intent intent=new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                        }else{
                            Toast.makeText(context,registerBean.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void Error(Throwable e) {

                }
            });
        }else{
            Toast.makeText(context,"无网",Toast.LENGTH_LONG).show();
        }
    }
}
