package exportkit.xd;

import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;

public class log_in_activity extends Activity {

	database db ;
	private View _bg__log_in_ek2;
	private View rectangle_33;
	private TextView email_or_username;
	private ImageView path_445;
	private ImageView rectangle_511;
	private View rectangle_33_ek1;
	private ImageView stroke_1;
	private ImageView stroke_3;
	private TextView password;
	private ImageView stroke_1_ek1;
	private ImageView stroke_3_ek1;
	private ImageView stroke_5;
	private TextView login_ek1;
	private TextView _sign_up_ek2;
	private TextView don_t_have_any_account_;
	private View rectangle_2_16;
	private View ellipse;
	private TextView welcome_back_;
	private View ellipse_ek1;
	private TextView please_enter_your_account_here;
	private ImageView vector;
	private ImageView vector_ek1;
	private ImageView vector_ek2;
	private ImageView vector_ek3;
	private View home_indicator_ek1;
	private View border_ek1;
	private ImageView cap_ek1;
	private View capacity_ek1;
	private ImageView wifi_ek1;
	private ImageView cellular_connection_ek1;
	private TextView time_ek1;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);
		rectangle_33 = (View) findViewById(R.id.rectangle_33);
		email_or_username = (TextView) findViewById(R.id.email_or_username);
		path_445 = (ImageView) findViewById(R.id.path_445);
		rectangle_511 = (ImageView) findViewById(R.id.rectangle_511);
		rectangle_33_ek1 = (View) findViewById(R.id.rectangle_33_ek1);
		stroke_1 = (ImageView) findViewById(R.id.stroke_1);
		stroke_3 = (ImageView) findViewById(R.id.stroke_3);
		password = (TextView) findViewById(R.id.password);
		stroke_1_ek1 = (ImageView) findViewById(R.id.stroke_1_ek1);
		stroke_3_ek1 = (ImageView) findViewById(R.id.stroke_3_ek1);
		stroke_5 = (ImageView) findViewById(R.id.stroke_5);
		login_ek1 = (TextView) findViewById(R.id.login_ek1);
		_sign_up_ek2 = (TextView) findViewById(R.id._sign_up_ek2);
		don_t_have_any_account_ = (TextView) findViewById(R.id.don_t_have_any_account_);
		rectangle_2_16 = (View) findViewById(R.id.rectangle_2_16);
		ellipse = (View) findViewById(R.id.ellipse);
		welcome_back_ = (TextView) findViewById(R.id.welcome_back_);
		ellipse_ek1 = (View) findViewById(R.id.ellipse_ek1);
		please_enter_your_account_here = (TextView) findViewById(R.id.please_enter_your_account_here);
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
	
	