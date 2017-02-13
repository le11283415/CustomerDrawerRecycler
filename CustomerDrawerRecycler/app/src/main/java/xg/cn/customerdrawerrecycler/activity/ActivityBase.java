package xg.cn.customerdrawerrecycler.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import xg.cn.customerdrawerrecycler.R;

/**
 * Created by xxx on 2016/10/14
 */
public class ActivityBase extends AppCompatActivity {

    private FrameLayout mFLContent;
    private LinearLayout mLLayout;
    private Toolbar mToolbar;
    private View mTitleBar;
    public TextView mTitleName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        mFLContent = (FrameLayout) mLLayout.findViewById(R.id.fl_content);

        super.setContentView(mLLayout);
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        mFLContent.addView(view,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        initNormalTitlebar();
        setCustomTitleBar(mTitleBar);
    }

    /**
     * 初始化标准的标题栏
     */
    private void initNormalTitlebar() {
        mTitleBar = getLayoutInflater().inflate(R.layout.view_toolbar_title, null);//通用的一种标题栏布局，包含一个返回键、标题、最右两个按钮。
        mTitleName = (TextView) mTitleBar.findViewById(R.id.tv_title);
    }

    public void setTitleName(String name) {
         mTitleName.setText(name);
    }


    /**
     * 添加自定义标题栏
     */
    public void setCustomTitleBar(View titlebarLayout) {
        mToolbar.removeAllViews();  // 先清除掉之前可能加入的

        Toolbar.LayoutParams lp = new Toolbar.LayoutParams(
                Toolbar.LayoutParams.MATCH_PARENT,
                Toolbar.LayoutParams.MATCH_PARENT);//传入的布局，覆盖整个Toolbar
        mToolbar.addView(titlebarLayout, lp);
    }
}