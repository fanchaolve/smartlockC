package com.qeasy.samrtlockb.activitiy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.base.m.AddmemberModle;
import com.qeasy.samrtlockb.base.p.AddmemberPresenter;
import com.qeasy.samrtlockb.base.v.AddmemberContract;
import com.qeasy.samrtlockb.bean.Member;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.utils.DataUtils;
import com.qeasy.samrtlockb.utils.LojaDateUtils;
import com.qeasy.samrtlockb.utils.StatusBarUtil;
import com.qeasy.samrtlockb.widget.TextviewObserver;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;

/**
 * 添加人员
 */
public class AddMemberActivity extends BaseActivity
        <AddmemberPresenter, AddmemberModle> implements AddmemberContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_back)
    ImageView title_back;
    @BindView(R.id.title_name)
    TextView title_name;
    @BindView(R.id.tv_right)
    TextView tv_right;


    @BindView(R.id.et_realName)
    EditText et_realName;

    @BindView(R.id.et_mobile)
    EditText et_mobile;

    @BindView(R.id.et_idcard)
    EditText et_idcard;


    @BindView(R.id.lin_timetotime)
    LinearLayout lin_timetotime;

    @BindView(R.id.tv_timetotime)
    TextView tv_timetotime;

    @BindView(R.id.switch1)
    SwitchCompat switch1;

    @BindView(R.id.switch2)
    SwitchCompat switch2;

    @BindView(R.id.switch3)
    SwitchCompat switch3;
    @BindView(R.id.tv_frequency)
    TextView tv_frequency;
    @BindView(R.id.lin_frequency)
    LinearLayout lin_frequency;

    private String frequencyStr = "";//频度
    private String serialNo = "";//序列号
    private String startDate = "", endDate = "";//有效期 开始 结束时间

    private TimePickerView mTimePickerView;
    private OptionsPickerView mOptionsPickerView;

    private int type = 0;// 起始时间的 0为开始 1为结束

    private Member member;


    @Override
    public int getLayoutId() {
        return R.layout.activity_add_member;
    }

    @Override
    public void initView() {
        StatusBarUtil.initStatusBar(this, toolbar, false);
        title_name.setText(R.string.add_member);
        title_back.setVisibility(View.VISIBLE);
        title_back.setImageResource(R.mipmap.icon_fanhui);
        tv_right.setTextColor(getResources().getColor(R.color.white));
        tv_right.setText(getString(R.string.save));
        tv_right.setVisibility(View.VISIBLE);
    }

    @Override
    public void initListener() {
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String realName = et_realName.getText().toString().trim();
                if (TextUtils.isEmpty(realName)) {
                    showMsg("请输入您的姓名");
                    return;
                }

                String mobile = et_mobile.getText().toString().trim();
                if (TextUtils.isEmpty(mobile) || mobile.length() != 11) {
                    showMsg("请输入您正确的手机号码");
                    return;
                }

                String idcard = et_idcard.getText().toString().trim();
                if (TextUtils.isEmpty(idcard) || idcard.length() < 18) {
                    showMsg("请输入您正确的身份证号");
                    return;
                }

                if (TextUtils.isEmpty(startDate) || TextUtils.isEmpty(endDate)) {
                    showMsg("请选择有效期");
                    return;
                }

                int isPinCode = switch1.isChecked() ? 10 : 20;

                int isIcCode = switch2.isChecked() ? 10 : 20;

                int isFingerprintCode = switch3.isChecked() ? 10 : 20;

                if (frequency == 0 || frequencyMode == 0) {
                    showMsg("请选择认证频度");
                    return;
                }
                mPresenter.save(serialNo, mobile, realName, idcard, startDate, endDate, isPinCode, isIcCode, isFingerprintCode, frequency,
                        frequencyMode, member != null ? member.getId() + "" : "");
            }
        });

        lin_timetotime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerView.setVisibility(View.INVISIBLE);
                mTimePickerView.show();
            }
        });

        lin_frequency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOptionsPickerView.show();
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

            if (bundle.containsKey(Constants.MEMBER)) {
                member = (Member) bundle.getSerializable(Constants.MEMBER);
                if (member != null) {
                    mPresenter.setType(1);
                    info(member);
                }
            }
        }
        initDatePicker();
        initOptionsPicker();

    }

    private void initDatePicker() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR),
                startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE));
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        mTimePickerView = new TimePickerView.Builder(this, mTimeSelectListener)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_date_custome, mCustomListener)
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false)
                .setOutSideCancelable(true)
                .setTitleSize(17)
                .setDividerColor(R.color.B8)
                .visibility(View.INVISIBLE)
                .build();

    }

    TimePickerView.OnTimeSelectListener mTimeSelectListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            TextView tv_start = (TextView) v.findViewById(R.id.tv_start);
            TextView tv_end = (TextView) v.findViewById(R.id.tv_end);
            String dateStr = com.qeasy.samrtlockb.utils.DataUtils.getTime(date);
            if (type == 0) {
                startDate = dateStr;
                tv_start.setText(startDate);
                tv_start.setSelected(true);
            }

            if (type == 1) {
                endDate = dateStr;
                tv_end.setText(endDate);
                tv_end.setSelected(true);
            }

        }
    };

    CustomListener mCustomListener = new CustomListener() {
        @Override
        public void customLayout(View v) {
            TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
            TextView tv_reset = (TextView) v.findViewById(R.id.tv_reset);
            final TextView tv_start = (TextView) v.findViewById(R.id.tv_start);
            final TextView tv_end = (TextView) v.findViewById(R.id.tv_end);
            tvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(startDate)) {
                        showTip("请选择开始时间");
                        return;
                    }

                    if (TextUtils.isEmpty(endDate)) {
                        showTip("请选择结束时间");
                        return;
                    }


                    tv_timetotime.setText(getString(R.string.timetotime,
                            startDate, endDate));
                    tv_start.setText(R.string.start_date);
                    tv_start.setSelected(false);
                    tv_end.setText(R.string.end_date);
                    tv_end.setSelected(false);
                    mTimePickerView.dismiss();
                }
            });
            tv_reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mTimePickerView.dismiss();
                    tv_start.setText(R.string.start_date);
                    tv_start.setSelected(false);
                    tv_end.setText(R.string.end_date);
                    tv_end.setSelected(false);
                    endDate = "";
                    startDate = "";
                }
            });

            tv_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type = 0;
                    if (mTimePickerView.getVisibility() == View.INVISIBLE)
                        mTimePickerView.setVisibility(View.VISIBLE);
                }
            });

            tv_end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type = 1;
                    if (mTimePickerView.getVisibility() == View.INVISIBLE)
                        mTimePickerView.setVisibility(View.VISIBLE);
                }
            });
        }
    };


    CustomListener mOptionListener = new CustomListener() {
        @Override
        public void customLayout(View v) {
            Button btnCancel = (Button) v.findViewById(R.id.btnCancel);
            Button btnSubmit = (Button) v.findViewById(R.id.btnSubmit);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOptionsPickerView.dismiss();
                }
            });
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOptionsPickerView.returnData();
                    mOptionsPickerView.dismiss();
                }
            });
        }
    };

    private void initOptionsPicker() {
        mOptionsPickerView = new OptionsPickerView.Builder(this, mOnOptionsSelectListener)
                .setLayoutRes(R.layout.pickerview_options, mOptionListener)
                .setTitleText(getString(R.string.select_tip))
                .setContentTextSize(17)//设置滚轮文字大小
                .setDividerColor(R.color.B8)//设置分割线的颜色
                .build();
        mOptionsPickerView.setNPicker(DataUtils.getP1(), DataUtils.getP2(), DataUtils.getP3());
    }


    int frequency;//认证频度
    int frequencyMode;//认证频度模式 10 天 20 周 30 月

    OptionsPickerView.OnOptionsSelectListener mOnOptionsSelectListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            frequencyStr = DataUtils.getP1().get(options1) + DataUtils.getP2().get(options2) + "1次";
            frequency = Integer.parseInt(DataUtils.getP1().get(options1));
            frequencyMode = DataUtils.getP2().get(options2).
                    equalsIgnoreCase("天") ? 10 : DataUtils.getP2().get(options2).
                    equalsIgnoreCase("月") ? 20 : 30;
            tv_frequency.setText(frequencyStr);


        }
    };


    @Override
    public void saveSucess() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void info(Member member) {
        title_name.setText(R.string.edit_member);
        et_realName.setText(member.getRealName());
        et_mobile.setText(member.getMobile());
        et_idcard.setText(member.getIdentityCard());
        startDate =LojaDateUtils.format(member.getUseStartTime(),LojaDateUtils.YYYY_MM_DD_HH_MM_FORMAT);
        endDate = LojaDateUtils.format(member.getUseEndTime(),LojaDateUtils.YYYY_MM_DD_HH_MM_FORMAT);
        tv_timetotime.setText(mContext.getString(R.string.timetotime,startDate,endDate));
        if(member.getIsPinCode() == 10)
            switch1.setChecked(true);
        else
            switch1.setChecked(false);
        if(member.getIsIcCode() == 10)
            switch2.setChecked(true);
        else
            switch2.setChecked(false);
        if(member.getIsFingerprintCode() == 10)
            switch2.setChecked(true);
        else
            switch2.setChecked(false);
        frequency = member.getFrequency();
        frequencyMode =member.getFrequencyMode();
        tv_frequency.setText(member.getFrequency()+member.getFrequencyModeTag()+"1次");

    }
}
