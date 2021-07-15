package exportkit.xd.View.Profile;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import exportkit.xd.Controller.userController;
import exportkit.xd.Controller.recipeController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.Recipe;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.IAppViews;
import exportkit.xd.View.Recipe.recipeDetails_activity;
import exportkit.xd.View.Register.log_in_activity;
import exportkit.xd.View.Search.SearchUser_activity;
import exportkit.xd.View.adapter;
import exportkit.xd.View.homepage_activity;

public  class myProfile_activity extends Activity implements IProfile, IAppViews {
    private CircularImageView uploadedImage, ProfileIcon;
    private TextView name , username ;
    private ImageButton HomeButton, editButton , logoutBtn ;
    private Button FavButton, SearchButton, RecipeButton;

    userController UserController;
    recipeController RecipeController;

    RecyclerView recycleRecipeList, recycleFavList;
    List<String>  recipeNameList = new ArrayList<>();
    List<String> recipeImageList = new ArrayList<>();
    adapter Adapter;

    String KEY_VALUE="myProfile";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        UserController = new userController(this);
        RecipeController= new recipeController(this);

        // finds views
        recycleRecipeList = findViewById(R.id.recipeList);
        recycleFavList= findViewById(R.id.favList);
        uploadedImage = findViewById(R.id.avatar);
        name = (TextView) findViewById(R.id.name);
        username = (TextView) findViewById(R.id.__tayshelby_ek2) ;
        FavButton = (Button) findViewById(R.id.FavoriteButton) ;
        editButton = (ImageButton) findViewById(R.id.edit11) ;
        logoutBtn= (ImageButton) findViewById(R.id._list_1) ;
        RecipeButton= (Button)findViewById(R.id.recipes);

        SearchButton = (Button) findViewById(R.id.ellipse_ek22);
        HomeButton = (ImageButton) findViewById(R.id.home_ek11);
        ProfileIcon = findViewById(R.id.ellipse_ek23);

        // get logged user
        SessionManager session = new SessionManager(this);
        long loggedUser= session.getUserFromSession();
        User user= UserController.getUser((int)loggedUser);

        //display profile info
        if(user.getAvatar() != null) {
            uploadedImage.setImageURI(Uri.parse(user.getAvatar()));
            ProfileIcon.setImageURI(Uri.parse(user.getAvatar()));
        }
        name.setText(user.getName());
        username.setText(user.getUsername());

        //Recyclerview as GridView for recipes
        //get user recipe from db
        recycleRecipeList.setVisibility(View.VISIBLE);
        Vector<Integer> recipesIdList= RecipeController.viewRecipeList((int) loggedUser);
        for(int i=0; i<recipesIdList.size();i++){
            Recipe recipe= RecipeController.getRecipe(recipesIdList.get(i));
            recipeNameList.add(recipe.getName());
            recipeImageList.add(recipe.getImage());
        }
        viewDynamic(recycleRecipeList ,recipesIdList);/*
        Adapter = new adapter(this, recipesIdList, recipeNameList, recipeImageList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false);
        recycleRecipeList.setLayoutManager(gridLayoutManager);
        recycleRecipeList.setAdapter(Adapter);*/

        // buttons functions
        HomeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
                startActivity(nextScreen);
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                session.logoutUserFromSession();
                Intent nextScreen = new Intent(getApplicationContext(), log_in_activity.class);
                startActivity(nextScreen);
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), editProfileActivity.class);
                startActivity(nextScreen);
            }
        });
        RecipeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), myProfile_activity.class);
                startActivity(nextScreen);
            }
        });
        FavButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            public void onClick(View v) {
                RecipeButton.setTextColor(R.color.choice);
                FavButton.setTextColor(R.color.select);
                recycleRecipeList.setVisibility(View.GONE);
                recycleFavList.setVisibility(View.VISIBLE);
                KEY_VALUE="userProfile";

                //Recyclerview as GridView for recipes
                //get user recipe from db
                Vector<Integer> favRecipesIdList= RecipeController.viewFavList((int) loggedUser);
                //System.out.println(loggedUser+"------------------------------------------------------------------------------"+favRecipesIdList);
                for(int i=0; i<favRecipesIdList.size();i++){
                    Recipe fav= RecipeController.getRecipe(favRecipesIdList.get(i));
                    recipeNameList.add(fav.getName());
                    recipeImageList.add(fav.getImage());
                    viewDynamic(recycleFavList, favRecipesIdList);
                }
            }
        });
        SearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), SearchUser_activity.class);
                startActivity(nextScreen);

            }
        });

    }

    private void viewDynamic(RecyclerView recycle, List<Integer> recipesIdList){
        Adapter = new adapter(this, recipesIdList, recipeNameList, recipeImageList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2, GridLayoutManager.VERTICAL,false);
        recycle.setLayoutManager(gridLayoutManager);
        recycle.setAdapter(Adapter);
    }
    @Override
    public void viewRecipeDetails(int id) {
        Intent nextScreen = new Intent(getApplicationContext(), recipeDetails_activity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putString("profile",KEY_VALUE);
        nextScreen.putExtras(bundle);
        startActivity(nextScreen);
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
        Intent nextScreen = new Intent(getApplicationContext(), myProfile_activity.class);
        startActivity(nextScreen);
    }
    @Override
    public void onError(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();
    }

}
