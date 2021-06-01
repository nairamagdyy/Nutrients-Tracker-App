package exportkit.xd.View;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import exportkit.xd.Controller.IUserController;
import exportkit.xd.R;

import static android.app.PendingIntent.getActivity;

public class profile_activity extends Activity {
    IUserController profileController;
    String Name,UserName,Gender,PhoneNumber,Email,Password;
    private TextView name;

    public void Profile(String name, String userName, String gender, String phoneNumber, String email, String password) {

        Name= name;
       UserName=userName;
       Gender=gender;
       PhoneNumber=phoneNumber;
       Email=email;
       Password=password;
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);
        name= (TextView) findViewById(R.id.name_);
        name.setText(Name);

    }



}
