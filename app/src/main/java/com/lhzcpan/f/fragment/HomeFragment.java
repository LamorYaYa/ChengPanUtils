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

import com.lhzcpan.R;
import com.lhzcpan.f.adapter.HomeAdapter;
import com.lhzcpan.f.bean.HomeBean;
import com.lhzcpan.f.interfaces.IHomeInterface;
import com.lhzcpan.f.interfaces.OnRecyclerViewOnClickListener;
import com.lhzcpan.f.presenter.HomePresenters;

import java.util.List;

/**
 *
 * @author master
 * @date 2018/1/19
 */

public class HomeFragment extends Fragment implements IHomeInterface.View {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private HomeAdapter mHomeAdapter;
    private IHomeInterface.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_list, container, false);

        initViews(view);

        presenter = new HomePresenters(this, getContext());
        presenter.loadPosts(System.currentTimeMillis(), true);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });

        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);

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
