package exportkit.xd.View.Recipe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import exportkit.xd.Controller.IRecipeController;
import exportkit.xd.Controller.recipeController;

import exportkit.xd.R;
import exportkit.xd.View.macroTracker_activity;

public class addRecipe_activity extends Activity  implements IAddRecipeView {
    IRecipeController RecipeController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecipe_one);
        RecipeController = new recipeController(this);
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
}
