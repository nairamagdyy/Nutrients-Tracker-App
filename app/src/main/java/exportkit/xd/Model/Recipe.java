package exportkit.xd.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class Recipe implements Serializable {
    int id ;
    String image;
    String name ;
    String description;
    String ingredients;
    String nutrientsFacts;
    int user; // foreign key to link between user and his recipes

    //----------------------------------------------------------------------------------------------
    public Recipe(int Userid)
    {
        this.user = Userid;
    }

    //---------------------------------------Setter-------------------------------------------------
    public void setId(int id) { this.id = id; }
    public void setImage(String image){ this.image= image;}
    public void setName(String name){this.name= name;}
    public void setDescription(String description){this.description= description;}
    public void setIngredients(String ingredients){ this.ingredients = ingredients; }
    public void setFacts(String nutrients){
        this.nutrientsFacts= nutrients;
    }

    //---------------------------------------Getter-------------------------------------------------
    //getter
    public int getId() {
        return id;
    }
    public int getUserID() {
        return user;
    }
    public String getName() {
        return name;
    }
    public String getIngredients() {
        return ingredients;
    }
    public String getImage() {
        return image;
    }
    public String getFacts() {
        return nutrientsFacts;
    }
    public String getDescription(){
        return description;
    }
}
