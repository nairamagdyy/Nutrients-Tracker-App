package exportkit.xd.Controller;

import android.content.Context;

import java.util.List;

import exportkit.xd.DB.AppDBController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.View.Profile.IMyProfileView;
import exportkit.xd.View.Register.IRegisterView;
import exportkit.xd.View.Profile.myProfile_activity;

public class userController implements IUserController{

    AppDBController db;
    SessionManager session;
    int id  ;
    String Name,UserName,Email;
    IRegisterView registerview;
    IMyProfileView profileview;

    public userController(IRegisterView view) {
        this.registerview = view;
        db = new AppDBController((Context) this.registerview);
    }

    public userController(IMyProfileView view) {
        this.profileview = view ;
        db = new AppDBController((Context) this.profileview);
    }

    @Override
    public void openSession(long id) {
        session= new SessionManager((Context) this.registerview);
        session.createLoginSession(id);
    }

    @Override
    public void signUp(User user){
        long id = db.Register(user);

        if(id>0) {
            openSession(id);
            registerview.onLoginSuccess("Registration Successfully");
        }
        else
            registerview.onLoginError("email exists or username, enter new one!!!!");
    }

    @Override
    public void login(String email, String password) {
        boolean i = db.loginValidation(email,password);

        if(i){
          //  System.out.println("User id " + db.GetUserID(email)) ;
            openSession(db.GetUserID(email));
            registerview.onLoginSuccess("Login Successfully");
        }
        else
            registerview.onLoginError("Try Again !!!!");
    }

    @Override
    public void logout() {
        if(session.checkLogin())
            session.logoutUserFromSession();
    }


    public String GetName (int id )
    {
        Name = db.GetTheNameOftheUser(id) ;
        return Name ;
    }
    public String GetUserName (int id )
    {
        UserName = db.GetUserName(id) ;
        return UserName ;
    }
    public String GetEmail (int id )
    {
        Email = db.GetUserEmail(id) ;
        return Email ;
    }
/*
    @Override
    public List<User> SearchUser(String username) {
        User user = new User() ;
        user = db.searchUser(username) ;
        if (user == null ) {
            return null ;
        }
        else
            {
            return user ;
            }

    }
*/
    public int GetUserid(String Email) {
        id = db.GetUserID(Email)  ;
        return id ;
    }

    @Override
    public void EditProfile(int id , String name, String username, String email, String phoneNumber, String password){

        Boolean data = db.edituser(id ,name, username, email,  phoneNumber, password);
        if(data)
            profileview.onEditSuccess("Edit Operation is Successfully");
        else
            profileview.onEditError("email exists or username , enter new one!!!!");
    }


}


