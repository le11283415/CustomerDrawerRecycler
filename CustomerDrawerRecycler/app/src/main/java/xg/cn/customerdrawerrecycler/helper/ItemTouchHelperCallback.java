package xg.cn.customerdrawerrecycler.helper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import xg.cn.customerdrawerrecycler.interfaces.IUISampleDrag;

/**
 * Created by yefeng on 2017/2/13.
 * Modified by xxx
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private IUISampleDrag mIUISampleDrag;

    public ItemTouchHelperCallback(IUISampleDrag iuiSampleDrag){
        this.mIUISampleDrag = iuiSampleDrag;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mIUISampleDrag.onTtemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mIUISampleDrag.onItemDismiss(viewHolder.getAdapterPosition());
    }

}
