package xg.cn.customerdrawerrecycler.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import xg.cn.customerdrawerrecycler.interfaces.ItemTouchAdapter;
import xg.cn.customerdrawerrecycler.viewholder.VHolderCreatePostAddImage;
import xg.cn.customerdrawerrecycler.viewholder.VHolderCreatePostImage;

/**
 * 内容添加中RecyclerView的Adapter
 * @author yuhuibin
 * @date 2016-06-06
 */

public class AdapterCreatePostImageList extends RecyclerView.Adapter implements ItemTouchAdapter {
    private static final int IMAGE = 1;
    private static final int ADD_IMAGE = 2;
    private static final int MAX_IMAGE_SIZE = 9;
    private List<Uri> mImageUris;
    private Context mContext;
    private View.OnClickListener mAddImageClickListener;
    private VHolderCreatePostImage.RemoveCreatePostImage mRemoveImageClickListener;
    private ImageListDataChange mImageListDataChange;

    public AdapterCreatePostImageList(Context context, View.OnClickListener onAddClickListener, VHolderCreatePostImage.RemoveCreatePostImage onRemoveClickListener,ImageListDataChange onImageListDataChange) {
        mImageUris = new ArrayList<>();
        mContext = context;
        mAddImageClickListener = onAddClickListener;
        mRemoveImageClickListener = onRemoveClickListener;
        mImageListDataChange = onImageListDataChange;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mImageUris.size()){
            return IMAGE;
        }else {
            return ADD_IMAGE;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ADD_IMAGE){
            ((VHolderCreatePostAddImage)holder).onBindViewHolder(mAddImageClickListener);
        }else {
            ((VHolderCreatePostImage)holder).onBindViewHolder(mImageUris.get(position).toString(), mRemoveImageClickListener, position);
        }
    }

    @Override
    public int getItemCount() {
        return mImageUris.size() >= MAX_IMAGE_SIZE ? MAX_IMAGE_SIZE : mImageUris.size() + 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case IMAGE:
                VHolderCreatePostImage vHolderCreatePostImage = new VHolderCreatePostImage(
                        LayoutInflater.from(mContext).inflate(VHolderCreatePostImage.LAYOUT_ID, parent, false));
                return vHolderCreatePostImage;
            case ADD_IMAGE:
                VHolderCreatePostAddImage vHolderCreatePostAddImage = new VHolderCreatePostAddImage(
                        LayoutInflater.from(mContext).inflate(VHolderCreatePostAddImage.LAYOUT_ID, parent, false));
                return vHolderCreatePostAddImage;
        }
        return null;
    }

    public void setImageUris(List<Uri> imageUris) {
        mImageUris =imageUris;
        notifyDataSetChanged();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        if (fromPosition == mImageUris.size() || toPosition == mImageUris.size() ){
            return;
        }

        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mImageUris, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mImageUris, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        mImageListDataChange.change(mImageUris);
    }

    @Override
    public void onSwiped(int position) {
        mImageUris.remove(position);
        notifyItemRemoved(position);
    }

    /** 图片集合发生变化通知接口 */
    public interface ImageListDataChange{
        void change(List<Uri> imageUris);
    }
}
