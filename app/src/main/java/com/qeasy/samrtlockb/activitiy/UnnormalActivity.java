package com.qeasy.samrtlockb.activitiy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.adapter.NotcieAdapter;
import com.qeasy.samrtlockb.adapter.UnnormalAdapter;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.base.m.UnnormalModle;
import com.qeasy.samrtlockb.base.p.UnnormalPresenter;
import com.qeasy.samrtlockb.base.v.UnnormalContract;
import com.qeasy.samrtlockb.bean.Unnormal;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.activitiy
 * <p>
 * 说明：申报异常
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/14
 * <p>
 * ==============================================
 */
public class UnnormalActivity extends BaseActivity<UnnormalPresenter,UnnormalModle> implements UnnormalContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title_name)
    TextView title_name;

    @BindView(R.id.title_back)
    ImageView title_back;


    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;

    @BindView(R.id.tv_confim)
    TextView tv_confim;

    private String serialNo = "";



    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private UnnormalAdapter adapter;

    private List<Unnormal>unnormalList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_unnormal;
    }

    @Override
    public void initView() {

        StatusBarUtil.initStatusBar(this, toolbar, false);
        title_name.setText(getString(R.string.titleright));
        title_back.setVisibility(View.VISIBLE);
        title_back.setImageResource(R.mipmap.icon_fanhui);

        adapter = new UnnormalAdapter(this);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadMoreEnabled(false);



    }

    @Override
    public void initListener() {
        tv_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i <unnormalList.size();i++){
                    Unnormal unnormal =unnormalList.get(i);
                    if(unnormal.isSelect()){
                        String content =unnormal.getContent();
                        if(!TextUtils.isEmpty(content))
                            mPresenter.declare(serialNo,content);
                        else
                            showMsg("请选择或者请输入故障情况");
                    }
                }
            }
        });
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initdata() {

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            if (bundle.containsKey(Constants.SERINO)) {
                serialNo = bundle.getString(Constants.SERINO);
            }

        }

        unnormalList =new ArrayList<>();
        Unnormal unnormal =new Unnormal();
        unnormal.setSelect(true);
        unnormal.setContent("输入密码无法开锁");
        unnormalList.add(unnormal);

        Unnormal unnormal1 =new Unnormal();
        unnormal1.setSelect(false);
        unnormal1.setContent("IC磁卡无法开锁");
        unnormalList.add(unnormal1);

        Unnormal unnormal2=new Unnormal();
        unnormal2.setSelect(false);
        unnormal2.setContent("电池过期");
        unnormalList.add(unnormal2);

        Unnormal unnormal3=new Unnormal();
        unnormal3.setSelect(false);
        unnormal3.setContent("锁键位损坏");
        unnormalList.add(unnormal3);

        Unnormal unnormal4=new Unnormal();
        unnormal4.setSelect(false);
        unnormal4.setContent("人脸验证失败");
        unnormalList.add(unnormal4);

        Unnormal unnormal5=new Unnormal();
        unnormal5.setSelect(false);
        unnormal5.setContent("");
        unnormalList.add(unnormal5);

        adapter.setDataList(unnormalList);
    }

    @Override
    public void successInfo() {
        showMsg("异常申报成功");
        finish();
    }
}
