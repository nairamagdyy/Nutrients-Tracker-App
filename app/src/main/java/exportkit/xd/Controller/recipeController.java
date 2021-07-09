package exportkit.xd.Controller;

import android.content.Context;

import exportkit.xd.DB.AppDBController;
import exportkit.xd.View.Recipe.IAddRecipeView;

public class recipeController implements IRecipeController {

    IAddRecipeView addRecipeView;
    AppDBController db;

    public recipeController(IAddRecipeView view) {
        this.addRecipeView = view;
        db = new AppDBController((Context) this.addRecipeView);
    }
}
