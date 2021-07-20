package com.greatplan.myapplication.Contract;

/**
 * @Author :jack
 * @Date :2021/7/9
 * @Effect :
 **/
public interface IView<P>{
    void iniView();

    void initData();

    int getLayout();

    P initPresenter();
}
