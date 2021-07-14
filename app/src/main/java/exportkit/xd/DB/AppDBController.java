package exportkit.xd.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import exportkit.xd.Model.Recipe;
import exportkit.xd.Model.User;

public class AppDBController extends SQLiteOpenHelper {
    SQLiteDatabase db;
    // Database Name
    public static final String DB_Name = "App" ;
    // Database Version
    public static final int DB_version = 7;
    //Tables
    UserTableConstants userTable= new UserTableConstants();
    RecipeTableConstants recipeTable= new RecipeTableConstants();
    //------------------------------------DATABASE------------------------------------------------
    public AppDBController(@Nullable Context context) {
        super(context, DB_Name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(userTable.CREATE_USER_TABLE);
        db.execSQL(recipeTable.CREATE_RECIPE_TABLE);

    }
    @Override
    // i -> means old version , i1 -> means new version
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Drop User Table if exist
        db.execSQL(userTable.DROP_USER_TABLE);
        db.execSQL(recipeTable.DROP_RECIPE_TABLE);
        // Create tables again
        onCreate(db);
    }

    //------------------------------------USER TABLE------------------------------------------------
    public long Register(User user) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(userTable.DB_col_name, user.getName());
        values.put(userTable.DB_col_username,user.getUsername()) ;
        values.put(userTable.DB_col_email, user.getEmail());
        values.put(userTable.DB_col_password, user.getPassword());
        values.put(userTable.DB_col_gender, user.getGender());
        values.put(userTable.DB_col_phoneNumber, user.getPhoneNumber());
        // Inserting Row
        long id = db.insert(userTable.DB_User_Table, null, values);
        db.close();

        return id;
    }

    public boolean loginValidation(String email, String password) {
        String[] columns = {userTable.DB_col_ID};
        db = this.getReadableDatabase();
        String selection = userTable.DB_col_email + " =?" + " AND " + userTable.DB_col_password + " =?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(userTable.DB_User_Table,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

       if (cursor.getCount()>0) return true ;
       else
           return false ;

    }

    public User getUser(int id){
        db = this.getReadableDatabase();
        Cursor cursor = db.query(userTable.DB_User_Table,
                new String[] {userTable.DB_col_username, userTable.DB_col_name,
                              userTable.DB_col_email, userTable.DB_col_phoneNumber,
                              userTable.DB_col_password, userTable.DB_col_IMAGE},
                userTable.DB_col_ID + "=?",
                new String[] { String.valueOf(id) },
                null, null, null, null
        );
        if (cursor != null)
            cursor.moveToFirst();

        User user= new User();
        user.setId(id);
        user.setUsername(cursor.getString(cursor.getColumnIndex(userTable.DB_col_username)));
        user.setName(cursor.getString(cursor.getColumnIndex(userTable.DB_col_name)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(userTable.DB_col_email)));
        user.setPhoneNumber(cursor.getString(cursor.getColumnIndex(userTable.DB_col_phoneNumber)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(userTable.DB_col_password)));
        user.setAvatar(cursor.getString(cursor.getColumnIndex(userTable.DB_col_IMAGE)));

        return user;
    }

    public int getUserID(String email) {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(userTable.DB_User_Table, new String[] {userTable.DB_col_ID}, userTable.DB_col_email + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getInt(0) ;
    }
    public int getUserIDByUsername(String username) {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(userTable.DB_User_Table, new String[] {userTable.DB_col_ID}, userTable.DB_col_username+ "=?",
                new String[] { String.valueOf(username) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getInt(0) ;
    }
    public boolean editUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(userTable.DB_col_name, user.getName());
        values.put(userTable.DB_col_username,user.getUsername()) ;
        values.put(userTable.DB_col_email, user.getEmail());
        values.put(userTable.DB_col_password, user.getPassword());
        values.put(userTable.DB_col_phoneNumber, user.getPhoneNumber());
        values.put(userTable.DB_col_IMAGE, user.getAvatar());
        // updating row
        int result =  db.update(userTable.DB_User_Table, values, userTable.DB_col_ID + " =?",
                new String[]{String.valueOf(user.getId())});
        db.close();
        if (result < 0) return false ;
        else
            return true ;
    }

    public List<User> searchUser(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(userTable.DB_User_Table, new String[] {userTable.DB_col_name , userTable.DB_col_ID ,userTable.DB_col_username}, userTable.DB_col_username + "=?",
                new String[] { String.valueOf(username) }, null, null, null, null);
        List<User> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                User userInfo = new User() ;
                userInfo.setId(cursor.getInt(cursor.getColumnIndex(userTable.DB_col_ID)));
                userInfo.setName(cursor.getString(cursor.getColumnIndex(userTable.DB_col_name)));
                userInfo.setUsername(cursor.getString(cursor.getColumnIndex(userTable.DB_col_username)));
                result.add(userInfo) ;
            }
            while (cursor.moveToNext());
        }

        return result;
    }

    //------------------------------------RECIPE TABLE----------------------------------------------
    public Long insertRecipe(Recipe recipe){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(recipeTable.DB_col_IMAGE,recipe.getImage());
        values.put(recipeTable.DB_col_NAME,recipe.getName()) ;
        values.put(recipeTable.DB_col_DESCRIPTION, recipe.getDescription());
        values.put(recipeTable.DB_col_INGREDIENTS, recipe.getIngredients());
        values.put(recipeTable.DB_col_USERID, recipe.getUserID());
        // Inserting Row
        long id = db.insert(recipeTable.DB_Table, null, values);
        db.close();

        return id;

    }

    public Vector<Integer> getRecipeList(int userId){
        Vector<Integer> recipesIdList= new Vector<>();

        db = this.getReadableDatabase();
        Cursor cursor = db.query(recipeTable.DB_Table, new String[] {recipeTable.DB_col_ID},
                recipeTable.DB_col_USERID + "=?", new String[] { String.valueOf(userId) },
                null, null, null, null);
        if (cursor != null) {
            while(cursor.moveToNext())
                recipesIdList.add(cursor.getInt(0));
        }

        return recipesIdList;
    }

    public Recipe getRecipe(int id){
        db = this.getReadableDatabase();
        Cursor cursor = db.query(recipeTable.DB_Table,
                new String[] {recipeTable.DB_col_IMAGE, recipeTable.DB_col_NAME,
                        recipeTable.DB_col_DESCRIPTION, recipeTable.DB_col_INGREDIENTS,
                        recipeTable.DB_col_USERID},
                recipeTable.DB_col_ID + "=?",
                new String[] { String.valueOf(id) },
                null, null, null, null
        );
        if (cursor != null)
            cursor.moveToFirst();

        int userId= cursor.getInt(cursor.getColumnIndex(recipeTable.DB_col_USERID));
        Recipe recipe= new Recipe(userId);
        recipe.setId(id);
        recipe.setImage(cursor.getString(cursor.getColumnIndex(recipeTable.DB_col_IMAGE)));
        recipe.setName(cursor.getString(cursor.getColumnIndex(recipeTable.DB_col_NAME)));
        recipe.setDescription(cursor.getString(cursor.getColumnIndex(recipeTable.DB_col_DESCRIPTION)));
        recipe.setIngredients(cursor.getString(cursor.getColumnIndex(recipeTable.DB_col_INGREDIENTS)));
        recipe.setFacts(cursor.getString(cursor.getColumnIndex(recipeTable.DB_col_IMAGE)));

        return recipe;
    }

    public boolean deleteRecipe(int id){
        db= this.getReadableDatabase();
        return db.delete(recipeTable.DB_Table, recipeTable.DB_col_ID+"=?", new String[]{String.valueOf(id)})
                > 0;
    }

}

