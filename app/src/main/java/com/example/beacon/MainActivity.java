package com.example.beacon;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {


    //Delimiter used in file
    public static final String COMMA_DELIMITER = ",";

    //new line
    private static final String NEW_LINE_SEPARATOR = "\n";

    //file header
    private static final String FILE_HEADER = "UUID,MAJOR,MINOR";

    //TODO: add needed fields
    ArrayList<Beacon> scanned_beacons;
    Button start_scan;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("MainActivity", "onCreate() started");

        //TODO: get intent
        // Done. Petro.
        Intent intent = getIntent();
    }

    @Override
    protected void onResume() {
        super.onResume(); // added on 26.11.16 by Petro
        Log.v("MainActivity", "onResume() started");
        //TODO: get intent and add beacons to List
        // Done. Petro 03.12.2016
        Intent intent = getIntent();
        if (intent.hasExtra("scanned_beacons")) {
            scanned_beacons = intent.getParcelableArrayListExtra("scanned_beacons");
            showBeaconsInLinearLayout();
        }
    }

    private void showBeaconsInLinearLayout() {
        //TODO: implement this
        Toast.makeText(this, "showBeaconsInLinearLayout()", Toast.LENGTH_SHORT).show();
    }

    //Do not change this!
    protected void writeBeaconSimulationFile(){

        //Create new beacon objects
        Beacon beacon1 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",4,1);
        Beacon beacon2 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",4,2);
        Beacon beacon3 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",4,3);
        Beacon beacon4 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",4,4);
        Beacon beacon5 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",4,5);
        Beacon beacon6 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",4,8);
        Beacon beacon7 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",4,9);
        Beacon beacon8 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",4,10);
        Beacon beacon9 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",3,10);
        Beacon beacon10 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",3,9);
        Beacon beacon11 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",3,8);
        Beacon beacon12 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",3,5);
        Beacon beacon13 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",3,4);
        Beacon beacon14 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",3,3);
        Beacon beacon15 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",3,2);
        Beacon beacon16 = new Beacon("EBBD7150-D911-11E4-8830-0800200C9A66",3,1);

        //Create a new list of beacons objects
        ArrayList<Beacon> beacons = new ArrayList<Beacon>();
        beacons.add(beacon1);
        beacons.add(beacon2);
        beacons.add(beacon3);
        beacons.add(beacon4);
        beacons.add(beacon5);
        beacons.add(beacon6);
        beacons.add(beacon7);
        beacons.add(beacon8);
        beacons.add(beacon9);
        beacons.add(beacon10);
        beacons.add(beacon11);
        beacons.add(beacon12);
        beacons.add(beacon13);
        beacons.add(beacon14);
        beacons.add(beacon15);
        beacons.add(beacon16);
        beacons.add(beacon15);
        beacons.add(beacon14);
        beacons.add(beacon3);
        beacons.add(beacon2);
        beacons.add(beacon1);


        try{
            FileOutputStream testFile = openFileOutput("Beacons.txt", Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(testFile);
            outputStreamWriter.append(FILE_HEADER);
            outputStreamWriter.append(NEW_LINE_SEPARATOR);

            for (Beacon beacon : beacons) {
                outputStreamWriter.append(String.valueOf(beacon.getUUID()));
                outputStreamWriter.append(COMMA_DELIMITER);
                outputStreamWriter.append(String.valueOf(beacon.getMajor()));
                outputStreamWriter.append(COMMA_DELIMITER);
                outputStreamWriter.append(String.valueOf(beacon.getMinor()));
                outputStreamWriter.append(NEW_LINE_SEPARATOR);
            }

            outputStreamWriter.close();
        }
        catch (IOException ex){
            Log.d("Message", ex.getMessage());
        }
    }

    //TODO: button starts the service
    // Done. Petro.
    public void startServiceByButtonClick(View v) {
        //TODO: Get user input
        //Done. Petro.
        start_scan = (Button) findViewById(R.id.start_scan);
        input = (EditText) findViewById(R.id.input);

        //Case with no input
        if(input.getText().toString().equals("")) {
            Toast.makeText(this, "Please, enter scan interval.", Toast.LENGTH_SHORT).show();
        }
        else {
            long seconds = Long.parseLong(input.getText().toString());
            Log.v("MainActivity", "startServiceByButtonClick() Scan interval: "
                    + String.valueOf(seconds) + " seconds");

            //Do not change this!
            File dir = getFilesDir();
            File file = new File(dir, "Beacons.txt");
            boolean deleted = file.delete();

            //this method writes the file containing simulated beacon data
            writeBeaconSimulationFile();

            //TODO: Service is started via intent
            // Done. Petro.
            Intent intent = new Intent(this, ServiceImpl.class);
            intent.putExtra("seconds", seconds);
            startService(intent);
            Log.v("Main Activity", "startServiceByButtonClick() starting service");
        }
    }

    //TODO: stop service
    public void stopServiceByButtonClick(View v) {
        //implement this
        stopService(new Intent(this, ServiceImpl.class));
        Toast.makeText(this, "Stop service main activity", Toast.LENGTH_SHORT).show();
    }
}