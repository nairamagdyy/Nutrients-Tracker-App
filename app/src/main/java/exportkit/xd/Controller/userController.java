package exportkit.xd.Controller;

import android.content.Context;

import exportkit.xd.Model.User;
import exportkit.xd.View.Register.IRegisterView;

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

        if(data)
            view.onLoginSuccess("Login Successfully");
        else
            view.onLoginError("Try Again !!!!");
    }
}
