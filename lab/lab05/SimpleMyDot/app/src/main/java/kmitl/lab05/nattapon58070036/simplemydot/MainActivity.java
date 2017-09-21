package kmitl.lab05.nattapon58070036.simplemydot;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import kmitl.lab05.nattapon58070036.simplemydot.fragment.DotViewFragment;
import kmitl.lab05.nattapon58070036.simplemydot.fragment.EditDotFragment;
import kmitl.lab05.nattapon58070036.simplemydot.model.Dot;


public class MainActivity extends AppCompatActivity implements DotViewFragment.OnDotSelectListener, EditDotFragment.OnDotUpdatedListener {
    private final String MAIN_FRAGMENT_TAG = "MainFragmentTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            initialFragment();
        }
    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, DotViewFragment.newInstance(), MAIN_FRAGMENT_TAG)
                .commit();
    }

    private void updateFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDotUpdate(Dot dot, int position) {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onDotSelect(Dot dot, int position) {
        updateFragment(EditDotFragment.newInstance(dot, position));
    }

}