package kmitl.lab03.nattapon58070036.simplemydot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorInt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import kmitl.lab03.nattapon58070036.simplemydot.model.DotParcelable;
import kmitl.lab03.nattapon58070036.simplemydot.model.Dots;


public class EditActivity extends AppCompatActivity {
    private Dots dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("Change Dot Color");
        final DotParcelable dotParcelable = getIntent().getParcelableExtra("dotParcelable");
        final ColorPicker cp = new ColorPicker(EditActivity.this, Color.red(dotParcelable.getColor()),
                Color.green(dotParcelable.getColor()), Color.blue(dotParcelable.getColor()));
        cp.show();
        cp.setCallback(new ColorPickerCallback() {
            @Override
            public void onColorChosen(@ColorInt int c) {
                int color = Color.rgb(Color.red(c), Color.green(c), Color.blue(c));
                final DotParcelable reDotParcelable = new DotParcelable(dotParcelable.getDotPosition(), color);
                Intent returnIntent = new Intent(EditActivity.this, MainActivity.class);
                returnIntent.putExtra("reDotParcelable", reDotParcelable);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                cp.cancel();
            }
        });

    }
}
