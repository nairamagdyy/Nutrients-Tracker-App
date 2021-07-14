package exportkit.xd.View.Search;
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
import java.util.Vector;

import exportkit.xd.Controller.userController;
import exportkit.xd.Controller.recipeController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.Recipe;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.IAppViews;
import exportkit.xd.View.Profile.IProfile;
import exportkit.xd.View.Profile.myProfile_activity;
import exportkit.xd.View.Recipe.recipeDetails_activity;
import exportkit.xd.View.adapter;
import exportkit.xd.View.homepage_activity;

public  class userprofile_Search extends Activity implements IProfile, IAppViews {
    private CircularImageView uploadedImage, ProfileIcon;
    private TextView name , username ;
    private ImageButton HomeButton, editButton , logoutBtn ;
    private Button FavButton , SearchButton;

    int userID;
    userController UserController;
    recipeController RecipeController;

    RecyclerView recycleRecipeList;
    List<String>  recipeNameList = new ArrayList<>();
    List<String> recipeImageList = new ArrayList<>();
    adapter Adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);

        UserController = new userController(this);
        RecipeController= new recipeController(this);

        // finds views
        recycleRecipeList = findViewById(R.id.recipeList);
        uploadedImage = findViewById(R.id.avatar);
        name = (TextView) findViewById(R.id.name);
        username = (TextView) findViewById(R.id.__tayshelby_ek2) ;
        FavButton = (Button) findViewById(R.id.FavoriteButton) ;
        editButton = (ImageButton) findViewById(R.id.edit11) ;
        logoutBtn= (ImageButton) findViewById(R.id._list_1) ;

        SearchButton = (Button) findViewById(R.id.ellipse_ek22);
        HomeButton = (ImageButton) findViewById(R.id.home_ek11);
        ProfileIcon = findViewById(R.id.ellipse_ek23);

        //hide unuseful buttons
        FavButton.setVisibility(View.GONE);
        editButton.setVisibility(View.GONE);
        logoutBtn.setVisibility(View.GONE);
        findViewById(R.id.logout22).setVisibility(View.GONE);

        // get logged user
        SessionManager session = new SessionManager(this);
        long loggedUser= session.getUserFromSession();
        User user= UserController.getUser((int)loggedUser);
        if(user.getAvatar() != null) {
            ProfileIcon.setImageURI(Uri.parse(user.getAvatar()));
        }

        //retrieve user id
        userID= getIntent().getExtras().getInt("id");
        user= UserController.getUser(userID);

        //display profile info
        if(user.getAvatar() != null) {
            uploadedImage.setImageURI(Uri.parse(user.getAvatar()));
        }
        name.setText(user.getName());
        username.setText(user.getUsername());

        //Recyclerview as GridView for recipes
        //get user recipe from db
        Vector<Integer> recipesIdList= RecipeController.viewRecipeList(userID);
        for(int i=0; i<recipesIdList.size();i++){
            Recipe recipe= RecipeController.getRecipe(recipesIdList.get(i));

            recipeNameList.add(recipe.getName());
            recipeImageList.add(recipe.getImage());
        }

        Adapter = new adapter(this, recipesIdList, recipeNameList, recipeImageList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false);
        recycleRecipeList.setLayoutManager(gridLayoutManager);
        recycleRecipeList.setAdapter(Adapter);

        // buttons functions
        HomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
                startActivity(nextScreen);
            }
        });
        SearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), SearchUser_activity.class);
                startActivity(nextScreen);

            }
        });
        ProfileIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), myProfile_activity.class);
                startActivity(nextScreen);
            }
        });

    }

    @Override
    public void viewRecipeDetails(int id) {
        Intent nextScreen = new Intent(getApplicationContext(), recipeDetails_activity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putString("profile","searchProfile");
        bundle.putInt("userId", userID);
        nextScreen.putExtras(bundle);
        startActivity(nextScreen);
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
