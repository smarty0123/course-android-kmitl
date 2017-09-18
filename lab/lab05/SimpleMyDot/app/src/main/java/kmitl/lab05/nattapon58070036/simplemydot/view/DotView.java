package kmitl.lab05.nattapon58070036.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab05.nattapon58070036.simplemydot.model.Dot;
import kmitl.lab05.nattapon58070036.simplemydot.model.Dots;

import static android.R.attr.radius;

public class DotView extends View {
    private long startTouch;
    private long stopTouch;
    private Paint paint;
    private Dots allDot;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.allDot != null) {
            for (Dot dot : allDot.getAllDot()) {
                paint.setColor(dot.getColor());
                canvas.drawCircle(
                        dot.getCenterX(),
                        dot.getCenterY(), dot.getRadius(), paint);
            }
        }
    }

    public interface OnDotViewPressListener {
        void onDotViewPressed(int x, int y, String status);
    }

    private OnDotViewPressListener onDotViewPressListener;

    public void setOnDotViewPressListener(
            OnDotViewPressListener onDotViewPressListener) {
        this.onDotViewPressListener = onDotViewPressListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startTouch = event.getEventTime();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            stopTouch = event.getEventTime();
            long timeTouched = stopTouch - startTouch;
            if (timeTouched < 300) {
                this.onDotViewPressListener
                        .onDotViewPressed(
                                (int) event.getX(),
                                (int) event.getY(),
                                "short");
                return true;
            } else {
                this.onDotViewPressListener
                        .onDotViewPressed(
                                (int) event.getX(),
                                (int) event.getY(),
                                "long");
                return true;
            }
        }
        return true;
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }


    public void setDots(Dots dots) {
        this.allDot = dots;
    }
}