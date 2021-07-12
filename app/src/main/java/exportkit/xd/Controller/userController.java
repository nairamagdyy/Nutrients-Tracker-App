package exportkit.xd.Controller;

import android.content.Context;

import exportkit.xd.DB.AppDBController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.View.IAppViews;

public class userController implements IUserController{

    AppDBController db;
    SessionManager session;
    IAppViews view;

    public userController(IAppViews view) {
        this.view = view;
        db = new AppDBController((Context) this.view);
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


