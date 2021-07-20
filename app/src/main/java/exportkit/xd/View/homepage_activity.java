
package exportkit.xd.View;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import exportkit.xd.Controller.userController;
import exportkit.xd.DB.SessionManager;
import exportkit.xd.Model.User;
import exportkit.xd.R;
import exportkit.xd.View.Profile.profile_activity;
import exportkit.xd.View.Recipe.addRecipe_activity;
import exportkit.xd.View.Search.SearchUser_activity;

public class homepage_activity extends Activity implements IAppViews{
	private TextView name;
	private ImageButton addrecipeButton;
	private CircularImageView ProfileButton;
	private Button SearchButton;

	userController userController;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);

		name = (TextView) findViewById(R.id.hello);
		addrecipeButton = (ImageButton) findViewById(R.id.addrecipebutton);
		ProfileButton = findViewById(R.id.ellipse_ek23);
		SearchButton = (Button) findViewById(R.id.ellipse_ek22);

		userController = new userController(this) ;

		SessionManager session= new SessionManager(this);
		long loggedUserID= session.getUserFromSession();
		User user= userController.getUser((int)loggedUserID);

		//set current information for logged user
		if(user.getAvatar() != null){
			ProfileButton.setImageURI(Uri.parse(user.getAvatar()));
		}
		name.setText("Hello " + user.getName());

		ProfileButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext(), profile_activity.class);
				startActivity(nextScreen);
			}
		});
		SearchButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext(), SearchUser_activity.class);
				startActivity(nextScreen);
				Toast.makeText(getApplication(),"Now You are On Search User Mode",Toast.LENGTH_LONG).show();

			}
		});
		addrecipeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext(), addRecipe_activity.class);
				startActivity(nextScreen);
			}
		});
	}

	@Override
	public void onSuccess(String message) {
		Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
		Intent nextScreen = new Intent(getApplicationContext(), profile_activity.class);
		startActivity(nextScreen);
	}
	@Override
	public void onError(String message) {
		Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();

	}

}
	
	