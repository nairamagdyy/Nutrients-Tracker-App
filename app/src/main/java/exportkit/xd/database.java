package exportkit.xd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;


public class database extends SQLiteOpenHelper {
    public database(Context context) {
        super(context, "Signup", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("Create Table MyUsers (Username Varchar(100) Primary Key,password Varchar(8)) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long Register(String username,String pass){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value= new ContentValues();
        value.put("Username",username);
        value.put("password",pass);
        long i = db.insert("MYUsers",null,value);
        return i;
    }



}
