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

    Button increaseFats, increaseCarbs, increaseProteins,
            decreaseFats, decreaseCarbs, decreaseProteins, search;
    ImageButton home, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.macro_tracker);
        search = findViewById(R.id.search1);
        home = (ImageButton)findViewById(R.id.home1);
        profile = (ImageButton)findViewById(R.id.profile1);
    }
    /*Display Tips_activity*/

}
