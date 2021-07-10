

	/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc
	 *	@file 		untitled
	 *	@date 		1621375453134
	 *	@title 		Page 1
	 *	@author
	 *	@keywords
	 *	@generator 	Export Kit v1.3.figma
	 *
	 */


	package exportkit.xd.View;

	import android.app.Activity;
	import android.os.Bundle;


	import android.view.View;
	import android.widget.TextView;
	import android.widget.ImageView;

	import exportkit.xd.DB.AppDBController;
	import exportkit.xd.DB.SessionManager;
	import exportkit.xd.R;

    public class homepage_activity extends Activity {
    	String Name ;
		private TextView name;
		AppDBController db ;

		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.homepage);
			db= new AppDBController(this);
			name = (TextView) findViewById(R.id.hello);
			SessionManager s= new SessionManager(this);
			long id= s.getUserFromSession();
			Name =  db.GetName((int) id)  ;
			name.setText("Hello " + Name);


			//custom code goes here

		}
	}
	
	