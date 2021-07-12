package exportkit.xd.Controller;

import exportkit.xd.Model.User;

public interface IUserController {

    void openSession(long id);
    void signUp(User user);
    void login(String Email, String password);
    void logout();
    void EditProfile(int id , String name, String username, String email, String phoneNumber, String password) ;
    String GetName(int id);
    String GetUserName(int id);
    String GetEmail(int id);
    User SearchUser(String username) ;
    int GetUserid(String Email) ;
}
