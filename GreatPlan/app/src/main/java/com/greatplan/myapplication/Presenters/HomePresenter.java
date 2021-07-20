package com.greatplan.myapplication.Presenters;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.greatplan.myapplication.Contract.BasePresenter;
import com.greatplan.myapplication.Urls;
import com.greatplan.myapplication.Utils.NetUtils;
import com.greatplan.myapplication.Utils.SpUtils;
import com.greatplan.myapplication.View.fragments.HomePageFragment;

import java.util.HashMap;

import retrofit2.http.Url;

/**
 * @Author :jack
 * @Date :2021/7/15
 * @Effect :
 **/
public class HomePresenter extends BasePresenter<HomePageFragment> {
    private Context context;

    public HomePresenter(Context context) {
        this.context = context;
    }
    //轮播图
    public void BannerWay(){
        if(NetUtils.isNetWork(context)){
            HashMap<String,Object> map=new HashMap<>();
            NetUtils.getInstance().getInfo(Urls.Url_Banner, map, new NetUtils.CallBackListener() {
                @Override
                public void Success(String json) {
                    iView.getBannerData(json);
                }

                @Override
                public void Error(Throwable e) {

                }
            });
        }else{
            Toast.makeText(context,"无网",Toast.LENGTH_LONG).show();
        }
    }
    //咨询
    public void infoWay(int page,int count){
        if(NetUtils.isNetWork(context)){
            final HashMap<String,Object> map=new HashMap<>();
            int userId = SpUtils.getUserId("UserId");
            String sessionId = SpUtils.getSessionId("SessionId");
            if(TextUtils.isEmpty(sessionId)){
               return;
            }
            if(userId==-1){
                return;
            }
//            map.put("plateId",1);
            map.put("page",page);
            map.put("count",count);
            NetUtils.getInstance().getInfo(Urls.Url_Info, map, new NetUtils.CallBackListener() {
                @Override
                public void Success(String json) {
                    iView.getInfoData(json);
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
