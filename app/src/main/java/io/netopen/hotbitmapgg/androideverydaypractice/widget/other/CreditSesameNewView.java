package io.netopen.hotbitmapgg.androideverydaypractice.widget.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hcc on 16/9/1 22:21
 * 100332338@qq.com
 * <p/>
 * 仿芝麻信用的圆环实现(新版)
 */
public class CreditSesameNewView extends View
{

    private final static int DEFAULT_PADDING = 20;

    String[] stringArray = new String[]{"350", "较差", "550", "中等", "600", "良好", "650", "优秀", "700", "极好", "950"};

    private final static float SWEEP_ANGLE = 210F;

    private final static float START_ANGLE = 165F;

    private int count = 0;

    private int distance_from_two_acr;

    //正中间字体
    private String textString = "信用良好";

    //信用分数
    private String numString = "666";

    //外半圆环的画笔
    private Paint outArcPaint;

    //内圆环的画笔
    private Paint inArcPaint;

    //文本画笔
    private Paint textPaint;

    //刻度线的深画笔
    private Paint keduDarkPaint;

    //刻度线的浅的画笔
    private Paint keduLightPaint;

    //刻度字体画笔
    private Paint keduFontPaint;

    private Paint changePaint;

    private int arcEnd;

    private float start = 350;


    public CreditSesameNewView(Context context)
    {

        this(context, null);
    }

    public CreditSesameNewView(Context context, AttributeSet attrs)
    {

        this(context, attrs, 0);
    }

    public CreditSesameNewView(Context context, AttributeSet attrs, int defStyleAttr)
    {

        super(context, attrs, defStyleAttr);
        init();
    }


    private void init()
    {

        distance_from_two_acr = dp2px(14);
        outArcPaint = new Paint();
        outArcPaint.setAntiAlias(true);
        outArcPaint.setStrokeWidth(8);
        outArcPaint.setColor(Color.parseColor("#ffffff"));
        outArcPaint.setStyle(Paint.Style.STROKE);

        inArcPaint = new Paint();
        inArcPaint.setAntiAlias(true);
        inArcPaint.setStrokeWidth(30);
        inArcPaint.setColor(Color.parseColor("#ffffff"));
        inArcPaint.setAlpha(60);
        inArcPaint.setStyle(Paint.Style.STROKE);

        //正中间字体画笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(60);
        textPaint.setColor(Color.parseColor("#ffffff"));

        keduDarkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        keduDarkPaint.setStrokeWidth(10);
        keduDarkPaint.setStyle(Paint.Style.STROKE);
        keduDarkPaint.setColor(Color.parseColor("#ffffff"));
        keduDarkPaint.setAlpha(30);

        keduLightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        keduLightPaint.setStrokeWidth(6);
        keduLightPaint.setStyle(Paint.Style.STROKE);
        keduLightPaint.setColor(Color.parseColor("#ffffff"));
        keduLightPaint.setAlpha(120);

        keduFontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        keduFontPaint.setTextSize(20);
        keduFontPaint.setColor(Color.parseColor("#ffffff"));
        keduFontPaint.setAlpha(120);

        changePaint = new Paint();
        changePaint.setAntiAlias(true);
        changePaint.setStrokeWidth(8);
        changePaint.setColor(Color.BLACK);
        changePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int wrap_Len = dp2px(300);
        int width = measureDimension(wrap_Len, widthMeasureSpec);
        int height = measureDimension(wrap_Len, heightMeasureSpec);
        len = Math.min(width, height);
        //保证他是一个正方形
        setMeasuredDimension(len, len);
    }

    public int measureDimension(int defaultSize, int measureSpec)
    {

        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY)
        {
            result = specSize;
        } else
        {
            result = defaultSize;
            if (specMode == MeasureSpec.AT_MOST)
            {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas)
    {

        super.onDraw(canvas);
        //获得中心点坐标
        int centerX = len / 2;
        int centerY = len / 2;

        //只要传入相对的坐标即可,画最最外圈的圆环
        RectF rectF = new RectF(DEFAULT_PADDING, DEFAULT_PADDING
                , getMeasuredWidth() - DEFAULT_PADDING, getMeasuredHeight() - DEFAULT_PADDING);
        canvas.drawArc(rectF, START_ANGLE, SWEEP_ANGLE, false, outArcPaint);

        //内圆环画圈
        rectF = new RectF(DEFAULT_PADDING + distance_from_two_acr, DEFAULT_PADDING + distance_from_two_acr, getMeasuredWidth() - DEFAULT_PADDING - distance_from_two_acr
                , getMeasuredHeight() - DEFAULT_PADDING - distance_from_two_acr);
        canvas.drawArc(rectF, START_ANGLE, SWEEP_ANGLE, false, inArcPaint);
        //中间字体的绘制
        drawText(canvas, centerX, centerY);
        //进行内圆环刻度线的draw
        drawtheLine(canvas, centerX, centerY);

        //动态通知圆环
        rectF = new RectF(DEFAULT_PADDING, DEFAULT_PADDING
                , getMeasuredWidth() - DEFAULT_PADDING, getMeasuredHeight() - DEFAULT_PADDING);

        float angle = 7 * (start - 350) / 20;

        canvas.drawArc(rectF, START_ANGLE, angle, false, changePaint);
        post(new Runnable()
        {

            @Override
            public void run()
            {

                if (start < arcEnd)
                {
                    start += (20 / 7);
                    postInvalidateDelayed(10);
                    count++;
                }
            }
        });
    }

    private void drawtheLine(Canvas canvas, int centerX, int centerY)
    {

        canvas.save();
        //我们将画布旋转,实际上是旋转我们的坐标轴
        canvas.rotate(-105, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        int lineStartY = (int) (DEFAULT_PADDING + distance_from_two_acr - inArcPaint.getStrokeWidth() / 2 - 1);
        int lineEndY = (int) (lineStartY + inArcPaint.getStrokeWidth());
        //每次画布旋转的角度
        int rotateRadius = 210 / 10;
        for (int i = 1; i < 12; i++)
        {

            if (i % 2 == 0)
            {
                canvas.drawLine(centerX, lineStartY, centerX, lineEndY, keduDarkPaint);
            } else
            {
                canvas.drawLine(centerX, lineStartY, centerX, lineEndY, keduLightPaint);
            }
            float textLength = keduFontPaint.measureText(stringArray[i - 1]);
            canvas.drawText(stringArray[i - 1], centerX - textLength / 2, lineEndY + 30, keduFontPaint);
            canvas.rotate(rotateRadius, centerX, centerY);
        }
        canvas.restore();
    }

    private void drawText(Canvas canvas, int centerX, int centerY)
    {

        if (!TextUtils.isEmpty(textString))
        {
            //计算字体的长度
            float textWidth = textPaint.measureText(textString);
            //textPaint.descent()-textPaint.ascent()测量字体高度
            canvas.drawText(textString, centerX - textWidth / 2, centerY + 10 + (textPaint.descent() - textPaint.ascent()), textPaint);
        }
        if (!TextUtils.isEmpty(textString))
        {
            float textWidth = textPaint.measureText(numString);
            canvas.drawText(numString, centerX - textWidth / 2, centerY, textPaint);
        }
    }

    private int len;

    //是否计算padding
    private void ensurePadding()
    {

        int paddingleft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();
        int width = getWidth() - paddingleft - paddingRight;
        int height = getHeight() - paddingBottom - paddingTop;
        len = Math.min(width, height) / 2;
    }

    public void setTextString(String string)
    {

        textString = string;
    }


    public void setNumString(String s)
    {

        numString = s;
        arcEnd = Integer.parseInt(s);
        if (arcEnd <= 350)
        {
            arcEnd = 350;
        } else if (arcEnd >= 950)
        {
            arcEnd = 950;
        }
    }

    @Override
    protected void onFinishInflate()
    {

        super.onFinishInflate();
    }

    public int dp2px(int values)
    {

        float density = getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }
}
