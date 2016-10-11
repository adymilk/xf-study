package com.lzy.headerviewpager;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lzy.headerviewpager.fragment.GridViewFragment;
import com.lzy.headerviewpager.fragment.ListViewFragment;
import com.lzy.headerviewpager.fragment.ScrollViewFragment;
import com.lzy.headerviewpager.fragment.base.HeaderViewPagerFragment;
import com.lzy.widget.HeaderViewPager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class PagerHeaderActivity extends AppCompatActivity {
    private ViewPager mViewPager;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    public List<HeaderViewPagerFragment> fragments;
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pager_header);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container1);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
//设置沉浸的颜色
 tintManager.setStatusBarTintResource(R.color.colorPrimary);

        fragments = new ArrayList<>();
        fragments.add(GridViewFragment.newInstance());
        fragments.add(ListViewFragment.newInstance());
        fragments.add(ScrollViewFragment.newInstance());


        final HeaderViewPager scrollableLayout = (HeaderViewPager) findViewById(R.id.scrollableLayout);
        final PtrFrameLayout ptr = (PtrFrameLayout) findViewById(R.id.ptr);
        ptr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return scrollableLayout.canPtr();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptr.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptr.refreshComplete();
                    }
                }, 2000);
            }
        });

        scrollableLayout.setCurrentScrollableContainer(fragments.get(0));
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                scrollableLayout.setCurrentScrollableContainer(fragments.get(position));
            }
        });
        scrollableLayout.setOnScrollListener(new HeaderViewPager.OnScrollListener() {
            @Override
            public void onScroll(int currentY, int maxY) {
//                pagerHeader.setTranslationY(currentY / 2);
            }
        });
        mViewPager.setCurrentItem(0);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "主页";
                case 1:
                    return "动态";
                case 2:
                    return "我的";
            }
            return null;
        }


        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }



}

