package com.lhzcpan.f.interfaces;

import com.lhzcpan.f.bean.HomeBean;

import java.util.List;

/**
 * @author master
 * @date 2018/1/19
 * <p>
 * MVP -- 接口定义
 */

public interface ISwipeInterface {

    interface View {
        void initViews(android.view.View view);
        void showLoading();
        void hideLoading();
        void showResults(List<HomeBean> list);
        void showError();
    }

    interface Presenter {
        void start();
        void loadPosts(long date, boolean clearing);
        void refresh();
        void loadMore(long date);
        void startReading(int position);
    }

}
