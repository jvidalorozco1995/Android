package navigation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.buzz.yora.R;

import activities.BaseActivity;
import activities.ContactsActivity;
import activities.MainActivity;
import activities.ProfileActivity;
import activities.SentMessagesActivity;
import infrastructure.User;

/**
 * Created by jorge on 02/01/2016.
 */
public class MainNavDrawer extends NavDrawer{

    private final TextView displayNameText;
    private final ImageView avatarImage;

    public MainNavDrawer(final BaseActivity activity) {
        super(activity);
        addItem(new ActivityDrawerItem(MainActivity.class, "Inbox", null, R.mipmap.ic_launcher, R.id.include_main_nav_drawer_topItems));
        addItem(new ActivityDrawerItem(SentMessagesActivity.class,"Sent Messages",null,R.mipmap.ic_launcher,R.id.include_main_nav_drawer_topItems));
        addItem(new ActivityDrawerItem(ContactsActivity.class,"Contacts",null,R.mipmap.ic_launcher,R.id.include_main_nav_drawer_topItems));
        addItem(new ActivityDrawerItem(ProfileActivity.class,"Profile",null,R.mipmap.ic_launcher,R.id.include_main_nav_drawer_topItems));

        addItem(new BasicNavDrawerItem("Logout",null,R.mipmap.ic_launcher,R.id.include_main_nav_drawer_bottomItems){

            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"Has presionado el boton de salir",Toast.LENGTH_LONG).show();
            }
        });
       // navDrawerView.findViewById(R.id.avatar).setSrc
        displayNameText =(TextView)activity.findViewById(R.id.include_main_nav_drawer_displayName);
        avatarImage =(ImageView)activity.findViewById(R.id.include_main_nav_drawer_avatar);
        User loggedInUser = activity.getYoraApplication().getAuth().getUser();
        displayNameText.setText(loggedInUser.getDisplayName());

        //TODO: cambiar imagen del avatar a imagen url desde el usuario logeado
        //TODO: min 19:10 video 3
    }

}
