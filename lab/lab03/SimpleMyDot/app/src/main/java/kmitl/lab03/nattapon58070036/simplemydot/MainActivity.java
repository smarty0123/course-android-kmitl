package kmitl.lab03.nattapon58070036.simplemydot;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.nattapon58070036.simplemydot.model.Dot;
import kmitl.lab03.nattapon58070036.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangeListener {
    final Context context = this;
    private DotView dotView;
    private ArrayList<Dot> listDot;
    private long startTouch;
    private long stopTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listDot = new ArrayList<>();
        setContentView(R.layout.activity_main);
        onTouchDot();
    }

    public void onTouchDot() {
        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    startTouch = event.getEventTime();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    stopTouch = event.getEventTime();
                    Random random = new Random();
                    int radius = random.nextInt(80) + 70;
                    int red = random.nextInt(255);
                    int green = random.nextInt(255);
                    int blue = random.nextInt(255);
                    long timeTouched = stopTouch - startTouch;
                    if (timeTouched < 500) {
                        createDot((int) (event.getX()), (int) (event.getY()), radius, red, green, blue);
                    } else {
                        isDot((int) event.getX(), (int) event.getY(), radius);
                    }
                }
                return true;
            }
        });
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        int radius = random.nextInt(80) + 70;
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        Dot dot = new Dot(this, centerX, centerY, radius, red, green, blue);
        listDot.add(dot);
    }

    public void createDot(int x, int y, int r, int red, int green, int blue) {
        Dot dot = new Dot(this, x, y, r, red, green, blue);
        listDot.add(dot);
    }

    public boolean isDot(int x, int y, int radius) {
        for (Dot dot : listDot) {
            double distance = Math.pow(Math.pow(dot.getCenterX() - x, 2) + Math.pow(dot.getCenterY() - y, 2), 0.5);
            if (distance <= radius) {
                alertDialog(dot);
                dotView.invalidate();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(listDot);
        dotView.invalidate();
    }

    public void clearAllDot(View view) {
        listDot = new ArrayList<>();
        dotView.setDot(listDot);
        dotView.invalidate();
    }

    public void alertDialog(final Dot dot) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        // set title
        alertDialogBuilder.setTitle("What you want to do?");
        // set dialog message
        alertDialogBuilder
                .setMessage("")
                .setCancelable(true)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, will delete item
                        listDot.remove(dot);
                        dotView.invalidate();
                    }
                })
                .setNegativeButton("Edit Color", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        colorPicker(dot, dot.getRed(), dot.getGreen(), dot.getBlue());
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    public void colorPicker(final Dot dot, int r, int g, int b) {
        final ColorPicker cp = new ColorPicker(MainActivity.this, r, g, b);
        /* Show color picker dialog */
        cp.show();
            /* Set a new Listener called when user click "select" */
        cp.setCallback(new ColorPickerCallback() {
            @Override
            public void onColorChosen(@ColorInt int color) {
                // Do whatever you want
                dot.setRed(Color.red(color));
                dot.setGreen(Color.green(color));
                dot.setBlue(Color.blue(color));
                listDot.remove(dot);
                listDot.add(dot);
                dotView.invalidate();
                cp.cancel();
            }
        });

    }
}
