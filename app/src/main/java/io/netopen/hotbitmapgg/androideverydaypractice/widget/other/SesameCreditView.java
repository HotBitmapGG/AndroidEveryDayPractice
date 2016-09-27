package io.netopen.hotbitmapgg.androideverydaypractice.widget.other;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by hcc on 16/9/2 07:52
 * 100332338@qq.com
 */
public class SesameCreditView extends View
{

    private Point mCenterPoint = new Point();

    private int mViewWidth;

    private int mViewHeight;

    private int mCenterX;

    private int mCenterY;

    private int mRadius;

    private Paint mDefaultPaint = new Paint();

    private Paint mLineDefaultPaint = new Paint();

    private Paint mVisivlePaint = new Paint();

    private Paint mTextPaint = new Paint();

    private Paint mBitmapPaint = new Paint();

    private double mIdentityValue;

    private double mCreditValue;

    private double mCreditHistoryValue;

    private double mPepoleRelativeValue;

    private double mActionFavoriteValue;

    private String mDefaultText = "未知";

    private Rect mTextRect = new Rect();

    private float mDefaultRatio = 0.5f;

    private int mPicAndViewSpacing;

    private ArrayList<Region> mPicAreas = new ArrayList<Region>();

//    private int[] mPicResIds = new int[]{
//            R.mipmap.ic_account, R.mipmap.ic_phone, R.mipmap.ic_safe_center,
//            R.mipmap.ic_weather, R.mipmap.ic_pig_box
//    };

    private ArrayList<Bitmap> mPicBitmap;

    private final ValueAnimator mIdentityAnimator = new ValueAnimator();

    private final ValueAnimator mCreditAnimator = new ValueAnimator();

    private final ValueAnimator mCreditHistoryAnimator = new ValueAnimator();

    private final ValueAnimator mPepoleRelativeAnimator = new ValueAnimator();

    private final ValueAnimator mActionFavoriteAnimator = new ValueAnimator();


    public SesameCreditView(Context context)
    {

        this(context, null);
    }

    public SesameCreditView(Context context, AttributeSet attrs)
    {

        this(context, attrs, 0);
    }

    public SesameCreditView(Context context, AttributeSet attrs, int defStyleAttr)
    {

        super(context, attrs, defStyleAttr);
        initPaint();
        initAnimator();
        loadPicBitmap();
        mDefaultText = "613";
        mPicAndViewSpacing = dp2px(40);
    }

    private void loadPicBitmap()
    {

//        mPicBitmap = new ArrayList<Bitmap>();
//        for (int i = 0; i < mPicResIds.length; i++)
//        {
//            mPicBitmap.add(BitmapFactory.decodeResource(getResources(), mPicResIds[i]));
//        }
    }

    private void initAnimator()
    {

        mIdentityAnimator.setFloatValues(mDefaultRatio, 0.9f);
        mCreditAnimator.setFloatValues(mDefaultRatio, 0.7f);
        mCreditHistoryAnimator.setFloatValues(mDefaultRatio, 0.8f);
        mPepoleRelativeAnimator.setFloatValues(mDefaultRatio, 0.6f);
        mActionFavoriteAnimator.setFloatValues(mDefaultRatio, 0.4f);

        mIdentityAnimator.setDuration(1000L);
        mCreditAnimator.setDuration(1000L);
        mCreditHistoryAnimator.setDuration(1000L);
        mPepoleRelativeAnimator.setDuration(1000L);
        mActionFavoriteAnimator.setDuration(1000L);

        mIdentityAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {

            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {

                mIdentityValue = (Float) animation.getAnimatedValue() * mRadius;
                InvalidateView();
            }
        });
        mCreditAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {

            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {

                mCreditValue = (Float) animation.getAnimatedValue() * mRadius;
                InvalidateView();
            }
        });
        mCreditHistoryAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {

            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {

                mCreditHistoryValue = (Float) animation.getAnimatedValue() * mRadius;
                InvalidateView();
            }
        });
        mPepoleRelativeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {

            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {

                mPepoleRelativeValue = (Float) animation.getAnimatedValue() * mRadius;
                InvalidateView();
            }
        });
        mActionFavoriteAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {

            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {

                mActionFavoriteValue = (Float) animation.getAnimatedValue() * mRadius;
                InvalidateView();
            }
        });
    }

    private void initPaint()
    {

        mDefaultPaint.setDither(true);
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setColor(Color.rgb(239, 245, 243));
//        mDefaultPaint.setColor(Color.GRAY);
        mDefaultPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mLineDefaultPaint.setDither(true);
        mLineDefaultPaint.setAntiAlias(true);
        mLineDefaultPaint.setStrokeWidth(1f);
        mLineDefaultPaint.setColor(Color.rgb(212, 218, 216));
//        mDefaultPaint.setColor(Color.GRAY);
        mLineDefaultPaint.setStyle(Paint.Style.STROKE);

        mVisivlePaint.setDither(true);
        mVisivlePaint.setAntiAlias(true);
        mVisivlePaint.setStrokeWidth(3f);
        mVisivlePaint.setColor(Color.rgb(78, 216, 180));
        mVisivlePaint.setAlpha(155);
        mVisivlePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mTextPaint.setDither(true);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setFakeBoldText(true);
        mTextPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 46, getResources().getDisplayMetrics()));

        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setDither(true);
        mBitmapPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        switch (event.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                if (mPicAreas.size() == 0)
                {
                    return true;
                }
                for (int i = 0; i < mPicAreas.size(); i++)
                {
                    Region mPicArea = mPicAreas.get(i);
                    if (mPicArea.contains((int) event.getX(), (int) event.getY()))
                    {
                        Toast.makeText(getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_CANCEL:

                break;
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {

        super.onSizeChanged(w, h, oldw, oldh);
        Point mPoint = getPICMaxHeightAndWidth();
        mViewWidth = w - (mPoint == null ? 0 : 2 * mPoint.x) - 2 * mPicAndViewSpacing;
        mViewHeight = h - (mPoint == null ? 0 : 2 * mPoint.y) - 2 * mPicAndViewSpacing;
        mCenterX = w / 2;
        mCenterY = h / 2;
        mCenterPoint.set(mCenterX, mCenterY);
        mRadius = mViewWidth > mViewHeight ? mViewHeight / 2 : mViewWidth / 2;
        //默认为半径一半
        mIdentityValue = mRadius * mDefaultRatio;
        mCreditValue = mRadius * mDefaultRatio;
        mCreditHistoryValue = mRadius * mDefaultRatio;
        mPepoleRelativeValue = mRadius * mDefaultRatio;
        mActionFavoriteValue = mRadius * mDefaultRatio;

        postDelayed(new Runnable()
        {

            @Override
            public void run()
            {

                mIdentityAnimator.start();
                mCreditAnimator.start();
                mCreditHistoryAnimator.start();
                mPepoleRelativeAnimator.start();
                mActionFavoriteAnimator.start();
            }
        }, 900);
    }

    private Point getPICMaxHeightAndWidth()
    {

        if (mPicBitmap == null || mPicBitmap.size() == 0)
        {
            return null;
        }
        Point point = new Point();
        int maxHeight = 0, maxWidth = 0;
        for (int i = 0; i < mPicBitmap.size(); i++)
        {
            maxHeight = Math.max(maxHeight, mPicBitmap.get(i).getHeight());
            maxWidth = Math.max(maxWidth, mPicBitmap.get(i).getWidth());
        }
        point.set(maxWidth, maxHeight);
        return point;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //绘制图片
        int mPicValue = mRadius + mPicAndViewSpacing;
        ArrayList<PointF> mPICDefaultPointF = getPoints(mCenterPoint, mPicValue, mPicValue, mPicValue, mPicValue, mPicValue);
        drawBitmap(canvas, mPICDefaultPointF);

        //绘制默认灰色背景
        ArrayList<PointF> mDefaultPointF = getPoints(mCenterPoint, mRadius, mRadius, mRadius, mRadius, mRadius);
        ArrayList<Path> mDefaultPath = getPaths(mCenterPoint, mDefaultPointF);
        drawView(canvas, mDefaultPath, mDefaultPaint);

        //为灰色背景添加明显线条,以便区分块儿
        ArrayList<PointF> mLineDefaultPointF = getPoints(mCenterPoint, mRadius, mRadius, mRadius, mRadius, mRadius);
        ArrayList<Path> mLineDefaultPath = getPaths(mCenterPoint, mLineDefaultPointF);
        drawView(canvas, mLineDefaultPath, mLineDefaultPaint);

        //绘制显示色块，初始半径的五分之一
        ArrayList<PointF> mVisivlePointF = getPoints(mCenterPoint, mIdentityValue, mCreditValue, mCreditHistoryValue, mPepoleRelativeValue, mActionFavoriteValue);
        ArrayList<Path> mVisivlePath = getPaths(mCenterPoint, mVisivlePointF);
        drawView(canvas, mVisivlePath, mVisivlePaint);
    }

    private void drawBitmap(Canvas canvas, ArrayList<PointF> mPICDefaultPointF)
    {

        if (mPicBitmap == null || mPicBitmap.size() == 0)
        {
            return;
        }
        if (mPICDefaultPointF == null || mPICDefaultPointF.size() == 0)
        {
            return;
        }
        mPicAreas.clear();
        for (int i = 0; i < mPicBitmap.size(); i++)
        {
            PointF point = mPICDefaultPointF.get(i);
            Bitmap bitmap = mPicBitmap.get(i);
            Region area = new Region();
            area.set((int) (point.x - bitmap.getWidth() / 2), (int) (point.y - bitmap.getHeight() / 2), (int) (point.x + bitmap.getWidth() / 2), (int) (point.y + bitmap.getHeight() / 2));
            mPicAreas.add(area);
            canvas.drawBitmap(bitmap, point.x - bitmap.getWidth() / 2, point.y - bitmap.getHeight() / 2, mBitmapPaint);
        }
    }

    private void drawView(Canvas mCanvas, ArrayList<Path> paths, Paint mPaint)
    {

        if (paths == null || paths.size() == 0)
        {
            return;
        }
        for (int i = 0; i < paths.size(); i++)
        {
            mCanvas.drawPath(paths.get(i), mPaint);
        }

        if (TextUtils.isEmpty(mDefaultText))
        {
            mDefaultText = "未知";
        }
        mTextPaint.getTextBounds(mDefaultText, 0, mDefaultText.length(), mTextRect);
        mCanvas.drawText(mDefaultText, mCenterPoint.x - (mTextRect.width() / 2), mCenterPoint.y + (mTextRect.height() / 2), mTextPaint);
    }

    private ArrayList<Path> getPaths(Point center, ArrayList<PointF> points)
    {

        if (points == null || points.size() == 0)
        {
            return null;
        }
        ArrayList<Path> paths = new ArrayList<Path>();
        for (int i = 0; i < points.size(); i++)
        {
            Path path = new Path();
            path.reset();
            path.moveTo(points.get(i).x, points.get(i).y);
            path.lineTo(center.x, center.y);
            path.lineTo(points.get(i == points.size() - 1 ? 0 : i + 1).x, points.get(i == points.size() - 1 ? 0 : i + 1).y);
            path.close();
            paths.add(path);
        }
        return paths;
    }

    /**
     * 获取各个点
     *
     * @param center               中心点
     * @param mIdentityValue       身份特质值
     * @param mCreditValue         履约能力值
     * @param mCreditHistoryValue  信用历史值
     * @param mPepoleRelativeValue 人脉关系值
     * @param mActionFavorite      行为偏好值
     */
    private ArrayList<PointF> getPoints(Point center, double mIdentityValue, double mCreditValue, double mCreditHistoryValue, double mPepoleRelativeValue, double mActionFavorite)
    {

        ArrayList<PointF> points = new ArrayList<PointF>();
        points.add(new PointF(center.x, toFloat(center.y - mIdentityValue)));
        points.add(new PointF(toFloat(center.x + Math.sin(Math.toRadians(72D)) * mCreditValue), toFloat(center.y - Math.cos(Math.toRadians(72d)) * mCreditValue)));
        points.add(new PointF(toFloat(center.x + Math.cos(Math.toRadians(54D)) * mCreditHistoryValue), toFloat(center.y + Math.sin(Math.toRadians(54d)) * mCreditHistoryValue)));
        points.add(new PointF(toFloat(center.x - Math.cos(Math.toRadians(54D)) * mPepoleRelativeValue), toFloat(center.y + Math.sin(Math.toRadians(54d)) * mPepoleRelativeValue)));
        points.add(new PointF(toFloat(center.x - Math.sin(Math.toRadians(72D)) * mActionFavorite), toFloat(center.y - Math.cos(Math.toRadians(72d)) * mActionFavorite)));
        return points;
    }

    public float toFloat(double d)
    {

        BigDecimal bigDecimal = new BigDecimal(d);
        return bigDecimal.floatValue();
    }

    public void InvalidateView()
    {

        if (Looper.getMainLooper() == Looper.myLooper())
        {
            invalidate();
        } else
        {
            postInvalidate();
        }
    }


    /**
     * dp2px
     *
     * @param values
     * @return
     */
    public int dp2px(int values)
    {

        float density = getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }
}
