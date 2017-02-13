package xg.cn.customerdrawerrecycler.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import xg.cn.customerdrawerrecycler.R;
import xg.cn.customerdrawerrecycler.adapter.AdapterSampleDrag;
import xg.cn.customerdrawerrecycler.helper.ItemTouchHelperCallback;

/**
 * Created by yefeng on 2017/2/13.
 * Modified by xxx
 */

public class SampleDragActivity extends Activity {

    private AdapterSampleDrag mAdapterSampleDrag;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_drag);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapterSampleDrag = new AdapterSampleDrag();

        mRecyclerView.setAdapter(new AdapterSampleDrag());

        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(mAdapterSampleDrag));
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

    }
}
