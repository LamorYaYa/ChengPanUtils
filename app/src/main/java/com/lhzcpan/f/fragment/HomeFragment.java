package com.lhzcpan.f.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lhzcpan.LogUtils;
import com.lhzcpan.R;
import com.lhzcpan.f.adapter.HomeAdapter;
import com.lhzcpan.f.bean.HomeBean;
import com.lhzcpan.f.interfaces.IHomeInterface;
import com.lhzcpan.f.interfaces.OnRecyclerViewOnClickListener;
import com.lhzcpan.f.presenter.HomePresenters;

import java.util.Calendar;
import java.util.List;

/**
 * @author master
 * @date 2018/1/19
 */

public class HomeFragment extends Fragment implements IHomeInterface.View {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private HomeAdapter mHomeAdapter;
    private IHomeInterface.Presenter presenter;


    private int mYear = Calendar.getInstance().get(Calendar.YEAR);
    private int mMonth = Calendar.getInstance().get(Calendar.MONTH);
    private int mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_list, container, false);

        initViews(view);

        presenter = new HomePresenters(this, getContext());
        presenter.start();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });

        return view;
    }

    @Override
    public void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 获取最后一个完全显示的 item position
                    int lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = linearLayoutManager.getItemCount();

                    // 判断是否滚动到底部并且向下滑动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        Calendar c = Calendar.getInstance();
                        c.set(mYear, mMonth, --mDay);
                        LogUtils.e(String.format("%s年%s月%s日", mYear, mMonth, mDay));
                        presenter.loadMore(c.getTimeInMillis());
                    }
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isSlidingToLast = dy > 0;
            }
        });
    }

    @Override
    public void showLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void showResults(List<HomeBean> list) {
        if (mHomeAdapter == null) {
            mHomeAdapter = new HomeAdapter(getContext(), list);
            recyclerView.setAdapter(mHomeAdapter);
            mHomeAdapter.setItemClickListenter(new OnRecyclerViewOnClickListener() {
                @Override
                public void OnItemClick(View v, int position) {
                    Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mHomeAdapter.setData(list);
        }
    }

    @Override
    public void showError() {
        Snackbar.make(recyclerView, R.string.loaded_failed, Snackbar.LENGTH_INDEFINITE).setAction(R.string.retry, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }
}
