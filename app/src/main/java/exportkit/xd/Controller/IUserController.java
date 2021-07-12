package exportkit.xd.Controller;

import exportkit.xd.Model.User;

public interface IUserController {

    void openSession(long id);
    void signUp(User user);
    void login(String Email, String password);
    void logout();
    void EditProfile(User user) ;
    User getUser(int id);
    String GetName(int id);
    String GetUserName(int id);
<<<<<<< HEAD
    String GetEmail(int id);
    String GetPassword(int id);
    String GetPhoneNumber(int id);
    void SearchUser(String username) ;
=======
   // User SearchUser(String username) ;
>>>>>>> 5b390090549718aa3a33872c6a07cf6858aaf6e6
    int GetUserid(String Email) ;
}
