package com.om.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnFlash;
    CameraManager cameraManager;
    String Cameraid;
    Boolean isFlashOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isFlashAvailable = getApplicationContext().getPackageManager().hasSystemFeature(getPackageManager().FEATURE_CAMERA_FLASH);

        btnFlash = findViewById(R.id.btn_torch);


        if(!isFlashAvailable)
        {
            btnFlash.setEnabled(false);
            btnFlash.setText("FLASH IS NOT AVAILABLE");
        }

        try {
            cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            Cameraid = cameraManager.getCameraIdList()[0];
        }catch (Exception ignored){}


        btnFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isFlashOn)
                {
                    isFlashOn = false;
                    //turn of the flash
                    btnFlash.setText("TURN ON FLASH");
                }
                else
                {
                    //turn on the flash
                    isFlashOn=true;
                    btnFlash.setText("TURN OFF FLASH");
                }
                switchFlash(isFlashOn);
            }
        });
    }
    public void switchFlash(Boolean status)
    {
       try {
           cameraManager.setTorchMode(Cameraid,status);
       }catch (Exception e){}
    }


}