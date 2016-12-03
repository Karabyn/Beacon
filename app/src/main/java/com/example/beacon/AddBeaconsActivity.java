package com.example.beacon;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.print.PrintAttributes;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// created 02.12.2016. Petro.

public class AddBeaconsActivity extends AppCompatActivity {

    private ArrayList<Beacon> scanned_beacons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beacons);
        Toast.makeText(this, "onCreate() AddBeaconsActivity", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        scanned_beacons = intent.getParcelableArrayListExtra("Beacon Array");
        TableLayout tableLayout = (TableLayout) findViewById(R.id.scanned_beacons_table);
        createTable(tableLayout, scanned_beacons);
    }

    protected void onResume(){
        super.onResume();
        Toast.makeText(this, "onResume() AddBeaconsActivity", Toast.LENGTH_SHORT).show();
    }

    private  void createTable(TableLayout tableLayout, ArrayList<Beacon> scanned_beacons) {

        ViewGroup.LayoutParams params;

        TextView [][] arrText = new TextView[scanned_beacons.size()][3];
        int column = 0;
        for(int row = 0; row < scanned_beacons.size(); row++)
        {
            column = 0;

            arrText[row][column] = new TextView(this);
            TextView uuid_header = (TextView) findViewById(R.id.uuid_header);
            params = uuid_header.getLayoutParams();
            arrText[row][column].setLayoutParams(params);
            arrText[row][column].setPaddingRelative(4, 0, 0, 0);
            arrText[row][column].setBackgroundResource(R.drawable.cell_background);
            arrText[row][column].setText(scanned_beacons.get(row).getUUID());

            column += 1;

            arrText[row][column] = new TextView(this);
            TextView major_header = (TextView) findViewById(R.id.major_header);
            params = major_header.getLayoutParams();
            arrText[row][column].setLayoutParams(params);
            arrText[row][column].setPaddingRelative(4, 0, 0, 0);
            arrText[row][column].setBackgroundResource(R.drawable.cell_background);
            arrText[row][column].setText(scanned_beacons.get(row).getMajor().toString());

            column += 1;

            arrText[row][column] = new TextView(this);
            TextView minor_header = (TextView) findViewById(R.id.minor_header);
            params = minor_header.getLayoutParams();
            arrText[row][column].setLayoutParams(params);
            arrText[row][column].setPaddingRelative(4, 0, 0, 0);
            arrText[row][column].setBackgroundResource(R.drawable.cell_background);
            arrText[row][column].setText(scanned_beacons.get(row).getMinor().toString());

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


    public void addBeaconsByButtonClick(View v) {
        Toast.makeText(this, "Add beacons button pressed", Toast.LENGTH_SHORT).show();
        //to be implemented
    }

    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "onResume() AddBeaconsActivity", Toast.LENGTH_SHORT).show();
    }

}
