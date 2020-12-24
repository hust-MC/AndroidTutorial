package com.emercy.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

public class RoundCornerDrawable extends Drawable {

    private Paint mPaint;
    private Bitmap mBitmap;

    public RoundCornerDrawable(Bitmap bitmap) {
        this.mBitmap = bitmap;
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);//着色器 水平和竖直都需要填充满
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);
    }

    @Override
    public void draw(Canvas canvas) {
        //RectF:圆角矩形区域
        canvas.drawRoundRect(new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight()), 100, 100, mPaint);//rx：x方向上的圆角半径; ry：y方向上的圆角半径。
    }

    @Override
    public void setAlpha(int i) {
        mPaint.setAlpha(i);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT; //设置系统默认，让drawable支持和窗口一样的透明度
    }

    //还需要从重写以下2个方法，返回drawable实际宽高
    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }
}
