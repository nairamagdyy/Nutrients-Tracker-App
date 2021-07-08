package exportkit.xd.Controller;

import exportkit.xd.Model.User;

public interface IUserController {

    void signUp(User user);
    void login(String Email, String password);
    void EditProfile(User user) ;
}
