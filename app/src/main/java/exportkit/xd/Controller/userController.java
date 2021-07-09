package exportkit.xd.Controller;

import android.content.Context;

import exportkit.xd.Model.User;
import exportkit.xd.View.Profile.IProfileView;
import exportkit.xd.View.Register.IRegisterView;
import exportkit.xd.View.Profile.profile_activity;

public class userController implements IUserController{

    IRegisterView registerview;
    IProfileView profileview;
    AppDBController db;
    profile_activity p;

    public userController(IRegisterView view) {
        this.registerview = view;
        db = new AppDBController((Context) this.registerview);
    }
    public userController(IProfileView view) {
        this.profileview = view ;
        db = new AppDBController((Context) this.profileview);
    }

    @Override
    public void signUp(User user){
        Boolean data = db.Register(user);

        if(data)
            registerview.onLoginSuccess("Registration Successfully");
        else
            registerview.onLoginError("email exists or username , enter new one!!!!");
    }
    public void EditProfile(User user){
        Boolean data = db.edituser(user);
        if(data)
            profileview.onEditSuccess("Edit Operation is Successfully");
        else
            profileview.onEditError("email exists or username , enter new one!!!!");
    }

    public void login(String email, String password) {
        Boolean data = db.loginValidation(email,password);

        if(data)
            registerview.onLoginSuccess("Login Successfully");

        else
            registerview.onLoginError("Try Again !!!!");
    }

}


