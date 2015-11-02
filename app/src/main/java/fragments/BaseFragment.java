package fragments;

import android.app.Fragment;
import android.os.Bundle;

import infrastructure.YoraApplication;

/**
 * Created by jorge on 01/11/2015.
 */
public abstract class BaseFragment extends Fragment {
    protected YoraApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application =(YoraApplication)getActivity().getApplication();
    }
}
