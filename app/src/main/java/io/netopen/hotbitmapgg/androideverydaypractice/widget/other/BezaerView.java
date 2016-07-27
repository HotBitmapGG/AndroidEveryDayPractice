package io.netopen.hotbitmapgg.androideverydaypractice.widget.other;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.text.NumberFormat;

/**
 * Created by 11 on 2016/6/28.
 * 画一个水球浮动的效果
 */
public class BezaerView extends View
{

    private Paint mPaint1;

    private Paint mPaint2;

    private int mViewWidth;

    private int mViewHeight;

    private int mWidth;

    private int mHeight;

    private float r;

    private RectF rectF;

    private Path mPath;

    private PointF mPointF;

    //百分比比例
    private float percent = 0f;

    private float rArc;

    private float x;

    private int mWaveLength = 1000;

    private int mWaveCount;

    private int mCenterY;

    private int mOffset;

    private Paint mCirclePaint;

    private int max = 100;

    private int progress;


    //#3BDD76 水球实体颜色  #73E091 外层圆进度颜色   #95FBB1最外层圆形进度颜色  #3ED07B 字体颜色  #D8E1E0 外层圆环灰色底色

    public BezaerView(Context context)
    {
        this(context, null);
    }

    public BezaerView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public BezaerView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {

        //初始化画笔
        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setDither(true);
        mPaint1.setStrokeWidth(3);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setColor(Color.parseColor("#3ED07B"));
        mPaint1.setTextSize(100);


        mPaint2 = new Paint();
        mPaint2.setColor(Color.parseColor("#3BDD76"));
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setStrokeWidth(8);
        mPaint2.setAntiAlias(true);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.parseColor("#D8E1E0"));
        mCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        mCirclePaint.setStrokeWidth(6);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setAntiAlias(true);


        //初始化路径
        mPath = new Path();
        mPointF = new PointF(0, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        mViewWidth = w;
        mViewHeight = h;

        mWidth = mViewWidth - getPaddingLeft() - getPaddingRight();
        mHeight = mViewHeight - getPaddingTop() - getPaddingBottom();

        r = Math.min(mWidth, mHeight) * 0.3f;

        rectF = new RectF(-r, -r, r, r);

        mWaveCount = (int) Math.round(mViewWidth / mWaveLength + 1.5);
        mCenterY = mViewHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //移动画板到中心位置
        canvas.translate(mViewWidth / 2, mViewHeight / 2);
        //画外层圆
        canvas.drawCircle(0, 0, r, mCirclePaint);
        rArc = r * (1 - 2 * percent);
        //计算角度
        double angle = Math.acos((double) rArc / r);
        //计算x轴的坐标
        x = r * (float) Math.sin(angle);
        //添加圆弧到path中
        mPath.addArc(rectF, 90 - (float) Math.toDegrees(angle), (float) Math.toDegrees(angle) * 2);
        //移动路径
        mPath.moveTo(-x, rArc);
        mPath.rQuadTo(x / 2 - percent, -r / 8, x - percent, 0);
        mPath.rQuadTo(x / 2 - percent, r / 8, x - percent, 0);
        canvas.drawPath(mPath, mPaint2);
        mPath.rewind();

       // mCirclePaint.setColor(Color.parseColor("#73E091"));
       // canvas.drawArc(rectF, -90, 360 * progress / max, false, mCirclePaint);


        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumIntegerDigits(1);

        drawText(new String[]{percentInstance.format(percent)}, canvas, Paint.Align.CENTER);
    }

    /**
     * 绘制文本方法
     *
     * @param strings
     * @param canvas
     * @param center
     */
    private void drawText(String[] strings, Canvas canvas, Paint.Align center)
    {
        //设置字体居中
        mPaint1.setTextAlign(center);
        //获取字体在屏幕中高度位置
        Paint.FontMetrics fontMetrics = mPaint1.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        int length = strings.length;
        float total = (length - 1) * (-top + bottom) + (-fontMetrics.ascent + fontMetrics.descent);
        float offset = total / 2 - bottom;
        //画文本
        for (int i = 0; i < length; i++)
        {
            float yAxis = -(length - i - i) * (-top + bottom) + offset;
            canvas.drawText(strings[i], mPointF.x, mPointF.y + yAxis, mPaint1);

        }


    }


    public void startCircleAnim()
    {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {


            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                progress = (int) valueAnimator.getAnimatedValue();
                postInvalidateDelayed(2000);
            }
        });
        valueAnimator.start();
    }


    public void startAnim()
    {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 1000);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                mOffset = (int) valueAnimator.getAnimatedValue();
                postInvalidateDelayed(2000);
            }
        });
        valueAnimator.start();
    }

    public void setPercent(float percent)
    {
        this.percent = percent;
    }
}
