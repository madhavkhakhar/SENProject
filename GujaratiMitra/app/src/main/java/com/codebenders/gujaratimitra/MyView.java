package com.codebenders.gujaratimitra;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyView extends View {

    private final int RADIUS = 80;
    Paint paint;
    float cx, cy, prevx, prevy;
    boolean first = true, disable = false, upDisable = false;
    ArrayList<PointF> listPoints = new ArrayList<PointF>();

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.DITHER_FLAG);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        DashPathEffect dashPathEffect = new DashPathEffect(
                new float[]{40, 40}, (float) 1.0);
        paint.setPathEffect(dashPathEffect);
        paint.setStrokeWidth(12);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!first) {
            // if (!disable && !upDisable)
            // canvas.drawCircle(cx, cy, RADIUS, paint);
            for (PointF pointF : listPoints)
                canvas.drawCircle(pointF.x, pointF.y, RADIUS, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        first = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                prevx = cx = event.getX();
                prevy = cy = event.getY();

                int i;
                if ((i = findCircle(new PointF(cx, cy), listPoints)) != -1) {
                    // disable = true;
                    // upDisable = true;
                    listPoints.remove(i);
                } else
                    listPoints.add(new PointF(cx, cy));
                break;
            case MotionEvent.ACTION_MOVE:
                cx = event.getX();
                cy = event.getY();
                // System.out.println(Math.abs(prevx - cx) < 80);
                if ((i = findCircle(new PointF(cx, cy), listPoints)) != -1) {
                    listPoints.get(i).x = cx;
                    listPoints.get(i).y = cy;
                } else {
                    if (Math.abs(prevx - cx) > 1 && Math.abs(prevy - cy) > 1)
                        listPoints.add(new PointF(cx, cy));
                }
                break;
            case MotionEvent.ACTION_UP:
                cx = event.getX();
                cy = event.getY();
                // if (!disable && !upDisable)
                // upDisable = false;
                // disable = false;
                break;

        }
        invalidate();
        return true;
    }

    private int findCircle(PointF pointF, ArrayList<PointF> list) {
        for (PointF p : list) {
            if (isInsideCircle(pointF, p))
                return list.indexOf(p);
        }
        return -1;
    }

    private boolean isInsideCircle(PointF current, PointF center) {
        float temp = (current.x - center.x) * (current.x - center.x)
                + (current.y - center.y) * (current.y - center.y);
        if (temp <= RADIUS * RADIUS)
            return true;
        return false;
    }
}
