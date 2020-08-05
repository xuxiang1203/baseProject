package com.xuxiang.other.app;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.xuxiang.other.util.transform.GlideCircleTransform;

@GlideExtension
public class XyGlideExtension {

    /**
     * 将构造方法设为私有，作为工具类使用
     */
    private XyGlideExtension() {
    }

    /**
     * 加载 圆形 图片
     *
     * @param options
     * @return
     */
    @GlideOption
    public static BaseRequestOptions<?> circleHead(BaseRequestOptions<?> options) {
        MultiTransformation multiTransformation = new MultiTransformation(new CenterCrop(), new GlideCircleTransform());
        options.apply(RequestOptions.bitmapTransform(multiTransformation));
        return options;
    }


}
