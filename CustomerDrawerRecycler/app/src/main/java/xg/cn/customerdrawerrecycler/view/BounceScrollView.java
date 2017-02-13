package xg.cn.customerdrawerrecycler.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ScrollView;

/**
 * 具有反弹效果的scrollview
 *
 * @author hzxulu
 * @date 5/5/2016.
 */
public class BounceScrollView extends ScrollView {
    private View inner;// 包含的View

    private float y;// 点击时y坐标

    private Rect normal = new Rect();// 用于记录inner布局的位置，方便会弹时准确归位

    private boolean isCount = false;// 是否开始计算
    private InputMethodManager imm;

    public BounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /***
     * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之后. 即使子类覆盖了 onFinishInflate
     * 方法，也应该调用父类的方法，使该方法得以执行.
     */
    @Override
    protected void onFinishInflate() {//当View中所有的子控件均被映射成xml后触发
        if (getChildCount() > 0) {
            inner = getChildAt(0);
        }
        super.onFinishInflate();
    }

    /***
     * 监听touch
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (inner != null) {
            commOnTouchEvent(ev);
        }

        return super.onTouchEvent(ev);
    }

    /***
     * 触摸事件
     *
     * @param ev 事件
     */
    private void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                // 手指松开，判断动画是否开启，回归原位
                if (isNeedAnimation()) {
                    animation();
                    isCount = false;
                }
                break;
            /***
             * 排除出第一次移动计算，因为第一次无法得知y坐标， 在MotionEvent.ACTION_DOWN中获取不到，
             * 因为此时是 BounceScrollView 的touch事件传递到到了 ViewLabelSelector 上面.所以从第二次计算开始.
             * 然而我们也要进行初始化，就是第一次移动的时候让滑动距离归0. 之后记录准确了就正常执行.
             */
            case MotionEvent.ACTION_MOVE:
                if(imm != null){
                    //隐藏软键盘
                    imm.hideSoftInputFromWindow(inner.getWindowToken(), 0);
                }

                final float preY = y;// 按下时的y坐标
                float nowY = ev.getY();// 时时y坐标
                int deltaY = (int) (preY - nowY);// 滑动距离
                if (!isCount) {
                    deltaY = 0; // 在这里要归0.
                }
                y = nowY;
                // 当滚动到最上或者最下时就不会再滚动，这时移动布局
                if (isNeedMove()) {
                    if (normal.isEmpty()) {
                        normal.set(inner.getLeft(), inner.getTop(),
                                inner.getRight(), inner.getBottom());// 保存正常的布局位置
                    }
                    inner.layout(inner.getLeft(), inner.getTop() - deltaY / 2,
                            inner.getRight(), inner.getBottom() - deltaY / 2); // 移动布局
                }
                isCount = true;
                break;

            default:
                break;
        }
    }

    /***
     * 回归原位动画
     */
    private void animation() {
        // 开启移动动画
        TranslateAnimation animation = new TranslateAnimation(0, 0, inner.getTop(),
                normal.top);
        animation.setDuration(200);
        inner.startAnimation(animation);
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);// 回归原位
        normal.setEmpty();//回归原位 置空
    }


    /**
     * 是否需要开启动画
     *
     * @return boolean值
     */
    private boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    /***
     * 是否需要移动布局
     * inner.getMeasuredHeight():获取的是控件的总高度
     * <p/>
     * getHeight()：获取的是屏幕的高度
     *
     * @return boolean值
     */
    private boolean isNeedMove() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        // 0是顶部，后面那个是底部
        return scrollY == 0 || scrollY == offset;
    }
    /**
     * 强制启用回弹的效果
     * @param direction
     * @return
     */
    @Override
    public boolean canScrollVertically(int direction) {
        return true;
    }

    public void setInputMethodManager(InputMethodManager inputMethodManager){
        imm = inputMethodManager;
    }

}
