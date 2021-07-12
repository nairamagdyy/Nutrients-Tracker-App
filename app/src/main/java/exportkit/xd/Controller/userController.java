package exportkit.xd.Controller;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import exportkit.xd.DB.AppDBController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.View.IAppViews;

public class userController{

    AppDBController db;
    SessionManager session;
    IAppViews view;

    public userController(IAppViews view) {
        this.view = view;
        db = new AppDBController((Context) this.view);
    }

    public void openSession(long id) {
        session= new SessionManager((Context) this.view);
        session.createLoginSession(id);
    }

    public void signUp(User user){
        long id = db.Register(user);

        if(id>0) {
            openSession(id);
            view.onSuccess("Registration Successfully");
        }
        else
            view.onError("email exists or username, enter new one!!!!");
    }

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

    public void logout() {
        if(session.checkLogin())
            session.logoutUserFromSession();
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
    public User getUser(int id){
        return db.getUser(id);
    }

    public void EditProfile(User user){

        Boolean data = db.editUser(user);
        if(data)
            view.onSuccess("Edit Operation is Successfully");
        else
            view.onError("email exists or username , enter new one!!!!");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void SearchUser(String username) {
        List<User> userinfo = new ArrayList<>() ;
        userinfo = db.searchUser(username) ;
        userinfo.forEach(user -> {
            System.out.println("Name : " + user.getName() + ", id : " + user.getId()); });
        if (userinfo.isEmpty()) {
            view.onError("The username Doesn't exist");
        }
        else
        {
            view.onSuccess("Done , Search Operation Done");
        }
    }


}


