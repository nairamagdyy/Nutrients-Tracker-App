package exportkit.xd.View.Recipe;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import java.util.Vector;

import exportkit.xd.Controller.RecipeController;
import exportkit.xd.Controller.UserController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.IAppViews;
import exportkit.xd.View.Profile.Profile_activity;
import exportkit.xd.View.Search.SearchUser_activity;
import exportkit.xd.View.Scanner.Camera;
import exportkit.xd.View.Homepage_activity;

public class MacroTracker_ extends Camera implements IAppViews {

    private CircularImageView ProfileButton;
    private ImageView photo;
    private Button SearchButton, recipeDetailsButton,
            increaseFats, increaseCarbs, increaseProteins,
            decreaseFats, decreaseCarbs, decreaseProteins;
    private ImageButton HomeButton;
    private TextView fats, carbs, protein;

    UserController userController;
    RecipeController recipeController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.macro_tracker);

        userController= new UserController(this);
        recipeController= new RecipeController(this);

        //get logged user
        SessionManager session= new SessionManager(this);
        long loggedUserID= session.getUserFromSession();
        User user= userController.getUser((int)loggedUserID);

        //retrieve recipe id
        int recipeId= getIntent().getExtras().getInt("recipeID");
        String PROFILE_KEY= getIntent().getExtras().getString("IProfile");
        String image= getIntent().getExtras().getString("image");

        //find views
        photo= findViewById(R.id.recipeImage);
        fats= findViewById(R.id.fatsMeasure);
        carbs= findViewById(R.id.carbsMeasure);
        protein= findViewById(R.id.proteinsMeasure);

        increaseFats= findViewById(R.id.increaseFats);
        increaseCarbs= findViewById(R.id.increaseCarbs);
        increaseProteins= findViewById(R.id.increaseProteins);

        decreaseFats= findViewById(R.id.decreaseFats);
        decreaseCarbs= findViewById(R.id.decreaseCarbs);
        decreaseProteins= findViewById(R.id.decreaseProteins);

        recipeDetailsButton= findViewById(R.id.recipeDetailsTap);
        ProfileButton = findViewById(R.id.profile1);
        SearchButton = (Button) findViewById(R.id.search1);
        HomeButton =  findViewById(R.id.home1);


        //set profile icon for logged user
        if(user.getAvatar() != null){
            ProfileButton.setImageURI(Uri.parse(user.getAvatar()));
        }
        if(image.equals("null")) {
            photo.setImageResource(R.drawable.recipeimage);
        }else {
            photo.setImageURI(Uri.parse(image));
        }

        //get recipe nutrients details
        Vector<String> macros= recipeController.getRecipeNutrients(recipeId);
        for(int i=0; i<macros.size(); i++){
            String[] split= macros.get(i).split(":");
            if(split[0].equals("Fats"))
                fats.setText(String.format("%.2f",Double.parseDouble(split[1].trim()))+"g");
            else if(split[0].equals("Carbs"))
                carbs.setText(String.format("%.2f",Double.parseDouble(split[1].trim()))+"g");
            else if(split[0].equals("Protein"))
                protein.setText(String.format("%.2f",Double.parseDouble(split[1].trim()))+"g");
        }

        increaseFats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fats_();
            }
        });
        decreaseFats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Tips_activity.class);
                startActivity(nextScreen);
            }
        });
        increaseCarbs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Tips_activity.class);
                startActivity(nextScreen);
            }
        });
        decreaseCarbs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Tips_activity.class);
                startActivity(nextScreen);
            }
        });
        increaseProteins.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Tips_activity.class);
                startActivity(nextScreen);
            }
        });
        decreaseProteins.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Tips_activity.class);
                startActivity(nextScreen);
            }
        });
        recipeDetailsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), RecipeDetails_activity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", recipeId);
                bundle.putString("IProfile",PROFILE_KEY);
                if(!PROFILE_KEY.equals("myProfile"))
                    bundle.putInt("userId",getIntent().getExtras().getInt("userId"));
                nextScreen.putExtras(bundle);
                startActivity(nextScreen);
            }
        });
        ProfileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Profile_activity.class);
                startActivity(nextScreen);
            }
        });
        SearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), SearchUser_activity.class);
                startActivity(nextScreen);
            }
        });
        HomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Homepage_activity.class);
                startActivity(nextScreen);

            }
        });
    }
    private void fats_() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Tips");
        dialogBuilder.setMessage("Some informative message for the user to do that.");
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogBuilder.create().show();
    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void onError(String message) {

    }
}
