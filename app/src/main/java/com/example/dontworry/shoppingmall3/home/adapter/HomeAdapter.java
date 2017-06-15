package com.example.dontworry.shoppingmall3.home.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dontworry.shoppingmall3.R;
import com.example.dontworry.shoppingmall3.home.bean.HomeBean;
import com.example.dontworry.shoppingmall3.home.utils.GlideImageLoader;
import com.example.dontworry.shoppingmall3.home.utils.MyGridView;
import com.example.dontworry.shoppingmall3.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.magicviewpager.transformer.RotateYTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

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
        } else if (viewType == ACT) {
            return new ActViewHolder(context, inflater.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            return new SecKillViewHolder(context, inflater.inflate(R.layout.seckill_item, null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(context, inflater.inflate(R.layout.recommend_item, null));
        } else if (viewType == HOT) {
            return new HotViewHolder(context, inflater.inflate(R.layout.hot_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(datas.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(datas.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(datas.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SecKillViewHolder secKillViewHolder = (SecKillViewHolder) holder;
            secKillViewHolder.setData(datas.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(datas.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(datas.getHot_info());
        }
    }

    @Override
    public int getItemCount() {
        return 6;
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

                            Toast.makeText(context, "position==" + position, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .start();
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @BindView(R.id.gv)
        GridView gv;


        public ChannelViewHolder(Context context, View inflate) {
            super(inflate);
            this.context = context;
            gv = (GridView) inflate.findViewById(R.id.gv);
        }

        public void setData(final List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
            ChannelAdapter adapter = new ChannelAdapter(context, channel_info);
            gv.setAdapter(adapter);

            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, channel_info.get(position).getChannel_name() + "被点击了", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @BindView(R.id.act_viewpager)
        ViewPager actViewpager;


        public ActViewHolder(Context context, View inflate) {
            super(inflate);
            this.context = context;
            actViewpager = (ViewPager) inflate.findViewById(R.id.act_viewpager);
        }

        public void setData(List<HomeBean.ResultBean.ActInfoBean> act_info) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(context, act_info);
            actViewpager.setAdapter(adapter);
            actViewpager.setPageMargin(20);
            actViewpager.setPageTransformer(true, new
                    RotateYTransformer());

        }
    }

    class SecKillViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @BindView(R.id.countdownview)
        CountdownView countdownview;
        @BindView(R.id.tv_more_seckill)
        TextView tvMoreSeckill;
        @BindView(R.id.rv_seckill)
        RecyclerView rvSeckill;

        public SecKillViewHolder(Context context, View inflate) {
            super(inflate);
            this.context = context;
            ButterKnife.bind(this, inflate);
        }

        public void setData(HomeBean.ResultBean.SeckillInfoBean seckill_info) {
            SecKillRecyclerAdapter adapter = new SecKillRecyclerAdapter(context, seckill_info.getList());
            rvSeckill.setAdapter(adapter);
            rvSeckill.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            adapter.setOnItemClickListener(new SecKillRecyclerAdapter.OnItemClickListener() {
                @Override
                public void ItemClick(int position) {
                    Toast.makeText(context, position + "被点击了", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @BindView(R.id.tv_more_recommend)
        TextView tvMoreRecommend;
        @BindView(R.id.gv_recommend)
        GridView gvRecommend;

        public RecommendViewHolder(Context context, View inflate) {
            super(inflate);
            this.context = context;
            ButterKnife.bind(this, inflate);

        }

        public void setData(final List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
            RecommendGridViewAdapter adapter = new RecommendGridViewAdapter(context, recommend_info);
            gvRecommend.setAdapter(adapter);

            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, recommend_info.get(position).getName() + "被点击了", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @BindView(R.id.tv_more_hot)
        TextView tvMoreHot;
        @BindView(R.id.gv_hot)
        MyGridView gvHot;

        public HotViewHolder(final Context context, View inflate) {
            super(inflate);
            this.context = context;
            ButterKnife.bind(this, inflate);

        }

        public void setData(final List<HomeBean.ResultBean.HotInfoBean> hot_info) {
            HotGridViewAdapter adapter = new HotGridViewAdapter(context, hot_info);
            gvHot.setAdapter(adapter);

            gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, hot_info.get(position).getName()+"被点击了", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
