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
    String Name,UserName;
    private TextView name , username;
    AppDBController db ;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);
        db= new AppDBController(this);
        name = (TextView) findViewById(R.id.name);
        username = (TextView) findViewById(R.id.__tayshelby_ek2) ;
        SessionManager s= new SessionManager(this);
        long id= s.getUserFromSession();
        Name =  db.GetName((int) id)  ;
        UserName  = db.GetUserName((int) id) ;
        name.setText(Name);
        username.setText(UserName);

    }

}
