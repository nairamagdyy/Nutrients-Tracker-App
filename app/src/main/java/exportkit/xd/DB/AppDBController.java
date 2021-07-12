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
   //User Table
    UserTableConstants userTable;

    //------------------------------------DATABASE------------------------------------------------
    public AppDBController(@Nullable Context context) {
        super(context, DB_Name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(userTable.CREATE_USER_TABLE);
    }

    @Override
    // i -> means old version , i1 -> means new version
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Drop User Table if exist
        db.execSQL(userTable.DROP_USER_TABLE);
        // Create tables again
        onCreate(db);
    }

    //------------------------------------USER TABLE------------------------------------------------
    public long Register(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(userTable.DB_col_name, user.getName());
        values.put(userTable.DB_col_username,user.getUsername()) ;
        values.put(userTable.DB_col_email, user.getEmail());
        values.put(userTable.DB_col_password, user.getPassword());
        values.put(userTable.DB_col_gender, user.getGender());
        values.put(userTable.DB_col_phonenumber, user.getPhoneNumber());
        // Inserting Row
        long id = db.insert(userTable.DB_User_Table, null, values);
        db.close();

        return id;
    }

    public boolean loginValidation(String email, String password) {
        String[] columns = {userTable.DB_col_ID};
        SQLiteDatabase db = this.getReadableDatabase();
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

    public int GetUserID(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(userTable.DB_User_Table, new String[] {userTable.DB_col_ID}, userTable.DB_col_email + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getInt(0) ;
    }

    public String GetTheNameOftheUser(int id ) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(userTable.DB_User_Table, new String[] {userTable.DB_col_name}, userTable.DB_col_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getString(cursor.getColumnIndex(userTable.DB_col_name));
    }

    public String GetUserName(int id ) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(userTable.DB_User_Table, new String[] {userTable.DB_col_username}, userTable.DB_col_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getString(cursor.getColumnIndex(userTable.DB_col_username));
    }

    public String GetUserEmail(int id ) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(userTable.DB_User_Table, new String[] {userTable.DB_col_email}, userTable.DB_col_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getString(cursor.getColumnIndex(userTable.DB_col_email));
    }

    public boolean edituser(int id , String name, String username, String email, String phoneNumber, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(userTable.DB_col_name, name);
        values.put(userTable.DB_col_username,username) ;
        values.put(userTable.DB_col_email, email);
        values.put(userTable.DB_col_password, password);
        values.put(userTable.DB_col_phonenumber, phoneNumber);
        // updating row
        int result =  db.update(userTable.DB_User_Table, values, userTable.DB_col_ID + " =?",
                new String[]{String.valueOf(id)});
        db.close();
        if (result < 0) return false ;
        else
            return true ;
    }
    public User searchUser(String username)
    {
        User userInfo =null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + userTable.DB_User_Table + " WHERE userTable.DB_col_username = ?" ,new String[]{username} );
        if (cursor.moveToFirst()){
            userInfo = new User();
            userInfo.setId(cursor.getInt(0));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(userTable.DB_col_name)));
            userInfo.setUsername(cursor.getString(cursor.getColumnIndex(userTable.DB_col_username)));
        }

        return userInfo;
    }
    //------------------------------------RECIPE TABLE------------------------------------------------
}

