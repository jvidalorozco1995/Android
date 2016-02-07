package activities;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.buzz.yora.R;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import infrastructure.User;
import navigation.MainNavDrawer;

/**
 * Created by jorge on 03/01/2016.
 */
public class ProfileActivity extends BaseAuthenticatedActivity implements View.OnClickListener {
    private static final int REQUEST_SELECT_IMAGE = 100;
    private static final int STATE_VIEWING =1;
    private static final int STATE_EDITING =2;
    private static final String BUNDLE_STATE ="BUNDLE_STATE";

    private int currentState;
    private EditText displayNameText;
    private EditText emailText;
    private View changeAvatarButton;
    private ActionMode editProfileActionMode;


    private ImageView avatarView;
    private View avatarProgressFrame;
    private File tempOutputFile;
    private static final String TAG  = "ProfileActivity";

    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        setNavDrawer(new MainNavDrawer(this));

        if(!isTablet){
            View textFields = findViewById(R.id.activity_profile_textFields);
            RelativeLayout.LayoutParams params =(RelativeLayout.LayoutParams)textFields.getLayoutParams();
            params.setMargins(0,params.getMarginStart(),0,0);
            params.removeRule(RelativeLayout.END_OF);
            params.addRule(RelativeLayout.BELOW,R.id.activity_profile_changeAvatar);
            textFields.setLayoutParams(params);
        }

        avatarView =(ImageView)findViewById(R.id.activity_profile_avatar);
        avatarProgressFrame = findViewById(R.id.activity_profile_avatarProgressFrame);
        changeAvatarButton = findViewById(R.id.activity_profile_changeAvatar);
        displayNameText = (EditText)findViewById(R.id.activity_profile_displayName);
        emailText = (EditText)findViewById(R.id.activity_profile_email);
        tempOutputFile = new File(getExternalCacheDir(),"temp-image.jpg");

        avatarView.setOnClickListener(this);
        changeAvatarButton.setOnClickListener(this);

        findViewById(R.id.activity_profile_changeAvatar).setOnClickListener(this);

        avatarProgressFrame.setVisibility(View.GONE);

        User user = application.getAuth().getUser();
        getSupportActionBar().setTitle(user.getDisplayName());
        displayNameText.setText(user.getDisplayName());
        emailText.setText(user.getEmail());

        changeState(STATE_VIEWING);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(viewId == R.id.activity_profile_changeAvatar || viewId == R.id.activity_profile_avatar){
            changeAvatar();
        }
    }

    private void changeAvatar() {
        List<Intent> otherImageCaptureIntents = new ArrayList<>();
        List<ResolveInfo> otherImageCaptureActivities = getPackageManager()
                .queryIntentActivities(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),0);

        for(ResolveInfo info : otherImageCaptureActivities){
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.setClassName(info.activityInfo.packageName,info.activityInfo.name);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempOutputFile));
            otherImageCaptureIntents.add(captureIntent);
        }

        Intent selectImageIntent = new Intent(Intent.ACTION_PICK);
        selectImageIntent.setType("image/*");

        Intent chooser = Intent.createChooser(selectImageIntent,"Choose Avatar");
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS,otherImageCaptureIntents.toArray(new Parcelable[otherImageCaptureActivities.size()]));
        
        startActivityForResult(chooser, REQUEST_SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK){
            Log.i(TAG,"Result Ok detele");
            tempOutputFile.delete();
            return;
        }
       /* if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK) {
            doSomethingWithCroppedImage(outputUri);
        }*/
        if(requestCode == REQUEST_SELECT_IMAGE) {
           Log.i(TAG,"Result select image");
            Uri outputFile;
            Uri tempFileUri = Uri.fromFile(tempOutputFile);
            if (data != null && (data.getAction() == null || !data.getAction().equals(MediaStore.ACTION_IMAGE_CAPTURE)))
                outputFile = data.getData();
            else
                outputFile = tempFileUri;

              new Crop(outputFile)
                      .asSquare()
                      .output(tempFileUri)
                      .start(this);
          }else if(requestCode == Crop.REQUEST_CROP){
             //TODO: Send tempfileUri to server as new avatar
           Log.i(TAG,"Result set image");
            avatarView.setImageURI(Uri.fromFile(tempOutputFile));
           }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_profile,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.activity_profile_menuEdit){


                 changeState(STATE_EDITING);
                return true;


        }

        return false;
    }

    private void changeState(int state) {
        if(state == currentState)
            return;

        currentState = state;

        if(state == STATE_VIEWING)
        {
            displayNameText.setEnabled(false);
            emailText.setEnabled(false);
            changeAvatarButton.setVisibility(View.VISIBLE);

            if(editProfileActionMode != null){
                editProfileActionMode.finish();
                editProfileActionMode = null;
            }
/*
*         <item name="colorPrimaryDark">@color/colorPrimary</item>
        <item name="colorAccent">@color/colorPrimary</item>
        <item name="spinBars">true</item>

        <item name="windowNoTitle">true</item>
        <item name="windowActionBar">false</item>

* */
        }
        else if(state == STATE_EDITING)
        {
            displayNameText.setEnabled(true);
            emailText.setEnabled(true);
            changeAvatarButton.setVisibility(View.GONE);
            editProfileActionMode = toolbar.startActionMode(new EditProfileActionCallback());

        }else

            throw  new IllegalArgumentException("Invalid state: "+state);

    }

    private class EditProfileActionCallback implements ActionMode.Callback {


        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.activity_edit,menu);
         //   toolbar.setVisibility(View.GONE);
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
          int itemId = item.getItemId();
            if(itemId == R.id.activity_profile_edit_menuDone){
              //TODO:Send request to update display name and email
                User user = application.getAuth().getUser();
                user.setDisplayName(displayNameText.getText().toString());
                user.setEmail(emailText.getText().toString());

                changeState(STATE_VIEWING);

                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
           // toolbar.setVisibility(View.INVISIBLE);
           if(currentState != STATE_VIEWING)
               changeState(STATE_VIEWING);
        }
    }
}



