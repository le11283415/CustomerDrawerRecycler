package xg.cn.customerdrawerrecycler.viewholder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import xg.cn.customerdrawerrecycler.R;

/**
 * Created by yefeng on 2017/2/13.
 * Modified by xxx
 */

public class VHolderSampleDrag extends RecyclerView.ViewHolder {

    private final TextView mTv;

    public VHolderSampleDrag(View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.tv);
    }

    public void bindView(String item) {
        mTv.setText(item);
        mTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
        mTv.setTextColor(Color.BLACK);
    }

}
