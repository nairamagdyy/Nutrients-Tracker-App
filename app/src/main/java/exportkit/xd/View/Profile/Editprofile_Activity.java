package exportkit.xd.View.Profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import exportkit.xd.Controller.IUserController;
import exportkit.xd.Controller.userController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.R;
public class Editprofile_Activity extends Activity implements IMyProfileView {
    IUserController Controller;
    private TextView email, password, phone, name, username;
    private ImageButton editButton, hidden;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        Controller = new userController((IMyProfileView) this) ;
        email= (TextView) findViewById(R.id.editmail);
        password= (TextView) findViewById(R.id.editpass);
        phone = (TextView) findViewById(R.id.editphonenumber);
        name = (TextView) findViewById(R.id.editname);
        username = (TextView) findViewById(R.id.editusername);
        editButton = (ImageButton) findViewById(R.id.done);
        hidden = (ImageButton) findViewById(R.id.pass);
        SessionManager s= new SessionManager(this);
        int  id= (int) s.getUserFromSession();
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
                          System.out.println(id + " , " +    Fullname + " , " +Username + " , " +Email + ", " +Phone + ", " +Password);
                    Controller.EditProfile(id , Fullname, Username,  Email, Phone, Password);
                    /*
                    email.setText(Email) ; 
                    name.setText(Fullname) ; 
                    */

                }


            }

        });

        hidden.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                switch ( event.getAction() ) {

                    case MotionEvent.ACTION_UP:
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                }
                return true;
            }
        });
    }
    @Override
    public void onEditSuccess(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
        Intent nextScreen = new Intent(getApplicationContext(), myProfile_activity.class);
        startActivity(nextScreen);
    }
    @Override
    public void onEditError(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();

    }
}
