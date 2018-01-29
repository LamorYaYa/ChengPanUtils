package com.lhzcpan.f.fragment.util;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lhzcpan.R;
import com.lhzcpan.f.fragment.HomeFragment;

/**
 * @author master
 * @date 2018/1/19
 * 切换Fragment的实现方法
 */

public class SwitchFragment {

    public enum FRAGMENT_TYPE {
        HOME, DIAMOND, FOCUS, SETTING;
    }


    private static HomeFragment mHomeFragment;
    private static HomeFragment mDiamondFragment;
    private static HomeFragment mFocusFragment;
    private static HomeFragment mSettingFragment;

    private FragmentManager manager;

    public SwitchFragment(FragmentManager manager) {
        this.manager = manager;
        FragmentTransaction transaction = manager.beginTransaction();
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            transaction.add(R.id.content, mHomeFragment);
        }
        hiderFragment(transaction);
        transaction.show(mHomeFragment);
        transaction.commit();
    }


    private void hiderFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mDiamondFragment != null) {
            transaction.hide(mDiamondFragment);
        }
        if (mFocusFragment != null) {
            transaction.hide(mFocusFragment);
        }
        if (mSettingFragment != null) {
            transaction.hide(mSettingFragment);
        }
    }

    public void chooseFragment(FRAGMENT_TYPE type) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (type) {
            case HOME:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.content, mHomeFragment);
                }
                hiderFragment(transaction);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.show(mHomeFragment);
                transaction.commit();
                break;
            case DIAMOND:
                if (mDiamondFragment == null) {
                    mDiamondFragment = new HomeFragment();
                    transaction.add(R.id.content, mDiamondFragment);
                }
                hiderFragment(transaction);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.show(mDiamondFragment);
                transaction.commit();
                break;
            case FOCUS:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.content, mHomeFragment);
                }
                hiderFragment(transaction);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.show(mHomeFragment);
                transaction.commit();
                break;
            case SETTING:
                if (mSettingFragment == null) {
                    mSettingFragment = new HomeFragment();
                    transaction.add(R.id.content, mSettingFragment);
                }
                hiderFragment(transaction);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.show(mSettingFragment);
                transaction.commit();
                break;
        }
    }
}
