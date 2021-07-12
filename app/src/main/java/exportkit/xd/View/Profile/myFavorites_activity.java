package exportkit.xd.View.Profile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import exportkit.xd.Controller.userController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.IAppViews;
import exportkit.xd.View.Register.log_in_activity;
import exportkit.xd.View.Search.SearchUser_activity;
import exportkit.xd.View.homepage_activity;

public class myFavorites_activity extends Activity implements IAppViews {
    private CircularImageView uploadedImage, ProfileIcon;
    private TextView name , username;
    userController userController;
    private ImageButton Homebutton, logoutBtn, editButton;
    Button recipesbtn, SearchButton ;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_favourite);

        userController = new userController(this);
        // find views
        uploadedImage = findViewById(R.id.profilepic);
        name = (TextView) findViewById(R.id.name);
        username = (TextView) findViewById(R.id.__tayshelby_ek2) ;
        recipesbtn = (Button) findViewById(R.id.recipes) ;
        editButton = (ImageButton) findViewById(R.id.edit11) ;
        logoutBtn= (ImageButton) findViewById(R.id._list_1) ;

        Homebutton = (ImageButton) findViewById(R.id.home_ek11);
        SearchButton = (Button) findViewById(R.id.ellipse_ek22);
        ProfileIcon = findViewById(R.id.ellipse_ek23);

        // get logged user
        SessionManager session = new SessionManager(this);
        long loggedUser= session.getUserFromSession();
        User user= userController.getUser((int)loggedUser);

        //display profile info
        if(user.getAvatar() != null) {
            uploadedImage.setImageURI(Uri.parse(user.getAvatar()));
            ProfileIcon.setImageURI(Uri.parse(user.getAvatar()));
        }
        name.setText(user.getName());
        username.setText(user.getUsername());

        recipesbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), myProfile_activity.class);
                startActivity(nextScreen);

            }
        });
        Homebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
                startActivity(nextScreen);
            }
        });
        SearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), SearchUser_activity.class);
                startActivity(nextScreen);
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                session.logoutUserFromSession();
                Intent nextScreen = new Intent(getApplicationContext(), log_in_activity.class);
                startActivity(nextScreen);
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), editProfileActivity.class);
                startActivity(nextScreen);
            }
        });

    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
        Intent nextScreen = new Intent(getApplicationContext(), myProfile_activity.class);
        startActivity(nextScreen);
    }
    @Override
    public void onError(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();

    }

}
