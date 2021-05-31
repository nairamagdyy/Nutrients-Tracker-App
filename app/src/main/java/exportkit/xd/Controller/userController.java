package exportkit.xd.Controller;

import android.content.Context;
import android.database.Cursor;

import exportkit.xd.Model.User;
import exportkit.xd.View.Register.IRegisterView;
import exportkit.xd.View.profile_activity;

public class userController implements IUserController{

    IRegisterView view;
    database db;

    public userController(IRegisterView view) {
        this.view = view;
        db = new database ((Context) this.view);
    }

    @Override
    public void signUp(User user){
        Boolean data = db.Register(user);

        if(data)
            view.onLoginSuccess("Registration Successfully");
        else
            view.onLoginError("email exists or username , enter new one!!!!");
    }
    public void login(String email, String password) {
        Boolean data = db.loginValidation(email,password);

        if(data){
            view.onLoginSuccess("Login Successfully");
            Cursor cursor= db.getInformation(email,password);
            String name,userName ,gender,phoneNumber;
            while (cursor.moveToNext()){
                name=cursor.getString(cursor.getColumnIndex(database.DB_col_name));
                userName=cursor.getString(cursor.getColumnIndex(database.DB_col_username));
                gender=cursor.getString(cursor.getColumnIndex(database.DB_col_gender));
                phoneNumber=cursor.getString(cursor.getColumnIndex(database.DB_col_phonenumber));
                profile_activity profile = new profile_activity(name,userName,gender,phoneNumber,email,password);


            }
        }
        else
            view.onLoginError("Try Again !!!!");
    }
}
