package exportkit.xd.View.Register;

import android.app.Activity;
import android.os.Bundle;

import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import exportkit.xd.Controller.IUserController;
import exportkit.xd.Controller.userController;
import exportkit.xd.DB.AppDBController;
import exportkit.xd.R;
import exportkit.xd.View.Profile.Editprofile_Activity;
import exportkit.xd.View.Profile.myProfile_activity;

public class log_in_activity extends Activity implements IRegisterView {

	IUserController loginController;
	AppDBController db;
	private TextView email, password,
			loginb, signUpb;
	private ImageView hidden;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);
		loginController = new userController(this);
		db = new AppDBController(this)  ;
		email= (TextView) findViewById(R.id.email_or_username);
		password = (TextView) findViewById(R.id.password);

		loginb = (TextView) findViewById(R.id.login_ek1);
		signUpb= (TextView) findViewById(R.id._sign_up_ek2);
		hidden = (ImageButton) findViewById(R.id.hide);

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

		signUpb.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext(), sign_up_activity.class);
				startActivity(nextScreen);

			}
		});
		loginb.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String Email = email.getText().toString();
				String Pass = password.getText().toString() ;
				if (Email.equalsIgnoreCase("")
						|| Pass.equalsIgnoreCase(""))
				{
					Toast.makeText(getApplication(),"you should fill the empty fields",Toast.LENGTH_LONG).show();

				}
				else
				{
					loginController.login(Email,Pass);
				//	System.out.println("User id " + db.GetUserID(Email)) ;

				}

			}
		});

	}

	@Override
	public void onLoginSuccess(String message) {
		Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
		Intent nextScreen = new Intent(getApplicationContext(), Editprofile_Activity.class);
		startActivity(nextScreen);
	}

	@Override
	public void onLoginError(String message) {
		Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();
	}

}
	
	