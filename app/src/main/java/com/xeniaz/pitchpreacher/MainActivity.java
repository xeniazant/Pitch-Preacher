package com.xeniaz.pitchpreacher;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;


public class MainActivity extends AppCompatActivity {

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private int buttonCheck = 0;
    float currentLow;
    float currentHigh;
    //TODO: describe what this variable does.
    private boolean shouldSetbasepitch = true;




    //TODO: Write documentation
    public String hzToNote(float f){
        if( (float)79.96 < f && f < (float)84.715){
            return "E2";
        }

        if(  (float)84.715 < f && f < (float)89.75 ){
            return "F2";
        }

        if(  (float)89.75 < f && f < (float)95.085 ){
            return "F#2";
        }
        if(  (float)95.085 < f && f < (float)100.745 ){
            return "G2";
        }
        if(  (float)100.745 < f && f < (float)106.73 ){
            return "G#2";
        }
        if(  (float)106.73 < f && f < (float)113.075 ){
            return "A2";
        }
        if(  (float)113.075 < f && f < (float)119.8 ){
            return "A#2";
        }
        if(  (float)119.8 < f && f < (float)126.92 ){
            return "B2";
        }
        if(  (float)126.92 < f && f < (float)134.47 ){
            return "C3";
        }
        if(  (float)134.47 < f && f < (float)142.465 ){
            return "C#3";
        }
        if(  (float)142.465 < f && f < (float)150.935){
            return "D3";
        }
        if(  (float)150.935 < f && f < (float)159.91 ){
            return "D#3";
        }
        if(  (float)159.91 < f && f < (float)169.415 ){
            return "E3";
        }
        if(  (float)169.415 < f && f < (float)179.5){
            return "F3";
        }
        if(  (float)179.5 < f && f < (float)190.175 ){
            return "F#3";
        }
        if(  (float)190.175 < f && f < (float)201.475 ){
            return "G3";
        }
        if(  (float)201.475 < f && f < (float)213.46){
            return "G#3";
        }
        if(  (float)val < f && f < (float)val ){
            return "A3";
        }
        if(  (float)val < f && f < (float)val ){
            return "A#3";
        }
        if(  (float)val < f && f < (float)val ){
            return "B3";
        }
        if(  (float)val < f && f < (float)val ){
            return "C4";
        }
        if(  (float)val < f && f < (float)val ){
            return "C#4";
        }
        if(  (float)val < f && f < (float)val ){
            return "D4";
        }
        if(  (float)val < f && f < (float)val ){
            return "D#4";
        }
        if(  (float)val < f && f < (float)val ){
            return "E4";
        }
        if(  (float)val < f && f < (float)val ){
            return "F4";
        }
        if(  (float)val < f && f < (float)val ){
            return "F#4";
        }
        if(  (float)val < f && f < (float)val ){
            return "G4";
        }
        if(  (float)val < f && f < (float)val ){
            return "G#4";
        }
        if(  (float)val < f && f < (float)val ){
            return "A4";
        }
        if(  (float)val < f && f < (float)val ){
            return "A#4";
        }
        if(  (float)val < f && f < (float)val ){
            return "B4";
        }
        if(  (float)val < f && f < (float)val ){
            return "C5";
        }
        if(  (float)val < f && f < (float)val ){
            return "C#5";
        }
        if(  (float)val < f && f < (float)val ){
            return "D5";
        }
        if(  (float)val < f && f < (float)val ){
            return "D#5";
        }
        if(  (float)val < f && f < (float)val ){
            return "E5";
        }
        if(  (float)val < f && f < (float)val ){
            return "F5";
        }
        if(  (float)val < f && f < (float)val ){
            return "F#5";
        }
        if(  (float)val < f && f < (float)val ){
            return "G5";
        }
        if(  (float)val < f && f < (float)val ){
            return "G#5";
        }
        if(  (float)val < f && f < (float)val ){
            return "A5";
        }
        if(  (float)val < f && f < (float)val ){
            return "A#5";
        }
        if(  (float)val < f && f < (float)val ){
            return "B5";
        }
        if(  (float)val < f && f < (float)val ){
            return "C6";
        }
        if(  (float)val < f && f < (float)val ){
            return "C#6";
        }
        if(  (float)val < f && f < (float)val ){
            return "D6";
        }
        if(  (float)val < f && f < (float)val ){
            return "D#6";
        }
        if(  (float)val < f && f < (float)val ){
            return "E6";
        }
        if(  (float)val < f && f < (float)val ){
            return "F6";
        }
        if(  (float)val < f && f < (float)val ){
            return "F#6";
        }
        if(  (float)val < f && f < (float)val ){
            return "G6";
        }
        if(  (float)val < f && f < (float)val ){
            return "G#6";
        }
        if(  (float)val < f && f < (float)val ){
            return "A6";
        }
        if(  (float)val < f && f < (float)val ){
            return "A#6";
        }
        if(  (float)val < f && f < (float)val ){
            return "B6";
        }




        return "Nothing yet";
    }


    //TODO: give credit for https://developer.android.com/training/permissions/requesting#java
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:{
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_RECORD_AUDIO_PERMISSION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            SharedPreferences sh = getSharedPreferences( "MySharedPref", MODE_PRIVATE);
            boolean shouldSkip = sh.getBoolean("shouldSkip", true);


            if(shouldSkip){
                skip();
            }













        final TextView hzDisplay =findViewById(R.id.hzdisplayer);
        Button toView = findViewById(R.id.toView);
        final TextView instructionsText = findViewById(R.id.singDown);

        toView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                    //openActivity();

                }
                buttonCheck += 1;

                if(buttonCheck == 1){
                    instructionsText.setText("Now Sing up to your highest note, click the button below when you're finished.");
                }

                if(buttonCheck >= 2){
                    // Storing the highest and lowest notes in a users range (initially)
                    //in shared preferences vey-value pairs.
                    // Storing data into SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                    // Creating an Editor object
                        // to edit(write to the file)
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putFloat("lowestNote", currentLow);
                    myEdit.putFloat("highestNote", currentHigh);
                    myEdit.putBoolean("shouldSkip", true);
                    myEdit.apply();

                    openActivity();
                }



            }
        });



        //TODO: be a good  programmer and write comments here
            AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050,1024,0);
            dispatcher.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, new PitchDetectionHandler() {
                @Override
                public void handlePitch(final PitchDetectionResult pitchDetectionResult,
                                        AudioEvent audioEvent) {
                    final float pitchInHz = pitchDetectionResult.getPitch();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            hzDisplay.setText("Initial activity Pitch in Hertz: " + pitchInHz);


                            //This ensures we only set the current high and low value arbitrarily ONCE
                            if(shouldSetbasepitch && (pitchInHz > 98)){
                                currentLow = pitchDetectionResult.getPitch();
                                currentHigh = pitchDetectionResult.getPitch();

                                shouldSetbasepitch = false;
                            }



                            if((currentLow > pitchDetectionResult.getPitch()) && (pitchDetectionResult.getPitch() > 98)){
                                currentLow = pitchDetectionResult.getPitch();
                            }
                            else if(currentHigh < pitchDetectionResult.getPitch()){
                                currentHigh = pitchDetectionResult.getPitch();
                            }


                        }
                    });

                }
            }));
            new Thread(dispatcher,"Audio Dispatcher").start();
        }






       }


       // A function that opens the selector activity the first
       public void openActivity(){
           if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
               ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 200);


               SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
               float lowPref = sh.getFloat("lowestNote", (float) 0.0);
               float highPref = sh.getFloat("highestNote", (float) 0.0);



               Toast toast = Toast.makeText(this, "initial low:" + lowPref + "initial high: " + highPref, Toast.LENGTH_LONG);
               toast.show();
               Intent intent = new Intent(this, selector.class);
               startActivity(intent);
           }



           //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 200);


       }
        // Function that will skip this activity after first run
        private void skip(){
            Intent intent = new Intent(this, selector.class);
            startActivity(intent);

        }


    }





