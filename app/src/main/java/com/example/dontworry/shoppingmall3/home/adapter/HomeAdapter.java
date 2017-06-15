package com.example.dontworry.shoppingmall3.home.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.dontworry.shoppingmall3.app.GoodsInfoActivity;
import com.example.dontworry.shoppingmall3.app.WebViewActivity;
import com.example.dontworry.shoppingmall3.home.bean.GoodsBean;
import com.example.dontworry.shoppingmall3.home.bean.HomeBean;
import com.example.dontworry.shoppingmall3.home.bean.WebViewBean;
import com.example.dontworry.shoppingmall3.home.utils.GlideImageLoader;
import com.example.dontworry.shoppingmall3.home.view.MyGridView;
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


    public static final String GOODS_BEAN = "goods_bean";
    public static final String WEBVIEW_BEAN = "webview_bean";
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

        public void setData(final List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            List<String> images = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                images.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());
            }
            banner.setImages(images)
                    .setImageLoader(new GlideImageLoader())
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            if (position < banner_info.size()) {
                                String product_id = "";
                                String name = "";
                                String cover_price = "";
                                if (position == 0) {
                                    product_id = "627";
                                    cover_price = "32.00";
                                    name = "剑三T恤批发";
                                } else if (position == 1) {
                                    product_id = "21";
                                    cover_price = "8.00";
                                    name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                                } else {
                                    product_id = "1341";
                                    cover_price = "50.00";
                                    name = "【蓝诺】《天下吾双》 剑网3同人本";
                                }
                                String image = banner_info.get(position).getImage();
                                GoodsBean goodsBean = new GoodsBean();
                                goodsBean.setName(name);
                                goodsBean.setCover_price(cover_price);
                                goodsBean.setFigure(image);
                                goodsBean.setProduct_id(product_id);

                                Intent intent = new Intent(context, GoodsInfoActivity.class);
                                intent.putExtra(GOODS_BEAN, goodsBean);
                                context.startActivity(intent);
                            }

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

        public void setData(final List<HomeBean.ResultBean.ActInfoBean> act_info) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(context, act_info);
            actViewpager.setAdapter(adapter);
            actViewpager.setPageMargin(20);
            actViewpager.setPageTransformer(true, new
                    RotateYTransformer());
            adapter.setOnItemCilckListener(new ViewPagerAdapter.OnItemCilckListener() {
                @Override
                public void IntiemClick(int position) {
                    HomeBean.ResultBean.ActInfoBean actInfoBean = act_info.get(position);
//                    Toast.makeText(mContext, "" + actInfoBean.getName(), Toast.LENGTH_SHORT).show();

                    WebViewBean webViewBean = new WebViewBean();
                    webViewBean.setName(actInfoBean.getName());
                    webViewBean.setIcon_url(actInfoBean.getIcon_url());
                    webViewBean.setUrl(Constants.BASE_URL_IMAGE + actInfoBean.getUrl());

                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra(WEBVIEW_BEAN, webViewBean);
                    context.startActivity(intent);
                }
            });

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

        public void setData(final HomeBean.ResultBean.SeckillInfoBean seckill_info) {
            SecKillRecyclerAdapter adapter = new SecKillRecyclerAdapter(context, seckill_info.getList());
            rvSeckill.setAdapter(adapter);
            rvSeckill.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            adapter.setOnItemClickListener(new SecKillRecyclerAdapter.OnItemClickListener() {
                @Override
                public void ItemClick(int position) {
                    HomeBean.ResultBean.SeckillInfoBean.ListBean infoBean = seckill_info.getList().get(position);
                    //传递数据
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setName(infoBean.getName());
                    goodsBean.setCover_price(infoBean.getCover_price());
                    goodsBean.setFigure(infoBean.getFigure());
                    goodsBean.setProduct_id(infoBean.getProduct_id());
                    Intent intent = new Intent(context, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);
                    context.startActivity(intent);
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
                    HomeBean.ResultBean.RecommendInfoBean infoBean = recommend_info.get(position);
                    //传递数据
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setName(infoBean.getName());
                    goodsBean.setCover_price(infoBean.getCover_price());
                    goodsBean.setFigure(infoBean.getFigure());
                    goodsBean.setProduct_id(infoBean.getProduct_id());
                    Intent intent = new Intent(context, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);
                    context.startActivity(intent);
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

        public HotViewHolder(Context context, View inflate) {
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
                    Toast.makeText(context, hot_info.get(position).getName() + "被点击了", Toast.LENGTH_SHORT).show();

                    HomeBean.ResultBean.HotInfoBean hotInfoBean = hot_info.get(position);
                    //传递数据
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setFigure(hotInfoBean.getFigure());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());
                    Intent intent = new Intent(context, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);
                    context.startActivity(intent);
                }
            });
        }
    }
}
