package com.lhzcpan.f.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhzcpan.R;
import com.lhzcpan.f.adapter.TanTanAdapter;
import com.lhzcpan.f.bean.HomeBean;
import com.lhzcpan.f.interfaces.ISwipeInterface;
import com.lhzcpan.f.presenter.SwipePresenters;
import com.lhzcpan.f.tantan.CardConfig;
import com.lhzcpan.f.tantan.OverLayCardLayoutManager;
import com.lhzcpan.f.tantan.SwipeCardBean;
import com.lhzcpan.f.tantan.TanTanCallback;

import java.util.List;

/**
 * Created by master on 2018/4/8.
 */

public class SwipeFragment extends Fragment implements ISwipeInterface.View {


    private RecyclerView mRecyclerRenRenView;
    private TanTanAdapter mTanTanAdapter;
    private List<SwipeCardBean> mSwipeCardBeanDatas;
    private SwipePresenters presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tantan_layoyt, container, false);
        initViews(view);

        presenter = new SwipePresenters(this, getContext());
        presenter.start();

        return view;
    }

    @Override
    public void initViews(View view) {
        mRecyclerRenRenView = view.findViewById(R.id.recyclerView);
        mRecyclerRenRenView.setHasFixedSize(true);
        mRecyclerRenRenView.setLayoutManager(new OverLayCardLayoutManager());

        mSwipeCardBeanDatas = SwipeCardBean.initDatas();
        mTanTanAdapter = new TanTanAdapter(getContext(), mSwipeCardBeanDatas, R.layout.item_swipe_card);
        mRecyclerRenRenView.setAdapter(mTanTanAdapter);

        CardConfig.initConfig(getContext());

        final TanTanCallback mRenRenCallback = new TanTanCallback(mRecyclerRenRenView, mTanTanAdapter, mSwipeCardBeanDatas);
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mRenRenCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerRenRenView);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showResults(List<HomeBean> list) {

    }

    @Override
    public void showError() {

    }


}
