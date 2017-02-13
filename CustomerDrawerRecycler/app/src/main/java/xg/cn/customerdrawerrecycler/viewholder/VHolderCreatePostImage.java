package xg.cn.customerdrawerrecycler.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import xg.cn.customerdrawerrecycler.R;
import xg.cn.customerdrawerrecycler.activity.ActivityCreatePost;
import xg.cn.customerdrawerrecycler.utils.ScreenUtil;
import xg.cn.customerdrawerrecycler.view.LoadingImageView;

/**
 * 发布图像,移除图像
 * @author yuhuibin
 * @date 2016-06-12
 */

public class VHolderCreatePostImage extends RecyclerView.ViewHolder{
    public static final int LAYOUT_ID = R.layout.dis_holder_create_post_image;

    private int mHeight = 0;

    private LoadingImageView mImageView;
    private RelativeLayout mLayout;
    private TextView mRemove;

    public VHolderCreatePostImage(View itemView) {
        super(itemView);
        Context context = itemView.getContext();
        if (mHeight == 0){
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            mHeight = (dm.widthPixels - ScreenUtil.dip2px(context, 4.5f)*2 -ScreenUtil.dip2px(context, 15f)
                    *2)/ ActivityCreatePost.SPAN_COUNT;
        }

        mImageView = (LoadingImageView) itemView.findViewById(R.id.image);
        mLayout = (RelativeLayout)itemView.findViewById(R.id.root_layout);
        mLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight));

        mRemove = (TextView)itemView.findViewById(R.id.btn_remove);
    }

    public void onBindViewHolder(String url, final RemoveCreatePostImage removeCreatePostImage, final int position){
        mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCreatePostImage.remove(position);
            }
        });

        mImageView.setGalleryImage(url, mHeight, mHeight);
    }

    /** 删除图像接口 */
    public interface RemoveCreatePostImage{
        void remove(int position);
    }


}
