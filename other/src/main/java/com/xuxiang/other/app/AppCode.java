package com.xuxiang.other.app;

public class AppCode {
    /**
     * 图片-一行张数
     */
    public static final int PIC_SPAN_COUNT = 3;
    //图片的最大选择数量
    public static final int MAX_PIC_SIZE = 9;
    //第三方获取图片的回调码 (目前是知乎)
    public static final int CODE_ZHIFU_PHOTO = 105;
    //列表分页 每页数量
    public static final int PAGE_COUNT = 15;

    /**
     *  intent 间的code传递
     */
    /**
     * 退出登录
     */
    public static final String EXIT_LOGIN = "exit_login";
    /**
     * activity之前参数传递的key
     */
    public static final String INTENT_OBJECT = "send_obj";
    public static final String INTENT_OTHER = "send_obj_other";


    /**
     *  event 事假传递
     */
    /**
     * 支付成功
     */
    public static final String PAYSUCCESS = "paysuccess";
    /**
     * 支付 取消 失败 等未完成状态
     */
    public static final String PAYERROR = "payerror";
    /**
     * 微信登录
     */
    public static final String APP_WX_LOGIN = "app_wx_login";
    public static final String APP_OPENID = "app_openid";

    /**
     * 获取用户信息成功
     */
    public static final String USERINFO_SUCCESS = "userinfo_succcess";


}
