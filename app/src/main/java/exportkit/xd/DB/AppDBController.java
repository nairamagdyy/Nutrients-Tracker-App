package exportkit.xd.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.List;

import exportkit.xd.Model.User;

public class AppDBController extends SQLiteOpenHelper {
    // Database Name
    public static final String DB_Name = "App" ;
    // Database Version
    public static final int DB_version = 5;
    // User table name
    public static final String DB_User_Table = "user" ;
    // User Table Columns names
    public static final String DB_col_ID = "id" ;
    public static final String DB_col_name = "name" ;
    public static final String DB_col_username = "username" ;
    public static final String DB_col_email = "email" ;
    public static final String DB_col_password = "password" ;
    public static final String DB_col_gender = "gender" ;
    public static final String DB_col_phonenumber = "phonenumber" ;


    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + DB_User_Table + "("
            + DB_col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DB_col_name + " TEXT, " + DB_col_username + " TEXT UNIQUE, "
            + DB_col_email + " TEXT UNIQUE, " + DB_col_password + " TEXT, " + DB_col_gender + " TEXT, " + DB_col_phonenumber + " INT "  +")";
    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + DB_User_Table;

    public AppDBController(@Nullable Context context) {
        super(context, DB_Name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    // i -> means old version , i1 -> means new version
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        // Create tables again
        onCreate(db);
    }
    /*
      This method is to create user // Register
     */
    public long Register(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_col_name, user.getName());
        values.put(DB_col_username,user.getUsername()) ;
        values.put(DB_col_email, user.getEmail());
        values.put(DB_col_password, user.getPassword());
        values.put(DB_col_gender, user.getGender());
        values.put(DB_col_phonenumber, user.getPhoneNumber());
        // Inserting Row
        long id = db.insert(DB_User_Table, null, values);
        db.close();

        return id;
    }
    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public long loginValidation(String email, String password) {
        // array of columns to fetch
        String[] columns = {DB_col_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = DB_col_email + " =?" + " AND " + DB_col_password + " =?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = '' AND user_password = '';
         */
        Cursor cursor = db.query(DB_User_Table, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        long id = cursor.getCount();
        return id;
    }

    public boolean editUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_col_name, user.getName());
        values.put(DB_col_username,user.getUsername()) ;
        values.put(DB_col_email, user.getEmail());
        values.put(DB_col_password, user.getPassword());
        values.put(DB_col_phonenumber, user.getPhoneNumber());
        // updating row
        // if db.update = 0 so there no row updating
       int result =  db.update(DB_User_Table, values, DB_col_ID + " =?",
                new String[]{String.valueOf(user.getId())});
        db.close();
        if (result < 0) return false ;
        else
            return true ;
    }
}

