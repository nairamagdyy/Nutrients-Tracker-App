package exportkit.xd.View.Recipe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import exportkit.xd.R;
import exportkit.xd.View.Homepage_activity;
import exportkit.xd.View.Profile.Profile_activity;
import exportkit.xd.View.Search.SearchUser_activity;

public class Tips_activity extends AppCompatActivity {

    Button increase_fats,increase_carbs,increase_proteins, search;
    ImageButton home, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.macro_tracker);
        search = findViewById(R.id.search1);
        home = (ImageButton)findViewById(R.id.home1);
        profile = (ImageButton)findViewById(R.id.profile1);

        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), SearchUser_activity.class);
                startActivity(nextScreen);

            }
        });
        home.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), Homepage_activity.class);
                startActivity(nextScreen);

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), Profile_activity.class);
                startActivity(nextScreen);

            }
        });
    }
    /*Display Tips_activity*/
    private void fats() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Tips_activity");
        dialogBuilder.setMessage("Some informative message for the user to do that.");
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogBuilder.create().show();
    }
}
