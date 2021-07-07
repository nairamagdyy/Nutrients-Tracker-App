package exportkit.xd.View;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import exportkit.xd.Controller.IUserController;
import exportkit.xd.Controller.database;
import exportkit.xd.Model.User;
import exportkit.xd.R;


public class profile_activity extends Activity {
    String Name,UserName,Gender,PhoneNumber,Email,Password;
    private TextView name;
    User user;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);
        database db= new database(this);
        db.Register(user);
        Intent intent = getIntent();
        Name= intent.getStringExtra("name");
        name= (TextView) findViewById(R.id.name_);
        name.setText(Name);
    }

}
