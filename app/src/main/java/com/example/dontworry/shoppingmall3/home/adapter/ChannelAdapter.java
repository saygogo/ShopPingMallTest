package com.example.dontworry.shoppingmall3.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

public class ChannelAdapter extends BaseAdapter {
    private final Context context;
    private final List<HomeBean.ResultBean.ChannelInfoBean> datas;

    public ChannelAdapter(Context context, List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
        this.context = context;
        this.datas = channel_info;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_channel, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HomeBean.ResultBean.ChannelInfoBean channelInfoBean = datas.get(position);
        viewHolder.tvChannel.setText(channelInfoBean.getChannel_name());
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + channelInfoBean.getImage())
                .into(viewHolder.ivChannel);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_channel)
        ImageView ivChannel;
        @BindView(R.id.tv_channel)
        TextView tvChannel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
