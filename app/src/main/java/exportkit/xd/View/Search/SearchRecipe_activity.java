package exportkit.xd.View.Search;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import java.util.List;

import exportkit.xd.Controller.RecipeController;
import exportkit.xd.Controller.UserController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.Recipe;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.Homepage_activity;
import exportkit.xd.View.IAppViews;
import exportkit.xd.View.Profile.Profile_activity;
import exportkit.xd.View.Recipe.RecipeDetails_activity;

public class SearchRecipe_activity extends Activity implements IAppViews {
    UserController userController;
    RecipeController recipeController;

    EditText recipeName;
    Button done , recipesButton , usersButton ;
    ImageButton back, HomeButton;
    int RecipeId ;

    private CircularImageView ProfileIcon;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_recipe);
        userController = new UserController(this);
        recipeController = new RecipeController(this);

        //find views
        recipeName = (EditText) findViewById(R.id.search);
        done = (Button) findViewById(R.id.ellipse_ek22);
        back = (ImageButton) findViewById(R.id.backk);
        HomeButton = (ImageButton) findViewById(R.id.home1);
        ProfileIcon = findViewById(R.id.profile1);
        recipesButton = (Button) findViewById(R.id.recipes);
        usersButton = (Button) findViewById(R.id.users) ;

        //display IProfile icon
        //get logged user
        SessionManager session = new SessionManager(this);
        long loggedUser= session.getUserFromSession();
        User user= userController.getUser((int)loggedUser);
        if(user.getAvatar() != null) {
            ProfileIcon.setImageURI(Uri.parse(user.getAvatar()));
        }

        // buttons Functions
        done.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {
                List<Recipe> recipeInfo = recipeController.SearchRecipe(recipeName.getText().toString());
                if (recipeInfo.isEmpty()) {
                    onError("Recipe Name Doesn't exist");
                }
                else
                {
                    RecipeId= recipeInfo.get(0).getId();
                    
                    onSuccess("");
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Homepage_activity.class);
                startActivity(nextScreen);
            }
        });
        HomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Homepage_activity.class);
                startActivity(nextScreen);
            }
        });
        usersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), SearchUser_activity.class);
                startActivity(nextScreen);
                Toast.makeText(getApplication(),"Now You are On Search User Mode",Toast.LENGTH_LONG).show();

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
        Intent nextScreen = new Intent(getApplicationContext(), RecipeDetails_activity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", RecipeId);
        bundle.putString("IProfile","myProfile");
        nextScreen.putExtras(bundle);
        startActivity(nextScreen);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();
    }
}
