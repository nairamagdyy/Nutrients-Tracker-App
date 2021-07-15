package exportkit.xd.Controller;

import android.content.Context;
import android.widget.Toast;

import java.util.Vector;

import exportkit.xd.DB.AppDBController;
import exportkit.xd.Model.Recipe;
import exportkit.xd.R;
import exportkit.xd.View.IAppViews;

public class recipeController {

    IAppViews view;
    AppDBController db;

    public recipeController(IAppViews view) {
        this.view = view;
        db = new AppDBController((Context) this.view);
    }

    public void addRecipe(Recipe recipe) {
        long id = db.insertRecipe(recipe);
        if(id>0) {
            view.onSuccess("Add Successfully");
        }
        else
            view.onError("FAILED");
    }

    public Vector<Integer> viewRecipeList(int userId){
        return db.getRecipeList(userId);
    }

    public Recipe getRecipe(int id){
        return  db.getRecipe(id);
    }

    public void deleteRecipe(int id){
        boolean remove= db.deleteRecipe(id);
        if(remove) {
            view.onSuccess("Delete Successfully");
        }
        else
            view.onError("FAILED");

    }

    public long addToFavList(int userID, int recipeID){
        return db.insertToFavList(userID, recipeID);
    }

    public Vector<Integer> viewFavList(int userId){
        return db.getFavList(userId);
    }

    public void unFavRecipe(int userId, int recipeId){
        boolean remove= false; //db.deleteRecipe(id);
        if(remove) {
            view.onSuccess("Un Favorite Recipe");
        }
        else
            view.onError("FAILED");

    }
}
