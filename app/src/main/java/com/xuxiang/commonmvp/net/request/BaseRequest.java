package com.xuxiang.commonmvp.net.request;

import com.xuxiang.commonmvp.bean.AppUpdateBean;
import com.xuxiang.commonmvp.net.back.BaseResponse;
import com.xuxiang.commonmvp.net.back.HttpCallBack;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BaseRequest {

    /**
     * 检查更新
     * @return
     */
    @GET("calculation/update")
    Observable<BaseResponse<AppUpdateBean>> checkUpdate(@Query("version") String version);
}
