package kmitl.lab05.nattapon58070036.simplemydot.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kmitl.lab05.nattapon58070036.simplemydot.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DotViewFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dot_view, container, false);
        return v;
    }

}
