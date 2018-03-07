package com.lhzcpan.f.presenter;

import android.content.Context;

import com.lhzcpan.f.bean.HomeBean;
import com.lhzcpan.f.interfaces.IHomeInterface;
import com.lhzcpan.f.util.Constant;
import com.lhzcpan.f.util.DateFormatter;
import com.lhzcpan.f.util.NetworkState;
import com.lhzcpan.f.util.NewsUtil;
import com.lhzcpan.http.HttpUtils;
import com.lhzcpan.http.interfaces.OnHttpListener;

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

    private DateFormatter formatter = new DateFormatter();

    public HomePresenters(IHomeInterface.View view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void start() {
        loadPosts(System.currentTimeMillis(), true);
    }

    @Override
    public void loadPosts(long date, final boolean clearing) {
        if (clearing) {
            // 下拉加载时不需要上面的 refresh
            mView.showLoading();
        }
        if (NetworkState.networkConnected(mContext)) {
            HttpUtils.executeGet(mContext, Constant.ZHIHU_HISTORY + formatter.ZhihuDailyDateFormat(date), new OnHttpListener() {
                @Override
                public void onFinish(String response) {
                    if (clearing) {
                        list = NewsUtil.getZhiHuList(response);
                    } else {
                        list.addAll(NewsUtil.getZhiHuList(response));
                    }
                    mView.hideLoading();
                    mView.showResults(list);
                }

                @Override
                public void onError(Exception e) {
                    mView.hideLoading();
                    mView.showError();
                }
            });
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
    public void loadMore(long date) {
        loadPosts(date, false);
    }

    @Override
    public void startReading(int position) {
        // TODO 处理点击事件
    }
}
