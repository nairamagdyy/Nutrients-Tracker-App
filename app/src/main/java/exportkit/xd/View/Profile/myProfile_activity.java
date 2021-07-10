package exportkit.xd.View.Profile;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import exportkit.xd.DB.AppDBController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.homepage_activity;
import exportkit.xd.View.myFavorites_activity;


public class myProfile_activity extends Activity {
    String Name,UserName;
    private TextView name , username ;
    AppDBController db ;
    private ImageButton Homebutton;
    private Button FavoriteButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);
        Homebutton = (ImageButton) findViewById(R.id.home_ek11);
        FavoriteButton = (Button) findViewById(R.id.FavoriteButton) ;
        db= new AppDBController(this);
        name = (TextView) findViewById(R.id.name);
        username = (TextView) findViewById(R.id.__tayshelby_ek2) ;
        SessionManager s= new SessionManager(this);
        long id= s.getUserFromSession();
        Name =  db.GetName((int) id)  ;
        UserName  = db.GetUserName((int) id) ;
        name.setText(Name);
        username.setText(UserName);
        Homebutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
                startActivity(nextScreen);

            }
        });
        FavoriteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), myFavorites_activity.class);
                startActivity(nextScreen);

            }
        });
    }

}
