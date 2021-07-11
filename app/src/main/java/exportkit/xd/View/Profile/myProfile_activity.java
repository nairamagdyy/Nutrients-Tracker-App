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
import exportkit.xd.DB.AppDBController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.R;
import exportkit.xd.View.homepage_activity;


public class myProfile_activity extends Activity implements IMyProfileView {
    private TextView name , username ;
    private ImageButton Homebutton;
    private Button FavButton;
    userController UController;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);
        Homebutton = (ImageButton) findViewById(R.id.home_ek11);
        FavButton = (Button) findViewById(R.id.FavoriteButton) ;
        UController = new userController((IMyProfileView)this) ;
        name = (TextView) findViewById(R.id.name);
        username = (TextView) findViewById(R.id.__tayshelby_ek2) ;
        SessionManager s= new SessionManager(this);
        long id= s.getUserFromSession();
        name.setText(UController.GetName((int) id));
        username.setText(UController.GetUserName((int) id));
        Homebutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
                startActivity(nextScreen);

            }
        });
        FavButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), myFavorites_activity.class);
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
