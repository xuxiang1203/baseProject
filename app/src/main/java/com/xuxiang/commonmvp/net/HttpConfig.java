package com.xuxiang.commonmvp.net;

/**
 * @author
 * @describe
 */
public class HttpConfig {
    public static final int HTTP_SUCCESS = 200;
    public static final int HTTP_TIME = 15;
    public static String BASE_URL = "https://fenshui.deslibs.com";

    public static String getURL() {
        return BASE_URL;
    }
}
