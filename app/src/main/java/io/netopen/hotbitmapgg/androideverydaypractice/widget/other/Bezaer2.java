package io.netopen.hotbitmapgg.androideverydaypractice.widget.other;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by hcc on 2016/7/21.
 */
public class Bezaer2 extends View implements View.OnClickListener
{

    private Paint mPaint;

    private Paint mAuxiliaryPaint;

    private Paint mAuxiliaryTextPaint;

    private float mAuxiliaryOneX;

    private float mAuxiliaryOneY;

    private float mAuxiliaryTwoX;

    private float mAuxiliaryTwoY;

    private float mStartPointX;

    private float mStartPointY;

    private float mEndPointX;

    private float mEndPointY;

    private int width;

    private int height;

    private Path mPath = new Path();

    private ValueAnimator valueAnimator;

    public Bezaer2(Context context)
    {
        this(context, null);
    }

    public Bezaer2(Context context, AttributeSet attrs)
    {
        this(context, null, 0);
    }

    public Bezaer2(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(7);

        mAuxiliaryPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAuxiliaryPaint.setStyle(Paint.Style.STROKE);
        mAuxiliaryPaint.setStrokeWidth(2);

        mAuxiliaryTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAuxiliaryTextPaint.setStyle(Paint.Style.STROKE);
        mAuxiliaryTextPaint.setTextSize(20);

        setOnClickListener(this);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        //确定各点的位置
        mStartPointX = w / 4;
        mStartPointY = h - 200;

        mEndPointX = w / 4 * 3;
        mEndPointY = h - 200;

        mAuxiliaryOneX = mStartPointX;
        mAuxiliaryOneY = mStartPointY;

        mAuxiliaryTwoX = mEndPointX;
        mAuxiliaryTwoY = mEndPointY;

        width = w;
        height = h;

        //设置动画
        valueAnimator = ValueAnimator.ofFloat(mStartPointY, h);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                mAuxiliaryOneY = (float) valueAnimator.getAnimatedValue();
                mAuxiliaryTwoY = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });


    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(width / 2, height / 2);

        mPath.reset();
        mPath.moveTo(mStartPointX, mStartPointY);

        //绘制辅助点
        canvas.drawPoint(mAuxiliaryOneX, mAuxiliaryOneY, mAuxiliaryPaint);
        //绘制文字
        canvas.drawText("辅助点1", mAuxiliaryOneX, mAuxiliaryOneY, mAuxiliaryTextPaint);
        canvas.drawText("辅助点2", mAuxiliaryTwoX, mAuxiliaryTwoY, mAuxiliaryTextPaint);
        canvas.drawText("起始点", mStartPointX, mStartPointY, mAuxiliaryTextPaint);
        canvas.drawText("终止点", mEndPointX, mEndPointY, mAuxiliaryTextPaint);
        //绘制辅助线
        canvas.drawLine(mStartPointX, mStartPointY, mAuxiliaryOneX, mAuxiliaryOneY, mAuxiliaryPaint);
        canvas.drawLine(mEndPointX, mEndPointY, mAuxiliaryTwoX, mAuxiliaryTwoY, mAuxiliaryPaint);
        canvas.drawLine(mAuxiliaryOneX, mAuxiliaryOneY, mAuxiliaryTwoX, mAuxiliaryTwoY, mAuxiliaryPaint);
        //三阶贝塞尔曲线
        mPath.cubicTo(mAuxiliaryOneX, mAuxiliaryOneY, mAuxiliaryTwoX, mAuxiliaryTwoY, mEndPointX, mEndPointY);
        canvas.drawPath(mPath, mPaint);

        canvas.restore();

    }

    @Override
    public void onClick(View view)
    {
        valueAnimator.start();
    }
}
