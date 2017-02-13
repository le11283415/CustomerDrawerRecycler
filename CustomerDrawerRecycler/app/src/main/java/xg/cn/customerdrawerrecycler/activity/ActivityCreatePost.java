package xg.cn.customerdrawerrecycler.activity;

import java.util.ArrayList;
import java.util.List;

import com.neteaseyx.image.ugallery.UGallery;
import com.neteaseyx.image.ugallery.utils.GridSpacingDecoration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import xg.cn.customerdrawerrecycler.R;
import xg.cn.customerdrawerrecycler.adapter.AdapterCreatePostImageList;
import xg.cn.customerdrawerrecycler.helper.MyItemTouchCallback;
import xg.cn.customerdrawerrecycler.helper.OnRecyclerItemClickListener;
import xg.cn.customerdrawerrecycler.interfaces.IUICreatePost;
import xg.cn.customerdrawerrecycler.presenter.PresenterCreateImagePost;
import xg.cn.customerdrawerrecycler.view.BounceScrollView;
import xg.cn.customerdrawerrecycler.viewholder.VHolderCreatePostImage;

/**
 * 发送图片
 * @author yuhuibin & hzxulu
 */

public class ActivityCreatePost extends ActivityBase implements IUICreatePost{
    public static final int     LAYOUT_ID    = R.layout.activity_create_post;
    public static final int     SPAN_COUNT   = 3;

    private final String TAG = "ActivityCreatePost";

    private PresenterCreateImagePost mPresenterCreateImagePost;

    private List<Uri> mUri;

    private RecyclerView mRecyclerView;
    private AdapterCreatePostImageList mAdapter;
    private Context mContext;
    private List<String> mPicList     = new ArrayList<>();// 图片列表,MIME为image/jpeg
    private List<String> mPicDescList = new ArrayList<>();// 图片对应描述,数量需与picList一致,未填写描述使用null


    private boolean            mIsFirstOpenUGallery;
    private BounceScrollView mScrollView;
    private ItemTouchHelper mItemtouchhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ActivityCreatePost.LAYOUT_ID);
        setTitleName("图片");
        UGallery.selectMultipleImage(this);
        mPresenterCreateImagePost = new PresenterCreateImagePost(this);
        mContext = this;
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case UGallery.SELECT_MUTIL_IMAGE:
                    List<Uri> uris = UGallery.getMultipleImage(data);
                    mUri = uris;
                    mPresenterCreateImagePost.addImage(uris);
                  //  UGallery.cropImage(ActivityCreatePost.this,mUri.get(0),16,9);
                    break;
                case UGallery.CROP_IMAGE:
                    System.out.println(UGallery.getSingleImage(data)+"");
                    break;
            }
        } else {
            if (mIsFirstOpenUGallery) {
                finish();
            }
        }
    }
    @Override
    public void onRefreshImageData(List<Uri> image) {
        if (image.size() == 0) {
            mPicList.clear();
        } else {
            mPicList.clear();
            for (int i = 0; i < image.size(); i++) {
                mPicList.add(i, image.get(i).getPath());
            }
        }
        mAdapter.setImageUris(image);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public Activity getActivity() {
        return ActivityCreatePost.this;
    }

    @Override
    public List<String> getPicList() {
        return mPicList;
    }

    @Override
    public List<String> getPicDescList() {
        return mPicDescList;
    }

    private void initView() {
        mScrollView = (BounceScrollView) findViewById(R.id.scroll_view);
        initImageView();
        mIsFirstOpenUGallery = true;

    }

    private void initImageView() {

        View viewById = findViewById(R.id.btn);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //mUri.get(0)
          //   UGallery.cropImage(ActivityCreatePost.this,mUri.get(0),16,9);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mRecyclerView.addItemDecoration(new GridSpacingDecoration(9));

        View.OnClickListener onAddClickListener = new AddImageListener();
        VHolderCreatePostImage.RemoveCreatePostImage onRemoveClickListener1 = new RemoveImageListener();
        ImageListDataChange onImageListDataChange = new ImageListDataChange();

        mAdapter = new AdapterCreatePostImageList(this, onAddClickListener, onRemoveClickListener1,onImageListDataChange);

        mRecyclerView.setAdapter(mAdapter);

        mItemtouchhelper = new ItemTouchHelper(new MyItemTouchCallback(mAdapter));
        mItemtouchhelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                if (vh.getLayoutPosition() != mPicList.size()) {
                    mItemtouchhelper.startDrag(vh);
                }else {
                    Toast.makeText(ActivityCreatePost.this,"我被封印了,不能动了",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
            }
        });

        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private class AddImageListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mPresenterCreateImagePost.openGallery();
            mIsFirstOpenUGallery = false;
        }
    }

    private class RemoveImageListener implements VHolderCreatePostImage.RemoveCreatePostImage {
        @Override
        public void remove(int position) {
            mPresenterCreateImagePost.removeImage(position);
        }
    }

    private class ImageListDataChange implements AdapterCreatePostImageList.ImageListDataChange {

        @Override
        public void change(List<Uri> imageUris) {
            mPicList.clear();
            for (int i = 0; i < imageUris.size(); i++) {
            mPicList.add(i, imageUris.get(i).getPath());
            }
        }
    }
}
