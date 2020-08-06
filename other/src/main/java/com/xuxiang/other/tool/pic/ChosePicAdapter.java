package com.xuxiang.other.tool.pic;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xuxiang.xxlib.R;
import com.xuxiang.xxlib.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2018/7/3 0003
 *
 * @describe 选择图片适配器
 */
public class ChosePicAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context context;
    private ArrayList<String> picPaths = new ArrayList<>();
    private OnDeleteAfter onDeleteAfter;
    //是否可以删除
    private boolean isCanDelete = false;

    public ChosePicAdapter(@Nullable ArrayList<String> picPaths, Context context) {
        this(picPaths, context, true);
    }

    public ChosePicAdapter(@Nullable ArrayList<String> picPaths, Context context, boolean isCanDelete) {
        super(R.layout.item_chose_pic, picPaths);
        this.context = context;
        this.isCanDelete = isCanDelete;
        this.picPaths = picPaths;
    }

    public ChosePicAdapter(@Nullable List<String> picPaths, Context context, boolean isCanDelete) {
        super(R.layout.item_chose_pic, picPaths);
        this.context = context;
        this.isCanDelete = isCanDelete;
        this.picPaths.addAll(picPaths);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(context).load(item)
                .placeholder(R.color.colorE1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.color.colorE1)
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.imageView));

        helper.getView(R.id.img_delete).setOnClickListener(view -> {
            picPaths.remove(helper.getAdapterPosition());
            notifyItemRemoved(helper.getAdapterPosition());
            if (ObjectUtil.isEmpty(picPaths) && onDeleteAfter != null) {
                onDeleteAfter.onEmpty();
            }
        });
        helper.setVisible(R.id.img_delete, isCanDelete)
                .itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(context, LookPicActivity.class);
                    intent.putExtra(LookPicActivity.POSITION, helper.getAdapterPosition());
                    intent.putStringArrayListExtra(LookPicActivity.IMGURLS, picPaths);
                    context.startActivity(intent);
                }
        );
    }

    public void setOnDeleteAfter(OnDeleteAfter onDeleteAfter) {
        this.onDeleteAfter = onDeleteAfter;
    }

    public interface OnDeleteAfter {
        void onEmpty();
    }
}
