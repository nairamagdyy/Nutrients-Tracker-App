package exportkit.xd.View.Search;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import exportkit.xd.Controller.userController;
import exportkit.xd.DB.AppDBController;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.Profile.IMyProfileView;
import exportkit.xd.View.Profile.myProfile_activity;
import exportkit.xd.View.Search.userprofile_Search;
import exportkit.xd.View.homepage_activity;

public class SearchUser_activity extends Activity implements ISearchView {
    EditText username  ;
    userController UController ;
    ImageButton done , back   ;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_user);
        // find views
        UController = new userController((ISearchView)this)  ;
        username = (EditText) findViewById(R.id.search);
        done = (ImageButton) findViewById(R.id.vector_ek1) ;
        back = (ImageButton) findViewById(R.id.backk) ;
        /*
        userinfo.forEach(user -> {
            System.out.println("Name : " + user.getName() + ", id : " + user.getId()); });
            */
        // buttons functions
        done.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String Username = username.getText().toString();
                UController.SearchUser(Username) ;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
                startActivity(nextScreen);
            }
        });
        //custom code goes here
    }


    @Override
    public void onSearchSuccess(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
        Intent nextScreen = new Intent(getApplicationContext(), userprofile_Search.class);
        startActivity(nextScreen);
    }

    @Override
    public void onSearchError(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();
    }
}