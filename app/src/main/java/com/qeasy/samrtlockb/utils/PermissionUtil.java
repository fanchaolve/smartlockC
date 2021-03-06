package com.qeasy.samrtlockb.utils;

import android.Manifest;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;




import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Junnan on 2017/3/6.
 */

public class PermissionUtil implements EasyPermissions.PermissionCallbacks{

    private static final String TAG = "PermissionUtil";
    private static final int RC_CAMERA_PERM = 123;         //打开相机和文件读取权限
    private static final int RC_CAMERA_OPEN = 124;      //单打开相机
    private static final int RC_LOCATION_CONTACTS_PERM = 125;
    private static final int RC_PHONE_READ_STATUS = 126;       //读取手机权限和文件读写权限
    private static final int RC_RAED_PHONE = 128;           //文件读写权限

    private static final int RC_READ_CONTTACT=130;//读取联系人权限
    private static final int RC_WRITE = 129;        //文件读写权限
    private static final int RC_STORGER_WRITE = 131;        //文件写权限


    private static PermissionUtil instance;
    public onPermissionGentedListener listener;


    private PermissionUtil(){

    }

    public static synchronized PermissionUtil getInstance(){
        if (instance == null){
            instance = new PermissionUtil();
        }
        return instance;
    }

    public void setListener(onPermissionGentedListener listener){
        this.listener = listener;
    }

    /**
     * Api 版本判断
     */
    private boolean isAndroidM(){
        //判断当前api是否为23以上版本
        Log.v("api", Build.VERSION.SDK_INT+"");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return true;
        }
        return false;
    }

    /**
     * 拍照权限和读取本地文件权限
     */
    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask() {
        //不是6.0以上的版本 直接运行
        if (!isAndroidM()){
            if (listener != null){
                listener.onGented();
            }
            return;
        }
        //Log.v("camera","two permission");
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA };
        //两个权限同时满足才执行接下去的任务
        if (EasyPermissions.hasPermissions(AppManager.getInstance().currentActivity(),perms)) {
            // Have permission, do the thing!
            //Log.v("camera","two permission");
            if (listener != null){
                listener.onGented();
            }
        } else {
            //获取多个权限
            ActivityCompat.requestPermissions(AppManager.getInstance().currentActivity(),perms, RC_CAMERA_PERM);
        }
    }


    /**
     * 拍照权限和读取本地文件权限
     */
    @AfterPermissionGranted(RC_STORGER_WRITE)
    public void storgewriteTask() {
        //不是6.0以上的版本 直接运行
        if (!isAndroidM()){
            if (listener != null){
                listener.onGented();
            }
            return;
        }
        //Log.v("camera","two permission");
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        //两个权限同时满足才执行接下去的任务
        if (EasyPermissions.hasPermissions(AppManager.getInstance().currentActivity(),perms)) {
            // Have permission, do the thing!
            //Log.v("camera","two permission");
            if (listener != null){
                listener.onGented();
            }
        } else {
            //获取多个权限
            ActivityCompat.requestPermissions(AppManager.getInstance().currentActivity(),perms, RC_STORGER_WRITE);
        }
    }

    @AfterPermissionGranted(RC_CAMERA_OPEN)
    public void cameraOpenTask() {
        //不是6.0以上的版本 直接运行
        if (!isAndroidM()){
            if (listener != null){
                listener.onGented();
            }
            return;
        }
        String[] perms = {Manifest.permission.CAMERA };
        if (EasyPermissions.hasPermissions(AppManager.getInstance().currentActivity(),perms)) {
            if (listener != null){
                listener.onGented();
            }
        } else {
            //获取多个权限
            ActivityCompat.requestPermissions(AppManager.getInstance().currentActivity(), new String[]{Manifest.permission.CAMERA},RC_CAMERA_OPEN);
        }
    }

    /**
     * status权限和读取本地文件权限
     */
    @AfterPermissionGranted(RC_PHONE_READ_STATUS)
    public void statusAndRead() {
        //不是6.0以上的版本 直接运行
        if (!isAndroidM()){
            if (listener != null){
                listener.onGented();
            }
            return;
        }
        String[] perms = { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE };
        //两个权限同时满足才执行接下去的任务
        if (EasyPermissions.hasPermissions(AppManager.getInstance().currentActivity(),perms)) {
            // Have permission, do the thing!
            //Log.v("camera","two permission");
            if (listener != null){
                listener.onGented();
            }
        } else {
            //获取多个权限
            ActivityCompat.requestPermissions(AppManager.getInstance().currentActivity(), new String[]{Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_EXTERNAL_STORAGE}, RC_PHONE_READ_STATUS);
        }
    }

    /**
     * 位置权限 （蓝牙相关）
     */
    @AfterPermissionGranted(RC_LOCATION_CONTACTS_PERM)
    public void locationAndContactsTask() {
        //不是6.0以上的版本 直接运行
        if (!isAndroidM()){
            if (listener != null){
                listener.onGented();
            }
            return;
        }
        String[] perms = { Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(AppManager.getInstance().currentActivity(), perms)) {
            // Have permissions, do the thing!
            if (listener != null){
                listener.onGented();
            }
        } else {
            // Ask for both permissions
            ActivityCompat.requestPermissions(AppManager.getInstance().currentActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, RC_LOCATION_CONTACTS_PERM);
        }
    }

    /**
     * 读写权限 （热修复相关）
     */
    @AfterPermissionGranted(RC_RAED_PHONE)
    public void ReadPhoneContactsTask() {
        //不是6.0以上的版本 直接运行
        if (!isAndroidM()){
            if (listener != null){
                listener.onGented();
            }
            return;
        }
        String[] perms = { Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(AppManager.getInstance().currentActivity(), perms)) {
            // Have permissions, do the thing!
            if (listener != null){
                listener.onGented();
            }
        } else {
            // Ask for both permissions
            ActivityCompat.requestPermissions(AppManager.getInstance().currentActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_RAED_PHONE);
        }
    }

    /**
     * 联系人权限
     */
    @AfterPermissionGranted(RC_READ_CONTTACT)
    public void ReadPhoneContactsTask2() {
        //不是6.0以上的版本 直接运行
        if (!isAndroidM()){
            if (listener != null){
                listener.onGented();
            }
            return;
        }
        String[] perms = { Manifest.permission.READ_CONTACTS};
        if (EasyPermissions.hasPermissions(AppManager.getInstance().currentActivity(), perms)) {
            // Have permissions, do the thing!
            if (listener != null){
                listener.onGented();
            }
        } else {
            // Ask for both permissions
            ActivityCompat.requestPermissions(AppManager.getInstance().currentActivity(), new String[]{Manifest.permission.READ_CONTACTS}, RC_READ_CONTTACT);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //AppManager.getInstance().currentActivity().onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onPermissionsResult:" );
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        //单次被拒绝
//        if (grantResults[0] != PackageManager.PERMISSION_GRANTED){
//            if (listener != null){
//                listener.onFalied();
//            }
//        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
        //权限获取后自动执行接下去的指令
//        if (requestCode == RC_CAMERA_PERM && perms.size() == 2){            //说明两个权限都已满足
//            if (listener != null){
//                listener.onGented();
//            }
//        }else if (requestCode == RC_CAMERA_OPEN){
//            if (listener != null){
//                listener.onGented();
//            }
//        }else if (requestCode == RC_PHONE_READ_STATUS && perms.size() == 2){
//            if (listener != null){
//                listener.onGented();
//            }
//        }else if (requestCode == RC_LOCATION_CONTACTS_PERM){
//            if (listener != null){
//                listener.onGented();
//            }
//        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());
        if (EasyPermissions.somePermissionPermanentlyDenied(AppManager.getInstance().currentActivity(), perms) && requestCode == RC_CAMERA_PERM) {
            new AppSettingsDialog.Builder(AppManager.getInstance().currentActivity(),"权限已被拒绝，要想使用该功能请在设置-权限中打开\"相机\"和\"存储空间\"权限").setTitle("获取权限").build().show();
        }else if (EasyPermissions.somePermissionPermanentlyDenied(AppManager.getInstance().currentActivity(), perms) && requestCode == RC_CAMERA_OPEN){
            new AppSettingsDialog.Builder(AppManager.getInstance().currentActivity(),"权限已被拒绝，要想使用该功能请在设置-权限中打开\"相机\"权限").setTitle("获取权限").build().show();
        }else if (EasyPermissions.somePermissionPermanentlyDenied(AppManager.getInstance().currentActivity(), perms) && requestCode == RC_PHONE_READ_STATUS){
            new AppSettingsDialog.Builder(AppManager.getInstance().currentActivity(),"权限已被拒绝，要想使用该功能请在设置-权限中打开\"存储空间\"和\"电话\"权限").setTitle("获取权限").build().show();
        }else if (EasyPermissions.somePermissionPermanentlyDenied(AppManager.getInstance().currentActivity(), perms) && requestCode == RC_LOCATION_CONTACTS_PERM) {
            new AppSettingsDialog.Builder(AppManager.getInstance().currentActivity(),"权限已被拒绝，要想使用该功能请在设置-权限中打开\"位置信息\"权限").setTitle("获取权限").build().show();
        }else if (EasyPermissions.somePermissionPermanentlyDenied(AppManager.getInstance().currentActivity(), perms) && requestCode == RC_RAED_PHONE) {
            new AppSettingsDialog.Builder(AppManager.getInstance().currentActivity(),"权限已被拒绝，要想使用该功能请在设置-权限中打开\"存储空间\"权限").setTitle("获取权限").build().show();
        }else if (EasyPermissions.somePermissionPermanentlyDenied(AppManager.getInstance().currentActivity(), perms) && requestCode == RC_READ_CONTTACT) {
            new AppSettingsDialog.Builder(AppManager.getInstance().currentActivity(),"权限已被拒绝，要想使用该功能请在设置-权限中打开\"通讯录\"权限").setTitle("获取权限").build().show();
        }
        if (listener != null){
            listener.onFalied();
        }
    }

    /**
     * 读取权限
     */
    @AfterPermissionGranted(RC_WRITE)
    public void writeContactsTask() {
        //不是6.0以上的版本 直接运行
        if (!isAndroidM()){
            if (listener != null){
                listener.onGented();
            }
            return;
        }
        String[] perms = { Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(AppManager.getInstance().currentActivity(), perms)) {
            // Have permissions, do the thing!
            if (listener != null){
                listener.onGented();
            }
        } else {
            // Ask for both permissions
            ActivityCompat.requestPermissions(AppManager.getInstance().currentActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_WRITE);
        }
    }

    public interface onPermissionGentedListener{
        void onGented();
        void onFalied();
    }

}
