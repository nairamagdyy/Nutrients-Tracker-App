package exportkit.xd.Controller;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import exportkit.xd.DB.AppDBController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
<<<<<<< HEAD
import exportkit.xd.View.Profile.IMyProfileView;
import exportkit.xd.View.Register.IRegisterView;
import exportkit.xd.View.Search.ISearchView;
=======
import exportkit.xd.View.IAppViews;
>>>>>>> 5b390090549718aa3a33872c6a07cf6858aaf6e6

public class userController implements IUserController{

    AppDBController db;
    SessionManager session;
<<<<<<< HEAD
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
=======
    IAppViews view;

    public userController(IAppViews view) {
        this.view = view;
        db = new AppDBController((Context) this.view);
>>>>>>> 5b390090549718aa3a33872c6a07cf6858aaf6e6
    }

    @Override
    public void openSession(long id) {
        session= new SessionManager((Context) this.view);
        session.createLoginSession(id);
    }

    @Override
    public void signUp(User user){
        long id = db.Register(user);

        if(id>0) {
            openSession(id);
            view.onSuccess("Registration Successfully");
        }
        else
            view.onError("email exists or username, enter new one!!!!");
    }

    @Override
    public void login(String email, String password) {
        boolean i = db.loginValidation(email,password);

        if(i){
          //  System.out.println("User id " + db.GetUserID(email)) ;
            openSession(db.GetUserID(email));
            view.onSuccess("Login Successfully");
        }
        else
            view.onError("Try Again !!!!");
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
            view.onSuccess("Edit Operation is Successfully");
        else
            view.onError("email exists or username , enter new one!!!!");
    }


}


