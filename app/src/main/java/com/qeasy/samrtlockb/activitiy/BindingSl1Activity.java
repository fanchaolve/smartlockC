package com.qeasy.samrtlockb.activitiy;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.utils.StatusBarUtil;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.activitiy
 * <p>
 * 说明：未绑定锁之前的显示
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/13
 * <p>
 * ==============================================
 */
public class BindingSl1Activity extends BaseActivity {



    @BindView(R.id.lin_title)
    LinearLayout lin_title;

    @BindView(R.id.iv_setting)
    ImageView iv_setting;

    @BindView(R.id.tv_abnormal)
    TextView tv_abnormal;

    @BindView(R.id.iv_lanya)
    ImageView iv_lanya;

    @BindView(R.id.tv_status)
    TextView tv_status;

    @BindView(R.id.et_serialNo)
    EditText et_serialNo;

    @BindView(R.id.ib_saoyisao)
    ImageButton ib_saoyisao;

    @BindView(R.id.tv_confim)
    TextView tv_confim;


    private final static int REQUESTCODE = 0x009;


    @Override
    public int getLayoutId() {
        return R.layout.activity_bindingsl;
    }

    @Override
    public void initView() {
        StatusBarUtil.initStatusBar(this, lin_title, false);

    }

    @Override
    public void initListener() {
        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_abnormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ib_saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.showCapture(null, REQUESTCODE);
            }
        });

        tv_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_serialNo.getText().toString().trim())) {
                    showMsg("未获取到锁序列号！！");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString(Constants.SERINO, et_serialNo.getText().toString().trim());
                Navigation.showAuthentication(bundle);
            }
        });
    }

    private BluetoothAdapter mBluetoothAdapter;
    @Override
    public void initdata() {
        BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 0x111);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == Activity.RESULT_OK) {
            String zxing = data.getStringExtra("zxing");
            if (!TextUtils.isEmpty(zxing))
                et_serialNo.setText(zxing);
        }
    }
}
