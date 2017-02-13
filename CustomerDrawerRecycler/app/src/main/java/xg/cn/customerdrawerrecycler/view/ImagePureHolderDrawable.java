package xg.cn.customerdrawerrecycler.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

public class ImagePureHolderDrawable extends Drawable {

    private static final int BG_COLOR = Color.parseColor("#eeeeee");

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(BG_COLOR);
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
