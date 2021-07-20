package com.greatplan.myapplication.Contract;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
/**
 * @Author :jack
 * @Date :2021/7/9
 * @Effect
 **/
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView<P> {
    public P pre;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), getLayout(), null);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iniView();
        pre = initPresenter();
        if (pre != null) {
            pre.attachView(this);
            initData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (pre != null) {
            pre.detachView();
            pre = null;
        }
    }
}
