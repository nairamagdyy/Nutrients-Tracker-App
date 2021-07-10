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
    public static final int DB_version = 6;
    // User table name
    public static final String DB_User_Table = "user" ;
    // User Table Columns names
    public static final String DB_col_ID = "id" ;
    public static final String DB_col_name = "fullname" ;
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
    public long loginValidation(String email, String password) {
        String[] columns = {DB_col_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = DB_col_email + " =?" + " AND " + DB_col_password + " =?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(DB_User_Table,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        long id = cursor.getCount();
     //   System.out.println("id + " + id ) ;
        return id;
    }
    public int GetUserID(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_User_Table, new String[] {DB_col_ID}, DB_col_email + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getInt(0) ;
    }
    public boolean edituser(int id , String name, String username, String email, String phoneNumber, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_col_name, name);
        values.put(DB_col_username,username) ;
        values.put(DB_col_email, email);
        values.put(DB_col_password, phoneNumber);
        values.put(DB_col_phonenumber, password);
        // updating row
        System.out.println("name = " + name + " " + " id = " + id );
        int result =  db.update(DB_User_Table, values, DB_col_ID + " =?",
                new String[]{String.valueOf(id)});
        db.close();
        if (result < 0) return false ;
        else
            return true ;
    }
}

