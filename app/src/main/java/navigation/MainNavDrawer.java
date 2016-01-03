package navigation;

import android.view.View;
import android.widget.Toast;

import com.buzz.yora.R;

import activities.BaseActivity;
import activities.MainActivity;

/**
 * Created by jorge on 02/01/2016.
 */
public class MainNavDrawer extends NavDrawer{
    public MainNavDrawer(final BaseActivity activity) {
        super(activity);
        addItem(new ActivityDrawerItem(MainActivity.class, "Inbox", null, R.mipmap.ic_launcher, R.id.include_main_nav_drawer_topItems));

        addItem(new BasicNavDrawerItem("Logout",null,R.mipmap.ic_launcher,R.id.include_main_nav_drawer_bottomItems){

            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"Has presionado el boton de salir",Toast.LENGTH_LONG).show();
            }
        });
       // navDrawerView.findViewById(R.id.avatar).setSrc
    }

}
