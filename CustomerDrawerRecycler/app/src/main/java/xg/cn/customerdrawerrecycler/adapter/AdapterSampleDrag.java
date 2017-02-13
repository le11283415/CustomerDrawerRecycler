package xg.cn.customerdrawerrecycler.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import xg.cn.customerdrawerrecycler.R;
import xg.cn.customerdrawerrecycler.interfaces.IUISampleDrag;
import xg.cn.customerdrawerrecycler.viewholder.VHolderSampleDrag;

/**
 * Created by yefeng on 2017/2/13.
 * Modified by xxx
 */
public class AdapterSampleDrag extends RecyclerView.Adapter implements IUISampleDrag {

    private List<String> mItems;

    public AdapterSampleDrag() {
        mItems = new ArrayList<>();
        for (int i = 0; i< 100 ; i++) {
            mItems.add("item" + i);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sample_drag_item, parent, false);
        VHolderSampleDrag itemViewHolder = new VHolderSampleDrag(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if(holder instanceof VHolderSampleDrag)
        ((VHolderSampleDrag)holder).bindView(mItems.get(position));
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }


    @Override
    public void onTtemMove(int fromPosition, int toPosition) {
        String prev = mItems.remove(fromPosition);
        mItems.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        this.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        this.notifyItemRemoved(position);
    }
}
