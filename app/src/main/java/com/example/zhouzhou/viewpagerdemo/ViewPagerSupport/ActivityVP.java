package com.example.zhouzhou.viewpagerdemo.ViewPagerSupport;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhouzhou.viewpagerdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityVP extends Activity {

    private List<View> mViewList;// view数组
    ViewPagerT mViewPager; // 对应的viewPager
    private List<String> mTitleList;  //标题列表数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);


        mViewPager = (ViewPagerT) findViewById(R.id.viewpager);
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

        PagerAdapterT pagerAdapter = new PagerAdapterT() {

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
        };

        mViewPager.setAdapter(pagerAdapter);
    }
}
