package exportkit.xd.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import exportkit.xd.R;
import exportkit.xd.View.Profile.profile_activity;
import exportkit.xd.View.Search.SearchUser_activity;

public class Tips extends AppCompatActivity {

    Button increase_fats,increase_carbs,increase_proteins, search;
    ImageButton home, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.macro_tracker);
        search = findViewById(R.id.search1);
        home = (ImageButton)findViewById(R.id.home1);
        profile = (ImageButton)findViewById(R.id.profile1);
        increase_fats = findViewById(R.id.increasefats);
        increase_proteins = findViewById(R.id.increaseproteins);
        increase_fats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fats();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), SearchUser_activity.class);
                startActivity(nextScreen);

            }
        });
        home.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), homepage_activity.class);
                startActivity(nextScreen);

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), profile_activity.class);
                startActivity(nextScreen);

            }
        });
    }
    /*Display Tips*/
    private void fats() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Tips");
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
