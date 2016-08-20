package io.netopen.hotbitmapgg.androideverydaypractice.widget.other;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by hcc on 2016/8/15.
 * <p/>
 * Slack旋转动画效果
 */
public class SlackAnimView extends View
{

    //静止状态
    private final int STATUS_STILL = 0;

    //加载状态
    private final int STATUS_LOADING = 1;

    //线条最大长度
    private final int MAX_LINE_LENGTH = dp2px(getContext(), 120);

    //线条最短长度
    private final int MIN_LINE_LENGTH = dp2px(getContext(), 40);

    //最大间隔时长
    private final int MAX_DURATION = 3000;

    //最小间隔时长
    private final int MIN_DURATION = 500;

    //画笔对象
    private Paint mPaint;

    //4条线条的颜色值
    private int[] mColors = new int[]{
            Color.parseColor("#B07ECBDA"),
            Color.parseColor("#B0E6A92C"),
            Color.parseColor("#B0D6014D"),
            Color.parseColor("#B05ABA94")
    };

    //view的宽高
    private int mWidth, mHeight;

    //动画间隔时长
    private int mDuration = MIN_DURATION;

    //线条总长度
    private int mEntireLineLength = MIN_LINE_LENGTH;

    //圆半径
    private int mCircleRadius;

    //所有动画
    private List<Animator> mAnimList = new ArrayList<>();

    //Canvas起始旋转角度
    private final int CANVAS_ROTATE_ANGLE = 60;

    //动画当前状态
    private int mStatus = STATUS_STILL;

    //Canvas旋转角度
    private int mCanvasAngle;

    //线条长度
    private float mLineLength;

    //半圆Y轴位置
    private float mCircleY;

    //第几部动画
    private int mStep;

    public SlackAnimView(Context context)
    {

        this(context, null);
    }

    public SlackAnimView(Context context, AttributeSet attrs)
    {

        this(context, attrs, 0);
    }

    public SlackAnimView(Context context, AttributeSet attrs, int defStyleAttr)
    {

        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColors[0]);
    }

    /**
     * 初始化数据
     */
    public void initData()
    {
        //canvas旋转角度
        mCanvasAngle = CANVAS_ROTATE_ANGLE;
        //线的长度
        mLineLength = mEntireLineLength;
        //圆的半径
        mCircleRadius = mEntireLineLength / 5;
        //设置画笔宽度
        mPaint.setStrokeWidth(mCircleRadius * 2);
        //第一个动画效果
        mStep = 0;
    }


    @Override
    protected void onDraw(Canvas canvas)
    {

        super.onDraw(canvas);
        switch (mStep % 4)
        {
            case 0:
                for (int i = 0; i < mColors.length; i++)
                {
                    mPaint.setColor(mColors[i]);
                    drawCRLC(canvas,
                            mWidth / 2 - mEntireLineLength / 2.2f,
                            mHeight / 2 - mEntireLineLength,
                            mWidth / 2 - mEntireLineLength / 2.2f,
                            mHeight / 2 + mEntireLineLength,
                            mPaint, mCanvasAngle + i * 90);
                }
                break;

            case 1:
                for (int i = 0; i < mColors.length; i++)
                {
                    mPaint.setColor(mColors[i]);
                    drawCR(canvas,
                            mWidth / 2 - mEntireLineLength / 2.2f,
                            mHeight / 2 + mEntireLineLength,
                            mPaint, mCanvasAngle + i * 90);
                }
                break;

            case 2:
                for (int i = 0; i < mColors.length; i++)
                {
                    mPaint.setColor(mColors[i]);
                    drawCRCC(canvas,
                            mWidth / 2 - mEntireLineLength / 2.2f,
                            mHeight / 2 + mCircleY, mPaint,
                            mCanvasAngle + i * 90);
                }

                break;

            case 3:
                for (int i = 0; i < mColors.length; i++)
                {
                    mPaint.setColor(mColors[i]);
                    drawLC(canvas,
                            mWidth / 2 - mEntireLineLength / 2.2f,
                            mHeight / 2 + mEntireLineLength,
                            mWidth / 2 + mEntireLineLength / 2.2f,
                            mHeight / 2 + mLineLength,
                            mPaint, mCanvasAngle + i * 90);
                }
                break;
        }
    }

    /**
     * 绘制画布旋转
     *
     * @param canvas
     * @param x
     * @param y
     * @param paint
     * @param rotate
     */
    public void drawCR(Canvas canvas, float x, float y, @NonNull Paint paint, int rotate)
    {

        canvas.rotate(rotate, mWidth / 2, mHeight / 2);
        canvas.drawCircle(x, y, mCircleRadius, paint);
        canvas.rotate(-rotate, mWidth / 2, mHeight / 2);
    }


    /**
     * 绘制圆和线条
     */
    public void drawCRLC(Canvas canvas,
                         float startX,
                         float startY,
                         float stopX,
                         float stopY,
                         @NonNull Paint paint,
                         int rotate)
    {

        canvas.rotate(rotate, mWidth / 2, mHeight / 2);
        canvas.drawArc(new RectF(startX - mCircleRadius,
                        startY - mCircleRadius,
                        startX + mCircleRadius,
                        startY + mCircleRadius),
                180, 180, true, paint);

        canvas.drawLine(startX, startY, stopX, stopY, mPaint);
        canvas.drawArc(new RectF(stopX - mCircleRadius,
                        stopY - mCircleRadius,
                        stopX + mCircleRadius,
                        stopY + mCircleRadius),
                0, 180, true, paint);

        canvas.rotate(-rotate, mWidth / 2, mHeight / 2);
    }


    /**
     * 绘制圆形变换
     *
     * @param canvas
     * @param startX
     * @param startY
     * @param paint
     * @param rotate
     */
    public void drawCRCC(Canvas canvas, float startX, float startY, @NonNull Paint paint, int rotate)
    {

        canvas.rotate(rotate, mWidth / 2, mHeight / 2);
        canvas.drawCircle(startX, startY, mCircleRadius, paint);
        canvas.rotate(-rotate, mWidth / 2, mHeight / 2);
    }

    /**
     * 绘制线条变换
     *
     * @param canvas
     * @param startX
     * @param startY
     * @param stopX
     * @param stopY
     * @param paint
     * @param rotate
     */

    public void drawLC(Canvas canvas, float startX, float startY, float stopX, float stopY, @NonNull Paint paint, int rotate)
    {

        canvas.rotate(rotate, mWidth / 2, mHeight / 2);
        canvas.drawArc(new RectF(
                        startX - mCircleRadius,
                        startY - mCircleRadius,
                        startX + mCircleRadius,
                        startY + mCircleRadius),
                0, 180, true, paint);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
        canvas.drawArc(new RectF(
                        stopX - mCircleRadius,
                        stopY - mCircleRadius,
                        stopX + mCircleRadius,
                        stopY + mCircleRadius),
                180, 180, true, paint);
        canvas.rotate(-rotate, mWidth / 2, mHeight / 2);
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
        mWidth = w;
        mHeight = h;
        initData();
    }


    /**
     * 画布旋转 线条变换动画
     * Canvas Rotate Line Change
     */
    public void startCRLCAnim()
    {

        Collection<Animator> animators = new ArrayList<>();

        //画布旋转动画
        ValueAnimator canvasRotateAnim = ValueAnimator.ofInt(CANVAS_ROTATE_ANGLE + 0, CANVAS_ROTATE_ANGLE + 360);
        canvasRotateAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                //获取画布旋转的角度
                mCanvasAngle = (int) valueAnimator.getAnimatedValue();
            }
        });

        animators.add(canvasRotateAnim);


        //线条变换动画
        ValueAnimator lineChangeAnim = ValueAnimator.ofFloat(mEntireLineLength, -mEntireLineLength);
        lineChangeAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {

                mLineLength = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        animators.add(lineChangeAnim);

        //设置动画组合
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(mDuration);
        mAnimatorSet.playTogether(animators);
        mAnimatorSet.setInterpolator(new LinearInterpolator());
        mAnimatorSet.addListener(new AnimatorListenerAdapter()
        {

            @Override
            public void onAnimationEnd(Animator animation)
            {

                //画布旋转+线条动画动画结束
                if (mStatus == STATUS_LOADING)
                {
                    //正在执行加载动画
                    mStep++;
                    startCRAnim();
                }
            }
        });

        mAnimatorSet.start();

        //添加到动画集合中
        mAnimList.add(mAnimatorSet);
    }

    /**
     * 画布旋转动画
     * Canvas Rotate
     */
    private void startCRAnim()
    {

        ValueAnimator canvasRotateAnim = ValueAnimator.ofInt(mCanvasAngle, mCanvasAngle + 180);
        canvasRotateAnim.setDuration(mDuration / 2);
        canvasRotateAnim.setInterpolator(new LinearInterpolator());
        canvasRotateAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {

                mCanvasAngle = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        canvasRotateAnim.addListener(new AnimatorListenerAdapter()
        {

            @Override
            public void onAnimationEnd(Animator animation)
            {

                super.onAnimationEnd(animation);
                //画布旋转动画结束
                if (mStatus == STATUS_LOADING)
                {
                    mStep++;
                    startCRCCAnim();
                }
            }
        });

        canvasRotateAnim.start();

        mAnimList.add(canvasRotateAnim);
    }


    /**
     * 画布旋转 圆形变化动画
     * <p/>
     * canvas rotate circle change
     */
    private void startCRCCAnim()
    {

        Collection<Animator> animators = new ArrayList<>();

        ValueAnimator canvasRotateAnim = ValueAnimator.ofInt(mCanvasAngle, mCanvasAngle + 90, mCanvasAngle + 180);
        canvasRotateAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {

                mCanvasAngle = (int) valueAnimator.getAnimatedValue();
            }
        });

        animators.add(canvasRotateAnim);


        ValueAnimator circleAnim = ValueAnimator.ofFloat(mEntireLineLength, mEntireLineLength / 4, mEntireLineLength);
        circleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {

                mCircleY = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        animators.add(circleAnim);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(mDuration);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(animators);
        animatorSet.addListener(new AnimatorListenerAdapter()
        {

            @Override
            public void onAnimationEnd(Animator animation)
            {

                if (mStatus == STATUS_LOADING)
                {
                    //画布旋转圆形变换动画结束
                    mStep++;
                    startLCAnim();
                }
            }
        });
        animatorSet.start();

        mAnimList.add(animatorSet);
    }


    /**
     * 线条变换动画
     * line change
     */
    private void startLCAnim()
    {

        ValueAnimator lineChangeAnim = ValueAnimator.ofFloat(mEntireLineLength, -mEntireLineLength);
        lineChangeAnim.setDuration(mDuration);
        lineChangeAnim.setInterpolator(new LinearInterpolator());
        lineChangeAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {

                mLineLength = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        lineChangeAnim.addListener(new AnimatorListenerAdapter()
        {

            @Override
            public void onAnimationEnd(Animator animation)
            {

                //线条变换动画结束
                if (mStatus == STATUS_LOADING)
                {
                    mStep++;
                    startCRLCAnim();
                }
            }
        });

        lineChangeAnim.start();

        mAnimList.add(lineChangeAnim);
    }


    public void setLineLength(float scale)
    {

        mEntireLineLength = (int) (scale * (MAX_LINE_LENGTH - MIN_LINE_LENGTH)) + MIN_LINE_LENGTH;
        reset();
    }

    public void setDuration(float scale)
    {

        mDuration = (int) (scale * (MAX_DURATION - MIN_DURATION)) + MIN_DURATION;
        reset();
    }

    public void start()
    {

        if (mStatus == STATUS_STILL)
        {
            mAnimList.clear();
            mStatus = STATUS_LOADING;
            startCRLCAnim();
        }
    }

    public void reset()
    {

        if (mStatus == STATUS_LOADING)
        {
            mStatus = STATUS_STILL;
            for (Animator anim : mAnimList)
            {
                anim.cancel();
            }
        }
        initData();
        invalidate();
    }

    /**
     * dp转px
     *
     * @param context
     * @param dp
     * @return
     */
    public int dp2px(Context context, int dp)
    {

        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }
}
