package kmitl.lab03.nattapon58070036.simplemydot;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.nattapon58070036.simplemydot.model.Dot;
import kmitl.lab03.nattapon58070036.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangeListener {
    private DotView dotView;
    private ArrayList<Dot> listDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listDot = new ArrayList<>();
        setContentView(R.layout.activity_main);
        dotView = (DotView) findViewById(R.id.dotView);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        int radius = random.nextInt(80) + 10;
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
        int color = Color.rgb(red, green, blue);
        Dot dot = new Dot(this, centerX, centerY, radius, color);
        dot.setCenterX(centerX);
        dot.setCenterY(centerY);
        dot.setRadius(radius);
        dot.setColor(color);
        listDot.add(dot);
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(listDot);
        dotView.invalidate();
    }

    public void clearDot(View view) {
        listDot = new ArrayList<>();
        dotView.setDot(listDot);
        dotView.invalidate();
    }
}
