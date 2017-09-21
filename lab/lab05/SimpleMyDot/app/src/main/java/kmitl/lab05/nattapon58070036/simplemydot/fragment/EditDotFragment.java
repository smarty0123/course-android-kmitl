package kmitl.lab05.nattapon58070036.simplemydot.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import kmitl.lab05.nattapon58070036.simplemydot.R;
import kmitl.lab05.nattapon58070036.simplemydot.model.Dot;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment {
    public interface OnDotUpdatedListener {
        void onDotUpdate(Dot dot, int position);
    }

    private static final String POSITION = "position";
    private static final String DOT = "dot";
    private Dot dot;
    private int position;
    private int currentX;
    private int currentY;
    private int currentSize;
    private int currentColor;
    OnDotUpdatedListener listener;

    public EditDotFragment() {
        // Required empty public constructor
    }


    public static EditDotFragment newInstance(Dot dot, int position) {
        Bundle args = new Bundle();
        EditDotFragment fragment = new EditDotFragment();
        args.putParcelable(DOT, dot);
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public static EditDotFragment newInstance() {
        Bundle args = new Bundle();
        EditDotFragment fragment = new EditDotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dot = getArguments().getParcelable(DOT);
        position = getArguments().getInt(POSITION);
        listener = (OnDotUpdatedListener) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);
        contentSetup(rootView);
        return rootView;
    }

    private void contentSetup(View rootView) {
        getActivity().setTitle("Edit Dot");
        Toast.makeText(getContext(), "Edit Dot", Toast.LENGTH_SHORT).show();
        final Button btnChangeColor = rootView.findViewById(R.id.btnChangeColor);
        final TextView colorText = rootView.findViewById(R.id.colorText);
        colorText.setTextColor(dot.getColor());
        currentColor = dot.getColor();
        final SeekBar xSeekBar = rootView.findViewById(R.id.xSeekBar);
        final TextView xPosition = rootView.findViewById(R.id.xTV);
        final SeekBar ySeekBar = rootView.findViewById(R.id.ySeekBar);
        final TextView yPosition = rootView.findViewById(R.id.yTV);
        final SeekBar sizeSeekBar = rootView.findViewById(R.id.sizeSeekBar);
        final TextView sizeText = rootView.findViewById(R.id.sizeTV);
        Button btnCancel = rootView.findViewById(R.id.btnCancel);
        Button btnOK = rootView.findViewById(R.id.btnOK);

        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ColorPicker cp = new ColorPicker(getActivity(), Color.red(dot.getColor()), Color.green(dot.getColor()), Color.blue(dot.getColor()));
                cp.show();
                cp.setCancelable(false);
                cp.setCallback(new ColorPickerCallback() {
                    @Override
                    public void onColorChosen(@ColorInt int c) {
                        int color = Color.rgb(Color.red(c), Color.green(c), Color.blue(c));
                        colorText.setTextColor(color);
                        currentColor = color;
                        cp.cancel();
                    }
                });
            }
        });

        xSeekBar.setMax(1000);
        xPosition.setText("X Axis: " + dot.getCenterX());
        xSeekBar.setProgress(dot.getCenterX());
        xSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                xPosition.setText("X Axis: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ySeekBar.setMax(1300);
        yPosition.setText("Y Axis: " + dot.getCenterY());
        ySeekBar.setProgress(dot.getCenterY());
        ySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                yPosition.setText("Y Axis: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sizeSeekBar.setProgress(dot.getRadius());
        sizeSeekBar.setMax(120);
        sizeText.setText("Size: " + dot.getRadius());
        sizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sizeText.setText("Radius: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentX = xSeekBar.getProgress();
                currentY = ySeekBar.getProgress();
                currentSize = sizeSeekBar.getProgress();
                dot.setCenterX(currentX);
                dot.setCenterY(currentY);
                dot.setRadius(currentSize);
                dot.setColor(currentColor);
                listener.onDotUpdate(dot, position);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDotUpdate(dot, position);
            }
        });
    }

}
