package com.greatplan.myapplication.Contract;

/**
 * @Author :jack
 * @Date :2021/7/9
 * @Effect
 **/
public class BasePresenter <V extends IView>{
    public V iView;
    //绑定
    public void attachView(V iView){
        this.iView=iView;
    }
    //解除绑定
    public void detachView(){
        this.iView=null;
    }
}
