	package exportkit.xd;

	import android.app.Activity;
	import android.content.Intent;
	import android.os.Bundle;


	import android.view.View;
	import android.widget.RadioButton;
	import android.widget.RadioGroup;
	import android.widget.TextView;
	import android.widget.ImageView;
	import android.widget.Toast;

	public class sign_up_activity extends Activity {

		database databaseHelper;
		User user ;
		private TextView phone_number;
		private TextView name;
		private TextView username;
		private TextView sign_up_ek6;
		private TextView email_address;
		private TextView password_ek1;
		private RadioGroup radioSexGroup;
		private RadioButton radioSexButton;

		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.sign_up);

			phone_number = (TextView) findViewById(R.id.phonenumber);
			name = (TextView) findViewById(R.id.name);
			username = (TextView) findViewById(R.id.username);
			sign_up_ek6 = (TextView) findViewById(R.id.sign_up_ek6);
			email_address = (TextView) findViewById(R.id.email_address);
			password_ek1 = (TextView) findViewById(R.id.password_ek1);
			radioSexGroup = (RadioGroup) findViewById(R.id.groupbutton);

			databaseHelper = new database(this);
			//custom code goes here
			sign_up_ek6.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					/*Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
					startActivity(nextScreen);*/
					String Fullname = name.getText().toString();
					String Username = username.getText().toString();
					String email = email_address.getText().toString();
					String password = password_ek1.getText().toString() ;
					String phoneNumber = phone_number.getText().toString();
					// get selected radio button from radioGroup
					int selectedId = radioSexGroup.getCheckedRadioButtonId();
					// find the radiobutton by returned id
					radioSexButton = (RadioButton) findViewById(selectedId);
					String gender = radioSexButton.getText().toString();
					if (Fullname.equalsIgnoreCase("") || Username.equalsIgnoreCase("") || email.equalsIgnoreCase("") ||
							password.equalsIgnoreCase("") || phoneNumber.equalsIgnoreCase("")) {
						Toast.makeText(getApplication(),"you should fill the empty fields",Toast.LENGTH_LONG).show();

					}
                     else
					{
						user= new User(Fullname, Username,  email, phoneNumber, password , gender );
						if(databaseHelper.Register(user))
						{
							Toast.makeText(getApplication(),"Registration Successfully",Toast.LENGTH_LONG).show();
							Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
							startActivity(nextScreen);
						}
						else {
							Toast.makeText(getApplication(), "email exists or username , enter new one!!!! ", Toast.LENGTH_LONG).show();
						}

					}


				}
			});

		}
	}
	
	