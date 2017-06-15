package com.example.dontworry.shoppingmall3.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dontworry.shoppingmall3.R;
import com.example.dontworry.shoppingmall3.home.bean.HomeBean;
import com.example.dontworry.shoppingmall3.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Don't worry on 2017/6/15.
 */

public class SecKillRecyclerAdapter extends RecyclerView.Adapter<SecKillRecyclerAdapter.MyViewHolder> {

    private final Context context;
    private final List<HomeBean.ResultBean.SeckillInfoBean.ListBean> datas;


    public SecKillRecyclerAdapter(Context context, List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list) {
        this.context = context;
        this.datas = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context, R.layout.item_seckill, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = datas.get(position);
        holder.tvCoverPrice.setText(listBean.getCover_price());
        holder.tvOriginPrice.setText(listBean.getOrigin_price());
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + listBean.getFigure())
                .into(holder.ivFigure);


    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_figure)
        ImageView ivFigure;
        @BindView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @BindView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @BindView(R.id.ll_root)
        LinearLayout llRoot;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.ItemClick(getLayoutPosition());
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void ItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
