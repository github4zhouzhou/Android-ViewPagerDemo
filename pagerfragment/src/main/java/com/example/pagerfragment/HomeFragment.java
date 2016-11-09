package com.example.pagerfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragmentsList;
    private ImageView mIvBottomLine;
	private ArrayList<TextView> mTabList;

    private int mCurrentIndex = 0;
    private int mBottomLineWidth;
    private int offset = 0;
    private int position_one;
	private int mScreenWidth;
    public final static int sTabNum = 2 ;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_home, null);

		initWidth(view);
		initTabs(view);
        initViewPager(view);

		// 给下划线添加一个初始动画
        TranslateAnimation animation = new TranslateAnimation(position_one, offset, 0, 0);
        animation.setFillAfter(true);
        animation.setDuration(300);
		mIvBottomLine.startAnimation(animation);

		return view;
	}

	private void initTabs(View parentView) {
		TextView tvTabNew = (TextView) parentView.findViewById(R.id.tv_tab_1);
		TextView tvTabHot = (TextView) parentView.findViewById(R.id.tv_tab_2);

		mTabList = new ArrayList<>();
		mTabList.add(tvTabNew);
		mTabList.add(tvTabHot);

		for (int i = 0; i < mTabList.size(); ++i) {
			final int currentIndex = i;
			mTabList.get(i).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mViewPager.setCurrentItem(currentIndex);
				}
			});
		}
	}

	private void initViewPager(View parentView) {
		mViewPager = (ViewPager) parentView.findViewById(R.id.vPager);
		mFragmentsList = new ArrayList<>();

		Fragment home1 = new HomeFragment_1();
		Fragment home2 = new HomeFragment_2();

		mFragmentsList.add(home1);
		mFragmentsList.add(home2);

		mViewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), mFragmentsList));
		mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				// 先全部设置成没有选中的背景色
				for (TextView tv : mTabList) {
					tv.setTextColor(getResources().getColor(R.color.white));
				}
				// 选中 tab 的背景色
				mTabList.get(arg0).setTextColor(getResources().getColor(R.color.white));

				mCurrentIndex = arg0;

				// 这里添加了一个点击时的动画，由于和 onPageScrolled 冲突就注释掉了
//				Animation animation;
//				if (mCurrentIndex == 1) {
//					animation = new TranslateAnimation(position_one, offset, 0, 0);
//				} else {
//					animation = new TranslateAnimation(offset, position_one, 0, 0);
//				}
//				animation.setFillAfter(true);
//				animation.setDuration(300);
//				mIvBottomLine.startAnimation(animation);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			/**
			 * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
			 * offsetPixels:当前页面偏移的像素位置
			 */
			@Override
			public void onPageScrolled(int position, float offset, int offsetPixels) {

				LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mIvBottomLine
						.getLayoutParams();

				//Log.e("offset:", offset + "");
				//Log.e("position:", position + "");
				//Log.e("current pos:", mCurrentIndex + "");
				/**
				 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
				 * 设置mTabLineIv的左边距 滑动场景：
				 * 记3个页面,
				 * 从左到右分别为0,1,2
				 * 0->1; 1->2; 2->1; 1->0
				 */

				int direction = (position - mCurrentIndex); // 0 代表 0->1； -1 代表 1->0
				double dbTabWidth = (mScreenWidth * 1.0 / sTabNum);
				lp.leftMargin = (int) ((direction + offset + mCurrentIndex) * dbTabWidth);

//				if (mCurrentIndex == 0 && position == 0) { // 0->1
//					lp.leftMargin = (int) (offset * (mScreenWidth * 1.0 / sTabNum)
//							+ mCurrentIndex * (mScreenWidth / sTabNum));
//				}
//				else if (mCurrentIndex == 1 && position == 0) { // 1->0
//					lp.leftMargin = (int) (-(1 - offset) * (mScreenWidth * 1.0 / sTabNum)
//							+ mCurrentIndex * (mScreenWidth / sTabNum));
//				}

				mIvBottomLine.setLayoutParams(lp);
			}
		});

		mViewPager.setCurrentItem(0);

	}

	private void initWidth(View parentView) {

		mIvBottomLine = (ImageView) parentView.findViewById(R.id.iv_bottom_line);
		initTabLineWidth();
		mBottomLineWidth = mIvBottomLine.getLayoutParams().width;
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = ((screenW / sTabNum - mBottomLineWidth) / 2);
		int avg = (screenW / sTabNum);
		position_one = avg + offset;
	}

	/**
	 * 设置滑动条的宽度为屏幕的1/tabCount(根据Tab的个数而定)
	 */
	private void initTabLineWidth() {
		DisplayMetrics dpMetrics = new DisplayMetrics();
		getActivity().getWindow().getWindowManager()
				.getDefaultDisplay()
				.getMetrics(dpMetrics);
		mScreenWidth = dpMetrics.widthPixels;

		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mIvBottomLine
				.getLayoutParams();
		lp.width = mScreenWidth / sTabNum;
		mIvBottomLine.setLayoutParams(lp);
	}

}
