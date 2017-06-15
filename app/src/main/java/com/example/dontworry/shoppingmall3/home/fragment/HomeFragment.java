package com.example.dontworry.shoppingmall3.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.dontworry.shoppingmall3.R;
import com.example.dontworry.shoppingmall3.base.BaseFragment;
import com.example.dontworry.shoppingmall3.home.adapter.HomeAdapter;
import com.example.dontworry.shoppingmall3.home.bean.HomeBean;
import com.example.dontworry.shoppingmall3.type.fragment.TypeFragment;
import com.example.dontworry.shoppingmall3.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Don't worry on 2017/6/15.
 */

public class HomeFragment extends BaseFragment {

    private static final String TAG = TypeFragment.class.getSimpleName();
    @BindView(R.id.tv_search_home)
    TextView tvSearchHome;
    @BindView(R.id.tv_message_home)
    TextView tvMessageHome;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.ib_top)
    ImageButton ibTop;
    Unbinder unbinder;
    private HomeAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDateFromNet();
    }

    private void getDateFromNet() {
        String homeUrl = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(homeUrl)
                .build()
                .execute(new MyStringCallBack());
    }

    class MyStringCallBack extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e(TAG, "请求失败" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "请求成功===" + response.toString());
            ProcessData(response);
        }
    }

    /**
     * 解析数据
     *
     * @param json
     */
    private void ProcessData(String json) {
        HomeBean homeBean = JSON.parseObject(json, HomeBean.class);
        Log.e(TAG, "解析成功了==" + homeBean.getResult().getAct_info().get(0).getName());

        adapter = new HomeAdapter(context, homeBean.getResult());
        rvHome.setAdapter(adapter);

        //使用布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvHome.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_search_home, R.id.tv_message_home, R.id.ib_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search_home:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home:
                Toast.makeText(context, "查看", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_top:
                Toast.makeText(context, "回到顶部", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
