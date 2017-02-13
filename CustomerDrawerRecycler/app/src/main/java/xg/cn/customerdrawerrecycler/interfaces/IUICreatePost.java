package xg.cn.customerdrawerrecycler.interfaces;

import java.util.List;

import android.app.Activity;
import android.net.Uri;

/**
 * @author yuhuibin
 * @date 2016-06-04
 */

public interface IUICreatePost {


    void onRefreshImageData(List<Uri> image);


    /**
     * 获取Activity
     *
     * @return
     */
    Activity getActivity();


    /**
     * 获取图片列表
     * @return
     */
    List<String> getPicList();

    /**
     * 获取图片列表描述信息
     * @return
     */
    List<String> getPicDescList();

}
