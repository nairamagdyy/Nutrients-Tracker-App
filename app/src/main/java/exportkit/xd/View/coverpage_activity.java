

    package exportkit.xd.View;

    import android.app.Activity;
    import android.os.Bundle;


    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;
    import android.widget.ImageView;
    import android.content.Intent;

    import exportkit.xd.R;
    import exportkit.xd.View.Register.log_in_activity;
    import exportkit.xd.View.Register.sign_up_activity;

    public class coverpage_activity extends Activity {

        private Button signup;

        private Button login;


        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.coverpage);
            signup = (Button) findViewById(R.id.signup);

            login = (Button) findViewById(R.id.login);

            signup.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    Intent nextScreen = new Intent(getApplicationContext(), sign_up_activity.class);
                    startActivity(nextScreen);


                }
            });
            login.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    //Intent nextScreen = new Intent(getApplicationContext(), log_in_activity.class);
                    Intent nextScreen = new Intent(getApplicationContext(), log_in_activity.class);
                    startActivity(nextScreen);
                }
            });

            //custom code goes here

        }
    }
	
	