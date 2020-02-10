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

import androidx.annotation.NonNull;
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




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted) finish();

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final TextView hzDisplay =findViewById(R.id.hzdisplayer);
        Button toView = findViewById(R.id.toView);

        toView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                    //openActivity();

                }
                buttonCheck += 1;
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
                    myEdit.apply();

                    openActivity();
                }
            }
        });




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

       public void openActivity(){
           if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
               ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 200);


               SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
               float lowPref = sh.getFloat("lowestNote", (float) 0.0);
               float highPref = sh.getFloat("highestNote", (float) 0.0);


               Toast toast = Toast.makeText(this, "initial low:" + lowPref + "initial high: " + highPref, Toast.LENGTH_LONG);
               toast.show();
               Intent intent = new Intent(this, pitch.class);
               startActivity(intent);
           }
           //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 200);


       }


    }





