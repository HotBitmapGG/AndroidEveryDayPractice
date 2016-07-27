package io.netopen.hotbitmapgg.androideverydaypractice.widget.other;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import io.netopen.hotbitmapgg.androideverydaypractice.R;


/**
 * Created by hcc on 2016/7/27.
 * <p/>
 * 自定义控件学习之Path练习
 */
public class PathDemoView extends View
{

    private Paint mPaint;

    private int width;

    private int height;

    //用来记录当前的位置 取值[0,1] 映射整个path的长度
    private float currentValues = 0;

    //当前的点的实际位置
    private float[] pos;

    //当前的切线值 用于计算图片旋转的角度
    private float[] tan;

    //小箭头图片
    private Bitmap bitmap;

    //图片旋转所需要的矩阵
    private Matrix matrix;

    private Path path;

    public PathDemoView(Context context)
    {

        this(context, null);
    }

    public PathDemoView(Context context, AttributeSet attrs)
    {

        this(context, attrs, 0);
    }

    public PathDemoView(Context context, AttributeSet attrs, int defStyleAttr)
    {

        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        //初始化变量
        pos = new float[2];
        tan = new float[2];

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_arrow, options);

        matrix = new Matrix();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.BLACK);

        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {

        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //移动中心点
        canvas.translate(width / 2, height / 2);
        //添加一个圆
        path.addCircle(0, 0, 200, Path.Direction.CW);
        //创建pathMeasure 用于测量path
        PathMeasure pathMeasure = new PathMeasure(path, false);
        //计算当前位置在总长度上的比例
        currentValues += 0.05;
        if (currentValues >= 1)
        {
            currentValues = 0;
        }

        //获取path当前位置的坐标
        pathMeasure.getPosTan(pathMeasure.getLength() * currentValues, pos, tan);
        //重置Matrix
        matrix.reset();
        //计算图片选装的角度
        float dagress = (float) Math.atan2(tan[1], tan[0] * 180 / Math.PI);
        //旋转图片
        matrix.postRotate(dagress, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        //图片默认是由左上角顶点开始 这里把他移动到图片中心点进行重合
        matrix.postTranslate(pos[0] - bitmap.getWidth() / 2, pos[1] - bitmap.getHeight() / 2);

        //绘制路径
        canvas.drawPath(path, mPaint);
        //绘制Bitmap
        canvas.drawBitmap(bitmap, matrix, mPaint);
        //重绘界面
        invalidate();
    }
}
