package exportkit.xd.View.Recipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import exportkit.xd.Controller.IRecipeController;
import exportkit.xd.Controller.cameraController;
import exportkit.xd.Controller.recipeController;

import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.Recipe;
import exportkit.xd.R;
import exportkit.xd.View.camera_activity;
import exportkit.xd.View.homepage_activity;
import exportkit.xd.View.macroTracker_activity;

public class addRecipe_activity extends camera_activity implements IAddRecipeView {
    //dynamic view
    LinearLayout newLayer;
    Button dynamicAddBtn;

    //variables
    private TextView name, description, ingredients;
    private Button saveBtn, cancelBtn;
    private Recipe recipe;

    IRecipeController RecipeController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe);

        //dynamic view
        dynamicAddBtn = findViewById(R.id.addIngredientBtn);
        newLayer = findViewById(R.id.linearLayout1);
        dynamicAddBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.addIngredientBtn:
                        addIngredient();
                }

            };
        });

        //get logged user
        SessionManager session= new SessionManager(this);
        int loggedUser = (int) session.getUserFromSession();
        recipe= new Recipe(loggedUser);

        //start to get input
        RecipeController = new recipeController(this);
        CamController = new cameraController(this);

        //get inputs
        uploadedImage= (CircularImageView)findViewById(R.id.uploadImage);
        name= findViewById(R.id.enter_food_name);
        description= findViewById(R.id.enter_description);
        ingredients= findViewById(R.id.enter_ingredient);
        saveBtn = findViewById(R.id.add_recipe);
        cancelBtn= findViewById(R.id.cancel);

        uploadedImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //show image pick dialog
                CamController.imagePickDialog();
                recipe.setImage(""+CamController.imageUri);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String recipeName= name.getText().toString().trim(),
                        recipeDescription= ""+description.getText().toString().trim(),
                        recipeIngredients= ingredients.getText().toString().trim();

                if (recipeName.equalsIgnoreCase("")  || recipeIngredients.equals(""))
                    Toast.makeText(getApplication(),"you should fill the empty fields",Toast.LENGTH_LONG).show();
                else
                {
                    recipe.setName(recipeName);
                    recipe.setDescription(recipeDescription);
                    recipe.setIngredients(recipeIngredients);
                    RecipeController.addRecipe(recipe);
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
                startActivity(nextScreen);
            }
        });
    }

    @Override
    public void onAddSuccess(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
        Intent nextScreen = new Intent(getApplicationContext(), macroTracker_activity.class);
        startActivity(nextScreen);
    }

    @Override
    public void onAddError(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();

    }

    //----------------------------------------------------------------------------------------------

    private void addIngredient() {
        View view = getLayoutInflater().inflate(R.layout.hidden, null);
        newLayer.addView(view);
    }

}
