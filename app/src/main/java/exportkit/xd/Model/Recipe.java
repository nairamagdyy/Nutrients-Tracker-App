package exportkit.xd.Model;

import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.Vector;

public class Recipe {
    int id ;
    String name ;
    HashMap<String,Double> ingredients;
    Vector<String> steps;
    User cook;
    HashMap<String,Double> nutrientsFacts;
    String description;
    String image;

    //---------------------------------------------------------------------------------------------
    public Recipe(User user, String name,  HashMap<String,Double> ingredients)
    {
        this.name = name;
        this.cook = user;
        this.ingredients = ingredients;
    }

    //---------------------------------------Setter-------------------------------------------------
    public void setImage( String image){
        this.image= image;
    }
    public void setSteps(Vector<String> steps){
        this.steps = steps;
    }
    public void setFacts(HashMap<String,Double> nutrients){
        this.nutrientsFacts= nutrients;
    }
    public void setDescription(String description){
        this.description= description;
    }
    public void setId(int id){
        this.id = id;
    }
    //---------------------------------------Getter-------------------------------------------------
    //getter
    public int getId() {
        return id;
    }
    public User getUser() {
        return cook;
    }
    public String getName() {
        return name;
    }
    public HashMap<String,Double> getIngredients() {
        return ingredients;
    }
    public String getImage() {
        return image;
    }
    public Vector<String> getSteps() {
        return steps;
    }
    public HashMap<String, Double> getFacts() {
        return nutrientsFacts;
    }
    public String getDescription(){
        return description;
    }
}
