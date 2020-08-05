package com.xuxiang.other.net.request;


import com.xuxiang.other.bean.AppUpdateBean;
import com.xuxiang.other.net.back.BaseResponse;

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
