package com.example.zhouzhou.viewpagerdemo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhouzhou.viewpagerdemo.fragments.Fragment1;
import com.example.zhouzhou.viewpagerdemo.fragments.Fragment2;
import com.example.zhouzhou.viewpagerdemo.fragments.Fragment3;

import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends FragmentActivity {

    private List<View> mViewList;// view数组
    ViewPager mViewPager; // 对应的viewPager
    private List<String> mTitleList;  //标题列表数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //构造适配器
        List<Fragment> fragments=new ArrayList<Fragment>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);

        mTitleList = new ArrayList<String>();// 每个页面的Title数据
        mTitleList.add("111");
        mTitleList.add("222");
        mTitleList.add("333");

        //设定适配器
        ViewPager vp = (ViewPager)findViewById(R.id.viewpager);
        vp.setAdapter(adapter);
    }

    private void pagerNormal() {
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.pager_layout1, null);
        View view2 = inflater.inflate(R.layout.pager_layout2, null);
        View view3 = inflater.inflate(R.layout.pager_layout3, null);

        mViewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);

        mTitleList = new ArrayList<String>();// 每个页面的Title数据
        mTitleList.add("111");
        mTitleList.add("222");
        mTitleList.add("333");

        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                //根据传来的key，找到view,判断与传来的参数View arg0是不是同一个视图
                return arg0 == mViewList.get((int)Integer.parseInt(arg1.toString()));
            }

            @Override
            public int getCount() {
                return mViewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(mViewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mViewList.get(position));
                //把当前新增视图的位置（position）作为Key传过去
                return position;
            }

            // Pager Tab Strip
            @Override
            public CharSequence getPageTitle(int position) {

                SpannableStringBuilder ssb = new SpannableStringBuilder("  "+mTitleList.get(position)); // space added before text
                // for
                Drawable myDrawable = getResources().getDrawable(
                        R.drawable.ic_launcher);
                myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(),
                        myDrawable.getIntrinsicHeight());
                ImageSpan span = new ImageSpan(myDrawable,
                        ImageSpan.ALIGN_BASELINE);

                ForegroundColorSpan fcs = new ForegroundColorSpan(Color.GREEN);// 字体颜色设置为绿色
                ssb.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);// 设置图标
                ssb.setSpan(fcs, 1, ssb.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);// 设置字体颜色
                ssb.setSpan(new RelativeSizeSpan(1.2f), 1, ssb.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                return ssb;
            }
        };

        mViewPager.setAdapter(pagerAdapter);
    }

    private void pagerFragment() {
        setContentView(R.layout.activity_fragment);

        //构造适配器
        List<Fragment> fragments=new ArrayList<Fragment>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);

        mTitleList = new ArrayList<String>();// 每个页面的Title数据
        mTitleList.add("111");
        mTitleList.add("222");
        mTitleList.add("333");

        //设定适配器
        ViewPager vp = (ViewPager)findViewById(R.id.viewpager);
        vp.setAdapter(adapter);
    }

    public class FragAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int arg0) {
            return mFragments.get(arg0);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }
}
