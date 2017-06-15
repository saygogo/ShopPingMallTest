package com.example.dontworry.shoppingmall3.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dontworry.shoppingmall3.home.bean.HomeBean;
import com.example.dontworry.shoppingmall3.utils.Constants;

import java.util.List;

/**
 * Created by Don't worry on 2017/6/15.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private final Context context;
    private final List<HomeBean.ResultBean.ActInfoBean> datas;

    public ViewPagerAdapter(Context context, List<HomeBean.ResultBean.ActInfoBean> act_info) {
        this.context = context;
        this.datas = act_info;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        HomeBean.ResultBean.ActInfoBean actInfoBean = datas.get(position);
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + actInfoBean.getIcon_url()).into(imageView);



        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
