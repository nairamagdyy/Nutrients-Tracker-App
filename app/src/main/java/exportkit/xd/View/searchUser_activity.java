package exportkit.xd.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import exportkit.xd.Controller.userController;
import exportkit.xd.DB.AppDBController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.Profile.IMyProfileView;
import exportkit.xd.View.Profile.myProfile_activity;
import exportkit.xd.View.Recipe.addRecipe_activity;

public class searchUser_activity extends Activity implements IMyProfileView {
    EditText username  ;
    userController UController ;
    User user  = null ;
    AppDBController db  ;
    ImageButton done , back   ;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_user);
        UController = new userController((IMyProfileView)this)  ;
        username = (EditText) findViewById(R.id.search);
        done = (ImageButton) findViewById(R.id.vector_ek1) ;
        back = (ImageButton) findViewById(R.id.backk) ;
        String Username = username.getText().toString();
        db = new AppDBController(this) ;
        List<User> userinfo = new ArrayList<>() ;
        userinfo = db.searchUser("alaafa");
        userinfo.forEach(user -> {
            System.out.println("Name : " + user.getName() + ", id : " + user.getId()); });

        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
                startActivity(nextScreen);
            }
        });
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
