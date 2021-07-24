package exportkit.xd.View.Scanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import exportkit.xd.Controller.CameraController;
import exportkit.xd.R;
import exportkit.xd.View.Homepage_activity;


public class Scanner_activity extends Camera {
    ImageView image;
    Button displayBtn, cancelBtn;
    ImageButton doneBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);

        cameraController= new CameraController(this);

        uploadedImage= findViewById(R.id.uploadImage);
        image= findViewById(R.id.image);
        displayBtn= findViewById(R.id.display);
        cancelBtn= findViewById(R.id.cancel);
        doneBtn= findViewById(R.id.done);

        //take image
        cameraController.imagePickDialog();

        displayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uploadedImage.setVisibility(View.GONE);
                displayBtn.setVisibility(View.GONE);
                image.setImageURI(cameraController.imageUri);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Homepage_activity.class);
                startActivity(nextScreen);
            }
        });
        doneBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //image
            }
        });
    }
}
