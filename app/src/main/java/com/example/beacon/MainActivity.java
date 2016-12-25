package com.example.beacon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //Delimiter used in file
    public static final String COMMA_DELIMITER = ",";

    //new line
    private static final String NEW_LINE_SEPARATOR = "\n";

    //file header
    private static final String FILE_HEADER = "UUID,MAJOR,MINOR";

    //TODO: add needed fields
    // Done. Petro.
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
            TableLayout tableLayout_s = (TableLayout)findViewById(R.id.scanned_beacons_table_second);
            showBeaconsInLinearLayout(tableLayout_s,scanned_beacons);
        }
    }

    //by Pavlo.
    private void showBeaconsInLinearLayout(TableLayout tableLayout, ArrayList<Beacon> scanned_beacons) {
        ViewGroup.LayoutParams params;

        TextView[][] arrText = new TextView[scanned_beacons.size()-1][6];
        int column;

        int a=0;
        int g=1;

        for (int row = 0; row < scanned_beacons.size()-1; row++) {

            if(row==0)
            {
                Log.v("AL_ST_1IF"," "+row);
                column = 0;
                arrText[row][column] = new TextView(this);
                TextView uuid_header_1 = (TextView) findViewById(R.id.uuid_header_1);
                params = uuid_header_1.getLayoutParams();
                arrText[row][column].setLayoutParams(params);
                arrText[row][column].setPaddingRelative(4, 0, 0, 0);
                arrText[row][column].setBackgroundResource(R.drawable.cell_background);
                arrText[row][column].setText(scanned_beacons.get(a).getUUID());

                column += 1;

                arrText[row][column] = new TextView(this);
                TextView major_header_1 = (TextView) findViewById(R.id.major_header_1);
                params = major_header_1.getLayoutParams();
                arrText[row][column].setLayoutParams(params);
                arrText[row][column].setPaddingRelative(4, 0, 0, 0);
                arrText[row][column].setBackgroundResource(R.drawable.cell_background);
                arrText[row][column].setText(String.valueOf(scanned_beacons.get(a).getMajor()));

                column += 1;

                arrText[row][column] = new TextView(this);
                TextView minor_header_1 = (TextView) findViewById(R.id.minor_header_1);
                params = minor_header_1.getLayoutParams();
                arrText[row][column].setLayoutParams(params);
                arrText[row][column].setPaddingRelative(4, 0, 0, 0);
                arrText[row][column].setBackgroundResource(R.drawable.cell_background);
                arrText[row][column].setText(String.valueOf(scanned_beacons.get(a).getMinor()));


                column += 1;
                arrText[row][column] = new TextView(this);
                TextView uuid_header_2 = (TextView) findViewById(R.id.uuid_header_2);
                params = uuid_header_2.getLayoutParams();
                arrText[row][column].setLayoutParams(params);
                arrText[row][column].setPaddingRelative(4, 0, 0, 0);
                arrText[row][column].setBackgroundResource(R.drawable.cell_background);
                arrText[row][column].setText(scanned_beacons.get(g).getUUID());

                column += 1;

                arrText[row][column] = new TextView(this);
                TextView major_header_2 = (TextView) findViewById(R.id.major_header_2);
                params = major_header_2.getLayoutParams();
                arrText[row][column].setLayoutParams(params);
                arrText[row][column].setPaddingRelative(4, 0, 0, 0);
                arrText[row][column].setBackgroundResource(R.drawable.cell_background);
                arrText[row][column].setText(String.valueOf(scanned_beacons.get(g).getMajor()));

                column += 1;

                arrText[row][column] = new TextView(this);
                TextView minor_header_2 = (TextView) findViewById(R.id.minor_header_2);
                params = minor_header_2.getLayoutParams();
                arrText[row][column].setLayoutParams(params);
                arrText[row][column].setPaddingRelative(4, 0, 0, 0);
                arrText[row][column].setBackgroundResource(R.drawable.cell_background);
                arrText[row][column].setText(String.valueOf(scanned_beacons.get(g).getMinor()));
                Log.v("AL_EN_1IF"," "+row);
            }
            else
            {
                Log.v("EL_ST"," "+row);
                a=g;
                g++;
                column = 0;
                arrText[row][column] = new TextView(this);
                TextView uuid_header_1 = (TextView) findViewById(R.id.uuid_header_1);
                params = uuid_header_1.getLayoutParams();
                arrText[row][column].setLayoutParams(params);
                arrText[row][column].setPaddingRelative(4, 0, 0, 0);
                arrText[row][column].setBackgroundResource(R.drawable.cell_background);
                arrText[row][column].setText(scanned_beacons.get(a).getUUID());

                column += 1;

                arrText[row][column] = new TextView(this);
                TextView major_header_1 = (TextView) findViewById(R.id.major_header_1);
                params = major_header_1.getLayoutParams();
                arrText[row][column].setLayoutParams(params);
                arrText[row][column].setPaddingRelative(4, 0, 0, 0);
                arrText[row][column].setBackgroundResource(R.drawable.cell_background);
                arrText[row][column].setText(String.valueOf(scanned_beacons.get(a).getMajor()));

                column += 1;

                arrText[row][column] = new TextView(this);
                TextView minor_header_1 = (TextView) findViewById(R.id.minor_header_1);
                params = minor_header_1.getLayoutParams();
                arrText[row][column].setLayoutParams(params);
                arrText[row][column].setPaddingRelative(4, 0, 0, 0);
                arrText[row][column].setBackgroundResource(R.drawable.cell_background);
                arrText[row][column].setText(String.valueOf(scanned_beacons.get(a).getMinor()));


                column += 1;
                arrText[row][column] = new TextView(this);
                TextView uuid_header_2 = (TextView) findViewById(R.id.uuid_header_2);
                params = uuid_header_2.getLayoutParams();
                arrText[row][column].setLayoutParams(params);
                arrText[row][column].setPaddingRelative(4, 0, 0, 0);
                arrText[row][column].setBackgroundResource(R.drawable.cell_background);
                arrText[row][column].setText(scanned_beacons.get(g).getUUID());

                column += 1;

                arrText[row][column] = new TextView(this);
                TextView major_header_2 = (TextView) findViewById(R.id.major_header_2);
                params = major_header_2.getLayoutParams();
                arrText[row][column].setLayoutParams(params);
                arrText[row][column].setPaddingRelative(4, 0, 0, 0);
                arrText[row][column].setBackgroundResource(R.drawable.cell_background);
                arrText[row][column].setText(String.valueOf(scanned_beacons.get(g).getMajor()));

                column += 1;

                arrText[row][column] = new TextView(this);
                TextView minor_header_2 = (TextView) findViewById(R.id.minor_header_2);
                params = minor_header_2.getLayoutParams();
                arrText[row][column].setLayoutParams(params);
                arrText[row][column].setPaddingRelative(4, 0, 0, 0);
                arrText[row][column].setBackgroundResource(R.drawable.cell_background);
                arrText[row][column].setText(String.valueOf(scanned_beacons.get(g).getMinor()));
                Log.v("EL_ED"," "+row);
            }

        }

        TableRow[] tableRows = new TableRow[scanned_beacons.size()-1];

        for(int row = 0; row < scanned_beacons.size()-1; row++)
        {
            Log.v("2For", "2For begin"+row);
            column = 0;
            tableRows[row] = new TableRow(this);
            tableRows[row].addView(arrText[row][column]);
            column += 1;
            tableRows[row].addView(arrText[row][column]);
            column += 1;
            tableRows[row].addView(arrText[row][column]);
            column += 1;
            tableRows[row].addView(arrText[row][column]);
            column += 1;
            tableRows[row].addView(arrText[row][column]);
            column += 1;
            tableRows[row].addView(arrText[row][column]);
            Log.v("2For", "2For end"+row);
            tableLayout.addView(tableRows[row]);
        }

        createSpecialMovementsButton();
    }

    /*
    * Creates a button for displaying special movements
    * after beacons are added and displayed in MainActivity.
    * Petro 25.12.2016
     */
    private void createSpecialMovementsButton() {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.SpecialMovementsButtonLayout);

        Button specialMovementsButton = new Button(this);
        specialMovementsButton.setText(R.string.specialMovements);
        specialMovementsButton.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        specialMovementsButton.getBackground().setAlpha(0);
        specialMovementsButton.setPaddingRelative(16, 0, 16, 0);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) specialMovementsButton.getLayoutParams();
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        specialMovementsButton.setLayoutParams(params);
        specialMovementsButton.setId(0);
        specialMovementsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                specialMovements();
            }
        });
        layout.addView(specialMovementsButton);
    }

    /*
    * Detects special movements by comparing beacons data.
    * Creates an AlertDialogue where special movements are displayed
    * Petro 25.12.2016
     */
    private void specialMovements() {
        int major = 0;
        int minor = 0;
        int major_previous = 0;
        int minor_previous = 0;
        int major_next = 0;
        int minor_next = 0;
        ArrayList<String> floors = new ArrayList<>();
        ArrayList<String> direction = new ArrayList<>();

        for (int i = 1; i < scanned_beacons.size() - 1; i++) {
            major = scanned_beacons.get(i).getMajor();
            minor = scanned_beacons.get(i).getMinor();
            major_previous = scanned_beacons.get(i - 1).getMajor();
            minor_previous = scanned_beacons.get(i - 1).getMinor();
            if (i < scanned_beacons.size() - 2) { // prevent out of bound
                major_next = scanned_beacons.get(i + 1).getMajor();
                minor_next = scanned_beacons.get(i + 1).getMinor();
            }
            // detect floor changes
            if (major != major_previous) {
                floors.add(" " + String.valueOf(major_previous) + "." + String.valueOf(minor_previous) +
                        " -> " + String.valueOf(major) + "." + String.valueOf(minor));
            }
            // detect direction changes
            if (minor_previous == minor_next && major_previous == major && major == major_next) {
                direction.add(" " + String.valueOf(major_previous) + "." + String.valueOf(minor_previous)
                        + " -> " +  String.valueOf(major) + "." + String.valueOf(minor) +
                        " -> " + String.valueOf(major_next) + "." + String.valueOf(minor_next));
            }
        }

        String text = "";
        if(!floors.isEmpty()) {
            text = "Floor changes:";
            for(int i = 0; i < floors.size(); i++) {
                text += floors.get(i);
                if(floors.size() - 1 != i) {text += ", ";}
                else {text += "\n";}
            }
        }
        if(!direction.isEmpty()) {
            text += "Direction changes:";
            for(int i = 0; i < direction.size(); i++) {
                text += direction.get(i);
                if(direction.size() - 1 != i) {text += ", ";}
            }
        }
        if(direction.isEmpty() && floors.isEmpty()){text = "No special movements.";}

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Special movements");
        builder.setMessage(text);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
    // Done. Petro.
    public void stopServiceByButtonClick(View v) {
        stopService(new Intent(this, ServiceImpl.class));
        Toast.makeText(this, "Stopped scanning beacons", Toast.LENGTH_SHORT).show();
    }
}