package fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.buzz.yora.R;

import activities.LoginActivity;

/**
 * Created by jorge on 01/11/2015.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private Button loginButton;
    private Callbacks callbacks;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,root,false);

        loginButton =(Button)view.findViewById(R.id.fragment_login_loginButton);
        loginButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==loginButton){
            /*notificamos a la actividad padre*/
            callbacks.onLoggedIn();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbacks =(Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    public interface Callbacks{
        void onLoggedIn();
    }
}
