package com.greatplan.myapplication.Contract;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @Author :jack
 * @Date :2021/7/9
 * @Effect 请求方法
 **/
public interface Api {
    //Get请求
    @GET
    Observable<ResponseBody> getInfo(@Url String path, @QueryMap HashMap<String,Object> map);
    //Post请求
    @POST
    Observable<ResponseBody> postInfo(@Url String path,@QueryMap HashMap<String,Object> map);
}
