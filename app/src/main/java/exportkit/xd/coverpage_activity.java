

    package exportkit.xd;

    import android.app.Activity;
    import android.os.Bundle;


    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;
    import android.widget.ImageView;
    import android.content.Intent;

    public class coverpage_activity extends Activity {


        private View _bg__cover_ek2;
        private TextView start_tracking;
        private TextView let_s_join_our_community_to_track_nutrients_in_your_food_;
        private ImageView rectangle_188;
        private ImageView ellipse_3;
        private ImageView ellipse_7;
        private ImageView ellipse_6;
        private ImageView image_1;
        private ImageView image_3;
        private View ellipse_2_3;
        private View _ellipse_2_4;
        private View ellipse_2_4_ek1;
        private Button signup;
        private ImageView ellipse_3_ek1;
        private ImageView mask_group;
        private ImageView image_4;
        private Button login;
        private View home_indicator;
        private View border;
        private ImageView cap;
        private View capacity;
        private ImageView wifi;
        private ImageView cellular_connection;
        private TextView time;

        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.coverpage);


            _bg__cover_ek2 = (View) findViewById(R.id._bg__cover_ek2);
            start_tracking = (TextView) findViewById(R.id.start_tracking);
            let_s_join_our_community_to_track_nutrients_in_your_food_ = (TextView) findViewById(R.id.let_s_join_our_community_to_track_nutrients_in_your_food_);
            rectangle_188 = (ImageView) findViewById(R.id.rectangle_188);
            ellipse_3 = (ImageView) findViewById(R.id.ellipse_3);
            ellipse_7 = (ImageView) findViewById(R.id.ellipse_7);
            ellipse_6 = (ImageView) findViewById(R.id.ellipse_6);
            image_1 = (ImageView) findViewById(R.id.image_1);
            image_3 = (ImageView) findViewById(R.id.image_3);
            signup = (Button) findViewById(R.id.signup);
            ellipse_3_ek1 = (ImageView) findViewById(R.id.ellipse_3_ek1);
            mask_group = (ImageView) findViewById(R.id.mask_group);
            image_4 = (ImageView) findViewById(R.id.image_4);
            login = (Button) findViewById(R.id.login);
            time = (TextView) findViewById(R.id.time);


            signup.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    Intent nextScreen = new Intent(getApplicationContext(), sign_up_activity.class);
                    startActivity(nextScreen);


                }
            });
            login.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    Intent nextScreen = new Intent(getApplicationContext(), log_in_activity.class);
                    startActivity(nextScreen);


                }
            });

            //custom code goes here

        }
    }
	
	