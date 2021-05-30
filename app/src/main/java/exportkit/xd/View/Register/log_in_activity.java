package exportkit.xd.View.Register;

import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;

import exportkit.xd.Controller.database;
import exportkit.xd.R;
import exportkit.xd.View.Register.sign_up_activity;
import exportkit.xd.View.homepage_activity;

public class log_in_activity extends Activity {

	database db ;
	private TextView email_or_username;
	private ImageView stroke_3;
	private TextView password;
	private TextView login_ek1;
	private TextView _sign_up_ek2;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);
		email_or_username = (TextView) findViewById(R.id.email_or_username);
		stroke_3 = (ImageView) findViewById(R.id.stroke_3);
		password = (TextView) findViewById(R.id.password);
		login_ek1 = (TextView) findViewById(R.id.login_ek1);
		_sign_up_ek2 = (TextView) findViewById(R.id._sign_up_ek2);
		 db = new database (this) ;
		_sign_up_ek2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				Intent nextScreen = new Intent(getApplicationContext(), sign_up_activity.class);
				startActivity(nextScreen);

			}
		});
		login_ek1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String email = email_or_username.getText().toString();
				String Pass = password.getText().toString() ;
				Boolean value = db.checkUser(email,Pass);
				if (value==true) {
					Toast.makeText(getApplication(),"Login Successfully",Toast.LENGTH_LONG).show();
					Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
					startActivity(nextScreen);
				}
				else
				{
					Toast.makeText(getApplication(), " Try Again !!!! ", Toast.LENGTH_LONG).show();
				}


			}
		});
		
		
		//custom code goes here
	
	}
}
	
	