package xg.cn.customerdrawerrecycler.interfaces;

/**
 * Created by yefeng on 2017/2/13.
 * Modified by xxx
 */

public interface ItemTouchAdapter {
    void onMove(int fromPosition, int toPosition);
    void onSwiped(int position);
}
