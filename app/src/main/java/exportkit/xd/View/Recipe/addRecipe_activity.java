package exportkit.xd.View.Recipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import exportkit.xd.Controller.cameraController;
import exportkit.xd.Controller.recipeController;

import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.Recipe;
import exportkit.xd.R;
import exportkit.xd.View.IAppViews;
import exportkit.xd.View.camera_activity;
import exportkit.xd.View.homepage_activity;
import exportkit.xd.View.macroTracker_activity;

class Ingredient implements Serializable {
    public String name;
    public double amount;
    public Ingredient() {}
}

public class addRecipe_activity extends camera_activity implements IAppViews {
    //dynamic view
    LinearLayout ingredients_layoutList;
    Button dynamicAddBtn;

    //variables
    ArrayList<Ingredient> ingredientsList = new ArrayList<>();

    private TextView name, description;
    private ImageButton saveBtn ;
    private Button cancelBtn;

    recipeController RecipeController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe);

        //dynamic view
        dynamicAddBtn = findViewById(R.id.addIngredientBtn);
        ingredients_layoutList = findViewById(R.id.linearLayout1);
        dynamicAddBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addIngredient();
            };
        });

        //get logged user
        SessionManager session= new SessionManager(this);
        int loggedUser = (int) session.getUserFromSession();

        //start to get input
        RecipeController = new recipeController(this);
        CamController = new cameraController(this);

        //find views
        uploadedImage= (CircularImageView)findViewById(R.id.uploadImage);
        name= findViewById(R.id.enter_food_name);
        description= findViewById(R.id.enter_description);
        //ingredients= findViewById(R.id.enter_ingredient);
        saveBtn = findViewById(R.id.done);
        cancelBtn= findViewById(R.id.cancel);

        uploadedImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //show image pick dialog
                CamController.imagePickDialog();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String recipeName= name.getText().toString().trim(),
                        recipeDescription= ""+description.getText().toString().trim();

                if (recipeName.equalsIgnoreCase(""))//|| recipeIngredients.equals(""))
                    Toast.makeText(getApplication(),"you should fill the empty fields",Toast.LENGTH_LONG).show();
                else
                {
                    if(checkIfValidAndRead()){
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("list",ingredientsList);

                        //read ingredientsList as a one string
                        String all_ingredients="";
                        for(int i=0; i<ingredientsList.size(); i++) {
                            String name= ingredientsList.get(i).name;
                            String amount= String.valueOf(ingredientsList.get(i).amount);
                            String record= String.valueOf(i)+": "+amount+" Cups of "+name+"end";
                            all_ingredients+=record;
                        }
                        Recipe recipe= new Recipe(loggedUser);
                        recipe.setImage(""+CamController.imageUri);
                        recipe.setName(recipeName);
                        recipe.setDescription(recipeDescription);
                        recipe.setIngredients(all_ingredients);
                        RecipeController.addRecipe(recipe);
                    }
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
    public void onSuccess(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
        Intent nextScreen = new Intent(getApplicationContext(), macroTracker_activity.class);
        startActivity(nextScreen);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();

    }

    //----------------------------------------------------------------------------------------------

    private void addIngredient() {
         final View view = getLayoutInflater().inflate(R.layout.hidden, null, false);
         EditText name = (EditText)view.findViewById(R.id.name),
                  amount = (EditText)view.findViewById(R.id.amount);

        System.out.println(name+"--------------------------------------"+amount);

        /*ImageView imageClose = (ImageView)view.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(cricketerView);
            }
            */
         ingredients_layoutList.addView(view);
    }

    private boolean checkIfValidAndRead() {
        ingredientsList.clear();
        boolean result = true;

        for(int i=0;i<ingredients_layoutList.getChildCount();i++){

            View ingredientView = ingredients_layoutList.getChildAt(i);
            System.out.println("............................................."+ingredientView);

            EditText TextName= (EditText)ingredientView.findViewById(R.id.name),
                    TextAmount= (EditText)ingredientView.findViewById(R.id.amount);

            System.out.println(TextName+"--------------------------------------"+TextAmount);

            Ingredient ingredient = new Ingredient();

            if(!TextName.getText().toString().equals("") || TextAmount.getText().toString().equals("")){
                ingredient.name= TextName.getText().toString();
                ingredient.amount= Double.parseDouble(TextAmount.getText().toString());
            }else {
                result = false;
                break;
            }
            ingredientsList.add(ingredient);
        }

        if(ingredientsList.size()==0){
            result = false;
            Toast.makeText(this, "Add Ingredient First!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }

        return result;
    }

}
