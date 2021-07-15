package exportkit.xd.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import exportkit.xd.Controller.userController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.Profile.profile_activity;
import exportkit.xd.View.Search.SearchUser_activity;

public class macroTracker_activity extends camera_activity implements IAppViews {

    private CircularImageView ProfileButton;
    private Button SearchButton;
    private ImageButton HomeButton;

    exportkit.xd.Controller.userController userController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProfileButton = findViewById(R.id.ellipse_ek23);
        SearchButton = (Button) findViewById(R.id.ellipse_ek22);
        HomeButton =  findViewById(R.id.home1);

        userController = new userController(this) ;

        SessionManager session= new SessionManager(this);
        long loggedUserID= session.getUserFromSession();
        User user= userController.getUser((int)loggedUserID);
        //set current information for logged user
        if(user.getAvatar() != null){
            ProfileButton.setImageURI(Uri.parse(user.getAvatar()));
        }

        ProfileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), profile_activity.class);
                startActivity(nextScreen);
            }
        });
        SearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), SearchUser_activity.class);
                startActivity(nextScreen);
            }
        });
        HomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
                startActivity(nextScreen);

            }
        });




    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void onError(String message) {

    }
}
