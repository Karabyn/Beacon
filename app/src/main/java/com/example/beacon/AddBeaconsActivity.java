package com.example.beacon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

// created 02.12.2016. Petro.

public class AddBeaconsActivity extends AppCompatActivity {

    public ArrayList<Beacon> scanned_beacons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title);
        setContentView(R.layout.activity_add_beacons);
        Log.v("AddBeaconsActivity", "onCreate() started");
        Intent intent = getIntent();
        scanned_beacons = intent.getParcelableArrayListExtra("Beacon Array");
        TableLayout tableLayout = (TableLayout) findViewById(R.id.scanned_beacons_table);
        createTable(tableLayout, scanned_beacons);
    }

    protected void onResume(){
        super.onResume();
    }

    /*
    * Creates a table containing scanned beacons data
    * Andriy, Petro 02.12.2016 - 03.12.2016
     */
    private  void createTable(TableLayout tableLayout, ArrayList<Beacon> scanned_beacons) {

        ViewGroup.LayoutParams params;

        TextView [][] arrText = new TextView[scanned_beacons.size()][3];
        int column;

        for(int row = 0; row < scanned_beacons.size(); row++)
        {
            column = 0;
            arrText[row][column] = new TextView(this);
            TextView uuid_header = (TextView) findViewById(R.id.uuid_header);
            params = uuid_header.getLayoutParams();
            arrText[row][column].setLayoutParams(params);
            arrText[row][column].setPaddingRelative(4, 0, 0, 0);
            arrText[row][column].setBackgroundResource(R.drawable.cell_background_orange);
            arrText[row][column].setText(scanned_beacons.get(row).getUUID());

            column += 1;

            arrText[row][column] = new TextView(this);
            TextView major_header = (TextView) findViewById(R.id.major_header);
            params = major_header.getLayoutParams();
            arrText[row][column].setLayoutParams(params);
            arrText[row][column].setPaddingRelative(4, 0, 0, 0);
            arrText[row][column].setBackgroundResource(R.drawable.cell_background_orange);
            arrText[row][column].setText(String.valueOf(scanned_beacons.get(row).getMajor()));

            column += 1;

            arrText[row][column] = new TextView(this);
            TextView minor_header = (TextView) findViewById(R.id.minor_header);
            params = minor_header.getLayoutParams();
            arrText[row][column].setLayoutParams(params);
            arrText[row][column].setPaddingRelative(4, 0, 0, 0);
            arrText[row][column].setBackgroundResource(R.drawable.cell_background_orange);
            arrText[row][column].setText(String.valueOf(scanned_beacons.get(row).getMinor()));

        }

        TableRow[] tableRows = new TableRow[scanned_beacons.size()];

        for(int row = 0; row < scanned_beacons.size(); row++)
        {
            column = 0;
            tableRows[row] = new TableRow(this);
            tableRows[row].addView(arrText[row][column]);
            column += 1;
            tableRows[row].addView(arrText[row][column]);
            column += 1;
            tableRows[row].addView(arrText[row][column]);
            tableLayout.addView(tableRows[row]);
        }

    }

    /*
    * Starts MainActivity, sends intent with an ArrayList of scanned beacons
    * Petro 03.12.2016.
     */
    public void addBeaconsByButtonClick(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putParcelableArrayListExtra("scanned_beacons", scanned_beacons);
        Log.v("AddBeaconsActivity", "AddBeacons button clicked. Starting MainActivity.");
        startActivity(intent);
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.v("AddBeaconsActivity", "onDestoy()");
    }
}
