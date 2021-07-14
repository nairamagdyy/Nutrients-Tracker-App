package exportkit.xd.View.Recipe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import exportkit.xd.R;

public class recipeDetails_activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details);

        int id= getIntent().getExtras().getInt("id");
        Toast.makeText(this, String.valueOf(id)+"ahln", Toast.LENGTH_SHORT).show();


    }
}
