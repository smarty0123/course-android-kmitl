package kmitl.lab05.nattapon58070036.simplemydot.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import kmitl.lab05.nattapon58070036.simplemydot.R;
import kmitl.lab05.nattapon58070036.simplemydot.model.Colors;
import kmitl.lab05.nattapon58070036.simplemydot.model.Dot;
import kmitl.lab05.nattapon58070036.simplemydot.model.Dots;
import kmitl.lab05.nattapon58070036.simplemydot.view.DotView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DotViewFragment extends Fragment implements DotView.OnDotViewPressListener, Dots.OnDotsChangeListener, View.OnClickListener {
    public interface OnDotSelectListener {
        void onDotSelect(Dot dot, int position);
    }

    private OnDotSelectListener listener;
    private Dots dots;
    private DotView dotView;
    private View myView;
    private static final String ALLDOT = "allDot";

    public DotViewFragment() {
        // Required empty public constructor
    }

    public static DotViewFragment newInstance() {

        Bundle args = new Bundle();

        DotViewFragment fragment = new DotViewFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            dots = savedInstanceState.getParcelable(ALLDOT);
        } else {
            dots = new Dots();
        }
        dots.setListener(this);
        listener = (OnDotSelectListener) getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dotView.setDots(dots);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ALLDOT, dots);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dot_view, container, false);
        myView = rootView.getRootView();
        initSetup(rootView);
        return rootView;
    }

    private void initSetup(View rootView) {
        dotView = rootView.findViewById(R.id.fragmentDotView);
        dotView.setOnDotViewPressListener(this);
        Button btnRandom = rootView.findViewById(R.id.btnRandom);
        Button btnClear = rootView.findViewById(R.id.btnClear);
        Button btnUndo = rootView.findViewById(R.id.btnUndo);
        Button btnShareAll = rootView.findViewById(R.id.btnShareAll);
        Button btnShare = rootView.findViewById(R.id.btnShare);
        btnRandom.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnUndo.setOnClickListener(this);
        btnShareAll.setOnClickListener(this);
        btnShare.setOnClickListener(this);
    }

    private void randomDot() {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        Dot newDot = new Dot(centerX, centerY, 70, new Colors().getColor());
        dots.addDot(newDot);
    }

    private void clearDot() {
        dots.clearAll();
    }

    private void undoDot() {
        dots.undoDot();
    }

    private void shareDot() {
        Bitmap bitmap = createBitmapFromView(dotView);
        saveBitmap(bitmap);
        Uri contentUri = getUriFile();
        shareIntent(contentUri);
    }

    private void shareAllContent() {
        Bitmap bitmap = createBitmapFromView(myView.getRootView());
        saveBitmap(bitmap);
        Uri contentUri = getUriFile();
        shareIntent(contentUri);
    }

    private Bitmap createBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        view.draw(c);
        return bitmap;
    }

    private void saveBitmap(Bitmap bitmap) {
        try {
            File cachePath = new File(getContext().getCacheDir(), "images");
            cachePath.mkdirs();
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Uri getUriFile() {
        File imagePath = new File(getContext().getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        return FileProvider.getUriForFile(getContext(), "kmitl.lab05.nattapon58070036.simplemydot.fileprovider", newFile);
    }

    private void shareIntent(Uri contentUri) {
        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.setDataAndType(contentUri, getContext().getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            startActivity(Intent.createChooser(shareIntent, "Choose an app"));
        }
    }


    @Override
    public void onDotViewPressed(int x, int y, String status) {
        final int dotPosition = dots.findDot(x, y);
        if (dotPosition == -1) {
            Dot newDot = new Dot(x, y, 70, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            if (status.equals("short")) {
                dots.removeBy(dotPosition);
            } else {
                listener.onDotSelect(dots.getAllDot().get(dotPosition), dotPosition);
            }
        }
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    @Override
    public void onClick(View view) {
        if (R.id.btnRandom == view.getId()) {
            randomDot();
        }
        if (R.id.btnClear == view.getId()) {
            clearDot();
        }
        if (R.id.btnUndo == view.getId()) {
            undoDot();
        }
        if (R.id.btnShareAll == view.getId()) {
            shareAllContent();
        }
        if (R.id.btnShare == view.getId()) {
            shareDot();
        }
    }

}

