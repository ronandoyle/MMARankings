package ronandoyle.ie.ufcrankings;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    /**
     * Fragment Tag.
     */
    public static final String FRAGMENT_TAG = "MainFragment";

    private GridLayoutManager mGridLayoutManager;
    private RecyclerView mRecyclerView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RelativeLayout relativeLayout =
                (RelativeLayout) inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) relativeLayout.findViewById(R.id.grid_view_fighters);
        mGridLayoutManager = new GridLayoutManager(getActivity(),2);
        mGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        return relativeLayout;
    }

    public void updateListView(ArrayList<Fighter> fighterArrayList) {
        if (getView() == null) {
            return;
        }

        CustomAdapter customAdapter = new CustomAdapter(getActivity(), fighterArrayList);
        mRecyclerView.setAdapter(customAdapter);
    }
}
