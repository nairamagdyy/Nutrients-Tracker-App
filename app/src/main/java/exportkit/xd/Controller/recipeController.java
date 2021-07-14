package exportkit.xd.Controller;

import android.content.Context;

import java.util.Vector;

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

    public Vector<Integer> viewRecipeList(int userId){
        return db.getRecipeList(userId);
    }

    public Recipe getRecipe(int id){
        return  db.getRecipe(id);
    }
}
