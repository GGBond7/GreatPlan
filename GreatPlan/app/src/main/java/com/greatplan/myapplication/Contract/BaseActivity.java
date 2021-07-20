package com.greatplan.myapplication.Contract;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Author :jack
 * @Date :2021/7/9
 * @Effect
 **/
public abstract class BaseActivity <P extends BasePresenter> extends AppCompatActivity implements IView<P> {
    public P pre;
//    private boolean hasFocus;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        iniView();
        pre=initPresenter();
        if(pre!=null){
            pre.attachView(this);
            initData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(pre!=null){
            pre.detachView();
            pre=null;
        }
    }
}
