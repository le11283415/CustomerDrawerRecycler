package xg.cn.customerdrawerrecycler.presenter;

import java.util.ArrayList;
import java.util.List;
import com.neteaseyx.image.ugallery.UGallery;

import android.net.Uri;
import xg.cn.customerdrawerrecycler.interfaces.IUICreatePost;

/**
 * 发布图像
 * @author yuhuibin
 * @date 2016-06-04
 */

public class PresenterCreateImagePost {

    private List<Uri> mImageUris = new ArrayList<>();
    private IUICreatePost mIUICreatePost;

    public PresenterCreateImagePost(IUICreatePost IUICreatePost) {
        this.mIUICreatePost = IUICreatePost;
        mImageUris = new ArrayList<>();
    }


    public void openGallery(){
        if (mImageUris.size() == 0){
            UGallery.selectMultipleImage(mIUICreatePost.getActivity());
        }else {
            UGallery.selectMultipleImage(mIUICreatePost.getActivity(), mImageUris);
        }
    }

    public void addImage(List<Uri> imageUris){
        mImageUris = imageUris;
        mIUICreatePost.onRefreshImageData(mImageUris);
    }

    public void removeImage(int position){
        mImageUris.remove(position);
        mIUICreatePost.onRefreshImageData(mImageUris);
    }


//    @Override
//    public void cancel() {
//        mIUICreatePost.onCancelToast(R.string.post_image);
//    }

}
