package com.xuxiang.commonmvp.net;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xuxiang.commonmvp.net.request.BaseRequest;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author
 * @describe Retrofit封装类
 * 如何取消请求？
 * Rx方式和Call方式
 */
public class HttpHeper {
    private Retrofit retrofit;
    private Object tag;

    private HttpHeper(Object object) {
        retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.getURL()) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
        tag = object;
    }

    public static  <T> ObservableTransformer<T, T> getThread() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 调用方式伪代码：
     * HttpHeper.from(this).xxxApi.xxx(x,y)
     * return : call  or observble
     * 手动取消 自动 取消
     * 判断是call 还是 observble
     */
    public static HttpHeper get() {
        HttpHeper httpHeper = new HttpHeper(new Object());
        return httpHeper;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    public BaseRequest baseRequest() {
        return create(BaseRequest.class);
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//        if (AppConfig.getInstance().isLogin()) {
        //添加公共的参数
//            BasicParamsInterceptor.Builder ParamBuilder = new BasicParamsInterceptor.Builder();
//            ParamBuilder.addParam("Authorization", AppConfig.getInstance().getToken());
//            builder.addInterceptor(ParamBuilder.build());
//        }
        builder.addInterceptor(loggingInterceptor)
                .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS);
        return builder.build();
    }
}
