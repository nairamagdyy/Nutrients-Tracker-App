package exportkit.xd.View.Profile;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

import exportkit.xd.Controller.userController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.IAppViews;
import exportkit.xd.View.Register.log_in_activity;
import exportkit.xd.View.Search.SearchUser_activity;
import exportkit.xd.View.adapter;
import exportkit.xd.View.homepage_activity;


public  class myProfile_activity extends Activity implements IAppViews {
    private CircularImageView uploadedImage, ProfileIcon;
    private TextView name , username ;
    private ImageButton HomeButton, editButton , logoutBtn ;
    private Button FavButton , SearchButton ;
    userController userController;
    RecyclerView dataList;
    List<String> titles;
    List<Integer> images;
    adapter Adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);

        userController = new userController(this) ;
        dataList = findViewById(R.id.dataList);

        titles = new ArrayList<>();
        images = new ArrayList<>();

        // finds views
        uploadedImage = findViewById(R.id.avatar);
        name = (TextView) findViewById(R.id.name);
        username = (TextView) findViewById(R.id.__tayshelby_ek2) ;
        FavButton = (Button) findViewById(R.id.FavoriteButton) ;
        editButton = (ImageButton) findViewById(R.id.edit11) ;
        logoutBtn= (ImageButton) findViewById(R.id._list_1) ;

        SearchButton = (Button) findViewById(R.id.ellipse_ek22);
        HomeButton = (ImageButton) findViewById(R.id.home_ek11);
        ProfileIcon = findViewById(R.id.ellipse_ek23);

        // get logged user
        SessionManager session = new SessionManager(this);
        long loggedUser= session.getUserFromSession();
        User user= userController.getUser((int)loggedUser);

        //display profile info
        if(user.getAvatar() != null) {
            uploadedImage.setImageURI(Uri.parse(user.getAvatar()));
            ProfileIcon.setImageURI(Uri.parse(user.getAvatar()));
        }
        name.setText(user.getName());
        username.setText(user.getUsername());

        // buttons functions
        HomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
                startActivity(nextScreen);
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                session.logoutUserFromSession();
                Intent nextScreen = new Intent(getApplicationContext(), log_in_activity.class);
                startActivity(nextScreen);
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), editProfileActivity.class);
                startActivity(nextScreen);
            }
        });
        FavButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), myFavorites_activity.class);
                startActivity(nextScreen);
            }
        });
        SearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), SearchUser_activity.class);
                startActivity(nextScreen);

            }
        });

        //Recyclerview as GridView for recipes
        titles.add("First Item");
        titles.add("Second Item");
        titles.add("Third Item");

        images.add(R.drawable.rectangle_188_ek1);
        images.add(R.drawable.rectangle_188_ek1);
        images.add(R.drawable.rectangle_188_ek1);

        Adapter = new adapter(this,titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(Adapter);

    }
    @Override
    public void onSuccess(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
        Intent nextScreen = new Intent(getApplicationContext(), myProfile_activity.class);
        startActivity(nextScreen);
    }
    @Override
    public void onError(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();
    }

}
