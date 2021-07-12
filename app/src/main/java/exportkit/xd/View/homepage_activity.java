
	package exportkit.xd.View;
	import android.app.Activity;
	import android.content.Intent;
	import android.os.Bundle;
	import android.view.View;
	import android.widget.Button;
	import android.widget.ImageButton;
	import android.widget.TextView;
	import android.widget.Toast;

	import exportkit.xd.Controller.userController;
	import exportkit.xd.DB.SessionManager;
	import exportkit.xd.R;
	import exportkit.xd.View.Profile.IMyProfileView;
	import exportkit.xd.View.Profile.myProfile_activity;
	public class homepage_activity extends Activity implements IMyProfileView{
    	String Name ;
		private TextView name;
		private ImageButton ProfileButton , Homebutton  ;
		private Button SearchButton;
		userController uController ;
		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.homepage);
			ProfileButton = (ImageButton) findViewById(R.id.ellipse_ek23);
			Homebutton = (ImageButton) findViewById(R.id.home_ek11);
			SearchButton = (Button) findViewById(R.id.ellipse_ek22);
			uController= new userController((IMyProfileView) this) ;
			name = (TextView) findViewById(R.id.hello);
			SessionManager s= new SessionManager(this);
			long id= s.getUserFromSession();
			name.setText("Hello " + uController.GetName((int) id));
			ProfileButton.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					Intent nextScreen = new Intent(getApplicationContext(), myProfile_activity.class);
					startActivity(nextScreen);

				}
			});
			SearchButton.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					Intent nextScreen = new Intent(getApplicationContext(), searchUser_activity.class);
					startActivity(nextScreen);

				}
			});
			Homebutton.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
					startActivity(nextScreen);

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
	
	