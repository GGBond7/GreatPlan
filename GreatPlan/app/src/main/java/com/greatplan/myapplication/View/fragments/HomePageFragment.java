package com.greatplan.myapplication.View.fragments;


import android.content.Intent;
import android.view.View;

import android.widget.ImageView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.greatplan.myapplication.Adapters.InformationAdapter;
import com.greatplan.myapplication.Bean.BannerBean;
import com.greatplan.myapplication.Bean.InformationBean;
import com.greatplan.myapplication.Contract.BaseFragment;

import com.greatplan.myapplication.MyApp;
import com.greatplan.myapplication.Presenters.HomePresenter;
import com.greatplan.myapplication.R;
import com.greatplan.myapplication.Utils.AgentWebActivity;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomePageFragment extends BaseFragment<HomePresenter> {
    private XBanner xBanner;
    private int page=1,count=10;
    private List<String> list = new ArrayList<>();//轮播图图片
    private List<String> list2 = new ArrayList<>();//轮播图标题
    //    AgentWeb mAgentWeb;
    private XRecyclerView recyclerView;
    private List<InformationBean.ResultBean> infolist = new ArrayList<>();
    private InformationAdapter informationAdapter;
    @Override
    public void iniView() {
        View view = getView();
        if (view == null) {
            return;
        }
        xBanner = view.findViewById(R.id.xBanner);
        recyclerView = view.findViewById(R.id.ReVInfo);

    }

    @Override
    public void initData() {
        pre.BannerWay();
        pre.infoWay(page,count);


    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home_page;
    }

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter(MyApp.application);
    }

    public void getBannerData(String json) {
        BannerBean bannerBean = new Gson().fromJson(json, BannerBean.class);
        final List<BannerBean.ResultBean> result = bannerBean.getResult();
        list.clear();
        for (BannerBean.ResultBean bean : result
        ) {
            list2.add(bean.getTitle());
            list.add(bean.getImageUrl());
        }
        //设置轮播图
        if (list.size() == 0) {
            return;
        } else {
            list.size();
        }
        if (list2.size() == 0) {
            return;
        } else {
            list2.size();
        }
        xBanner.setData(list, list2);
        //加载图片
        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //Glide加载图片
                Glide.with(Objects.requireNonNull(getActivity())).load(list.get(position)).into((ImageView) view);
            }
        });
        xBanner.setPageChangeDuration(2000);//图片切换时间
        xBanner.setClipChildren(true);
        xBanner.setPageTransformer(Transformer.Default);//切换效果
        //点击广告 进入详情页
        xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Intent intent = new Intent(getContext(), AgentWebActivity.class);
                intent.putExtra("path", result.get(position).getJumpUrl());
                startActivity(intent);
            }
        });
    }

    //新闻信息
    public void getInfoData(String json) {
        InformationBean informationBean = new Gson().fromJson(json, InformationBean.class);
        infolist.addAll(informationBean.getResult());
        informationAdapter=new InformationAdapter(infolist,getActivity());
        recyclerView.setAdapter(informationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //开启下拉刷新  上拉加载
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;//下拉刷加载在第一页
                pre.infoWay(page,count);//调用网络请求
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;//页数累加
                pre.infoWay(page,count);
                recyclerView.loadMoreComplete();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //开启自动轮播
        xBanner.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止自动轮播
        xBanner.stopAutoPlay();
    }
}
