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
import exportkit.xd.View.Profile.myProfile_activity;
<<<<<<< HEAD:app/src/main/java/exportkit/xd/View/Search/SearchUser_activity.java
import exportkit.xd.View.Search.userprofile_Search;
import exportkit.xd.View.homepage_activity;

public class SearchUser_activity extends Activity implements ISearchView {
=======

public class searchUser_activity extends Activity implements IAppViews {
>>>>>>> 5b390090549718aa3a33872c6a07cf6858aaf6e6:app/src/main/java/exportkit/xd/View/searchUser_activity.java
    EditText username  ;
    userController UController ;
    ImageButton done , back   ;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_user);
<<<<<<< HEAD:app/src/main/java/exportkit/xd/View/Search/SearchUser_activity.java
        // find views
        UController = new userController((ISearchView)this)  ;
=======
        UController = new userController(this)  ;
>>>>>>> 5b390090549718aa3a33872c6a07cf6858aaf6e6:app/src/main/java/exportkit/xd/View/searchUser_activity.java
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
<<<<<<< HEAD:app/src/main/java/exportkit/xd/View/Search/SearchUser_activity.java
    public void onSearchSuccess(String message) {
=======
    public void onSuccess(String message) {
>>>>>>> 5b390090549718aa3a33872c6a07cf6858aaf6e6:app/src/main/java/exportkit/xd/View/searchUser_activity.java
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
        Intent nextScreen = new Intent(getApplicationContext(), userprofile_Search.class);
        startActivity(nextScreen);
    }

    @Override
<<<<<<< HEAD:app/src/main/java/exportkit/xd/View/Search/SearchUser_activity.java
    public void onSearchError(String message) {
=======
    public void onError(String message) {
>>>>>>> 5b390090549718aa3a33872c6a07cf6858aaf6e6:app/src/main/java/exportkit/xd/View/searchUser_activity.java
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();
    }
}