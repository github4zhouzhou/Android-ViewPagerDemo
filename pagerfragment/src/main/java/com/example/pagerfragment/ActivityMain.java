package com.example.pagerfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ActivityMain extends FragmentActivity {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        Fragment fragment = new HomeFragment();
        mTransaction.replace(R.id.content, fragment);
        mTransaction.commit();

        mRadioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        ((RadioButton)mRadioGroup.findViewById(R.id.radio0)).setChecked(true);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio0:
                        mTransaction = mFragmentManager.beginTransaction();
                        Fragment homeFragment = new HomeFragment();
                        mTransaction.replace(R.id.content, homeFragment);
                        mTransaction.commit();
                        break;
                    case R.id.radio1:
                        mTransaction = mFragmentManager.beginTransaction();
                        Fragment sortFragment = new SortFragment();
                        mTransaction.replace(R.id.content, sortFragment);
                        mTransaction.commit();
                        break;
                    case R.id.radio2:
                        mTransaction = mFragmentManager.beginTransaction();
                        Fragment personFragment = new PersonFragment();
                        mTransaction.replace(R.id.content, personFragment);
                        mTransaction.commit();
                        break;
                }

            }
        });
    }
}
