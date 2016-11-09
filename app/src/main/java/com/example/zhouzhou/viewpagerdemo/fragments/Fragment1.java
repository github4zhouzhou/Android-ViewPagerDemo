package com.example.zhouzhou.viewpagerdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhouzhou.viewpagerdemo.R;

/**
 * Created by zhouzhou on 2016/11/9.
 */

public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pager_layout1, container, false);
    }
}
