package com.greatplan.myapplication.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.greatplan.myapplication.Bean.LoginBean;
import com.greatplan.myapplication.Contract.Api;
import com.greatplan.myapplication.Urls;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author :jack
 * @Date :2021/7/9
 * @Effect网络工具类
 **/
public class NetUtils {
    private Retrofit retrofit;
    private Api api;

    //构造方法
    private NetUtils() {
        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //OkHttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)//添加拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return chain.proceed(chain.request()
                                .newBuilder()
                                //.addHeader("ak", "0110010010000")
                                .build());
                    }
                }).connectTimeout(3, TimeUnit.SECONDS)//连接超时
                .readTimeout(3, TimeUnit.SECONDS)//读取超时
                .writeTimeout(3, TimeUnit.SECONDS)//写入超时
                .build();

        //retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BaseUrl)//最基本的Url
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        //对外提供Api
        api = retrofit.create(Api.class);
    }

    //判断网络状态
    public static boolean isNetWork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }
        return false;
    }

    //内部类实现单例
    private static class NetUtilsHolder {
        private static final NetUtils instance = new NetUtils();
    }

    public static final NetUtils getInstance() {
        return NetUtilsHolder.instance;
    }

    /**
     * 接口回调(接口请求成功或者失败)
     */
    public interface CallBackListener {
        void Success(String json);

        void Error(Throwable e);
    }

    //设置头参
    public void setHeader(final int userId, final String sessionId){

        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //OkHttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)//添加拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return chain.proceed(chain.request()
                                .newBuilder()
                                .addHeader("userId",userId+"")
                                .addHeader("sessionId",sessionId)
                                //.addHeader("ak", "0110010010000")
                                .build());
                    }
                }).connectTimeout(3, TimeUnit.SECONDS)//连接超时
                .readTimeout(3, TimeUnit.SECONDS)//读取超时
                .writeTimeout(3, TimeUnit.SECONDS)//写入超时
                .build();
    }


    //请求方法
    public void getInfo(String url, HashMap<String, Object> map, final CallBackListener callBackListener) {
        api.getInfo(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            if (callBackListener != null) {
                                callBackListener.Success(json);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //Post请求
    public void postInfo(String url, HashMap<String, Object> map, final CallBackListener callBackListener) {
        api.postInfo(url, map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            if (string != null && callBackListener != null) {
                                callBackListener.Success(string);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        callBackListener.Error(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
