package com.example.dontworry.shoppingmall3.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dontworry.shoppingmall3.R;
import com.example.dontworry.shoppingmall3.home.bean.HomeBean;
import com.example.dontworry.shoppingmall3.home.utils.GlideImageLoader;
import com.example.dontworry.shoppingmall3.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Don't worry on 2017/6/15.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private static final int BANNER = 0;
    public static final int CHANNEL = 1;
    public static final int ACT = 2;
    public static final int SECKILL = 3;
    public static final int RECOMMEND = 4;
    public static final int HOT = 5;
    private final Context context;
    private final HomeBean.ResultBean datas;
    public int currentType = BANNER;


    private LayoutInflater inflater;

    public HomeAdapter(Context context, HomeBean.ResultBean datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == CHANNEL) {
            currentType = CHANNEL;
        } else if (position == ACT) {
            currentType = ACT;
        } else if (position == SECKILL) {
            currentType = SECKILL;
        } else if (position == RECOMMEND) {
            currentType = RECOMMEND;
        } else if (position == HOT) {
            currentType = HOT;
        }
        return currentType;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(context, inflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(context, inflater.inflate(R.layout.channel_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(datas.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private Banner banner;
        public BannerViewHolder(Context context, View inflate) {
            super(inflate);
            this.context = context;
            banner = (Banner) inflate.findViewById(R.id.banner);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            List<String> images = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                images.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());
            }
            banner.setImages(images)
                    .setImageLoader(new GlideImageLoader())
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {

                            Toast.makeText(context, "position=="+position, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .start();
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        public ChannelViewHolder(Context context, View inflate) {
            super(inflate);
            this.context = context;
        }
    }
}
