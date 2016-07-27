package io.netopen.hotbitmapgg.androideverydaypractice.widget.other;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import io.netopen.hotbitmapgg.androideverydaypractice.utils.LogUtil;

/**
 * Created by hcc on 16/7/27 20:25
 * 100332338@qq.com
 * <p/>
 * path练习之搜索动画
 */
public class SearchViewAnim extends View implements View.OnClickListener
{


    /**
     * 定义一组枚举 设置View的几种状态
     */
    public static enum Status
    {
        NONE,
        STARTING,
        SEARCHING,
        ENDING
    }

    private ValueAnimator startAnim;

    private ValueAnimator searchAnim;

    private ValueAnimator endAnim;

    private Paint mPaint;

    private Path path_circle;

    private Path path_search;

    private PathMeasure pathMeasure;

    private int width;

    private int height;

    private float animatedValue;

    private ValueAnimator.AnimatorUpdateListener animatorUpdateListener;

    private Animator.AnimatorListener animatorListener;

    private Handler mHandler;

    private Status mCurrentStatus = Status.NONE;

    private boolean isOver = false;

    private long defaultTime = 2000;

    private int count = 0;

    public SearchViewAnim(Context context)
    {

        this(context, null);
    }

    public SearchViewAnim(Context context, AttributeSet attrs)
    {

        this(context, attrs, 0);
    }

    public SearchViewAnim(Context context, AttributeSet attrs, int defStyleAttr)
    {

        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        //初始化画笔
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);


        //初始化path
        path_circle = new Path();
        path_search = new Path();

        //创建pathMeasure
        pathMeasure = new PathMeasure();

        // 搜索的放大镜圆环
        RectF oval1 = new RectF(-50, -50, 50, 50);
        path_search.addArc(oval1, 45, 359.9f);

        //外层的圆环
        RectF oval2 = new RectF(-100, -100, 100, 100);
        path_circle.addArc(oval2, 45, 359.9f);

        float[] pos = new float[2];
        //设置放大镜把手的位置
        pathMeasure.setPath(path_circle, false);
        pathMeasure.getPosTan(0, pos, null);

        path_search.lineTo(pos[0], pos[1]);

        initAnimListener();
        initHanlder();
        initAnim();


        setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {

        mCurrentStatus = Status.STARTING;
        startAnim.start();
    }


    private void initAnim()
    {

        startAnim = ValueAnimator.ofFloat(0, 1).setDuration(defaultTime);
        searchAnim = ValueAnimator.ofFloat(0, 1).setDuration(defaultTime);
        endAnim = ValueAnimator.ofFloat(1, 0).setDuration(defaultTime);

        //设置动画监听
        startAnim.addUpdateListener(animatorUpdateListener);
        searchAnim.addUpdateListener(animatorUpdateListener);
        endAnim.addUpdateListener(animatorUpdateListener);

        startAnim.addListener(animatorListener);
        searchAnim.addListener(animatorListener);
        endAnim.addListener(animatorListener);
    }

    private void initHanlder()
    {

        mHandler = new Handler()
        {

            @Override
            public void handleMessage(Message msg)
            {

                switch (mCurrentStatus)
                {
                    case STARTING:
                        // 开始状态
                        isOver = false;
                        mCurrentStatus = Status.SEARCHING;
                        startAnim.removeAllUpdateListeners();
                        startAnim.start();
                        break;

                    case SEARCHING:
                        //搜索状态
                        if (!isOver)
                        {
                            // 如果搜索未结束 就执行搜索状态
                            searchAnim.start();

                            count++;
                            if (count > 2)
                            {
                                isOver = true;
                            }
                        } else
                        {
                            //结束
                            mCurrentStatus = Status.ENDING;
                            endAnim.start();
                        }

                        break;

                    case ENDING:
                        //结束状态
                        LogUtil.all("动画结束");
                        mCurrentStatus = Status.NONE;
                        break;
                }
            }
        };
    }

    private void initAnimListener()
    {

        animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener()
        {

            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {

                animatedValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        };

        animatorListener = new Animator.AnimatorListener()
        {

            @Override
            public void onAnimationStart(Animator animation)
            {

            }

            @Override
            public void onAnimationEnd(Animator animation)
            {

                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animation)
            {

            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {

            }
        };
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {

        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        super.onDraw(canvas);

        mPaint.setColor(Color.WHITE);
        canvas.translate(width / 2, height / 2);
        canvas.drawColor(Color.parseColor("#0082D7"));

        switch (mCurrentStatus)
        {
            case NONE:
                canvas.drawPath(path_search, mPaint);
                break;

            case STARTING:
                pathMeasure.setPath(path_search, false);
                Path dst = new Path();
                pathMeasure.getSegment(pathMeasure.getLength() * animatedValue, pathMeasure.getLength(), dst, true);
                canvas.drawPath(dst, mPaint);
                break;


            case SEARCHING:
                pathMeasure.setPath(path_circle, false);
                //计算开始结束的点
                Path dst2 = new Path();
                float stop = pathMeasure.getLength() * animatedValue;
                float start = (float) (stop - (0.5 - (Math.abs(animatedValue - 0.5))) * 200f);
                pathMeasure.getSegment(start, stop, dst2, true);
                canvas.drawPath(dst2, mPaint);
                break;

            case ENDING:
                pathMeasure.setPath(path_search, false);
                Path dst3 = new Path();
                pathMeasure.getSegment(pathMeasure.getLength(), pathMeasure.getLength() * animatedValue, dst3, true);
                canvas.drawPath(dst3, mPaint);

                break;
        }
    }
}
