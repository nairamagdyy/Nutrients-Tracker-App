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

import exportkit.xd.Controller.RecipeController;
import exportkit.xd.Controller.UserController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.Recipe;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.Homepage_activity;
import exportkit.xd.View.IAppViews;
import exportkit.xd.View.Profile.Profile_activity;
import exportkit.xd.View.Profile.UserProfile_activity;
import exportkit.xd.View.Search.SearchUser_activity;

public class RecipeDetails_activity extends Activity implements IAppViews {
    RecipeController recipeController;
    UserController userController;
    ImageView image;
    TextView name, description, ingredient, nutrients;
    private ImageButton editButton, HomeButton, backButton, favButton;
    private Button SearchButton, MacroTracker;
    private CircularImageView ProfileIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_details);

        recipeController = new RecipeController(this);
        userController = new UserController(this);

        // get logged user
        SessionManager session = new SessionManager(this);
        long loggedUser= session.getUserFromSession();
        User user= userController.getUser((int)loggedUser);

        //retrieve recipe id
        int recipeId= getIntent().getExtras().getInt("id");
        String PROFILE_KEY= getIntent().getExtras().getString("IProfile");

        // finds views
        image= findViewById(R.id.food_picture);
        name= findViewById(R.id.foodName);
        description= findViewById(R.id.getDescription);
        ingredient= findViewById(R.id.ingredients);
        nutrients= findViewById(R.id.nutritionFacts);

        backButton = findViewById(R.id.back);
        editButton= findViewById(R.id.edit);
        favButton= findViewById(R.id.favorite);
        MacroTracker= findViewById(R.id.macroTrackerTap);
        SearchButton= findViewById(R.id.ellipse_ek22);
        HomeButton= findViewById(R.id.home_ek11);
        ProfileIcon= findViewById(R.id.profile1);

        //display IProfile icon
        if(user.getAvatar() != null) {
            ProfileIcon.setImageURI(Uri.parse(user.getAvatar()));
        }
        //if current logged user IProfile -> already show delete icon
        //if another user IProfile -> is it in my favList -> show favButton; else show star icon to can add it to favList
        if(!PROFILE_KEY.equals("myProfile")){
            Vector<Integer> favList=  recipeController.viewFavList((int)loggedUser);
            if(favList.contains(recipeId)){ //already added into favList
                editButton.setVisibility(View.GONE);
                favButton.setVisibility(View.VISIBLE);
            }else
                editButton.setImageResource(R.drawable.star_1);
        }

        //get recipe info from db
        Recipe recipe= recipeController.getRecipe(recipeId);

        //display info
        if(recipe.getImage().equals("null")) {
            image.setImageResource(R.drawable.recipeimage);
        }else {
            image.setImageURI(Uri.parse(recipe.getImage()));
        }
        name.setText(recipe.getName());
        description.setText(recipe.getDescription());
        ingredient.setText(recipe.getIngredients());
        //get facts information
        Vector<String> facts= recipeController.getRecipeNutrients(recipe.getNutrientsID());
        nutrients.setText(String.valueOf(facts));


        //buttons
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(PROFILE_KEY.equals("myProfile")) //delete recipe
                    recipeController.deleteRecipe(recipeId);

                else {//add to favoriteList
                    long id= recipeController.addToFavList((int) loggedUser, recipeId);
                    if(id>0) {
                        editButton.setVisibility(View.GONE);
                        favButton.setVisibility(View.VISIBLE);
                    }else
                        onError("FAILED!!");
                }
            }
        });
        favButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) { //remove from favorite List
                recipeController.unFavRecipe((int) loggedUser,recipeId);
                favButton.setVisibility(View.GONE);
                editButton.setImageResource(R.drawable.star_1);
                editButton.setVisibility(View.VISIBLE);
            }
        });
        MacroTracker.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) { //remove from favorite List
                Intent nextScreen = new Intent(getApplicationContext(), MacroTracker_activity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("recipeID",recipeId);
                bundle.putString("IProfile", PROFILE_KEY);
                if(!PROFILE_KEY.equals("myProfile"))
                    bundle.putInt("userId",getIntent().getExtras().getInt("userId"));
                bundle.putString("image",recipe.getImage());
                nextScreen.putExtras(bundle);
                startActivity(nextScreen);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen;
                if(PROFILE_KEY.equals("myProfile"))
                    nextScreen= new Intent(getApplicationContext(), Profile_activity.class);
                else {
                    nextScreen = new Intent(getApplicationContext(), UserProfile_activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",getIntent().getExtras().getInt("userId"));
                    nextScreen.putExtras(bundle);
                }
                startActivity(nextScreen);
            }
        });
        HomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Homepage_activity.class);
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
                Intent nextScreen = new Intent(getApplicationContext(), Profile_activity.class);
                startActivity(nextScreen);
            }
        });
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
        Intent nextScreen = new Intent(getApplicationContext(), Profile_activity.class);
        startActivity(nextScreen);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();

    }
}
