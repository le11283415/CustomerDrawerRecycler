package xg.cn.customerdrawerrecycler.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import xg.cn.customerdrawerrecycler.R;
import xg.cn.customerdrawerrecycler.activity.ActivityCreatePost;
import xg.cn.customerdrawerrecycler.utils.ScreenUtil;

/**
 * 发布图像,添加图像
 * @author yuhuibin
 * @date 2016-06-06
 */

public class VHolderCreatePostAddImage extends RecyclerView.ViewHolder{

    public static final int LAYOUT_ID = R.layout.dis_holder_create_post_add_image;

    private int mHeight = 0;

    private LinearLayout mLinearLayout;
    public VHolderCreatePostAddImage(View itemView) {
        super(itemView);
        Context context = itemView.getContext();
        if (mHeight == 0){
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            mHeight = (dm.widthPixels - ScreenUtil.dip2px(context, 4.5f)*2 -ScreenUtil.dip2px(context, 15f)
                    *2)/ ActivityCreatePost.SPAN_COUNT;
        }
        mLinearLayout = (LinearLayout)itemView.findViewById(R.id.ly);
        mLinearLayout.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight));
    }

    public void onBindViewHolder(View.OnClickListener onClickListener){
        mLinearLayout.setOnClickListener(onClickListener);
    }
}
