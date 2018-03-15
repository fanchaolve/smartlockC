package com.qeasy.samrtlockb.activitiy;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.adapter.NotcieAdapter;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.base.m.NoticeModle;
import com.qeasy.samrtlockb.base.p.NoticePresenter;
import com.qeasy.samrtlockb.base.v.NoticeContract;
import com.qeasy.samrtlockb.bean.NoticeResult;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.utils.StatusBarUtil;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.activitiy
 * <p>
 * 说明：通知
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/6
 * <p>
 * ==============================================
 */
public class NoticeActivity extends BaseActivity<NoticePresenter,NoticeModle> implements NoticeContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title_name)
    TextView title_name;

    @BindView(R.id.title_back)
    ImageView title_back;

    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;


    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private NotcieAdapter adapter;

    private int TOTAL_COUNTER;


    @Override
    public int getLayoutId() {
        return R.layout.activity_notice;
    }

    @Override
    public void initView() {
        StatusBarUtil.initStatusBar(this, toolbar, false);
        title_name.setText(getString(R.string.notice));
        title_back.setVisibility(View.VISIBLE);
        title_back.setImageResource(R.mipmap.icon_fanhui);

        adapter = new NotcieAdapter(this);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallTrianglePath);
        mRecyclerView.setLoadMoreEnabled(true);

        //设置底部加载文字提示
        mRecyclerView.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

    }

    @Override
    public void initListener() {

        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                //adapter.clear();
                //mLRecyclerViewAdapter.notifyDataSetChanged();//fix bug:crapped or attached views may not be recycled. isScrap:false isAttached:true
                mPresenter.notice_search(Constants.FRESH);

            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                if (adapter.getDataList().size() < TOTAL_COUNTER) {
                    // loading more
                    mPresenter.notice_search(Constants.LOADMORE);
                } else {
                    //the end
                    mRecyclerView.setNoMore(true);
                }
            }
        });
    }

    @Override
    public void initdata() {
        mRecyclerView.forceToRefresh();
    }

    @Override
    public void refresh(NoticeResult result) {
        TOTAL_COUNTER = result.getTotal();
        if (result.getRows() != null && result.getRows().size() > 0) {
            adapter.setDataList(result.getRows());
            mRecyclerView.refreshComplete(result.getRows().size());
        } else
            failrefresh();
    }

    @Override
    public void loadMore(final NoticeResult result) {
        TOTAL_COUNTER = result.getTotal();
        if (result.getRows() != null && result.getRows().size() > 0) {
            adapter.addAll(result.getRows());
            mRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mRecyclerView != null)
                        mRecyclerView.refreshComplete(result.getRows().size());
                }
            }, 500);

        }
    }

    @Override
    public void failrefresh() {
        if (mRecyclerView != null)
            mRecyclerView.refreshComplete(0);
    }
}
