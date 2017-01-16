package com.yx.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 10371 on 2017/1/16.
 */

public class BezierView extends View {
    //画笔
    private Paint paint;
    //中心点的x，y的坐标
    private int centerX, centerY;
    //三个点  开始点  结束点  控制点
    private PointF startPoint, endPoint, controlPoint,controlPoint2;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
        initPointF();

    }

    /**
     * 初始化三个点
     */
    private void initPointF() {
        startPoint = new PointF(0, 0);
        endPoint = new PointF(0, 0);
        controlPoint = new PointF(0, 0);
        controlPoint2 = new PointF(0, 0);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(60);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("TTTT","onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("TTTT","onLayout");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.d("TTTT","onSizeChanged");

        //初始化位置几个点的位置
        centerX = w / 2;
        centerY = h / 2;

        startPoint.x = centerX - 300;
        endPoint.x = centerX + 300;

        startPoint.y = centerY;
        endPoint.y = centerY;


        controlPoint.x = centerX;
        controlPoint.y = centerY - 200;

        controlPoint2.x = centerX;
        controlPoint2.y = centerY + 200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("TTTT","onDraw");
        //绘制数据点和控制点
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        canvas.drawPoint(startPoint.x, startPoint.y, paint);
        canvas.drawPoint(endPoint.x, endPoint.y, paint);
        canvas.drawPoint(controlPoint.x, controlPoint.y, paint);
        canvas.drawPoint(controlPoint2.x, controlPoint2.y, paint);

        //绘制辅助线
        paint.setStrokeWidth(8);
        canvas.drawLine(startPoint.x, startPoint.y, controlPoint.x, controlPoint.y, paint);
        canvas.drawLine(controlPoint.x, controlPoint.y, controlPoint2.x, controlPoint2.y, paint);
        canvas.drawLine(controlPoint2.x, controlPoint2.y, endPoint.x, endPoint.y, paint);


        //绘制贝塞尔曲线
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(startPoint.x, startPoint.y);
//        path.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y);

        path.cubicTo(controlPoint.x,controlPoint.y,controlPoint2.x,controlPoint2.y,endPoint.x,endPoint.y);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //手指滑动  取得控制点的坐标
        controlPoint2.x = event.getX();
        controlPoint2.y = event.getY();
        invalidate();
        return true;
    }
}
