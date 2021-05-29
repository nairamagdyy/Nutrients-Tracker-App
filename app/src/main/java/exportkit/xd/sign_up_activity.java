	package exportkit.xd;

	import android.app.Activity;
	import android.content.Intent;
	import android.os.Bundle;


	import android.view.View;
	import android.widget.TextView;
	import android.widget.ImageView;
	import android.widget.Toast;

	public class sign_up_activity extends Activity {


		private View _bg__sign_up_ek5;
		private View rectangle_33_ek2;
		private TextView phone_number;
		private ImageView download_1;
		private View rectangle_33_ek3;
		private TextView name;
		private ImageView gs1_user_grey_1;
		private View rectangle_33_ek4;
		private TextView username;
		private ImageView gs1_user_grey_1_ek1;
		private TextView sign_up_ek6;
		private View rectangle_33_ek5;
		private TextView email_address;
		private ImageView path_445_ek1;
		private ImageView rectangle_511_ek1;
		private View rectangle_33_ek6;
		private ImageView stroke_1_ek2;
		private ImageView stroke_3_ek2;
		private TextView password_ek1;
		private ImageView stroke_1_ek3;
		private ImageView stroke_3_ek3;
		private ImageView stroke_5_ek1;
		private View male;
		private View female;
		private TextView female_ek1;
		private TextView male_ek1;
		private View home_indicator_ek2;
		private View rectangle_2_16_ek1;
		private View ellipse_ek2;
		private TextView welcome_;
		private View ellipse_ek3;
		private TextView sign_up_ek7;
		private ImageView vector_ek4;
		private ImageView vector_ek5;
		private View border_ek2;
		private ImageView cap_ek2;
		private View capacity_ek2;
		private ImageView wifi_ek2;
		private ImageView cellular_connection_ek2;
		private TextView time_ek2;
		database d = new database(this);
		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.sign_up);

			rectangle_33_ek2 = (View) findViewById(R.id.rectangle_33_ek2);
			phone_number = (TextView) findViewById(R.id.phonenumber);
			download_1 = (ImageView) findViewById(R.id.download_1);
			rectangle_33_ek3 = (View) findViewById(R.id.rectangle_33_ek3);
			name = (TextView) findViewById(R.id.name);
			gs1_user_grey_1 = (ImageView) findViewById(R.id.gs1_user_grey_1);
			rectangle_33_ek4 = (View) findViewById(R.id.rectangle_33_ek4);
			username = (TextView) findViewById(R.id.username);
			gs1_user_grey_1_ek1 = (ImageView) findViewById(R.id.gs1_user_grey_1_ek1);
			sign_up_ek6 = (TextView) findViewById(R.id.sign_up_ek6);
			rectangle_33_ek5 = (View) findViewById(R.id.rectangle_33_ek5);
			email_address = (TextView) findViewById(R.id.email_address);
			path_445_ek1 = (ImageView) findViewById(R.id.path_445_ek1);
			rectangle_511_ek1 = (ImageView) findViewById(R.id.rectangle_511_ek1);
			rectangle_33_ek6 = (View) findViewById(R.id.rectangle_33_ek6);
			password_ek1 = (TextView) findViewById(R.id.password_ek1);
			stroke_1_ek3 = (ImageView) findViewById(R.id.stroke_1_ek3);
			stroke_3_ek3 = (ImageView) findViewById(R.id.stroke_3_ek3);
			stroke_5_ek1 = (ImageView) findViewById(R.id.stroke_5_ek1);
			male = (View) findViewById(R.id.male);
			female = (View) findViewById(R.id.female);
			rectangle_2_16_ek1 = (View) findViewById(R.id.rectangle_2_16_ek1);
			ellipse_ek2 = (View) findViewById(R.id.ellipse_ek2);
			welcome_ = (TextView) findViewById(R.id.welcome_);
			ellipse_ek3 = (View) findViewById(R.id.ellipse_ek3);
			sign_up_ek7 = (TextView) findViewById(R.id.sign_up_ek7);

			//custom code goes here
			sign_up_ek6.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					/*Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
					startActivity(nextScreen);*/
                    long save=d.Register(username.getText().toString(),password_ek1.getText().toString());
                    if(save>=1)
						Toast.makeText(getApplication(),"User has been created done",Toast.LENGTH_LONG).show();
                    else
						Toast.makeText(getApplication(),"error",Toast.LENGTH_LONG).show();

				}
			});

		}
	}
	
	