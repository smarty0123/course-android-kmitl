package kmitl.lab05.nattapon58070036.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kmitl.lab05.nattapon58070036.simplemydot.model.DotParcelable;
import kmitl.lab05.nattapon58070036.simplemydot.model.DotSerializable;


public class SecondActivity extends AppCompatActivity {

    private DotSerializable dotSerializable;
    private DotParcelable dotPacelable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button btnBack = (Button) findViewById(R.id.button3);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int x = getIntent().getIntExtra("xValue", 0);
        dotSerializable = (DotSerializable)getIntent().getSerializableExtra("dotSerializable");
        dotPacelable = getIntent().getParcelableExtra("dotParcelable");
        TextView text = (TextView) findViewById(R.id.textView3);
        TextView text2 = (TextView) findViewById(R.id.textView4);
        TextView text3 = (TextView) findViewById(R.id.textView5);
        TextView text4 = (TextView) findViewById(R.id.textView6);
        TextView text5 = (TextView) findViewById(R.id.textView8);
        text.setText(String.valueOf(x));
        text2.setText(String.valueOf("centerX : " + dotSerializable.getCenterX()) +
                "centerY : " + dotSerializable.getCenterY());
        text3.setText("Radius : " + dotSerializable.getRadius());
        text4.setText("Color : " + dotSerializable.getColor());
        text5.setText("Parcelable => centerX: " + dotPacelable.getCenterX() + "centerY: " + dotPacelable.getCenterY() +
                "radius: " + dotPacelable.getRadius());
    }


}