package exportkit.xd.View;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import exportkit.xd.Controller.cameraController;

import static exportkit.xd.DB.CameraConstants.CAMERA_REQUEST_CODE;
import static exportkit.xd.DB.CameraConstants.IMAGE_PICK_CAMERA_CODE;
import static exportkit.xd.DB.CameraConstants.IMAGE_PICK_GALLERY_CODE;
import static exportkit.xd.DB.CameraConstants.STORAGE_REQUEST_CODE;

public abstract class camera_activity extends AppCompatActivity {
    public cameraController CamController;
    public CircularImageView uploadedImage;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //result of permission allowed/denied
        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0){
                    //if allowed return true else false
                    boolean cameraAccepted= grantResults[0]== PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted= grantResults[1]== PackageManager.PERMISSION_GRANTED;

                    if(cameraAccepted && storageAccepted){
                        //both permission allowed
                        CamController.pickFromCamera();
                    }
                    else{
                        Toast.makeText(this, "Camera & Storage permissions are required...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0){
                    //if allowed return true else false
                    boolean storageAccepted= grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        //storage permission allowed
                        CamController.pickFromGallery();
                    }
                    else{
                        Toast.makeText(this, "Storage permissions is required...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        //image picked from camera or gallery will be received here
        if(resultCode == RESULT_OK){
            //image is picked
            if(requestCode == IMAGE_PICK_GALLERY_CODE){
                //picked from gallery
                //crop image
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if(requestCode == IMAGE_PICK_CAMERA_CODE){
                //picked from camera
                //crop image
                CropImage.activity(CamController.imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                //cropped image received
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if(resultCode == RESULT_OK){
                    CamController.imageUri= result.getUri();
                    //set image
                    uploadedImage.setImageURI(CamController.imageUri);
                }
                else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    //error
                    Exception error= result.getError();
                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
