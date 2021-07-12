package exportkit.xd.View.Profile;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import exportkit.xd.Controller.userController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.R;
import exportkit.xd.View.Register.log_in_activity;
import exportkit.xd.View.homepage_activity;
import exportkit.xd.View.searchUser_activity;


public  class myProfile_activity extends Activity implements IMyProfileView  {
    private TextView name , username ;
    private ImageButton Homebutton , editButton , logoutBtn ;
    private Button FavButton , SearchButton ;
    userController UController;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);
        // finds views
        Homebutton = (ImageButton) findViewById(R.id.home_ek11);
        FavButton = (Button) findViewById(R.id.FavoriteButton) ;
        SearchButton = (Button) findViewById(R.id.ellipse_ek22);
        editButton = (ImageButton) findViewById(R.id.edit11) ;
        logoutBtn= (ImageButton) findViewById(R.id._list_1) ;
        name = (TextView) findViewById(R.id.name);
        username = (TextView) findViewById(R.id.__tayshelby_ek2) ;
        UController = new userController((IMyProfileView)this) ;
        // get logged user
        SessionManager s = new SessionManager(this);
        long id= s.getUserFromSession();
        name.setText(UController.GetName((int) id));
        username.setText(UController.GetUserName((int) id));
        // buttons functions
        Homebutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
                startActivity(nextScreen);

            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                s.logoutUserFromSession();
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
        FavButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), myFavorites_activity.class);
                startActivity(nextScreen);

            }
        });
        SearchButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), searchUser_activity.class);
                startActivity(nextScreen);

            }
        });
    }
    @Override
    public void onEditSuccess(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
        Intent nextScreen = new Intent(getApplicationContext(), myProfile_activity.class);
        startActivity(nextScreen);
    }
    @Override
    public void onEditError(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();

    }

}
