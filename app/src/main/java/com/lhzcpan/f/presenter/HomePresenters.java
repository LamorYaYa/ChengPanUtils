package com.lhzcpan.f.presenter;

import android.content.Context;

import com.lhzcpan.f.bean.HomeBean;
import com.lhzcpan.f.interfaces.IHomeInterface;
import com.lhzcpan.f.util.NetworkState;

import java.util.ArrayList;
import java.util.List;

/**
 * @author master
 * @date 2018/1/19
 * <p>
 * Home 实现类
 */

public class HomePresenters implements IHomeInterface.Presenter {

    private IHomeInterface.View mView;
    private Context mContext;
    private List<HomeBean> list = new ArrayList<>();

    public HomePresenters(IHomeInterface.View view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void loadPosts(long date, boolean clearing) {
        mView.showLoading();
        if (NetworkState.networkConnected(mContext)) {
            for (int i = 999; i < 1009; i++) {
                HomeBean bean = new HomeBean(i, "有网络有网络有网络有网络有网络有网络", "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg");
                list.add(bean);
            }
            mView.hideLoading();
            mView.showResults(list);
        } else {
            mView.hideLoading();
            mView.showError();
        }

    }

    @Override
    public void refresh() {
        loadPosts(System.currentTimeMillis(), true);
    }

    @Override
    public void startReading(int position) {
        // TODO 处理点击事件
    }
}
