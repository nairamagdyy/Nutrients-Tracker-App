package exportkit.xd.View;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.fragment.app.DialogFragment;

import exportkit.xd.Controller.IUserController;
import exportkit.xd.Controller.userController;
import exportkit.xd.R;
import exportkit.xd.View.Register.sign_up_activity;

import static android.app.PendingIntent.getActivity;

public class profile_activity extends Activity {
    IUserController profileController;
    private String Name,UserName,Gender,PhoneNumber,Email,Password;

    public profile_activity(String name,String userName, String gender, String phoneNumber, String email, String password) {
        Name=name;
        UserName=userName;
        Gender=gender;
        PhoneNumber=phoneNumber;
        Email=email;
        Password=password;
    }


    Context context = getApplicationContext();
    CharSequence text = "Hello toast!";
    int duration = Toast.LENGTH_SHORT;

    Toast toast;

    {
        Toast.makeText(context, Name, duration).show();
    }


}
