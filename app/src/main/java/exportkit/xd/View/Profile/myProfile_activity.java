package exportkit.xd.View.Profile;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import exportkit.xd.DB.AppDBController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.R;


public class myProfile_activity extends Activity {
    String Name,UserName,Gender,PhoneNumber,Email,Password;
    private TextView name;
    User user;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);
/*
        name = (TextView) findViewById(R.id.textView);

        SessionManager s= new SessionManager(this);
        long id= s.getUserFromSession();
        name.setText("nadaaaa   "+id);
*/
        /*
        AppDBController db= new AppDBController(this);
        db.Register(user);
        Intent intent = getIntent();
        Name= intent.getStringExtra("name");
        name= (TextView) findViewById(R.id.name_);
        name.setText(Name);
         */
    }

}
