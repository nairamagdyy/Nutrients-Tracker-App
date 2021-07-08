package exportkit.xd.View.Profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import exportkit.xd.Controller.IUserController;
import exportkit.xd.Controller.userController;
import exportkit.xd.Model.User;
import exportkit.xd.R;
public class Editprofile_Activity extends Activity implements IProfileView {
    IUserController Controller;
    private TextView email, password, phone, name, username,editButton;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        Controller = new userController((IProfileView) this) ;
        email= (TextView) findViewById(R.id.editmail);
        password= (TextView) findViewById(R.id.editpass);
        phone = (TextView) findViewById(R.id.editphonenumber);
        name = (TextView) findViewById(R.id.editname);
        username = (TextView) findViewById(R.id.editusername);
        editButton = (TextView) findViewById(R.id.sign_up_ek6);
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String Fullname = name.getText().toString();
                String Username = username.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString() ;
                String Phone = phone.getText().toString();
                if (Fullname.equalsIgnoreCase("")
                        || Username.equalsIgnoreCase("")
                        || Email.equalsIgnoreCase("")
                        || Password.equalsIgnoreCase("")
                        || Phone.equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplication(),"you should fill the empty fields",Toast.LENGTH_LONG).show();

                }
                else
                {
                    User edituser= new User(Fullname, Username,  Email, Phone, Password);
                    Controller.EditProfile(edituser);

                }


            }

        });
    }
    @Override
    public void onEditSuccess(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
        Intent nextScreen = new Intent(getApplicationContext(), profile_activity.class);
        startActivity(nextScreen);
    }
    @Override
    public void onEditError(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();

    }
}
