package exportkit.xd.View.Search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import exportkit.xd.Controller.userController;
import exportkit.xd.R;
import exportkit.xd.View.IAppViews;
import exportkit.xd.View.homepage_activity;


public class userprofile_Search extends Activity implements IAppViews {
    EditText username1 ;
    TextView name , username2  ;
    userController UController ;
    Button back ;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);
        // find views
        back = (Button) findViewById(R.id.back_ek6) ;
        UController = new userController(this) ;
        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
                startActivity(nextScreen);
            }
        });
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
        Intent nextScreen = new Intent(getApplicationContext(), userprofile_Search.class);
        startActivity(nextScreen);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();
    }
}
