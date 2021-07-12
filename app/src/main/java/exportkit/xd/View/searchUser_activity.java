package exportkit.xd.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import exportkit.xd.Controller.userController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.Profile.IMyProfileView;
import exportkit.xd.View.Profile.myProfile_activity;

public class searchUser_activity extends Activity implements IMyProfileView {
    EditText username  ;
    userController UController ;
    User user  = null ;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_user);
        UController = new userController((IMyProfileView)this)  ;
        username = (EditText) findViewById(R.id.search);
        String Username = username.getText().toString();
//        UController.SearchUser(Username) ;
  //      user = UController.SearchUser(Username) ;
  //      System.out.println(user.getName()) ;


        //custom code goes here
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
