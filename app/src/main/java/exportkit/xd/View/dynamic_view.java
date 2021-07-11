package exportkit.xd.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import exportkit.xd.R;

public class dynamic_view extends AppCompatActivity implements OnClickListener {

    LinearLayout newlayer, newstep;
    Button buttonAdd, step;
    ScrollView sv = new ScrollView(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecipe_one);
        buttonAdd = findViewById(R.id.ingredient);
        newlayer = findViewById(R.id.linearLayout1);
        step = findViewById(R.id.step);
        newstep = findViewById(R.id.addstep);
        buttonAdd.setOnClickListener(this);
        step.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ingredient:
                addingredient();
            case R.id.step:
                addstep();
        }
    }

    public void addingredient() {
        View view = getLayoutInflater().inflate(R.layout.hidden, null);
        newlayer.addView(view);
    }

    public void addstep() {
        View view = getLayoutInflater().inflate(R.layout.hiddenstep, null);
        newstep.addView(view);
    }

}
