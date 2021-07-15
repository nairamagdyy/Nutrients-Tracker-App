package exportkit.xd.View.Recipe;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import java.util.Vector;

import exportkit.xd.Controller.recipeController;
import exportkit.xd.Controller.userController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.Recipe;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.IAppViews;
import exportkit.xd.View.Profile.profile_activity;
import exportkit.xd.View.Search.SearchUser_activity;
import exportkit.xd.View.Search.userprofile_Search;
import exportkit.xd.View.homepage_activity;

public class recipeDetails_activity extends Activity implements IAppViews {
    recipeController RecipeController;
    userController UserController;
    ImageView image;
    TextView name, description, ingredient;
    private ImageButton editButton, HomeButton, backButton, favButton;
    private Button SearchButton;
    private CircularImageView ProfileIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_details);

        RecipeController= new recipeController(this);
        UserController= new userController(this);

        // get logged user
        SessionManager session = new SessionManager(this);
        long loggedUser= session.getUserFromSession();
        User user= UserController.getUser((int)loggedUser);

        //retrieve recipe id
        int recipeId= getIntent().getExtras().getInt("id");
        String PROFILE_KEY= getIntent().getExtras().getString("IProfile");

        // finds views
        image= findViewById(R.id.food_picture);
        name= findViewById(R.id.foodName);
        description= findViewById(R.id.getDescription);
        ingredient= findViewById(R.id.ingredients);

        backButton = findViewById(R.id.back);
        editButton= findViewById(R.id.edit);
        favButton= findViewById(R.id.favorite);
        SearchButton= findViewById(R.id.ellipse_ek22);
        HomeButton= findViewById(R.id.home_ek11);
        ProfileIcon= findViewById(R.id.ellipse_ek23);

        //display IProfile icon
        if(user.getAvatar() != null) {
            ProfileIcon.setImageURI(Uri.parse(user.getAvatar()));
        }
         //if is it current logged user IProfile -> already show delete icon
        //if is another user IProfile -> is it in my favList -> show favButton; else show star icon to can add it to favList
        if(!PROFILE_KEY.equals("myProfile")){
            Vector<Integer> favList=  RecipeController.viewFavList((int)loggedUser);
            if(favList.contains(recipeId)){ //already added into favList
                editButton.setVisibility(View.GONE);
                favButton.setVisibility(View.VISIBLE);
            }else
                editButton.setImageResource(R.drawable.star_1);
        }

        //get recipe info from db
        Recipe recipe= RecipeController.getRecipe(recipeId);

        //display info
        if(recipe.getImage().equals("null"))
            image.setImageResource(R.drawable.recipeimage);
        else
            image.setImageURI(Uri.parse(recipe.getImage()));
        name.setText(recipe.getName());
        description.setText(recipe.getDescription());
        ingredient.setText(recipe.getIngredients());

        //buttons
        editButton.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 if(PROFILE_KEY.equals("myProfile")) //delete recipe
                     RecipeController.deleteRecipe(recipeId);

                 else {//add to favoriteList
                     long id= RecipeController.addToFavList((int) loggedUser, recipeId);
                     if(id>0) {
                         editButton.setVisibility(View.GONE);
                         favButton.setVisibility(View.VISIBLE);
                     }else
                         onError("FAILED");
                 }
             }
        });
        favButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) { //remove from favorite List
                RecipeController.unFavRecipe((int) loggedUser,recipeId);
                favButton.setVisibility(View.GONE);
                editButton.setImageResource(R.drawable.star_1);
                editButton.setVisibility(View.VISIBLE);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen;
                if(PROFILE_KEY.equals("myProfile"))
                    nextScreen= new Intent(getApplicationContext(), profile_activity.class);
                else {
                    nextScreen = new Intent(getApplicationContext(), userprofile_Search.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",getIntent().getExtras().getInt("userId"));
                    nextScreen.putExtras(bundle);
                }
                startActivity(nextScreen);
            }
        });
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
                Intent nextScreen = new Intent(getApplicationContext(), profile_activity.class);
                startActivity(nextScreen);
            }
        });
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
        Intent nextScreen = new Intent(getApplicationContext(), profile_activity.class);
        startActivity(nextScreen);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();

    }
}
