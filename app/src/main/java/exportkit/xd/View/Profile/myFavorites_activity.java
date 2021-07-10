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
import exportkit.xd.R;
import exportkit.xd.View.homepage_activity;

public class myFavorites_activity extends Activity {
    String Name,UserName;
    private TextView name , username ;
    AppDBController db ;
    SessionManager s ;
    private ImageButton Homebutton;
    Button recipesbtn ;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_favourite);
        name = (TextView) findViewById(R.id.name);
        recipesbtn = (Button) findViewById(R.id.recipes_ek2) ;
        Homebutton = (ImageButton) findViewById(R.id.home_ek11);
        username = (TextView) findViewById(R.id.__tayshelby_ek2) ;
        db= new AppDBController(this);
        s = new SessionManager(this);
        long id= s.getUserFromSession();
        Name =  db.GetName((int) id)  ;
        UserName  = db.GetUserName((int) id) ;
        name.setText(Name);
        username.setText(UserName);
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
}
