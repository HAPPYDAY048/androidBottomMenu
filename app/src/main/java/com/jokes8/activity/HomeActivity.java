package com.jokes8.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.UnreadMsgUtils;
import com.flyco.tablayout.widget.MsgView;
import com.jokes8.entity.TabEntity;
import com.jokes8.fragment.ContentFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HAPPYDAY048 on 2018/8/27.
 */
public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.tab_bottom)
    CommonTabLayout tabBottom;
    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"首页", "消息", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        for (String title : mTitles) {
            mFragments.add(ContentFragment.getInstance(title));
        }
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        vpContent.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabBottom.setTabData(mTabEntities);
        tabBottom.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpContent.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    tabBottom.showMsg(0, 120);
                }
            }
        });

        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabBottom.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vpContent.setCurrentItem(0, false);

        //三位数
        tabBottom.showMsg(0, 100);
        tabBottom.setMsgMargin(0, -5, 5);

        //设置未读消息红点
        tabBottom.showDot(1);
        MsgView rtv_2_2 = tabBottom.getMsgView(1);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }
    }


    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
