package exportkit.xd.View.Register;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import exportkit.xd.Controller.IUserController;
import exportkit.xd.Controller.database;
import exportkit.xd.Controller.userController;
import exportkit.xd.R;
import exportkit.xd.View.homepage_activity;

public class log_in_activity extends Activity implements ILoginView{

	database db = new database (this);
	IUserController loginController;

	private TextView email, password,
			loginb, signUpb;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);
		loginController = new userController(this);

		email= (TextView) findViewById(R.id.email_or_username);
		password = (TextView) findViewById(R.id.password);

		loginb = (TextView) findViewById(R.id.login_ek1);
		signUpb= (TextView) findViewById(R.id._sign_up_ek2);


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
				loginController.login(Email,Pass);
			}
		});

	}

	@Override
	public void onLoginSuccess(String message) {
		Toast.makeText(getApplication(),"Login Successfully",Toast.LENGTH_LONG).show();
		Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
		startActivity(nextScreen);
	}

	@Override
	public void onLoginError(String message) {
		Toast.makeText(getApplication(), " Try Again !!!! ", Toast.LENGTH_LONG).show();
	}
}
	
	