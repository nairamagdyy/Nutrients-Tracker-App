package exportkit.xd.Controller;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import exportkit.xd.DB.AppDBController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.View.Profile.IMyProfileView;
import exportkit.xd.View.Register.IRegisterView;
import exportkit.xd.View.Search.ISearchView;

public class userController implements IUserController{

    AppDBController db;
    SessionManager session;
    IRegisterView registerview;
    IMyProfileView profileview;
    ISearchView searchview;
    public userController(IRegisterView view) {
        this.registerview = view;
        db = new AppDBController((Context) this.registerview);
    }
    public userController(ISearchView view) {
        this.searchview = view ;
        db = new AppDBController((Context) this.searchview);
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
    @Override
    public String GetName (int id )
    {
        return db.GetTheNameOfUser(id) ;
    }
    @Override
    public String GetUserName (int id )
    {
        return db.GetUserName(id) ;
    }
    @Override
    public String GetEmail (int id )
    {
        return db.GetUserEmail(id) ;
    }

    @Override
    public String GetPassword(int id) {
        return db.GetUserPassword(id) ;
    }

    @Override
    public String GetPhoneNumber(int id) {
        return db.GetUserPhoneNumber(id) ;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void SearchUser(String username) {
        List<User> userinfo = new ArrayList<>() ;
        userinfo = db.searchUser(username) ;
        userinfo.forEach(user -> {
            System.out.println("Name : " + user.getName() + ", id : " + user.getId()); });
        if (userinfo.isEmpty()) {
            searchview.onSearchError("The username Doesn't exist");
        }
        else
        {
            searchview.onSearchSuccess("Done , Search Operation Done");
        }
    }
    @Override
    public User getUser(int id){
        return db.getUser(id);
    }

    @Override
    public int GetUserid(String Email) {
        return db.GetUserID(Email)  ;
    }

    @Override
    public void EditProfile(User user){

        Boolean data = db.editUser(user);
        if(data)
            profileview.onEditSuccess("Edit Operation is Successfully");
        else
            profileview.onEditError("email exists or username , enter new one!!!!");
    }


}


