package com.lhzcpan.f.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lhzcpan.R;
import com.lhzcpan.f.bean.HomeBean;
import com.lhzcpan.f.interfaces.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author master
 * @date 2018/1/19
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mInflater;
    private List<HomeBean> mlist = new ArrayList<>();
    private OnRecyclerViewOnClickListener mListener;

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_FOOTER = 1;

    public HomeAdapter(Context mContext, List<HomeBean> mlist) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mlist = mlist;
    }

    public void setData(List<HomeBean> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    public void setItemClickListenter(OnRecyclerViewOnClickListener listenter) {
        this.mListener = listenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NORMAL:
                return new ViewHolder(mInflater.inflate(R.layout.home_list_item_layout, parent, false), mListener);
            case TYPE_FOOTER:
                return new FooterViewHolder(mInflater.inflate(R.layout.home_list_item_layout_footer, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            HomeBean homeBean = mlist.get(position);
            if (homeBean.getImgUrl().startsWith("http")) {
                Glide.with(mContext)
                        .load(homeBean.getImgUrl())
                        .asBitmap()
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.mipmap.ic_launcher)
                        .centerCrop()
                        .into(((ViewHolder) holder).iImageView);
            } else {
                ((ViewHolder) holder).iImageView.setImageResource(R.mipmap.ic_launcher);
            }
            ((ViewHolder) holder).iTextView.setText(homeBean.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mlist.size()) {
            return HomeAdapter.TYPE_FOOTER;
        } else {
            return HomeAdapter.TYPE_NORMAL;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iImageView;
        private TextView iTextView;
        private OnRecyclerViewOnClickListener iOnRecyclerViewOnClickListener;


        public ViewHolder(View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);
            iImageView = itemView.findViewById(R.id.imageViewCover);
            iTextView = itemView.findViewById(R.id.textViewTitle);
            this.iOnRecyclerViewOnClickListener = listener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (iOnRecyclerViewOnClickListener != null) {
                iOnRecyclerViewOnClickListener.OnItemClick(v, getLayoutPosition());
            }
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }

    }

}
