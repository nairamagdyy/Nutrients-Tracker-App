package exportkit.xd.View.Recipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import exportkit.xd.Controller.IRecipeController;
import exportkit.xd.Controller.cameraController;
import exportkit.xd.Controller.recipeController;

import exportkit.xd.R;
import exportkit.xd.View.camera_activity;
import exportkit.xd.View.macroTracker_activity;

public class addRecipe_activity extends camera_activity implements IAddRecipeView {
    //-----dynamic view
    LinearLayout newLayer;
    Button buttonAdd;

    IRecipeController RecipeController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecipe_one);

        //dynamic view
        buttonAdd = findViewById(R.id.ingredient);
        newLayer = findViewById(R.id.linearLayout1);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ingredient:
                        addIngredient();
                }

            };
        });

        //
        RecipeController = new recipeController(this);
        CamController = new cameraController(this);

        uploadedImage= (CircularImageView)findViewById(R.id.uploadImage);


        uploadedImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //show image pick dialog
                CamController.imagePickDialog();
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
