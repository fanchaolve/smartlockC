package com.qeasy.samrtlockb.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.adapter.LockerAdapter;
import com.qeasy.samrtlockb.base.BaseFragment;
import com.qeasy.samrtlockb.base.m.LockerModle;
import com.qeasy.samrtlockb.base.p.LockerPresenter;
import com.qeasy.samrtlockb.base.v.LockerContract;
import com.qeasy.samrtlockb.bean.Member;
import com.qeasy.samrtlockb.bean.MemberResult;
import com.qeasy.samrtlockb.listener.LockListener;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.widget.AbstactDailog;

import butterknife.BindView;


public class LockerFragment extends BaseFragment<LockerPresenter, LockerModle> implements View.OnClickListener,
        LockerContract.View{


    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;
    public int type = 0;


    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private LockerAdapter adapter;
    private int TOTAL_COUNTER;
    private String serialNo = "";

    private final static int REQUESTCODE = 0x111;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }
        if (getArguments() != null) {
            serialNo = getArguments().getString(Constants.SERINO);
        }
    }

    public static LockerFragment newInstance(int type,String serialNo) {
        LockerFragment fragment = new LockerFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putString(Constants.SERINO,serialNo);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_locker;
    }

    @Override
    public void initView() {
        adapter = new LockerAdapter(getActivity());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallTrianglePath);
        mRecyclerView.setLoadMoreEnabled(true);

        //设置底部加载文字提示
        mRecyclerView.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                //adapter.clear();
                //mLRecyclerViewAdapter.notifyDataSetChanged();//fix bug:crapped or attached views may not be recycled. isScrap:false isAttached:true

                mPresenter.member_smartlock_search(Constants.FRESH,serialNo,
                        type == 0 ? 10 : 20,""
                );
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                if (adapter.getDataList().size() < TOTAL_COUNTER) {
                    // loading more
                    mPresenter.member_smartlock_search(Constants.LOADMORE,serialNo,
                            type == 0 ? 10 : 20,""
                            );
                } else {
                    //the end
                    mRecyclerView.setNoMore(true);
                }
            }
        });

        adapter.setLockListener(new LockListener() {
            @Override
            public void stop(int pos, final Member member) {
                new AbstactDailog(mContext).setTitle("确定要将此用户停用吗？")
                        .setContent("停用后用户将移到历史使用人员列表")
                        .setmPositiveButtonListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                mPresenter.member_smartlock_stop(member.getId()+"");
                            }
                        },mContext.getString(R.string.stop_use))
                        .setmNegativeButtonListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        },mContext.getString(R.string.cancel)).show();
            }

            @Override
            public void edit(int pos, Member member) {
                Bundle bundle =new Bundle();
                bundle.putString(Constants.SERINO,serialNo);
                bundle.putSerializable(Constants.MEMBER,member);
                Navigation.showAddmember2(bundle,REQUESTCODE);
            }

            @Override
            public void recode(int pos, Member member) {
                //记录
            }

            @Override
            public void start(int pos, final Member member) {
                new AbstactDailog(mContext).setTitle("确定要将此用户启用吗")
                        .setContent("启用后用户将移到历史使用人员列表")
                        .setmPositiveButtonListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                mPresenter.member_smartlock_start(member.getId()+"");
                            }
                        },mContext.getString(R.string.use))
                        .setmNegativeButtonListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        },mContext.getString(R.string.cancel)).show();
            }
        });
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        mRecyclerView.forceToRefresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        }
    }


    @Override
    public void refresh(MemberResult result) {
        TOTAL_COUNTER = result.getTotal();
        if (result.getRows() != null ) {
            adapter.setDataList(result.getRows());
            mRecyclerView.refreshComplete(result.getRows().size());
        }else
            failrefresh();
    }

    @Override
    public void loadMore(final MemberResult result) {
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
    public void stopSuccess() {
        mRecyclerView.forceToRefresh();
    }

    @Override
    public void startSuccess() {
        mRecyclerView.forceToRefresh();
    }

    @Override
    public void failrefresh() {
        if (mRecyclerView != null)
            mRecyclerView.refreshComplete(0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUESTCODE && resultCode == Activity.RESULT_OK){
            mRecyclerView.forceToRefresh();
        }
    }
}
