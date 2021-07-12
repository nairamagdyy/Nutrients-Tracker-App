package exportkit.xd.Controller;

import android.content.Context;

import exportkit.xd.DB.AppDBController;
import exportkit.xd.Model.Recipe;
import exportkit.xd.View.IAppViews;

public class recipeController {

    IAppViews addRecipeView;
    AppDBController db;

    public recipeController(IAppViews view) {
        this.addRecipeView = view;
        db = new AppDBController((Context) this.addRecipeView);
    }

    public void addRecipe(Recipe recipe) {
        long id = db.insertRecipe(recipe);
        if(id>0) {
            addRecipeView.onSuccess("Add Successfully");
        }
        else
            addRecipeView.onError("FAILED");
    }
}
