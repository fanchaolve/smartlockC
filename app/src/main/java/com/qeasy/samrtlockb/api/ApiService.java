package com.qeasy.samrtlockb.api;


import com.qeasy.samrtlockb.bean.MemberResult;
import com.qeasy.samrtlockb.bean.NoticeResult;
import com.qeasy.samrtlockb.bean.SmarLock;
import com.qeasy.samrtlockb.bean.SmartlockRecordResult;
import com.qeasy.samrtlockb.bean.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by fancl.
 * 服务器接口
 * <p>
 * <p>
 * <p>
 * --------------------- 画重点---------------------
 * <p>
 * -----------------------------------------------
 */

public interface ApiService {


    /**
     * 短信验证码
     *
     * @param mobile 手机号码
     * @return
     */
    @GET("sms/sendLoginSmsCode/{mobile}")
    Call<Result_Api> sms_sendLoginSmsCode(@Path("mobile") String mobile);


    /**
     *  会员登录
     *
     * @param mobile
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("member/login")
    Call<Result_Api<User>> merchant_login(@Field("mobile") String mobile,
                                          @Field("code") String code);



    //--------------------


    /**
     *首页
     * @return
     */
    @POST("home")
    Call<Result_Api<List<SmarLock>>> home();


    /**
     *异常申报
     * @return
     */
    @FormUrlEncoded
    @POST("declare")
    Call<Result_Api> declare(@Field("serialNo") String serialNo,
                             @Field("content") String content);



    /**
     *客服电话
     * @return
     */
    @POST("tel")
    Call<Result_Api<String>> tel();





    /**
     * 消息列表
     *
     * @param offset
     * @param limit
     * @return
     */
    @FormUrlEncoded
    @POST("notice/search")
    Call<Result_Api<NoticeResult>> notice_search(@Field("offset") int offset, @Field("limit") int limit);


    /**
     * 商家智能锁信息变更-身份认证
     *
     * @param serialNo
     * @param pinCode         临时密码
     * @param fingerprintCode 指纹
     * @param icCode          ic
     * @return
     */
    @FormUrlEncoded
    @POST("changeInfo")
    Call<Result_Api> merchant_smartlock_changeInfo(
            @Field("serialNo") String serialNo,
            @Field("pinCode") String pinCode,
            @Field("fingerprintCode") String fingerprintCode,
            @Field("icCode") String icCode);






    //-------------------------以上是c端



    /**
     * 锁详情对应的开锁记录接口
     *
     * @param serialNo
     * @param offset
     * @param limit
     * @param param
     * @param startUnlockTime
     * @param endUnlockTime
     * @param memberId
     * @return
     */
    @FormUrlEncoded
    @POST("unlockrecord/search")
    Call<Result_Api<SmartlockRecordResult>> unlockrecord_search
    (@Field("serialNo") String serialNo, @Field("offset") int offset,
     @Field("limit") int limit, @Field("param") String param,
     @Field("startUnlockTime") String startUnlockTime,
     @Field("endUnlockTime") String endUnlockTime,
     @Field("memberId") String memberId);




    /**
     * 商家智能锁停用
     *
     * @param serialNo
     * @return
     */
    @FormUrlEncoded
    @POST("smartlock/stop")
    Call<Result_Api> smartlock_stop(@Field("serialNo") String serialNo);


    /**
     * 重置智能锁
     *
     * @param serialNo
     * @return
     */
    @FormUrlEncoded
    @POST("merchant/smartlock/unBinding")
    Call<Result_Api> merchant_smartlock_unBinding(@Field("serialNo") String serialNo);


    /**
     * 商家智能锁信息变更-认证频度
     *
     * @param serialNo
     * @param frequency     认证频度
     * @param frequencyMode 认证频度模式 10 天 20 周 30 月
     * @return
     */
    @FormUrlEncoded
    @POST("merchant/smartlock/changeInfo")
    Call<Result_Api> merchant_smartlock_changeInfo(@Field("serialNo") String serialNo,
                                                   @Field("frequency") int frequency,
                                                   @Field("frequencyMode") int frequencyMode
    );





    /**
     * 商家智能锁信息变更-地址
     *
     * @param serialNo
     * @param address
     * @return
     */
    @FormUrlEncoded
    @POST("merchant/smartlock/changeInfo")
    Call<Result_Api> merchant_smartlock_changeInfo(
            @Field("serialNo") String serialNo,
            @Field("address") String address);


    /**
     * 商户智能钥对应用户
     *
     * @param serialNo
     * @param smartlockId
     * @param status
     * @param offset
     * @param limit
     * @param param
     * @return
     */
    @FormUrlEncoded
    @POST("member/smartlock/search")
    Call<Result_Api<MemberResult>> member_smartlock_search(
            @Field("serialNo") String serialNo,
            @Field("smartlockId") int smartlockId,
            @Field("status") int status,
            @Field("offset") int offset,
            @Field("limit") int limit,
            @Field("param") String param
    );


    /**
     *  添加商家智能锁关联用户
     * @param serialNo
     * @param mobile
     * @param realName
     * @param identityCard
     * @param useStartTime
     * @param useEndTime
     * @param isPinCode
     * @param isIcCode
     * @param isFingerprintCode
     * @param frequency
     * @param frequencyMode
     * @return
     */
    @FormUrlEncoded
    @POST("member/smartlock/add")
    Call<Result_Api> member_smartlock_add(
            @Field("serialNo") String serialNo,
            @Field("mobile") String mobile,
            @Field("realName") String realName,
            @Field("identityCard") String identityCard,
            @Field("useStartTime") String useStartTime,
            @Field("useEndTime") String useEndTime,
            @Field("isPinCode") int isPinCode,
            @Field("isIcCode") int isIcCode,
            @Field("isFingerprintCode") int isFingerprintCode,
            @Field("frequency") int frequency,
            @Field("frequencyMode") int frequencyMode

    );



    /**
     *  停用商家智能锁关联的用户
     * @return
     */
    @FormUrlEncoded
    @POST("member/smartlock/stop")
    Call<Result_Api> member_smartlock_stop(@Field("id") String id);

    /**
     *  启用商家智能锁关联的用户
     * @return
     */
    @FormUrlEncoded
    @POST("member/smartlock/start")
    Call<Result_Api> member_smartlock_start(@Field("id") String id);

    /**
     *  编辑商家智能锁关联用户
     * @return
     */
    @FormUrlEncoded
    @POST("member/smartlock/edit")
    Call<Result_Api> member_smartlock_edit(
            @Field("serialNo") String serialNo,
            @Field("mobile") String mobile,
            @Field("realName") String realName,
            @Field("identityCard") String identityCard,
            @Field("useStartTime") String useStartTime,
            @Field("useEndTime") String useEndTime,
            @Field("isPinCode") int isPinCode,
            @Field("isIcCode") int isIcCode,
            @Field("isFingerprintCode") int isFingerprintCode,
            @Field("frequency") int frequency,
            @Field("frequencyMode") int frequencyMode,
            @Field("id") String id);




}
