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

import exportkit.xd.Controller.recipeController;
import exportkit.xd.Controller.userController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.Recipe;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.IAppViews;
import exportkit.xd.View.Profile.myProfile_activity;
import exportkit.xd.View.Search.SearchUser_activity;
import exportkit.xd.View.Search.userprofile_Search;
import exportkit.xd.View.homepage_activity;

public class recipeDetails_activity extends Activity implements IAppViews {
    recipeController RecipeController;
    userController UserController;
    ImageView image;
    TextView name, description, ingredient;
    private ImageButton editButton, HomeButton, backButton;
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
        int id= getIntent().getExtras().getInt("id");
        String PROFILE_KEY= getIntent().getExtras().getString("profile");

        // finds views
        image= findViewById(R.id.food_picture);
        name= findViewById(R.id.foodName);
        description= findViewById(R.id.getDescription);
        ingredient= findViewById(R.id.ingredients);

        backButton = findViewById(R.id.back);
        editButton= findViewById(R.id.edit);
        SearchButton= findViewById(R.id.ellipse_ek22);
        HomeButton= findViewById(R.id.home_ek11);
        ProfileIcon= findViewById(R.id.ellipse_ek23);

        //display profile icon
        if(user.getAvatar() != null) {
            ProfileIcon.setImageURI(Uri.parse(user.getAvatar()));
        }
        //select delete icon or fav icon
        if(PROFILE_KEY.equals("myProfile"))
            editButton.setImageResource(R.drawable.ic_delete);
        else
            editButton.setImageResource(R.drawable.star_1);


        //get recipe info from db
        Recipe recipe= RecipeController.getRecipe(id);

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
                     if(PROFILE_KEY.equals("myProfile"))
                         RecipeController.deleteRecipe(id);
                     else {}
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen;
                if(PROFILE_KEY.equals("myProfile"))
                    nextScreen= new Intent(getApplicationContext(), myProfile_activity.class);
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
                Intent nextScreen = new Intent(getApplicationContext(), myProfile_activity.class);
                startActivity(nextScreen);
            }
        });
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
