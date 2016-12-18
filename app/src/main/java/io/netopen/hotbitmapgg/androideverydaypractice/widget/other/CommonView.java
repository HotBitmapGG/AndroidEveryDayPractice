package io.netopen.hotbitmapgg.androideverydaypractice.widget.other;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义控件练习
 * <p/>
 * View的绘制流程练习代码
 */
public class CommonView extends View
{

    private Paint mPaint;

    /**
     * 构造函数 在代码中初始化时调用
     * CommonView commonView = new CommonView();
     *
     * @param context
     */
    public CommonView(Context context)
    {

        this(context, null);
    }

    /**
     * 构造函数 在xml中使用时调用
     * attrs 这个是用于自定义属性时使用
     * 在res-values目录下 建一个attr文件夹
     *
     * @param context
     * @param attrs
     */
    public CommonView(Context context, AttributeSet attrs)
    {

        this(context, attrs, 0);
    }

    public CommonView(Context context, AttributeSet attrs, int defStyleAttr)
    {

        super(context, attrs, defStyleAttr);

        init();
    }

    private void init()
    {

        // 初始化画笔
        mPaint = new Paint();
    }


    /**
     * View的测量
     * <p>
     * UNSPECIFIED：未指定测量模式。View大小不确定，想要多大有多大。
     * <p>
     * EXACTLY： 精确值模式。当控件的width和height设置为具体值或者match_parent时就是这个模式。
     * <p>
     * AT_MOST：最大值模式。父布局决定子布局大小（例如：父布局width或者height设置一个默认的精确值，子布局设置为wrap_content。
     * 此时子布局的最大width或者height就是父布局的width或者height）。
     * 使用这种测量模式的View，设置的一定是wrap_content。
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    /**
     * 确定子view的位置
     * 一般是ViewGroup会使用这个方法
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {

        super.onLayout(changed, left, top, right, bottom);
    }


    /**
     * 当View的宽高都确定回调该方法
     * 可以拿到已经测量后的宽高值
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {

        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * View的生命周期方法
     * 添加至窗口
     */
    @Override
    protected void onAttachedToWindow()
    {

        super.onAttachedToWindow();
    }

    /**
     * View的生命周期方法
     * 从窗口中删除
     */
    @Override
    protected void onDetachedFromWindow()
    {

        super.onDetachedFromWindow();
    }

    /**
     * View的绘制
     * <p/>
     * View的绘制大致分为4步
     * 1，绘制背景
     * 2，绘制自己
     * 3，绘制子View（ViewGroup）
     * 4，绘制滚动条
     * <p>
     * Canvas基本Api使用
     * 绘制各种图形
     *
     * @param canvas
     */

    @Override
    protected void onDraw(Canvas canvas)
    {


        /**
         * 绘制一个矩形
         */
        //画笔设置颜色
        mPaint.setColor(Color.LTGRAY);
        //利用canvas绘制矩形
        canvas.drawRect(100, 100, 400, 400, mPaint);
        //重置画笔对象
        mPaint.reset();


        /**
         * 绘制直线
         */

        //设置画笔颜色
        mPaint.setColor(Color.BLACK);
        //设置画笔空心线的宽度
        mPaint.setStrokeWidth(10);
        //绘制直线
        canvas.drawLine(0, 100, 200, 0, mPaint);
        //重置画笔


        /**
         *  绘制带边框的矩形
         */
        //画笔设置颜色
        mPaint.setColor(Color.BLACK);
        //画笔设置argb色值
        mPaint.setARGB(150, 90, 255, 0);
        //设置画笔样式 空心样式
        mPaint.setStyle(Paint.Style.STROKE);
        //创建一个矩形 参数传矩形的左上右下坐标点
        Rect rect = new Rect(0, 0, 100, 100);
        //绘制矩形
        canvas.drawRect(rect, mPaint);
        //重置画笔
        mPaint.reset();


        /**
         * 绘制实心圆
         */

        //设置画笔颜色
        mPaint.setColor(Color.BLUE);
        //设置画笔样式
        mPaint.setStyle(Paint.Style.FILL);
        //设置画笔抗锯齿
        mPaint.setAntiAlias(true);
        //绘制圆形  参数 圆形的宽高，半径，画笔
        canvas.drawCircle(200, 200, 100, mPaint);
        //重置画笔
        mPaint.reset();

        /**
         * 绘制椭圆形
         */

        //设置画笔颜色
        mPaint.setColor(Color.YELLOW);
        //设置画笔抗锯齿
        mPaint.setAntiAlias(true);
        //设置画笔宽度
        mPaint.setStrokeWidth(14);
        //创建矩形
        RectF rect1 = new RectF(200, 200, 400, 400);
        //绘制椭圆形
        canvas.drawOval(rect1, mPaint);
        //重置画笔
        mPaint.reset();


        /**
         * 绘制文字
         */

        //设置画笔颜色
        mPaint.setColor(Color.BLACK);
        //设置文字大小
        mPaint.setTextSize(60);
        //设置文字下划线
        mPaint.setUnderlineText(true);
        //绘制文字 参数 绘制的内容 绘制的x，y坐标点，画笔
        canvas.drawText("文本内容", 100, 100, mPaint);
        //重置画笔
        mPaint.reset();


        /**
         * canvas的一些常用api使用
         */


        /**
         * canvas.translate
         * 画布平移的使用
         */

        canvas.drawColor(Color.LTGRAY);
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(60);
        canvas.drawText("平移前的文字", 200, 200, mPaint);
        mPaint.reset();
        canvas.translate(200, 200);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(60);
        canvas.drawText("平移后的文字", 200, 200, mPaint);


        /**
         * canvas.rotate
         *
         * 旋转画布
         */

        canvas.drawColor(Color.LTGRAY);
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(60);
        canvas.drawText("平移前的文字", 200, 200, mPaint);
        mPaint.reset();
        canvas.rotate(10);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(60);
        canvas.drawText("平移后的文字", 200, 200, mPaint);


        /**
         * canvas.clipRect
         *
         * 画布裁剪
         */

        canvas.drawColor(Color.GREEN);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(60);
        canvas.drawText("裁剪前的内容", 200, 200, mPaint);
        Rect rect2 = new Rect(200, 400, 800, 800);
        canvas.clipRect(rect2);
        canvas.drawColor(Color.BLUE);
        mPaint.setColor(Color.BLACK);
        canvas.drawText("裁剪后的内容", 200, 200, mPaint);


        /**
         * canvas.save和canvas.restore
         *
         * 保存和重置
         */


        canvas.drawColor(Color.GREEN);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(60);
        canvas.drawText("裁剪前的内容", 200, 200, mPaint);
        canvas.save();
        Rect rect3 = new Rect(100, 100, 400, 400);
        canvas.clipRect(rect3);
        canvas.drawColor(Color.BLUE);
        mPaint.setColor(Color.BLACK);
        canvas.drawText("裁剪后的内容", 200, 200, mPaint);
        canvas.restore();
        canvas.drawText("1111", 100, 200, mPaint);


        /**
         * Shader 渲染
         *
         * BitmapShader———图像渲染
         * LinearGradient——–线性渲染
         * RadialGradient——–环形渲染
         * SweepGradient——–扫描渲染
         * ComposeShader——组合渲染
         */

        /**
         * TileMode知识点
         *
         * REPEAT ：重复
         * MIRROR ：镜像
         * CLAMP：拉伸
         */

//        //使用BitmapShader实现圆形图片
//        mPaint.setAntiAlias(true);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.no_media);
//        //创建BitmapShader
//        int angle = bitmap.getWidth() / 2;
//        BitmapShader bitmapShader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
//        mPaint.setShader(bitmapShader);
//        canvas.translate(200, 200);
//        canvas.drawCircle(angle, angle, angle, mPaint);


        /**
         * PathEffect  Path路径相关知识
         *
         * CornerPathEffect用平滑的方式衔接Path的各部分
         * DashPathEffect将Path的线段虚线化
         * PathDashPathEffect与DashPathEffect效果类似但需要自定义路径虚线的样式
         * DiscretePathEffect离散路径效果
         * ComposePathEffect两种样式的组合。先使用第一种效果然后在此基础上应用第二种效果
         * SumPathEffect两种样式的叠加。先将两种路径效果叠加起来再作用于Path
         */

//        canvas.translate(0, 300);
//        mPaint.setAntiAlias(true);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(8);
//        mPaint.setColor(Color.YELLOW);
//        Path path = new Path();
//        path.moveTo(0, 40);
//        for (int i = 0; i < 40; i++)
//        {
//            path.lineTo(i * 30, (float) (Math.random() * 150));
//        }
//        canvas.drawPath(path, mPaint);
//        canvas.translate(0, 400);
//
//        //圆滑效果 不会那么尖锐
//        mPaint.setPathEffect(new CornerPathEffect(60));
//        canvas.drawPath(path, mPaint);
//        canvas.translate(0, 500);
//
//        //虚线效果
//        mPaint.setPathEffect(new DashPathEffect(new float[]{10, 19}, 1));
//        canvas.drawPath(path, mPaint);


    }
}
