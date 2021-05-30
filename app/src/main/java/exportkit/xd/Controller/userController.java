package exportkit.xd.Controller;

import android.content.Context;

import exportkit.xd.View.Register.ILoginView;

public class userController implements IUserController{

    ILoginView loginView;
    database db;

    public userController(ILoginView loginView) {
        this.loginView = loginView;
        db = new database ((Context) this.loginView);
    }

    @Override
    public void login(String email, String password) {
        Boolean data = db.loginValidation(email,password);

        if(data)
            loginView.onLoginSuccess("Login Successfully");
        else
            loginView.onLoginError("Try Again !!!!");
    }
}
