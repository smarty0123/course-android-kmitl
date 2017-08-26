package kmitl.lab03.nattapon58070036.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;


import kmitl.lab03.nattapon58070036.simplemydot.model.Dot;

/**
 * Created by student on 8/25/2017 AD.
 */

public class DotView extends View {
    private Paint paint;
    private ArrayList<Dot> listDot;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (listDot != null) {
            for (Dot dot : listDot) {
                paint.setColor(dot.getColor());
                canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
            }
        }
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

    public void setDot(ArrayList<Dot> listDot) {
        this.listDot = listDot;
    }

}
