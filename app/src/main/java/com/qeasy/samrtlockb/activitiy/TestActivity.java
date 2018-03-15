package com.qeasy.samrtlockb.activitiy;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
//import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.CaptureActivity;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.utils.AppManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;

public class TestActivity extends BaseActivity {

    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;

    private OptionsPickerView mOptionsPickerView;
    private TimePickerView mTimePickerView;
    private ArrayList<String> p1 = new ArrayList<>();
    private ArrayList<String> p2 = new ArrayList<>();
    private ArrayList<String> p3 = new ArrayList<>();

    private String startDate, endDate;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerView.setVisibility(View.INVISIBLE);
                mTimePickerView.show();
            }
        });


        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOptionsPickerView.show();
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AppManager.getInstance().showActivity(MipcaActivityCapture.class, null);
                AppManager.getInstance().showActivity(CaptureActivity.class,null);
            }
        });

    }

    @Override
    public void initdata() {
        p1.add("30");
        p1.add("20");
        p1.add("10");

        p2.add("周");
        p2.add("月");
        p2.add("年");

        p3.add("2次");

        initDatePicker();
        initOptionsPicker();
    }

    private void initDatePicker() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
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

    private int type = 0;


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

                    endDate = "";
                    startDate = "";
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


    private void initOptionsPicker() {
        mOptionsPickerView = new OptionsPickerView.Builder(this, mOnOptionsSelectListener)
                .setLayoutRes(R.layout.pickerview_options, mOptionListener)
                .setTitleText(getString(R.string.select_tip))
                .setContentTextSize(17)//设置滚轮文字大小
                .setDividerColor(R.color.B8)//设置分割线的颜色
                .build();
        mOptionsPickerView.setNPicker(p1, p2, p3);
    }

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
                }
            });
        }
    };

    OptionsPickerView.OnOptionsSelectListener mOnOptionsSelectListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            String str = "food:" + p1.get(options1)
                    + "\nclothes:" + p2.get(options2)
                    + "\ncomputer:" + p3.get(options3);

            Toast.makeText(TestActivity.this, str, Toast.LENGTH_SHORT).show();
        }
    };
}
