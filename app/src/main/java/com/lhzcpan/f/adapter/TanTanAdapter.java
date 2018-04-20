package com.lhzcpan.f.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lhzcpan.R;
import com.lhzcpan.f.tantan.SwipeCardBean;

import java.util.List;

/**
 * @author master
 * @date 2018/4/8
 */

public class TanTanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<SwipeCardBean> mDatas;
    LayoutInflater mInflater;


    public TanTanAdapter(Context context, List<SwipeCardBean> datas, int layoutId) {
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(mContext);
        mLayoutId = layoutId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(mInflater.inflate(mLayoutId, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            SwipeCardBean swipeCardBean = this.mDatas.get(position);

            ((ViewHolder) holder).tvName.setText(swipeCardBean.getName());
            ((ViewHolder) holder).tvPrecent.setText(swipeCardBean.getPostition() + " /" + mDatas.size());

            Glide.with(mContext)
                    .load(swipeCardBean.getUrl())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .centerCrop()
                    .into(((ViewHolder) holder).iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        SparseArray<View> mViews = new SparseArray();
        public ImageView iv;
        public ImageView iv_love;
        public ImageView iv_del;
        public TextView tvName;
        public  TextView tvPrecent;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            iv_love = itemView.findViewById(R.id.iv_love);
            iv_del = itemView.findViewById(R.id.iv_del);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrecent = itemView.findViewById(R.id.tvPrecent);
        }


        public ViewHolder setAlpha(int viewId, float value) {
            if (Build.VERSION.SDK_INT >= 11) {
                this.getView(viewId).setAlpha(value);
            } else {
                AlphaAnimation alpha = new AlphaAnimation(value, value);
                alpha.setDuration(0L);
                alpha.setFillAfter(true);
                this.getView(viewId).startAnimation(alpha);
            }
            return this;
        }

        public View getView(int viewId) {
            View view = (View) this.mViews.get(viewId);
            if (view == null) {
                view = this.itemView.findViewById(viewId);
                this.mViews.put(viewId, view);
            }
            return view;
        }
    }
}
