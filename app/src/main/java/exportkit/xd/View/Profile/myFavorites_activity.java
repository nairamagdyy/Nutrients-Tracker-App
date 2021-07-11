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

public class myFavorites_activity extends Activity implements IMyProfileView  {
    private TextView name , username ;
    SessionManager s ;
    userController UController;
    private ImageButton Homebutton;
    Button recipesbtn ;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_favourite);
        name = (TextView) findViewById(R.id.name);
        recipesbtn = (Button) findViewById(R.id.recipes_ek2) ;
        Homebutton = (ImageButton) findViewById(R.id.home_ek11);
        username = (TextView) findViewById(R.id.__tayshelby_ek2) ;
        s = new SessionManager(this);
        UController = new userController((IMyProfileView) this);
        long id= s.getUserFromSession();
        name.setText(UController.GetName((int) id));
        username.setText(UController.GetUserName((int) id));
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
