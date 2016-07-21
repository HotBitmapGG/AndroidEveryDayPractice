package io.netopen.hotbitmapgg.androideverydaypractice.widget.other;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by hcc on 2016/7/21.
 */
public class Bezaer1 extends View
{

    private Path mPath;

    private Paint mPaint;

    private float mX;

    private float mY;

    private float offset = ViewConfiguration.get(getContext()).getScaledTouchSlop();

    public Bezaer1(Context context)
    {
        this(context, null);
    }

    public Bezaer1(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public Bezaer1(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        init();

    }

    private void init()
    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                mPaint.reset();
                //获取按下的点
                float x = event.getX();
                float y = event.getY();
                mX = x;
                mY = y;

                mPath.moveTo(x, y);

                break;

            case MotionEvent.ACTION_MOVE:
                //移动时候进行涂鸦
                float x1 = event.getX();
                float y1 = event.getY();
                float preX = mX;
                float preY = mY;
                float dx = Math.abs(x1 - preX);
                float dy = Math.abs(y1 - preY);
                if (dx >= offset || dy >= offset)
                {
                    //贝塞尔曲线的控制点为起点和终点的中间点
                    float cX = (x1 + preX) / 2;
                    float cY = (y1 + preY) / 2;
                    //mPath.quadTo(preX, preY, cX, cY);
                    mPath.lineTo(x1 , y1);
                    mX = x1;
                    mX = y1;

                }


        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}
