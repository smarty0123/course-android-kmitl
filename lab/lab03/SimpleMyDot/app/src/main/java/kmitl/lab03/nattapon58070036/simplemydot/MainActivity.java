package kmitl.lab03.nattapon58070036.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.nattapon58070036.simplemydot.model.Dot;
import kmitl.lab03.nattapon58070036.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangeListener{
    private DotView dotView;
    private Dot dot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
        dot = new Dot(this, 0, 0, 30);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(800);
        int centerY = random.nextInt(800);
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);

    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(dot);
        dotView.invalidate();
        /*TextView centerXtextView = (TextView) findViewById(R.id.centerXtextView);
        TextView centerYtextView = (TextView) findViewById(R.id.centerYtextView);
        centerXtextView.setText(String.valueOf(dot.getCenterX()));
        centerYtextView.setText(String.valueOf(dot.getCenterY()));*/
    }
}
