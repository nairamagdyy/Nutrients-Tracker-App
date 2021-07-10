
	package exportkit.xd.View;
	import android.app.Activity;
	import android.content.Intent;
	import android.os.Bundle;
	import android.view.View;
	import android.widget.ImageButton;
	import android.widget.TextView;
	import exportkit.xd.DB.AppDBController;
	import exportkit.xd.DB.SessionManager;
	import exportkit.xd.R;
	import exportkit.xd.View.Profile.myProfile_activity;
	public class homepage_activity extends Activity {
    	String Name ;
		private TextView name;
		AppDBController db ;
		private ImageButton ProfileButton;
		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.homepage);
			ProfileButton = (ImageButton) findViewById(R.id.ellipse_ek23);
			db= new AppDBController(this);
			name = (TextView) findViewById(R.id.hello);
			SessionManager s= new SessionManager(this);
			long id= s.getUserFromSession();
			Name =  db.GetName((int) id)  ;
			name.setText("Hello " + Name);
			ProfileButton.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					Intent nextScreen = new Intent(getApplicationContext(), myProfile_activity.class);
					startActivity(nextScreen);

				}
			});
			//custom code goes here
		}
	}
	
	