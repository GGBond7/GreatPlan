package com.greatplan.myapplication.View.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.greatplan.myapplication.Contract.BaseActivity;
import com.greatplan.myapplication.R;
import com.greatplan.myapplication.View.fragments.DynamicPageFragment;
import com.greatplan.myapplication.View.fragments.HomePageFragment;
import com.greatplan.myapplication.View.fragments.InfomationPageFragment;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseActivity {
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    List<Fragment> list = new ArrayList<>();//创建Fragment集合

    @Override
    public void iniView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.ViewPager);
        QBadge();
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public Object initPresenter() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initPage();
    }

    //初始化页面(bottomNavigationView+viewpager+fragment)
    public void initPage() {
        //实例化每个fragment页面
        HomePageFragment homePageFragment = new HomePageFragment();
        InfomationPageFragment infomationPageFragment = new InfomationPageFragment();
        DynamicPageFragment dynamicPageFragment = new DynamicPageFragment();
        //添加进去fragment集合
        list.add(homePageFragment);
        list.add(infomationPageFragment);
        list.add(dynamicPageFragment);
        //底部标题栏切换
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        viewPager.setCurrentItem(0, false);
                        return true;
                    case R.id.page_2:
                        viewPager.setCurrentItem(1, false);
                        return true;
                    case R.id.page_3:
                        viewPager.setCurrentItem(2, false);
                        return true;
                }
                return false;
            }
        });
        //默认选中
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        //ViewPager
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //选中对应下标的页面
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //ViewPager绑定Fragment
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    //角标
    public void QBadge() {
        //角标
        QBadgeView qBadgeView = new QBadgeView(MainActivity.this);

        BottomNavigationMenuView itemView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        //获取导航栏Tab数量
        int childCount = itemView.getChildCount();

        qBadgeView.bindTarget(itemView.getChildAt(0))
                .setBadgeNumber(100)//数量
                .setBadgeTextColor(Color.WHITE)//字体颜色
                .setBadgeGravity(Gravity.TOP | Gravity.END)//角标位置(右上)
                .setExactMode(false)//显示具体数字还是啥99+
                .setGravityOffset(30, 0, true)//角标的位置(距离字体的距离，X轴越大距离字体越近)
                .setShowShadow(true)//是否有阴影效果
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {//设置可拖拽
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {

                    }
                });

//        //第二个标题
//        qBadgeView.bindTarget(itemView.getChildAt(1))
//                .setExactMode(false)//显示具体的数字还是99+
//                .setBadgeNumber(100)
//                .setBadgeGravity(Gravity.TOP | Gravity.START)//角标位置(左上)
//        ;
//        qBadgeView.bindTarget(itemView.getChildAt(2))
//                .setExactMode(true)//显示具体的数字还是99+
//                .setBadgeNumber(1000)
//                .setBadgeGravity(Gravity.BOTTOM | Gravity.START)//角标位置(左上)
//        ;
    }
}
