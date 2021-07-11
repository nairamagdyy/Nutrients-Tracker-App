package exportkit.xd.View;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import exportkit.xd.R;

public class dynamic_view extends Activity implements OnClickListener{


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.addrecipe_one);
            View buttonAdd = findViewById(R.id.ingredient);
            buttonAdd.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.ingredient:
                    //Check if the Layout already exists
                    LinearLayout hiddenLayout = (LinearLayout)findViewById(R.id.hiddenLayout);
                    if(hiddenLayout == null){
                        //Inflate the Hidden Layout Information View
                        LinearLayout myLayout = (LinearLayout)findViewById(R.id.linearLayout2);
                        View hiddenInfo = getLayoutInflater().inflate(R.layout.hidden, myLayout, false);
                        myLayout.addView(hiddenInfo);
                    }
                    break;
                    
            }

        }

}
